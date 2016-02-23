/*
 * 文  件   名: Identity.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.domain;

import java.util.List;

import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * 身份
 * 
 * @author zhaoyong
 * @version 2016年2月23日
 */
public class Identity extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6705520317367840224L;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 唯一编码
     */
    private String code;
    
    /**
     * 身份角色
     */
    private List<IdentityRole> identityRoles;
    
    /**
     * <默认构造器>
     */
    public Identity()
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
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public List<IdentityRole> getIdentityRoles()
    {
        return identityRoles;
    }
    
    public void setIdentityRoles(List<IdentityRole> identityRoles)
    {
        this.identityRoles = identityRoles;
    }
}