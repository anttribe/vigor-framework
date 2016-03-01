/*
 * 文  件   名: UserRole.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月26日
 */
package org.anttribe.vigor.defensor.domain;

import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * @author zhaoyong
 * @version 2016年2月26日
 */
public class UserRole extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2003506690819155002L;
    
    /**
     * 用户
     */
    private User user;
    
    /**
     * 角色
     */
    private Role role;
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
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