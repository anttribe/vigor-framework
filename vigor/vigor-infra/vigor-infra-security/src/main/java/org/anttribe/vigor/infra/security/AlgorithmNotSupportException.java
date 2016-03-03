/*
 * 文  件   名: AlgorithmNotSupportException.java
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
public class AlgorithmNotSupportException extends Exception
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4265863468231767580L;
    
    /**
     * <构造函数>
     */
    public AlgorithmNotSupportException(String message)
    {
        super(message);
    }
    
    /**
     * <构造函数>
     */
    public AlgorithmNotSupportException(Throwable e)
    {
        super(e);
    }
    
    /**
     * <构造函数>
     */
    public AlgorithmNotSupportException(String message, Throwable e)
    {
        super(message, e);
    }
    
}