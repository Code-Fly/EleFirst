package com.elefirst.report.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.report.po.ReportEleByDaily;
import com.elefirst.report.service.IReportEleDailyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/2/2.
 */
@Controller
@RequestMapping("/report/electricity")
@Api(value = "data", description = "区域操作")
public class ReportElectricityController extends BaseController {
	
	@Resource(name = "reportEleDailyServiceImpl")
	private IReportEleDailyService reportEleDailyServiceImpl;

    @RequestMapping(value = "/daily/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getReportDailyElectricityList(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  @RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "rows", required = false) Integer rows,String date
    ) throws Exception {
    	List<ReportEleByDaily> reportEleByDailys = reportEleDailyServiceImpl.fetchAllReportEleByDaily2(date, null, null, rows, page, true);
        DataGrid dg = new DataGrid();
        long count = reportEleDailyServiceImpl.fetchAllReportEleByDaily2(date, null, null, rows, page, false).size();
        dg.setTotal(count);
        dg.setRows(reportEleByDailys);
        return new ErrorMsg(Error.SUCCESS, "success", dg);
    }

}