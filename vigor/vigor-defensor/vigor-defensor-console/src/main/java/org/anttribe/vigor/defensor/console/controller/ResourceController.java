/*
 * 文  件   名: ResourceController.java
 * 版         本 : vigor-defensor-console © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.console.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.anttribe.vigor.defensor.domain.Resource;
import org.anttribe.vigor.defensor.service.IResourceService;
import org.anttribe.vigor.defensor.type.ResourceTarget;
import org.anttribe.vigor.defensor.type.ResourceType;
import org.anttribe.vigor.infra.common.constants.Constants;
import org.anttribe.vigor.infra.common.constants.Keys;
import org.anttribe.vigor.infra.common.entity.Result;
import org.anttribe.vigor.infra.common.errorno.SystemErrorNo;
import org.anttribe.vigor.infra.common.exception.ServiceException;
import org.anttribe.vigor.infra.common.exception.UnifyException;
import org.anttribe.vigor.infra.common.type.YesOrNo;
import org.anttribe.vigor.infra.common.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @version 2016年2月23日
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends AbstractController
{
    
    @javax.annotation.Resource
    private IResourceService resourceService;
    
    @RequestMapping(value = {"", "/", "/index"})
    public ModelAndView index(HttpServletRequest request, ModelAndView mv, Resource resource)
    {
        mv.setViewName(Views.LIST_VIEW);
        
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("parent", resource.getParent());
        List<Resource> resources = resourceService.listEntities(criteria);
        mv.addObject(Keys.KEY_PAGE_DATA, resources);
        return mv;
    }
    
    @RequestMapping("/list/exec")
    @ResponseBody
    public Result<?> doList(HttpServletRequest request, Resource resource)
    {
        Result<List<Resource>> result = new Result<List<Resource>>();
        try
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("name", resource.getName());
            criteria.put("parent", resource.getParent());
            List<Resource> resources = resourceService.listEntities(criteria);
            result.setData(resources);
            
            result.setResultCode(Constants.Common.DEFAULT_RESULT_CODE);
        }
        catch (ServiceException e)
        {
            result.setResultCode(e.getErrorNo());
        }
        return result;
    }
    
    @RequestMapping("/tool/selector")
    public String select(HttpServletRequest request, Resource resource)
    {
        return Views.SELECTOR_VIEW;
    }
    
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request, ModelAndView mv, Resource resource)
    {
        mv.setViewName(Views.ADD_VIEW);
        mv.addObject("resourceTypes", ResourceType.values());
        mv.addObject("resourceTargets", ResourceTarget.values());
        mv.addObject("yesOrNos", YesOrNo.values());
        
        if (null != resource.getParent() && null != resource.getParent().getId())
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("id", resource.getParent().getId());
            Resource parent = resourceService.findEntity(criteria);
            resource.setParent(parent);
        }
        mv.addObject(Keys.KEY_PARAM, resource);
        
        return mv;
    }
    
    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request, ModelAndView mv, Resource resource)
    {
        if (null == resource || null == resource.getId())
        {
            throw new UnifyException(SystemErrorNo.DATA_NOT_EXIST_ERROR);
        }
        
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("id", resource.getId());
        resource = resourceService.findEntity(criteria);
        if (null == resource || null == resource.getId())
        {
            throw new UnifyException(SystemErrorNo.DATA_NOT_EXIST_ERROR);
        }
        
        mv.setViewName(Views.EDIT_VIEW);
        mv.addObject(Keys.KEY_PARAM, resource);
        mv.addObject("resourceTypes", ResourceType.values());
        mv.addObject("resourceTargets", ResourceTarget.values());
        mv.addObject("yesOrNos", YesOrNo.values());
        return mv;
    }
    
    @ResponseBody
    @RequestMapping("/edit/exec")
    public Result<?> doEdit(HttpServletRequest request, Resource resource)
    {
        Result<?> result = new Result<String>();
        try
        {
            // TODO： 数据校验
            
            resourceService.persistentEntity(resource);
            result.setResultCode(Constants.Common.DEFAULT_RESULT_CODE);
        }
        catch (ServiceException e)
        {
            result.setResultCode(e.getErrorNo());
        }
        return result;
    }
    
    @ResponseBody
    @RequestMapping("/remove/exec")
    public Result<?> doRemove(HttpServletRequest request, Resource resource)
    {
        Result<?> result = new Result<String>();
        try
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("id", resource.getId());
            resourceService.removeEntity(criteria);
            result.setResultCode(Constants.Common.DEFAULT_RESULT_CODE);
        }
        catch (ServiceException e)
        {
            result.setResultCode(e.getErrorNo());
        }
        return result;
    }
    
    class Views
    {
        /**
         * 列表视图
         */
        public static final String LIST_VIEW = "/resource/list";
        
        /**
         * 添加视图
         */
        public static final String ADD_VIEW = "/resource/edit";
        
        /**
         * 编辑视图
         */
        public static final String EDIT_VIEW = "/resource/edit";
        
        /**
         * 选择器视图
         */
        public static final String SELECTOR_VIEW = "/resource/tool.selector";
    }
    
}