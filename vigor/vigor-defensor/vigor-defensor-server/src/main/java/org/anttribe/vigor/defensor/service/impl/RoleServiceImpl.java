/*
 * 文  件   名: RoleServiceImpl.java
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

import org.anttribe.vigor.defensor.dao.IRoleDao;
import org.anttribe.vigor.defensor.dao.IRoleResourceDao;
import org.anttribe.vigor.defensor.domain.Role;
import org.anttribe.vigor.defensor.domain.RoleResource;
import org.anttribe.vigor.defensor.service.IRoleService;
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
public class RoleServiceImpl extends AbstractServiceImpl<IRoleDao, Role> implements IRoleService
{
    
    @Autowired
    private IRoleResourceDao roleResourceDao;
    
    @Override
    public Role findEntity(Map<String, Object> criteria)
    {
        logger.debug("find role refer to criteria, param: criteria[{}]", criteria);
        
        if (null == criteria || null == criteria.get("id"))
        {
            // 参数错误
            return null;
        }
        return dao.findById(criteria);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void persistentEntity(Role role)
    {
        super.persistentEntity(role);
        
        // 持久化处理角色权限
        this.persistentRoleResource(role);
    }
    
    /**
     * 持久化处理角色权限
     * 
     * @param role
     */
    private void persistentRoleResource(Role role)
    {
        if (null != role && null != role.getId())
        {
            // 删除该角色下所有的权限
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("role", role);
            roleResourceDao.delete(criteria);
            
            List<RoleResource> roleResources = role.getResources();
            if (!CollectionUtils.isEmpty(roleResources))
            {
                List<RoleResource> tempRoleResources = new ArrayList<RoleResource>();
                for (RoleResource roleResource : roleResources)
                {
                    if (roleResource.getResource() == null)
                    {
                        continue;
                    }
                    roleResource.setRole(role);
                    tempRoleResources.add(roleResource);
                }
                roleResourceDao.batchInsert(tempRoleResources);
            }
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeEntity(Map<String, Object> criteria)
    {
        logger.debug("deleting role from DB, param: criteria[{}]", criteria);
        
        if (!MapUtils.isEmpty(criteria))
        {
            // 级联删除角色权限
            List<Role> roles = this.listEntities(criteria);
            if (!CollectionUtils.isEmpty(roles))
            {
                Map<String, Object> deleteCriteria = new HashMap<String, Object>();
                deleteCriteria.put("roles", roles);
                roleResourceDao.delete(deleteCriteria);
                
                dao.delete(criteria);
            }
        }
    }
}