/*
 * 文  件   名: AbstractServiceImpl.java
 * 版         本 : vigor-infra-common © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月22日
 */
package org.anttribe.vigor.infra.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.anttribe.vigor.infra.common.errorno.SystemErrorNo;
import org.anttribe.vigor.infra.common.exception.ServiceException;
import org.anttribe.vigor.infra.persist.dao.IDao;
import org.anttribe.vigor.infra.persist.entity.Entity;
import org.anttribe.vigor.infra.persist.entity.Pagination;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaoyong
 * @version 2016年2月22日
 */
public abstract class AbstractServiceImpl<Dao extends IDao<T>, T extends Entity> implements IService<T>
{
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected Dao dao;
    
    @Override
    public List<T> listEntities(Map<String, Object> criteria)
    {
        logger.debug("listing entities refer to criteria, param: [{}]", criteria);
        
        if (null == criteria)
        {
            criteria = new HashMap<String, Object>();
        }
        return dao.find(criteria);
    }
    
    @Override
    public Pagination listEntities(Map<String, Object> criteria, Pagination pagination)
    {
        logger.debug("listing entities refer to criteria and pagination, param: criteria[{}], pagination[{}]",
            criteria,
            pagination);
            
        if (null == criteria)
        {
            criteria = new HashMap<String, Object>();
        }
        List<T> tempEntities = dao.find(criteria);
        int totalCount = dao.count(criteria);
        if (null == pagination)
        {
            pagination = new Pagination();
        }
        pagination.setTotalRecords(totalCount);
        pagination.setDatas(tempEntities);
        
        return pagination;
    }
    
    @Override
    public T findEntity(Map<String, Object> criteria)
    {
        logger.debug("find Entity refer to criteria, param: criteria[{}]", criteria);
        
        if (null == criteria)
        {
            // 参数错误
            return null;
        }
        
        List<T> tempEntities = dao.find(criteria);
        if (!CollectionUtils.isEmpty(tempEntities))
        {
            return tempEntities.get(0);
        }
        return null;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void persistentEntity(T entity)
    {
        logger.debug("persistenting Entity to DB, param: entity[{}]", entity);
        // TODO: 参数校验
        if (null == entity)
        {
            logger.warn("persistenting Entity to DB, param entity is null.");
            throw new ServiceException(SystemErrorNo.PARAMETER_IS_NULL);
        }
        
        if (null == entity.getId())
        {
            entity.setCreateTime(new Date());
            dao.insert(entity);
            logger.debug("entity not exist in DB, then save new entity to DB, entity: {}", entity);
            return;
        }
        
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("id", entity.getId());
        List<T> tempEntities = dao.find(criteria);
        if (CollectionUtils.isEmpty(tempEntities))
        {
            entity.setCreateTime(new Date());
            dao.insert(entity);
            logger.debug("entity not exist in DB, then save new entity to DB, entity: {}", entity);
            return;
        }
        entity.setUpdateTime(new Date());
        dao.update(entity);
        logger.debug("entity exist in DB, then update entity info, entity: {}", entity);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeEntity(Map<String, Object> criteria)
    {
        logger.debug("deleting entity from DB, param: criteria[{}]", criteria);
        
        if (null != criteria)
        {
            dao.delete(criteria);
        }
    }
}