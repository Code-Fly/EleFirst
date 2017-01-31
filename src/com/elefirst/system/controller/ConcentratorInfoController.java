package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.system.po.ConcentratorInfo;
import com.elefirst.system.service.iface.IConcentratorInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "rows", required = false) Integer rows
    ) {
        ConcentratorInfo template = new ConcentratorInfo();
        if (null != areaId) {
            template.setAreaId(areaId);
        }

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<ConcentratorInfo> result = concentratorInfoService.getConcentratorInfoList(template);

            DataGrid dg = new DataGrid();
            int count = concentratorInfoService.getConcentratorInfoListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<ConcentratorInfo> result = concentratorInfoService.getConcentratorInfoList(template);
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
    public ErrorMsg updateTreeInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "id") String id,
                                   @RequestParam(value = "areaId") String areaId,
                                   @RequestParam(value = "concentratorId") String concentratorId,
                                   @RequestParam(value = "name") String name
    ) {
        ConcentratorInfo template = new ConcentratorInfo();

        if (null != name && !name.isEmpty()) {

            template.setId(id);
            template.setAreaId(areaId);
            template.setConcentratorId(concentratorId);
            template.setName(name);
            template.setUpdatePerson("admin");
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
    public ErrorMsg updateTreeInfoByInfo(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestParam(value = "areaId") String areaId,
                                         @RequestParam(value = "concentratorId") String concentratorId,
                                         @RequestParam(value = "name") String name
    ) {
        ConcentratorInfo template = new ConcentratorInfo();

        if (null != name && !name.isEmpty()) {

            template.setAreaId(areaId);
            template.setConcentratorId(concentratorId);
            template.setName(name);
            template.setUpdatePerson("admin");
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
    public ErrorMsg addTreeInfo(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(value = "areaId") String areaId,
                                @RequestParam(value = "concentratorId") String concentratorId,
                                @RequestParam(value = "name") String name
    ) {
        ConcentratorInfo template = new ConcentratorInfo();
        template.setId(UUID.randomUUID().toString());
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        template.setName(name);
        template.setCreatePerson("admin");
        template.setCreateDate(new Date());
        int result = concentratorInfoService.addConcentratorInfo(template);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/delete.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deleteTreeInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "id") String id
    ) {
        int result = concentratorInfoService.delConcentratorInfo(id);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/info/deleteByInfo.do")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg deleteTreeInfoByInfo(HttpServletRequest request,
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
