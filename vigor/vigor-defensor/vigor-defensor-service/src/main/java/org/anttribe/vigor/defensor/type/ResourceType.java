/*
 * 文  件   名: ResourceType.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.type;

import org.anttribe.vigor.defensor.domain.Resource;
import org.apache.commons.lang3.StringUtils;

/**
 * 资源类型
 * 
 * @author zhaoyong
 * @version 2016年2月23日
 */
public enum ResourceType
{
    
    /** 菜单 */
    MENU
    {
        @Override
        public boolean isMenuResource(Resource resource)
        {
            return true;
        }
        
        @Override
        public String assemblePermission(Resource resource)
        {
            String resourceUrl = resource.getResourceUrl();
            return this.name() + (StringUtils.isEmpty(resourceUrl) ? ":" + resource.getId()
                : StringUtils.join(resourceUrl.split("/"), ":"));
        }
    },
    /** 页面 */
    PAGE
    {
        @Override
        public boolean isMenuResource(Resource resource)
        {
            return true;
        }
        
        @Override
        public String assemblePermission(Resource resource)
        {
            return this.name() + resource.getId();
        }
    },
    /** 操作 */
    OPERATOR
    {
        @Override
        public boolean isMenuResource(Resource resource)
        {
            return false;
        }
        
        @Override
        public String assemblePermission(Resource resource)
        {
            return resource.getPrivilege();
        }
    };
    
    /**
     * 判断是否可作为菜单
     * 
     * @param resource
     * @return boolean
     */
    public abstract boolean isMenuResource(Resource resource);
    
    /**
     * 组装权限字符串
     * 
     * @param resource
     * @return String
     */
    public abstract String assemblePermission(Resource resource);
    
}