/*
 * 文  件   名: Result.java
 * 版         本 : vigor-infra-common © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月24日
 */
package org.anttribe.vigor.infra.common.entity;

/**
 * @author zhaoyong
 * @version 2016年2月24日
 */
public class Result<T>
{
    /**
     * 结果码
     */
    private String resultCode;
    
    /**
     * 消息
     */
    private String message;
    
    /**
     * 数据
     */
    private T data;
    
    public String getResultCode()
    {
        return resultCode;
    }
    
    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public T getData()
    {
        return data;
    }
    
    public void setData(T data)
    {
        this.data = data;
    }
}