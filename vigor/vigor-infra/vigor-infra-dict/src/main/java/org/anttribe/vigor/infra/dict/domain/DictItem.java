/*
 * 文  件   名: DictItem.java
 * 版         本 : vigor-infra-dict © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年4月9日
 */
package org.anttribe.vigor.infra.dict.domain;

import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * @author zhaoyong
 * @version 2016年4月9日
 */
public class DictItem extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1201497196145123578L;
    
    /**
     * 字典项名称
     */
    private String name;
    
    /**
     * 字典项值
     */
    private String value;
    
    /**
     * 所属字典
     */
    private Dict dict;
    
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
    
    public Dict getDict()
    {
        return dict;
    }
    
    public void setDict(Dict dict)
    {
        this.dict = dict;
    }
    
}