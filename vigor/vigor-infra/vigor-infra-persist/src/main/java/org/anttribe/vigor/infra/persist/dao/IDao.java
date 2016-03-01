/*
 * 文  件   名: IDao.java
 * 版         本 : vigor-infra-persist © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月22日
 */
package org.anttribe.vigor.infra.persist.dao;

import java.util.List;
import java.util.Map;

import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * @author zhaoyong
 * @version 2016年2月22日
 */
public interface IDao<T extends Entity>
{
    
    /**
     * 插入单条数据
     * 
     * @param entity
     * @return int
     */
    int insert(T entity);
    
    /**
     * 批量插入数据
     * 
     * @param entities
     * @return
     */
    int batchInsert(List<T> entities);
    
    /**
     * 更新单条数据
     * 
     * @param entity
     * @return
     */
    int update(T entity);
    
    /**
     * 批量更新数据
     * 
     * @param entities
     * @return
     */
    int batchUpdate(List<T> entities);
    
    /**
     * 删除数据
     * 
     * @param criteria
     * @return
     */
    int delete(Map<String, Object> criteria);
    
    /**
     * 查找所有数据
     * 
     * @return
     */
    List<T> findAll();
    
    /**
     * 根据条件查找数据
     * 
     * @param criteria
     * @return
     */
    List<T> find(Map<String, Object> criteria);
    
    /**
     * 根据id查询数据
     * 
     * @param criteria
     * @return T
     */
    T findById(Map<String, Object> criteria);
    
    /**
     * 计算根据条件查询的数据条数
     * 
     * @param criteria
     * @return
     */
    int count(Map<String, Object> criteria);
    
}