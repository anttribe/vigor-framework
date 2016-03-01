/*
 * 文  件   名: UserServiceImpl.java
 * 版         本 : vigor-defensor-server © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.anttribe.vigor.defensor.dao.IUserDao;
import org.anttribe.vigor.defensor.dao.IUserRoleDao;
import org.anttribe.vigor.defensor.domain.User;
import org.anttribe.vigor.defensor.domain.UserRole;
import org.anttribe.vigor.defensor.service.IUserService;
import org.anttribe.vigor.infra.common.service.AbstractServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author zhaoyong
 * @version 2016年2月23日
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<IUserDao, User> implements IUserService
{
    @Autowired
    private IUserRoleDao userRoleDao;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void persistentEntity(User user)
    {
        super.persistentEntity(user);
        
        // 持久化处理用户角色
        this.persistentUserRoles(user);
    }
    
    /**
     * 持久化处理用户角色
     * 
     * @param role
     */
    private void persistentUserRoles(User user)
    {
        if (null != user && null != user.getId())
        {
            // 删除该角色下所有的权限
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("user", user);
            userRoleDao.delete(criteria);
            
            List<UserRole> userRoles = user.getRoles();
            if (!CollectionUtils.isEmpty(userRoles))
            {
                List<UserRole> tempUserRoles = new ArrayList<UserRole>();
                for (UserRole userRole : userRoles)
                {
                    if (userRole.getRole() == null)
                    {
                        continue;
                    }
                    userRole.setUser(user);
                    tempUserRoles.add(userRole);
                }
                userRoleDao.batchInsert(tempUserRoles);
            }
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeEntity(Map<String, Object> criteria)
    {
        logger.debug("deleting user from DB, param: criteria[{}]", criteria);
        
        if (!MapUtils.isEmpty(criteria))
        {
            // 级联删除用户角色
            List<User> users = this.listEntities(criteria);
            if (!CollectionUtils.isEmpty(users))
            {
                Map<String, Object> deleteCriteria = new HashMap<String, Object>();
                deleteCriteria.put("users", users);
                userRoleDao.delete(deleteCriteria);
                
                dao.delete(criteria);
            }
        }
    }
}