/*
 * 文  件   名: FormAuthenticationFilter.java
 * 版         本 : vigor-defensor-auth © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年3月1日
 */
package org.anttribe.vigor.defensor.auth.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.anttribe.vigor.defensor.auth.UsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zhaoyong
 * @version 2016年3月1日
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter
{
    
    @Override
    protected AuthenticationToken createToken(String username, String password, ServletRequest request,
        ServletResponse response)
    {
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        String identity = request.getParameter("identity");
        AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe, host, identity);
        return token;
    }
    
}