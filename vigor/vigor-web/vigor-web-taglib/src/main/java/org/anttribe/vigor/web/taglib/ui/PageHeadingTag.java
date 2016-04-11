/*
 * 文  件   名: PageHeadingTag.java
 * 版         本 : vigor-web-taglib © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年4月10日
 */
package org.anttribe.vigor.web.taglib.ui;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author zhaoyong
 * @version 2016年4月10日
 */
public class PageHeadingTag extends TagSupport
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1548957819757698981L;
    
    @Override
    public int doEndTag()
        throws JspException
    {
        // 获取系统所有resource
        return super.doEndTag();
    }
}