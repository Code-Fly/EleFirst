package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.system.po.RoleInfo;
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
        RoleInfo template = new Gson().fromJson(sData, RoleInfo.class);
        template.setUpdatePerson("admin");
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
        RoleInfo template = new Gson().fromJson(sData, RoleInfo.class);
        template.setId(UUID.randomUUID().toString());
        template.setCreatePerson("admin");
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
}
