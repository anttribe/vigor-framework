/*
 * 文  件   名: UsernamePasswordToken.java
 * 版         本 : vigor-defensor-auth © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月29日
 */
package org.anttribe.vigor.defensor.auth;

/**
 * @author zhaoyong
 * @version 2016年2月29日
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1772566755022977297L;
    
    /**
     * 角色标记(身份)
     */
    private String identify;
    
    public UsernamePasswordToken()
    {
        super();
    }
    
    public UsernamePasswordToken(final String username, final String password)
    {
        super(username, password != null ? password.toCharArray() : null, false, null);
    }
    
    public UsernamePasswordToken(final String username, final String password, final String host)
    {
        super(username, password != null ? password.toCharArray() : null, false, host);
    }
    
    public UsernamePasswordToken(final String username, final String password, final boolean rememberMe)
    {
        super(username, password != null ? password.toCharArray() : null, rememberMe, null);
    }
    
    public UsernamePasswordToken(final String username, final String password, final boolean rememberMe,
        final String host)
    {
        super(username, password != null ? password.toCharArray() : null, rememberMe, host);
    }
    
    public UsernamePasswordToken(final String username, final String password, final boolean rememberMe,
        final String host, String identify)
    {
        super(username, password != null ? password.toCharArray() : null, rememberMe, host);
        this.identify = identify;
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