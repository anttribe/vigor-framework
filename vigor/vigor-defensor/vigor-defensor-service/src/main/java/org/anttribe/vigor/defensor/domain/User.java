/*
 * 文  件   名: User.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.domain;

import java.util.List;

import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * 用户
 * 
 * @author zhaoyong
 * @version 2016年2月23日
 */
public class User extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5682169987157258966L;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 用户角色
     */
    private List<UserRole> roles;
    
    /**
     * 角色标记
     */
    private String identify;
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public List<UserRole> getRoles()
    {
        return roles;
    }
    
    public void setRoles(List<UserRole> roles)
    {
        this.roles = roles;
    }
    
    public String getIdentify()
    {
        return identify;
    }
    
    public void setIdentify(String identify)
    {
        this.identify = identify;
    }
    
}