package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.Page2;
import com.elefirst.base.exception.SessionExpiredException;
import com.elefirst.system.po.ConcentratorInfo;
import com.elefirst.system.po.UserInfo;
import com.elefirst.system.service.iface.IConcentratorInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by barrie on 17/1/29.
 */
@Controller
@RequestMapping("/system/concentrator")
@Api(value = "concentrator", description = "馈线柜操作")
public class ConcentratorInfoController extends BaseController {
    @Autowired
    private IConcentratorInfoService concentratorInfoService;

    @RequestMapping(value = "/info/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getConcentratorInfoList(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestParam(value = "areaId", required = false) String areaId,
                                            @RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "node", required = false) String node,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "rows", required = false) Integer rows
    ) {
        List<ConcentratorInfo> result = new ArrayList<>();

        if (null == node) {
            ConcentratorInfo template = new ConcentratorInfo();
            if (null != areaId) {
                template.setAreaId(areaId);
            }

            if (null != name) {
                template.setName(areaId);
            }
            result = concentratorInfoService.getConcentratorInfoList(template);
        } else {
            JSONObject jNode = JSONObject.fromObject(node);
            String jAreaId = jNode.getString("areaId");
            JSONArray jCIds = jNode.getJSONArray("concentrators");

            List<ConcentratorInfo> templates = new ArrayList<>();
            if (jCIds.size() == 0) {
                ConcentratorInfo template = new ConcentratorInfo();
                template.setAreaId(jAreaId);
                templates.add(template);
            }
            for (int i = 0; i < jCIds.size(); i++) {
                String jId = jCIds.getJSONObject(i).getString("concentratorId");
                ConcentratorInfo template = new ConcentratorInfo();
                template.setAreaId(jAreaId);
                template.setConcentratorId(jId);
                templates.add(template);
            }

            result = concentratorInfoService.getConcentratorInfoListByInfos(templates);
        }

        if (null != page && null != rows) {

            Page2 page2 = new Page2(result, rows);

            DataGrid dg = new DataGrid();
            dg.setTotal(result.size());
            dg.setRows(page2.getPages(page));

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }
    }

    @RequestMapping(value = "/info/detailByInfo.do")
    @ApiOperation(value = "详情", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getConcentratorInfoDetailByInfo(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    @RequestParam(value = "areaId") String areaId,
                                                    @RequestParam(value = "concentratorId") String concentratorId
    ) {
        ConcentratorInfo template = new ConcentratorInfo();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        List<ConcentratorInfo> result = concentratorInfoService.getConcentratorInfoList(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/update.do")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg updateConcentratorInfo(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestParam(value = "id") String id,
                                           @RequestParam(value = "areaId") String areaId,
                                           @RequestParam(value = "concentratorId") String concentratorId,
                                           @RequestParam(value = "name") String name
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        ConcentratorInfo template = new ConcentratorInfo();

        if (null != name && !name.isEmpty()) {

            template.setId(id);
            template.setAreaId(areaId);
            template.setConcentratorId(concentratorId);
            template.setName(name);
            template.setUpdatePerson(userInfo.getUserName());
            template.setUpdateDate(new Date());
            int result = concentratorInfoService.updateConcentratorInfo(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        } else {
            return new ErrorMsg(Error.SUCCESS, "success");
        }

    }

    @RequestMapping(value = "/info/updateByInfo.do")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg updateConcentratorInfoByInfo(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @RequestParam(value = "areaId") String areaId,
                                                 @RequestParam(value = "concentratorId") String concentratorId,
                                                 @RequestParam(value = "name") String name
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        ConcentratorInfo template = new ConcentratorInfo();

        if (null != name && !name.isEmpty()) {

            template.setAreaId(areaId);
            template.setConcentratorId(concentratorId);
            template.setName(name);
            template.setUpdatePerson(userInfo.getUserName());
            template.setUpdateDate(new Date());
            int result = concentratorInfoService.updateConcentratorInfoByInfo(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        } else {
            return new ErrorMsg(Error.SUCCESS, "success");
        }

    }

    @RequestMapping(value = "/info/add.do")
    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg addConcentratorInfo(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam(value = "areaId") String areaId,
                                        @RequestParam(value = "concentratorId") String concentratorId,
                                        @RequestParam(value = "name") String name
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        ConcentratorInfo template = new ConcentratorInfo();
        template.setId(UUID.randomUUID().toString());
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        template.setName(name);
        template.setCreatePerson(userInfo.getUserName());
        template.setCreateDate(new Date());
        int result = concentratorInfoService.addConcentratorInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/delete.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deleteConcentratorInfo(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestParam(value = "id") String id
    ) {
        int result = concentratorInfoService.delConcentratorInfo(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/deleteByInfo.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deleteConcentratorInfoByInfo(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @RequestParam(value = "areaId") String areaId,
                                                 @RequestParam(value = "concentratorId") String concentratorId
    ) {
        ConcentratorInfo template = new ConcentratorInfo();
        template.setId(UUID.randomUUID().toString());
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        int result = concentratorInfoService.delConcentratorInfoByInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}
