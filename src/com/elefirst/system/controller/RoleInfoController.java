package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.GeneralMessage;
import com.elefirst.base.exception.SessionExpiredException;
import com.elefirst.base.utils.UUIDKeyGenerator;
import com.elefirst.system.po.RoleInfo;
import com.elefirst.system.po.UserInfo;
import com.elefirst.system.po.UserRoleMap;
import com.elefirst.system.service.iface.IRoleInfoService;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by barrie on 17/1/29.
 */
@Controller
@RequestMapping("/system/role")
@Api(value = "role", description = "用户操作")
public class RoleInfoController extends BaseController {
    @Autowired
    private IRoleInfoService roleInfoService;

    @RequestMapping(value = "/info/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getRoleInfoList(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "roleId", required = false) String roleId,
                                    @RequestParam(value = "roleCode", required = false) String roleCode,
                                    @RequestParam(value = "roleName", required = false) String roleName,
                                    @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows
    ) {
        RoleInfo template = new RoleInfo();

        template.setId(roleId);
        template.setRoleCode(roleCode);
        template.setRoleName(roleName);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<RoleInfo> result = roleInfoService.getRoleInfoList(template);

            DataGrid dg = new DataGrid();
            long count = roleInfoService.getRoleInfoListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<RoleInfo> result = roleInfoService.getRoleInfoList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/info/detail.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getRoleInfoDetail(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(value = "id") String id
    ) {
        List<RoleInfo> result = roleInfoService.getRoleInfoDetail(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/update.do")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg updateRoleInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestBody String sData
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }
        
        RoleInfo template = new Gson().fromJson(sData, RoleInfo.class);
        template.setUpdatePerson(userInfo.getUserName());
        template.setUpdateDate(new Date());
        int result = roleInfoService.updateRoleInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/add.do")
    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg addRoleInfo(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestBody String sData
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        RoleInfo template = new Gson().fromJson(sData, RoleInfo.class);
        template.setId(UUID.randomUUID().toString());
        template.setCreatePerson(userInfo.getUserName());
        template.setCreateDate(new Date());
        int result = roleInfoService.addRoleInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/delete.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deleteRoleInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "id") String id
    ) {
        int result = roleInfoService.delRoleInfo(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
    
    /**
	 * 新增角色
	 * @param user
	 * @return
	 */
	@RequestMapping("/info/addroleinfo.do")
	public @ResponseBody ErrorMsg addRoleInfo(String roleCode,String roleName,String description,HttpServletRequest request,HttpSession session) {
		RoleInfo roleInfo = null;
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
	        if (null == userInfo || null == userInfo.getUserName()) {
	            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
	            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
	        }
			roleInfo = new RoleInfo();
			roleInfo.setId(UUIDKeyGenerator.getUUID());
			roleInfo.setRoleCode(roleCode);
			roleInfo.setRoleName(roleName);
			roleInfo.setDescription(description);
			roleInfo.setCreateDate(new Date());
			roleInfo.setCreatePerson(userInfo.getUserName());
			roleInfoService.addRoleInfo(roleInfo);
			logger.info("添加新用户实例对象成功!");
			return new ErrorMsg(Error.SUCCESS, "success", "");
		} catch (Exception e) {
			logger.error("{}", e);
			return new ErrorMsg(Error.API_RESPONSE_EXCEPTION, "faile", "");
		}
	}
	
	/**
	 * 修改角色信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/info/updateroleinfo.do")
	public @ResponseBody ErrorMsg updateRoleInfo(String roleId,String roleCode,String roleName,String description,HttpServletRequest request,HttpSession session){
		
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }
		try {
			RoleInfo roleInfo = new RoleInfo();
			roleInfo.setId(roleId);
			roleInfo.setRoleCode(roleCode);
			roleInfo.setRoleName(roleName);
			roleInfo.setDescription(description);
			roleInfo.setUpdateDate(new Date());
			roleInfo.setUpdatePerson(userInfo.getUserName());
			roleInfoService.updateRoleInfo(roleInfo);
			logger.info("修改角色实例对象成功!");
			return new ErrorMsg(Error.SUCCESS, "success", "");
		} catch (Exception e) {
			logger.error("{}", e);
			return new ErrorMsg(Error.API_RESPONSE_EXCEPTION, "faile", "");
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/info/queryRoleByUserId.do")
	public @ResponseBody ErrorMsg queryRoleByUserId(String page, String rows,String userId,HttpSession session){
		DataGrid dg = new DataGrid();
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			if (null == userInfo || null == userInfo.getUserName()) {
				logger.error("用户Session过期");
				return null;
			}
			RoleInfo roleInfo = new RoleInfo();
			roleInfo.setPage(pageNum);
			roleInfo.setRows(rowsNum);
			List<RoleInfo> roleInfos = roleInfoService.fetchRoleInfoByUserId(roleInfo, true, userId);
			// 返回分页对象数
			dg.setRows(roleInfos);
			int totalNum = roleInfoService.fetchRoleInfoByCond(roleInfo, false).size();
			dg.setTotal(totalNum);
			
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			dg = null;
			logger.error("根据条件查看角色信息失败！", e);
			return new ErrorMsg(Error.API_RESPONSE_EXCEPTION, "faile", "");
		}
	}
	
	/**
	 * 新增角色与用户关系
	 * @param user
	 * @return
	 */
	@RequestMapping("/info/addUserRoleMap.do")
	public @ResponseBody ErrorMsg addUserRoleMap(String userId,String roleIdsStr) {
		//保存前先根据userId清空角色与用户关系表
		List<UserRoleMap> usrerRoleMaps = null;
		try {
			if(roleIdsStr !=null && roleIdsStr.length() > 0){
				usrerRoleMaps = new ArrayList<UserRoleMap>();
				String[] rIds = roleIdsStr.split(",");
				for(int i=0;i < rIds.length;i++){
					UserRoleMap tmpUserRoleMap = new UserRoleMap();
					tmpUserRoleMap.setId(UUIDKeyGenerator.getUUID());
					tmpUserRoleMap.setUserId(userId);
					tmpUserRoleMap.setRoleId(rIds[i]);
					usrerRoleMaps.add(tmpUserRoleMap);
				}
			}
			roleInfoService.saveUserRoleMaps(usrerRoleMaps, userId);
			logger.info("添加角色与用户关系实例成功!");
			return new ErrorMsg(Error.SUCCESS, "success", "");
		} catch (Exception e) {
			logger.error("{}", e);
			return new ErrorMsg(Error.API_RESPONSE_EXCEPTION, "faile", "");
		}
	}
}
