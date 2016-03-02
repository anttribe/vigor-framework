/*
 * 文  件   名: DefensorCredentialsMatcher.java
 * 版         本 : vigor-defensor-auth © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年3月2日
 */
package org.anttribe.vigor.defensor.auth.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author zhaoyong
 * @version 2016年3月2日
 */
public class DefensorCredentialsMatcher extends SimpleCredentialsMatcher
{
    
    /**
     * hash算法
     */
    private String hashAlgorithm;
    
    /**
     * hash处理迭代次数
     */
    private int hashIterations;
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
    {
        Object tokenCredentials = hashProvidedCredentials(token, info);
        Object accountCredentials = getCredentials(info);
        return equals(tokenCredentials, accountCredentials);
    }
    
    private Object hashProvidedCredentials(AuthenticationToken token, AuthenticationInfo info)
    {
        Object salt = null;
        if (info instanceof SaltedAuthenticationInfo)
        {
            salt = ((SaltedAuthenticationInfo)info).getCredentialsSalt();
        }
        return null; // hashProvidedCredentials(token.getCredentials(), salt, getHashIterations());
    }
    
    public String getHashAlgorithm()
    {
        return hashAlgorithm;
    }
    
    public void setHashAlgorithm(String hashAlgorithm)
    {
        this.hashAlgorithm = hashAlgorithm;
    }
    
    public int getHashIterations()
    {
        return hashIterations;
    }
    
    public void setHashIterations(int hashIterations)
    {
        this.hashIterations = hashIterations;
    }
}