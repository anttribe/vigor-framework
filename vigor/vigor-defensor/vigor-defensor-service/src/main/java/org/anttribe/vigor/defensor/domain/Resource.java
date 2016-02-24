/*
 * 文  件   名: Resource.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.domain;

import org.anttribe.vigor.defensor.type.ResourceTarget;
import org.anttribe.vigor.defensor.type.ResourceType;
import org.anttribe.vigor.infra.persist.entity.TreeEntity;

/**
 * 资源
 * 
 * @author zhaoyong
 * @version 2016年2月23日
 */
public class Resource extends TreeEntity<Resource>
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5417796754794540630L;
    
    /**
     * 资源名称
     */
    private String name;
    
    /**
     * 资源类型, 包括菜单、页面、操作按钮
     */
    private ResourceType resourceType;
    
    /**
     * 资源url
     */
    private String resourceUrl;
    
    /**
     * 目标：_blank、_self、_top、_parent、frame
     */
    private ResourceTarget target;
    
    /**
     * 权限点
     */
    private String privilege;
    
    /**
     * 图标
     */
    private String icon;
    
    /**
     * 是否显示
     */
    private String isShow;
    
    /**
     * <默认构造器>
     */
    public Resource()
    {
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public ResourceType getResourceType()
    {
        return resourceType;
    }
    
    public void setResourceType(ResourceType resourceType)
    {
        this.resourceType = resourceType;
    }
    
    public String getResourceUrl()
    {
        return resourceUrl;
    }
    
    public void setResourceUrl(String resourceUrl)
    {
        this.resourceUrl = resourceUrl;
    }
    
    public ResourceTarget getTarget()
    {
        return target;
    }
    
    public void setTarget(ResourceTarget target)
    {
        this.target = target;
    }
    
    public String getPrivilege()
    {
        return privilege;
    }
    
    public void setPrivilege(String privilege)
    {
        this.privilege = privilege;
    }
    
    public String getIcon()
    {
        return icon;
    }
    
    public void setIcon(String icon)
    {
        this.icon = icon;
    }
    
    public String getIsShow()
    {
        return isShow;
    }
    
    public void setIsShow(String isShow)
    {
        this.isShow = isShow;
    }
}