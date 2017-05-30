package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.exception.SessionExpiredException;
import com.elefirst.base.utils.UUIDKeyGenerator;
import com.elefirst.system.po.UserInfo;
import com.elefirst.system.po.UserInfoCustom;
import com.elefirst.system.service.iface.IUserInfoService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by barrie on 17/1/29.
 */
@Controller
@RequestMapping("/system/user")
@Api(value = "user", description = "用户操作")
public class UserInfoController extends BaseController {
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping(value = "/info/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getUserInfoList(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "userId", required = false) String userId,
                                    @RequestParam(value = "userCode", required = false) String userCode,
                                    @RequestParam(value = "userName", required = false) String userName,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows
    ) {
        UserInfo template = new UserInfo();

        template.setId(userId);
        template.setUserCode(userCode);
        template.setUserName(userName);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<UserInfo> result = userInfoService.getUserInfoList(template);

            DataGrid dg = new DataGrid();
            long count = userInfoService.getUserInfoListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<UserInfo> result = userInfoService.getUserInfoList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/info/extends.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getUserInfoExtends(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestParam(value = "userName", required = true) String userName
    ) {
        UserInfo template = new UserInfo();
        template.setUserName(userName);
        List<UserInfoCustom> result = userInfoService.getUserInfoExtends(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/detail.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getUserInfoDetail(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(value = "id") String id
    ) {
        List<UserInfo> result = userInfoService.getUserInfoDetail(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/update.do")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg updateUserInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody String sData
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        UserInfo template = new Gson().fromJson(sData, UserInfo.class);
        template.setUpdatePerson(userInfo.getUserName());
        template.setUpdateDate(new Date());
        int result = userInfoService.updateUserInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/add.do")
    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg addUserInfo(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestBody String sData
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        UserInfo template = new Gson().fromJson(sData, UserInfo.class);
        template.setId(UUID.randomUUID().toString());
        template.setCreatePerson(userInfo.getUserName());
        template.setCreateDate(new Date());
        int result = userInfoService.addUserInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
    
    @RequestMapping("/info/updatePersonInfo.do")
    @ResponseBody
    public ErrorMsg updatePersonInfo(@RequestParam(value = "userId", required = false)String userId, 
    		@RequestParam(value = "userCode", required = false)String userCode, 
    		@RequestParam(value = "userName", required = false)String userName, 
    		@RequestParam(value = "description", required = false)String description,  
    		@RequestParam(value = "passwd", required = false)String passwd, 
    		HttpServletRequest request, HttpSession session) {
    	
    	UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期");
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }
        try {
        	UserInfo personInfo = new UserInfo();
        	personInfo.setId(userId);
        	personInfo.setUserCode(userCode);
        	personInfo.setUserName(userName);
            personInfo.setDescription(description);
            //对密码进行MD5加密
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String encoded = encoder.encodePassword(passwd, userName);
            personInfo.setPassword(encoded);
            personInfo.setUpdateDate(new Date());
            personInfo.setUpdatePerson(userInfo.getUserName());
            userInfoService.updateUserInfo(personInfo);
            logger.info("修改用户实例对象成功!");
            return new ErrorMsg(Error.SUCCESS, "success", "");
        } catch (Exception e) {
            logger.error("{}", e);
            return new ErrorMsg(Error.API_RESPONSE_EXCEPTION, "faile", "");
        }
    }
    
    /**
     * 用户注册
     *
     * @param userCode
     * @param userName
     * @param description
     * @param passwd
     * @param roleName
     * @param organizeName
     * @param session
     * @return
     */
    @RequestMapping("/info/addpersoninfo.do")
    @ResponseBody
    public  ErrorMsg addPersonInfo(@RequestParam(value = "userCode", required = false)String userCode, 
    		@RequestParam(value = "userName", required = false)String userName, 
    		@RequestParam(value = "description", required = false)String description, 
    		@RequestParam(value = "passwd", required = false)String passwd, 
    		HttpServletRequest request,HttpSession session) {
        try {
        	UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            if (null == userInfo || null == userInfo.getUserName()) {
                logger.error("用户Session过期");
                return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
            }
            UserInfo personInfo = new UserInfo();
            personInfo.setId(UUIDKeyGenerator.getUUID());
            personInfo.setUserCode(userCode);
            personInfo.setUserName(userName);
            //对密码进行MD5加密
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String encoded = encoder.encodePassword(passwd, userName);
            personInfo.setPassword(encoded);
            personInfo.setDescription(description);
            personInfo.setCreateDate(new Date());
            personInfo.setCreatePerson(userInfo.getUserName());
            userInfoService.addUserInfo(personInfo);
            logger.info("添加新用户实例对象成功!");
            return new ErrorMsg(Error.SUCCESS, "success", "");
        } catch (Exception e) {
            logger.error("{}", e);
            return new ErrorMsg(Error.API_RESPONSE_EXCEPTION, "faile", "");
        }
    }

    @RequestMapping(value = "/info/delete.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deleteUserInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "id") String id
    ) {
        int result = userInfoService.delUserInfo(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}
