/*
 * 文  件   名: DefaultPasswordService.java
 * 版         本 : vigor-infra-security © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年3月3日
 */
package org.anttribe.vigor.infra.security;

import org.anttribe.vigor.infra.security.utils.Digests;
import org.anttribe.vigor.infra.security.utils.Encodes;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaoyong
 * @version 2016年3月3日
 */
public class DefaultPasswordService implements PasswordService
{
    
    private static final Logger logger = LoggerFactory.getLogger(DefaultPasswordService.class);
    
    private static final String DEFAULT_DIGEST_ALGORITHMNAME = DigestAlgorithm.SHA1.name();
    
    private static final int DEFAULT_DIGEST_ITERATIONS = 1024;
    
    private static final int DEFAULT_PUBLICSALT_LENGTH = 8;
    
    /**
     * 算法
     */
    private String algorithmName;
    
    /**
     * hash处理迭代次数
     */
    private int iterations;
    
    /**
     * 是否生成salt
     */
    private boolean generatePublicSalt;
    
    /**
     * salt的长度
     */
    private int publicSaltLength = DEFAULT_PUBLICSALT_LENGTH;
    
    public DefaultPasswordService()
    {
        this.algorithmName = DEFAULT_DIGEST_ALGORITHMNAME;
        this.iterations = DEFAULT_DIGEST_ITERATIONS;
        this.generatePublicSalt = false;
    }
    
    @Override
    public String encryptPassword(String plaintextPassword)
        throws IllegalArgumentException
    {
        byte[] salt = null;
        if (generatePublicSalt)
        {
            salt = Digests.generateSalt(publicSaltLength);
        }
        String plainPassword = Encodes.unescapeHtml(plaintextPassword);
        byte[] hashPassword = Digests.digest(plainPassword.getBytes(), algorithmName, salt, this.iterations);
        return (null == salt ? "" : Encodes.encodeHex(salt)) + Encodes.encodeHex(hashPassword);
    }
    
    @Override
    public boolean passwordsMatch(String submittedPlaintext, String encrypted)
    {
        byte[] salt = null;
        if (generatePublicSalt)
        {
            // 从原密码中获取salt
            salt = Encodes.decodeHex(encrypted.substring(0, publicSaltLength * 2));
        }
        String plainPassword = Encodes.unescapeHtml(submittedPlaintext);
        byte[] hashPassword = Digests.digest(plainPassword.getBytes(), algorithmName, salt, this.iterations);
        return encrypted.equals((null == salt ? "" : Encodes.encodeHex(salt)) + Encodes.encodeHex(hashPassword));
    }
    
    public String getAlgorithmName()
    {
        return algorithmName;
    }
    
    public void setAlgorithmName(String algorithmName)
        throws AlgorithmNotSupportException
    {
        // 验证算法
        if (StringUtils.isEmpty(algorithmName) || null == DigestAlgorithm.valueOf(algorithmName))
        {
            logger.warn("Not support this algorithm {} yet", algorithmName);
            throw new AlgorithmNotSupportException("Not support this algorithm yet");
        }
        this.algorithmName = algorithmName;
    }
    
    public int getIterations()
    {
        return iterations;
    }
    
    public void setIterations(int iterations)
    {
        if (iterations <= 0)
        {
            iterations = 1;
        }
        this.iterations = iterations;
    }
    
    public boolean isGeneratePublicSalt()
    {
        return generatePublicSalt;
    }
    
    public void setGeneratePublicSalt(boolean generatePublicSalt)
    {
        this.generatePublicSalt = generatePublicSalt;
    }
    
    public int getPublicSaltLength()
    {
        return publicSaltLength;
    }
    
    public void setPublicSaltLength(int publicSaltLength)
    {
        if (this.generatePublicSalt)
        {
            this.publicSaltLength = publicSaltLength;
        }
    }
    
}