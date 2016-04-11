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
import org.anttribe.vigor.defensor.domain.Resource;
import org.anttribe.vigor.defensor.domain.Role;
import org.anttribe.vigor.defensor.service.IResourceService;
import org.anttribe.vigor.infra.common.service.AbstractServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyong
 * @version 2016年2月23日
 */
@Service
public class ResourceServiceImpl extends AbstractServiceImpl<IResourceDao, Resource> implements IResourceService
{
    
    @Override
    public List<Resource> listResources(List<Role> roles)
    {
        logger.debug("listing resources from DB, param: roles[{}]", roles);
        
        if (!CollectionUtils.isEmpty(roles))
        {
            // 根据角色查询对应的资源
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("roles", roles);
            return dao.find(criteria);
        }
        return null;
    }
    
}