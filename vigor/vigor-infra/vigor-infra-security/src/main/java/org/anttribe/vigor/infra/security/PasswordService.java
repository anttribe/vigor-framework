/*
 * 文  件   名: PasswordService.java
 * 版         本 : vigor-infra-security © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年3月3日
 */
package org.anttribe.vigor.infra.security;

/**
 * @author zhaoyong
 * @version 2016年3月3日
 */
public interface PasswordService
{
    
    String encryptPassword(String plaintextPassword)
        throws IllegalArgumentException;
        
    boolean passwordsMatch(String submittedPlaintext, String encrypted);
    
}