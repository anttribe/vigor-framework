/*
 * 文  件   名: IService.java
 * 版         本 : vigor-infra-common © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月22日
 */
package org.anttribe.vigor.infra.common.service;

import java.util.List;
import java.util.Map;

import org.anttribe.vigor.infra.persist.entity.Pagination;

/**
 * @author zhaoyong
 * @version 2016年2月22日
 */
public interface IService<T>
{
    
    /**
     * 列表实体数据
     * 
     * @param criteria
     * @return List<T>
     */
    List<T> listEntities(Map<String, Object> criteria);
    
    /**
     * 分页列表实体数据
     * 
     * @param criteria
     * @param pagination
     * @return Pagination
     */
    Pagination listEntities(Map<String, Object> criteria, Pagination pagination);
    
    /**
     * 查找单条数据
     * 
     * @param criteria
     * @return
     */
    T findEntity(Map<String, Object> criteria);
    
    /**
     * 持久化数据
     * 
     * @param entity
     */
    void persistentEntity(T entity);
    
    /**
     * 删除数据
     * 
     * @param criteria
     */
    void removeEntity(Map<String, Object> criteria);
    
}