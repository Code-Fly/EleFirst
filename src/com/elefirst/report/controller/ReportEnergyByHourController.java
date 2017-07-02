package com.elefirst.report.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.report.po.ReportEnergyByHour;
import com.elefirst.report.service.IReportDisplayDailyService;
import com.elefirst.report.service.iface.IReportEnergyByHourService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Controller
@RequestMapping("/report/energy")
@Api(value = "data", description = "区域操作")
public class ReportEnergyByHourController extends BaseController {
    @Autowired
    private IReportEnergyByHourService reportEnergyByHourService;
    

    @RequestMapping(value = "/hour/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getReportEnergyByHourList(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam(value = "areaId", required = false) String areaId,
                                  @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "rows", required = false) Integer rows
    ) {
        ReportEnergyByHour template = new ReportEnergyByHour();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<ReportEnergyByHour> result = reportEnergyByHourService.getReportEnergyByHourList(template);

            DataGrid dg = new DataGrid();
            long count = reportEnergyByHourService.getReportEnergyByHourListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<ReportEnergyByHour> result = reportEnergyByHourService.getReportEnergyByHourList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/hour/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getReportEnergyByHourListByNodes(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestParam(value = "node", required = false) String node,
                                         @RequestParam(value = "startTime", required = false) String startTime,
                                         @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<ReportEnergyByHour> nodes = new Gson().fromJson(node, new TypeToken<List<ReportEnergyByHour>>() {
        }.getType());

        List<ReportEnergyByHour> result = reportEnergyByHourService.getReportEnergyByHourList(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
    
    @RequestMapping(value = "/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getReportDailyDisplayctricityList(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows,String date
                                         
    ) throws Exception{
    	List<ReportEnergyByHour> reportEnergyByHours = reportEnergyByHourService.fetchAllReportEnergyByHour(date, null, null, rows, page, true);
        DataGrid dg = new DataGrid();
        long count = reportEnergyByHourService.fetchAllReportEnergyByHour(date, null, null, rows, page, false).size();
        dg.setTotal(count);
        dg.setRows(reportEnergyByHours);
        return new ErrorMsg(Error.SUCCESS, "success", dg);
    }
}