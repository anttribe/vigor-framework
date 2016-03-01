/*
 * 文  件   名: ResourceService.java
 * 版         本 : vigor-defensor-server © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.anttribe.vigor.defensor.dao.IResourceDao;
import org.anttribe.vigor.defensor.dao.IRoleDao;
import org.anttribe.vigor.defensor.domain.Resource;
import org.anttribe.vigor.defensor.domain.Role;
import org.anttribe.vigor.defensor.domain.User;
import org.anttribe.vigor.defensor.service.IResourceService;
import org.anttribe.vigor.infra.common.service.AbstractServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author zhaoyong
 * @version 2016年2月23日
 */
@Service
public class ResourceServiceImpl extends AbstractServiceImpl<IResourceDao, Resource> implements IResourceService
{
    
    @Autowired
    private IRoleDao roleDao;
    
    @Override
    public List<Resource> listUserResources(User user, String identify)
    {
        logger.debug("listing user's resources from DB, param: user[{}], identify[{}]", user, identify);
        
        if (null != user && null != user.getId())
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("user", user);
            criteria.put("identify", identify);
            List<Role> roles = roleDao.find(criteria);
            if (!CollectionUtils.isEmpty(roles))
            {
                // 根据角色查询对应的资源
                criteria = new HashMap<String, Object>();
                criteria.put("roles", roles);
                return dao.find(criteria);
            }
        }
        return null;
    }
    
}