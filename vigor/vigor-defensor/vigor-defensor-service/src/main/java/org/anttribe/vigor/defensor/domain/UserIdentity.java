/*
 * 文  件   名: UserIdentity.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.domain;

import org.anttribe.vigor.infra.persist.entity.Entity;

/**
 * 用户身份
 * 
 * @author zhaoyong
 * @version 2016年2月23日
 */
public class UserIdentity extends Entity
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4171062068627177528L;
    
    /**
     * 用户
     */
    private User user;
    
    /**
     * 身份
     */
    private Identity identity;
    
    /**
     * <默认构造器>
     */
    public UserIdentity()
    {
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
    public Identity getIdentity()
    {
        return identity;
    }
    
    public void setIdentity(Identity identity)
    {
        this.identity = identity;
    }
    
}