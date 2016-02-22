/*
 * 文  件   名: Entity.java
 * 版         本 : vigor-infra-persist © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月22日
 */
package org.anttribe.vigor.infra.persist.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 领域实体接口
 * 
 * @author zhaoyong
 * @version 2016年2月22日
 */
public abstract class Entity implements Serializable
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1383687133862369106L;
    
    /**
     * id编号
     */
    protected Long id;
    
    /**
     * 排序序号
     */
    protected Integer sortNo;
    
    /**
     * 备注
     */
    protected String remarks;
    
    /**
     * 创建时间
     */
    protected Date createTime;
    
    /**
     * 数据更新时间
     */
    protected Date updateTime;
    
    @Override
    public String toString()
    {
        return (new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        {
            @Override
            protected boolean accept(Field f)
            {
                return super.accept(f) && !f.getName().equals("password");
            }
        }).toString();
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public Integer getSortNo()
    {
        return sortNo;
    }
    
    public void setSortNo(Integer sortNo)
    {
        this.sortNo = sortNo;
    }
    
    public String getRemarks()
    {
        return remarks;
    }
    
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
}