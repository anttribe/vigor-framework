/*
 * 文  件   名: UserController.java
 * 版         本 : vigor-defensor-console © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月26日
 */
package org.anttribe.vigor.defensor.console.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.anttribe.vigor.defensor.domain.User;
import org.anttribe.vigor.defensor.service.IUserService;
import org.anttribe.vigor.infra.common.constants.Constants;
import org.anttribe.vigor.infra.common.constants.Keys;
import org.anttribe.vigor.infra.common.entity.Result;
import org.anttribe.vigor.infra.common.errorno.SystemErrorNo;
import org.anttribe.vigor.infra.common.exception.ServiceException;
import org.anttribe.vigor.infra.common.exception.UnifyException;
import org.anttribe.vigor.infra.common.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @version 2016年2月26日
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController
{
    @Resource
    private IUserService userService;
    
    @RequestMapping({"", "/", "/index"})
    public ModelAndView index(HttpServletRequest request, ModelAndView mv, User user)
    {
        mv.setViewName(Views.INDEX_VIEW);
        
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("username", user.getUsername());
        List<User> users = userService.listEntities(criteria);
        mv.addObject(Keys.KEY_PAGE_DATA, users);
        return mv;
    }
    
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request, ModelAndView mv, User user)
    {
        mv.setViewName(Views.ADD_VIEW);
        mv.addObject(Keys.KEY_PARAM, user);
        return mv;
    }
    
    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request, ModelAndView mv, User user)
    {
        if (null == user || null == user.getId())
        {
            throw new UnifyException(SystemErrorNo.DATA_NOT_EXIST_ERROR);
        }
        mv.setViewName(Views.EDIT_VIEW);
        
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("id", user.getId());
        user = userService.findEntity(criteria);
        if (null == user || null == user.getId())
        {
            throw new UnifyException(SystemErrorNo.DATA_NOT_EXIST_ERROR);
        }
        mv.addObject(Keys.KEY_PARAM, user);
        return mv;
    }
    
    @ResponseBody
    @RequestMapping("/edit/exec")
    public Result<?> doEdit(HttpServletRequest request, User user)
    {
        Result<?> result = new Result<String>();
        try
        {
            // TODO： 数据校验
            userService.persistentEntity(user);
            result.setResultCode(Constants.Common.DEFAULT_RESULT_CODE);
        }
        catch (ServiceException e)
        {
            result.setResultCode(e.getErrorNo());
        }
        return result;
    }
    
    @ResponseBody
    @RequestMapping("/delete/exec")
    public Result<?> doDelete(HttpServletRequest request, User user)
    {
        Result<?> result = new Result<String>();
        try
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("id", user.getId());
            userService.removeEntity(criteria);
            
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
         * index视图
         */
        public static final String INDEX_VIEW = "/user/list";
        
        /**
         * 列表视图
         */
        public static final String LIST_VIEW = "/user/list";
        
        /**
         * 添加视图
         */
        public static final String ADD_VIEW = "/user/edit";
        
        /**
         * 编辑视图
         */
        public static final String EDIT_VIEW = "/user/edit";
        
    }
}