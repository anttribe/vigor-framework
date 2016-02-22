/*
 * 文  件   名: CustomStringEditor.java
 * 版         本 : vigor-infra-common © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月22日
 */
package org.anttribe.vigor.infra.common.web.controller;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author zhaoyong
 * @version 2016年2月22日
 */
public class CustomStringEditor extends PropertyEditorSupport
{
    @Override
    public void setAsText(String text)
    {
        setValue(text == null ? null : StringEscapeUtils.escapeHtml(StringUtils.trim(text)));
    }
    
    @Override
    public String getAsText()
    {
        Object value = getValue();
        return value != null ? value.toString() : "";
    }
}