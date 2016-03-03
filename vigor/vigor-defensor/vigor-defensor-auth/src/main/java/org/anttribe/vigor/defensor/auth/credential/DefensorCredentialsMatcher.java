/*
 * 文  件   名: DefensorCredentialsMatcher.java
 * 版         本 : vigor-defensor-auth © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年3月2日
 */
package org.anttribe.vigor.defensor.auth.credential;

import org.anttribe.vigor.defensor.auth.UsernamePasswordToken;
import org.anttribe.vigor.infra.security.PasswordService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author zhaoyong
 * @version 2016年3月2日
 */
public class DefensorCredentialsMatcher extends SimpleCredentialsMatcher
{
    
    private PasswordService passwordService;
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
    {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        Object accountCredentials = getCredentials(info);
        return passwordService.passwordsMatch(new String(usernamePasswordToken.getPassword()),
            (String)accountCredentials);
    }
    
    public void setPasswordService(PasswordService passwordService)
    {
        this.passwordService = passwordService;
    }
    
}