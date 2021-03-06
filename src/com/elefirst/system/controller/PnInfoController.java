package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.Page2;
import com.elefirst.base.exception.SessionExpiredException;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.po.UserInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/system/pn")
@Api(value = "pn", description = "监测点操作")
public class PnInfoController extends BaseController {
    @Autowired
    private IPnInfoService pnInfoService;

    @RequestMapping(value = "/info/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getPnInfoList(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam(value = "areaId", required = false) String areaId,
                                  @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "node", required = false) String node,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "rows", required = false) Integer rows
    ) {

        List<PnInfo> result = new ArrayList<>();

        if (null == node) {
            PnInfo template = new PnInfo();
            if (null != areaId) {
                template.setAreaId(areaId);
            }
            if (null != name) {
                template.setName(areaId);
            }
            if (null != concentratorId) {
                template.setConcentratorId(concentratorId);
            }
            result = pnInfoService.getPnInfoList(template);
        } else {
            JSONObject jNode = JSONObject.fromObject(node);
            String jAreaId = jNode.getString("areaId");
            JSONArray jCIds = jNode.getJSONArray("concentrators");

            List<PnInfo> templates = new ArrayList<>();
            if (jCIds.size() == 0) {
                PnInfo template = new PnInfo();
                template.setAreaId(jAreaId);
                templates.add(template);
            }
            for (int i = 0; i < jCIds.size(); i++) {
                String jCId = jCIds.getJSONObject(i).getString("concentratorId");
                JSONArray jPIds = jCIds.getJSONObject(i).getJSONArray("pns");
                if (jPIds.size() == 0) {
                    PnInfo template = new PnInfo();
                    template.setAreaId(jAreaId);
                    template.setConcentratorId(jCId);
                    templates.add(template);
                } else {
                    for (int j = 0; j < jPIds.size(); j++) {
                        String jPid = jPIds.getString(j);
                        PnInfo template = new PnInfo();
                        template.setAreaId(jAreaId);
                        template.setConcentratorId(jCId);
                        template.setPn(jPid);
                        templates.add(template);
                    }
                }

            }
            result = pnInfoService.getPnInfoListByInfos(templates);
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

    @RequestMapping(value = "/info/detail.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getPnInfoDetail(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @RequestParam(value = "areaId") String areaId,
                                    @RequestParam(value = "concentratorId") String concentratorId,
                                    @RequestParam(value = "pn") String pn
    ) {
        PnInfo template = new PnInfo();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        template.setPn(pn);

        List<PnInfo> result = pnInfoService.getPnInfoList(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/detailById.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getPnInfoDetailById(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam(value = "id") String id
    ) {
        List<PnInfo> result = pnInfoService.getPnInfoDetail(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/update.do")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg updatePnInfo(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestBody String sData
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        PnInfo template = new Gson().fromJson(sData, PnInfo.class);
        template.setUpdatePerson(userInfo.getUserName());
        template.setUpdateDate(new Date());

        int result = pnInfoService.updatePnInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);

    }

    @RequestMapping(value = "/info/add.do")
    @ApiOperation(value = "添加", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg addPnfo(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestBody String sData
    ) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        if (null == userInfo || null == userInfo.getUserName()) {
            logger.error("用户Session过期(" + Error.SESSION_EXPIRED_EXCEPTION + ")", new SessionExpiredException("Session Expired"));
            return new ErrorMsg(Error.SESSION_EXPIRED_EXCEPTION, "Session expired");
        }

        PnInfo template = new Gson().fromJson(sData, PnInfo.class);
        template.setId(UUID.randomUUID().toString());
        template.setCreatePerson(userInfo.getUserName());
        template.setCreateDate(new Date());
        int result = pnInfoService.addPnInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/delete.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deletePnInfo(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "id") String id
    ) {
        int result = pnInfoService.delPnInfo(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}