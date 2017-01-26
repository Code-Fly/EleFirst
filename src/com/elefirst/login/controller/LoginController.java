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

/**
 * Created by barrie on 17/1/14.
 */
@Controller
@RequestMapping("/login")
@Api(value = "login", description = "登陆操作")
public class LoginController extends BaseController {
    @RequestMapping(value = "/test.do")
    @ApiOperation(value = "测试", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getWorkflowTemplateList(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "rows", required = false) Integer rows
    ) {
        return new ErrorMsg(Error.SUCCESS, "success");
    }

}
