/*
 * 文  件   名: DictController.java
 * 版         本 : vigor-platform-console © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年3月27日
 */
package org.anttribe.vigor.platform.console.modules.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.anttribe.vigor.infra.common.constants.Constants;
import org.anttribe.vigor.infra.common.constants.Keys;
import org.anttribe.vigor.infra.common.entity.Result;
import org.anttribe.vigor.infra.common.errorno.SystemErrorNo;
import org.anttribe.vigor.infra.common.exception.ServiceException;
import org.anttribe.vigor.infra.common.exception.UnifyException;
import org.anttribe.vigor.infra.common.web.controller.AbstractController;
import org.anttribe.vigor.infra.dict.domain.Dict;
import org.anttribe.vigor.infra.dict.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @version 2016年3月27日
 */
@Controller
@RequestMapping("/dict")
public class DictController extends AbstractController
{
    @Autowired
    private IDictService dictService;
    
    @RequestMapping(value = {"", "/", "/index"})
    public ModelAndView index(HttpServletRequest request, ModelAndView mv, Dict dict)
    {
        mv.setViewName(Views.LIST_VIEW);
        
        Map<String, Object> criteria = new HashMap<String, Object>();
        List<Dict> dicts = dictService.listEntities(criteria);
        mv.addObject(Keys.KEY_PAGE_DATA, dicts);
        return mv;
    }
    
    @RequestMapping("/list/exec")
    @ResponseBody
    public Result<?> doList(HttpServletRequest request, Dict dict)
    {
        Result<List<Dict>> result = new Result<List<Dict>>();
        try
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("key", dict.getKey());
            List<Dict> dicts = dictService.listEntities(criteria);
            result.setData(dicts);
            
            result.setResultCode(Constants.Common.DEFAULT_RESULT_CODE);
        }
        catch (ServiceException e)
        {
            result.setResultCode(e.getErrorNo());
        }
        return result;
    }
    
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request, ModelAndView mv, Dict dict)
    {
        mv.setViewName(Views.ADD_VIEW);
        mv.addObject(Keys.KEY_PARAM, dict);
        
        return mv;
    }
    
    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request, ModelAndView mv, Dict dict)
    {
        if (null == dict || null == dict.getId())
        {
            throw new UnifyException(SystemErrorNo.DATA_NOT_EXIST_ERROR);
        }
        
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("id", dict.getId());
        dict = dictService.findEntity(criteria);
        if (null == dict || null == dict.getId())
        {
            throw new UnifyException(SystemErrorNo.DATA_NOT_EXIST_ERROR);
        }
        
        mv.setViewName(Views.EDIT_VIEW);
        mv.addObject(Keys.KEY_PARAM, dict);
        return mv;
    }
    
    @ResponseBody
    @RequestMapping("/edit/exec")
    public Result<?> doEdit(HttpServletRequest request, Dict dict)
    {
        Result<?> result = new Result<String>();
        try
        {
            // TODO： 数据校验
            
            dictService.persistentEntity(dict);
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
    public Result<?> doRemove(HttpServletRequest request, Dict dict)
    {
        Result<?> result = new Result<String>();
        try
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("id", dict.getId());
            dictService.removeEntity(criteria);
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
        public static final String LIST_VIEW = "/modules/system/dict/list";
        
        /**
         * 添加视图
         */
        public static final String ADD_VIEW = "/modules/system/dict/edit";
        
        /**
         * 编辑视图
         */
        public static final String EDIT_VIEW = "/modules/system/dict/edit";
    }
}