/*
 * 文  件   名: SigninController.java
 * 版         本 : vigor-defensor-console © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月29日
 */
package org.anttribe.vigor.defensor.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.anttribe.vigor.defensor.domain.User;
import org.anttribe.vigor.infra.common.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @version 2016年2月29日
 */
@Controller
public class SigninController extends AbstractController
{
    
    @RequestMapping("/signin")
    public ModelAndView signin(HttpServletRequest request, HttpServletResponse response, ModelAndView mv)
    {
        mv.setViewName(Views.SIGNIN_VIEW);
        return mv;
    }
    
    @RequestMapping("/signin/exec")
    public void doSignin(HttpServletRequest request, HttpServletResponse response, User user)
    {
        System.out.println(user);
    }
    
    class Views
    {
        
        public static final String SIGNIN_VIEW = "/signin";
        
    }
    
}