/*
 * 文  件   名: DefensorAuthorizingRealm.java
 * 版         本 : vigor-defensor-auth © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月29日
 */
package org.anttribe.vigor.defensor.auth.realm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.anttribe.vigor.defensor.auth.UsernamePasswordToken;
import org.anttribe.vigor.defensor.auth.constants.Keys;
import org.anttribe.vigor.defensor.auth.credential.DefensorCredentialsMatcher;
import org.anttribe.vigor.defensor.domain.Resource;
import org.anttribe.vigor.defensor.domain.Role;
import org.anttribe.vigor.defensor.domain.User;
import org.anttribe.vigor.defensor.service.IResourceService;
import org.anttribe.vigor.defensor.service.IRoleService;
import org.anttribe.vigor.defensor.service.IUserService;
import org.anttribe.vigor.defensor.type.ResourceType;
import org.anttribe.vigor.infra.security.DefaultPasswordService;
import org.anttribe.vigor.infra.security.PasswordService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhaoyong
 * @version 2016年2月29日
 */
public class DefensorAuthorizingRealm extends AuthorizingRealm
{
    
    private static final Logger logger = LoggerFactory.getLogger(DefensorAuthorizingRealm.class);
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IRoleService roleService;
    
    @Autowired
    private IResourceService resourceService;
    
    private PasswordService passwordService = new DefaultPasswordService();
    
    @PostConstruct
    public void initCredentialsMatcher()
    {
        DefensorCredentialsMatcher matcher = new DefensorCredentialsMatcher();
        matcher.setPasswordService(passwordService);
        setCredentialsMatcher(matcher);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User)session.getAttribute(Keys.KEY_USER_SESSION);
        if (null != user && null != user.getId())
        {
            this.clearCachedAuthorizationInfo(principals);
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            // 加载权限
            List<Role> roles = (List<Role>)session.getAttribute(Keys.KEY_USER_ROLES);
            List<Resource> resources = (List<Resource>)session.getAttribute(Keys.KEY_USER_RESOURCES);
            if (CollectionUtils.isEmpty(roles) || CollectionUtils.isEmpty(resources))
            {
                this.loadUserResources(user, user.getIdentity(), session);
            }
            
            // 用户角色
            roles = (List<Role>)session.getAttribute(Keys.KEY_USER_ROLES);
            List<String> userRoles = this.processUserRoles(roles);
            authorizationInfo.addRoles(userRoles);
            // 用户资源权限
            resources = (List<Resource>)session.getAttribute(Keys.KEY_USER_RESOURCES);
            List<String> permissions = this.processUserPermision(resources);
            authorizationInfo.addStringPermissions(permissions);
            
            return authorizationInfo;
        }
        return null;
    }
    
    /**
     * 处理用户角色
     * 
     * @param roles
     * @return List<String>
     */
    private List<String> processUserRoles(List<Role> roles)
    {
        List<String> roleCodes = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(roles))
        {
            for (Role role : roles)
            {
                if (StringUtils.isEmpty(role.getCode()))
                {
                    continue;
                }
                roleCodes.add(role.getCode());
            }
        }
        return roleCodes;
    }
    
    /**
     * 处理用户权限
     * 
     * @param resources
     */
    private List<String> processUserPermision(List<Resource> resources)
    {
        List<String> permissions = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(resources))
        {
            for (Resource resource : resources)
            {
                ResourceType resourceType = resource.getResourceType();
                if (null == resourceType)
                {
                    continue;
                }
                String permission = resourceType.assemblePermission(resource);
                if (StringUtils.isEmpty(permission))
                {
                    continue;
                }
                permissions.addAll(this.processUserPermision(resource.getChildren()));
            }
        }
        return permissions;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
        throws AuthenticationException
    {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        if (!StringUtils.isEmpty(username))
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("username", username);
            User user = userService.findEntity(criteria);
            if (null == user)
            {
                // 未知帐号异常
                throw new UnknownAccountException();
            }
            
            // 验证用户密码
            boolean passwordMatch =
                passwordService.passwordsMatch(new String(usernamePasswordToken.getPassword()), user.getPassword());
            if (!passwordMatch)
            {
                logger.warn("Failed to login with the password not match.");
                throw new AuthenticationException("Failed to login with the password not match.");
            }
            // 设置用户当前身份
            user.setIdentity(usernamePasswordToken.getIdentity());
            
            // 获取当前会话
            Session session = SecurityUtils.getSubject().getSession();
            // 加载用户角色、资源权限
            this.loadUserResources(user, usernamePasswordToken.getIdentity(), session);
            List<Role> roles = (List<Role>)session.getAttribute(Keys.KEY_USER_ROLES);
            if (CollectionUtils.isEmpty(roles))
            {
                // 该身份下没有角色, 登录失败
                logger.warn("Failed to login with identity, there is no role with this identity: {}.",
                    usernamePasswordToken.getIdentity());
                throw new AuthenticationException("Failed to login with identity, there is no role with this identity: "
                    + usernamePasswordToken.getIdentity() + ".");
            }
            
            // 将用户信息放置session中
            session.setAttribute(Keys.KEY_USER_SESSION, user);
            // 构造当前用户菜单
            List<Resource> resources = (List<Resource>)session.getAttribute(Keys.KEY_USER_RESOURCES);
            if (!CollectionUtils.isEmpty(resources))
            {
                List<Resource> menuResources = this.processMenuResource(resources);
                session.setAttribute(Keys.KEY_MENUS, menuResources);
            }
            
            SimpleAuthenticationInfo authentication =
                new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), null, getName());
            clearCachedAuthenticationInfo(authentication.getPrincipals());
            return authentication;
        }
        return null;
    }
    
    /**
     * 加载用户角色、资源权限
     * 
     * @param user
     * @param identity
     * @param session
     */
    private void loadUserResources(User user, String identity, Session session)
    {
        // 加载用户角色
        List<Role> userRoles = roleService.listUserRoles(user, identity);
        session.setAttribute(Keys.KEY_USER_ROLES, userRoles);
        if (!CollectionUtils.isEmpty(userRoles))
        {
            // 加载对应的资源权限
            List<Resource> resources = resourceService.listResources(userRoles);
            session.setAttribute(Keys.KEY_USER_RESOURCES, resources);
        }
    }
    
    /**
     * 处理菜单资源
     * 
     * @param resources List<Resource>
     * @return List<Resource>
     */
    private List<Resource> processMenuResource(List<Resource> resources)
    {
        List<Resource> menuResources = null;
        if (!CollectionUtils.isEmpty(resources))
        {
            menuResources = new ArrayList<Resource>();
            for (Resource resource : resources)
            {
                ResourceType resourceType = resource.getResourceType();
                if (resourceType.isMenuResource(resource))
                {
                    resource.setChildren(this.processMenuResource(resource.getChildren()));
                    menuResources.add(resource);
                }
            }
        }
        return menuResources;
    }
    
    public void setPasswordService(PasswordService passwordService)
    {
        this.passwordService = passwordService;
    }
    
}