/*
 * 文  件   名: Dict.java
 * 版         本 : vigor-infra-dict © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月26日
 */
package org.anttribe.vigor.infra.dict.domain;

import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * @author zhaoyong
 * @version 2016年2月26日
 */
public class Dict extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3852587008777677397L;
    
    /**
     * 字典键
     */
    private String key;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 字典值
     */
    private String value;
    
    public String getKey()
    {
        return key;
    }
    
    public void setKey(String key)
    {
        this.key = key;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getValue()
    {
        return value;
    }
    
    public void setValue(String value)
    {
        this.value = value;
    }
    
}