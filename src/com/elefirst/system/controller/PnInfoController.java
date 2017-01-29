package com.elefirst.system.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "rows", required = false) Integer rows
    ) {
        PnInfo template = new PnInfo();

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<PnInfo> result = pnInfoService.getPnInfoList(template);

            DataGrid dg = new DataGrid();
            int count = pnInfoService.getPnInfoListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<PnInfo> result = pnInfoService.getPnInfoList(template);
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
}