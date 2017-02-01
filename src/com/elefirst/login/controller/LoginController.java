package com.elefirst.login.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by barrie on 17/1/14.
 */
@Controller
@RequestMapping("/login/")
public class LoginController extends BaseController {
    @RequestMapping("login.do")
    public String login(HttpServletRequest request,
                                            HttpServletResponse response,HttpSession session
    ){
    	
        return "redirect:/index.jsp";
    }

}
