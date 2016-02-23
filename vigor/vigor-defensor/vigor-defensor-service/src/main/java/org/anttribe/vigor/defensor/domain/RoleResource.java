/*
 * 文  件   名: RoleResource.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.domain;

import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * 角色资源
 * 
 * @author zhaoyong
 * @version 2016年2月23日
 */
public class RoleResource extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6097945635713578267L;
    
    /**
     * 角色
     */
    private Role role;
    
    /**
     * 资源
     */
    private Resource resource;
    
    /**
     * <默认构造器>
     */
    public RoleResource()
    {
    }
    
    public Role getRole()
    {
        return role;
    }
    
    public void setRole(Role role)
    {
        this.role = role;
    }
    
    public Resource getResource()
    {
        return resource;
    }
    
    public void setResource(Resource resource)
    {
        this.resource = resource;
    }
    
}