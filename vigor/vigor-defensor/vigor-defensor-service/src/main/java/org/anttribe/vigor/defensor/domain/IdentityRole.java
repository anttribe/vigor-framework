/*
 * 文  件   名: IdentityRole.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.domain;

import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * 身份角色
 * 
 * @author zhaoyong
 * @version 2016年2月23日
 */
public class IdentityRole extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8550642302995896208L;
    
    /**
     * 身份
     */
    private Identity identity;
    
    /**
     * 角色
     */
    private Role role;
    
    /**
     * <默认构造器>
     */
    public IdentityRole()
    {
    }
    
    public Identity getIdentity()
    {
        return identity;
    }
    
    public void setIdentity(Identity identity)
    {
        this.identity = identity;
    }
    
    public Role getRole()
    {
        return role;
    }
    
    public void setRole(Role role)
    {
        this.role = role;
    }
    
}