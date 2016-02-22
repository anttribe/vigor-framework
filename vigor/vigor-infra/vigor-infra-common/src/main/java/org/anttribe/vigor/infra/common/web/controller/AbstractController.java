/*
 * 文  件   名: AbstractController.java
 * 版         本 : vigor-infra-common © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月22日
 */
package org.anttribe.vigor.infra.common.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author zhaoyong
 * @version 2016年2月22日
 */
public class AbstractController
{
    
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new CustomStringEditor());
    }
    
}