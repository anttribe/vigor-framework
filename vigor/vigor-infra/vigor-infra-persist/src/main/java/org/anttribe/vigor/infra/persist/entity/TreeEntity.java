/*
 * 文  件   名: TreeEntity.java
 * 版         本 : vigor-infra-persist © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月22日
 */
package org.anttribe.vigor.infra.persist.entity;

import java.util.List;

/**
 * 树形结构实体
 * 
 * @author zhaoyong
 * @version 2016年2月22日
 */
public abstract class TreeEntity<T> extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1444648311548769221L;
    
    /**
     * 父节点实体
     */
    protected T parent;
    
    /**
     * 子节点
     */
    private List<T> children;
    
    public T getParent()
    {
        return parent;
    }
    
    public void setParent(T parent)
    {
        this.parent = parent;
    }
    
    public List<T> getChildren()
    {
        return children;
    }
    
    public void setChildren(List<T> children)
    {
        this.children = children;
    }
    
}