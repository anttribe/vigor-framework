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
            List<Resource> resources = (List<Resource>)session.getAttribute(Keys.KEY_USER_RESOURCES);
            if (null == resources)
            {
                resources = resourceService.listUserResources(user, user.getIdentify());
            }
            List<String> permissions = this.processUserPermision(resources);
            authorizationInfo.addStringPermissions(permissions);
            
            return authorizationInfo;
        }
        return null;
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
            
            // 将用户信息放置session中
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(Keys.KEY_USER_SESSION, user);
            
            // 加载用户资源
            List<Resource> resources = resourceService.listUserResources(user, usernamePasswordToken.getIdentify());
            session.setAttribute(Keys.KEY_USER_RESOURCES, resources);
            if (!CollectionUtils.isEmpty(resources))
            {
                // 构造菜单
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
                if (resourceType == ResourceType.MENU || resourceType == ResourceType.PAGE)
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