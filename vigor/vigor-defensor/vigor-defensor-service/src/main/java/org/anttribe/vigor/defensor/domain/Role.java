/*
 * 文  件   名: Role.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.domain;

import java.util.List;

import org.anttribe.vigor.infra.dict.domain.Dict;
import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * 角色
 * 
 * @author zhaoyong
 * @version 2016年2月23日
 */
public class Role extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4189092222726319058L;
    
    /**
     * 角色名
     */
    private String name;
    
    /**
     * 创建人
     */
    private User creator;
    
    /**
     * 角色标记
     */
    private Dict identify;
    
    /**
     * 资源
     */
    private List<RoleResource> resources;
    
    /**
     * <默认构造器>
     */
    public Role()
    {
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public User getCreator()
    {
        return creator;
    }
    
    public void setCreator(User creator)
    {
        this.creator = creator;
    }
    
    public List<RoleResource> getResources()
    {
        return resources;
    }
    
    public void setResources(List<RoleResource> resources)
    {
        this.resources = resources;
    }
    
    public Dict getIdentify()
    {
        return identify;
    }
    
    public void setIdentify(Dict identify)
    {
        this.identify = identify;
    }
    
}