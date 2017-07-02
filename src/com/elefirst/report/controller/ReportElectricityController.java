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
    	List<ReportEleByDaily> reportEleByDailys = reportEleDailyServiceImpl.fetchAllReportEleByDaily(date, null, null, rows, page, true);
        DataGrid dg = new DataGrid();
        long count = reportEleDailyServiceImpl.fetchAllReportEleByDaily(date, null, null, rows, page, false).size();
        dg.setTotal(count);
        dg.setRows(reportEleByDailys);
        return new ErrorMsg(Error.SUCCESS, "success", dg);
    }

    @RequestMapping(value = "/monthly/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getReportMonthlyElectricityList(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    @RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "rows", required = false) Integer rows
    ) {
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
    	/*for(int i=0;i < 12;i++){
    		Map<String,String> map = new HashMap<String,String>();
    		map.put("date", "2017-06-0"+(i+1));
    		map.put("display", "1553.75");
    		map.put("olddisplay", "1553.75");
    		map.put("displaydiffer", "6.81");
    		map.put("override", "60");
    		map.put("power", "409");
    		lists.add(map);
    	}*/
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("date", "2017-06-01");
        map1.put("display", "1553.75");
        map1.put("olddisplay", "1553.75");
        map1.put("displaydiffer", "6.81");
        map1.put("override", "60");
        map1.put("power", "409");
        lists.add(map1);

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("date", "2017-06-02");
        map2.put("display", "1562.8");
        map2.put("olddisplay", "1553.75");
        map2.put("displaydiffer", "9.05");
        map2.put("override", "60");
        map2.put("power", "543");
        lists.add(map2);

        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("date", "2017-06-03");
        map3.put("display", "1572.46");
        map3.put("olddisplay", "1562.8");
        map3.put("displaydiffer", "9.66");
        map3.put("override", "60");
        map3.put("power", "580");
        lists.add(map3);

        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("date", "2017-06-04");
        map4.put("display", "1576.51");
        map4.put("olddisplay", "1572.46");
        map4.put("displaydiffer", "4.05");
        map4.put("override", "60");
        map4.put("power", "243");
        lists.add(map4);

        DataGrid dg = new DataGrid();
        long count = lists.size();
        dg.setTotal(count);
        dg.setRows(lists);
        return new ErrorMsg(Error.SUCCESS, "success", dg);
    }

    @RequestMapping(value = "/lineloss/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getReportLinelossList(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "rows", required = false) Integer rows
    ) {
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", 1);
        map1.put("region", "配用电监测体验");
        map1.put("f1", "2247");
        map1.put("f2", "1307");
        map1.put("f3", "940");
        map1.put("f4", "41.84%");
        lists.add(map1);
        Map<String, Object> map11 = new HashMap<String, Object>();
        map11.put("id", 11);
        map11.put("region", "10kV润麒路I线");
        map11.put("f1", "2247");
        map11.put("f2", "1307");
        map11.put("f3", "940");
        map11.put("f4", "41.84%");
        map11.put("_parentId", 1);
        lists.add(map11);
        Map<String, Object> map111 = new HashMap<String, Object>();
        map111.put("id", 111);
        map111.put("region", "1#变压器");
        map111.put("f1", "1125");
        map111.put("f2", "601");
        map111.put("f3", "524");
        map111.put("f4", "46.58%");
        map111.put("_parentId", 11);
        lists.add(map111);

        Map<String, Object> map12 = new HashMap<String, Object>();
        map12.put("id", 12);
        map12.put("region", "10kV润麒路II线");
        map12.put("f1", "2247");
        map12.put("f2", "1307");
        map12.put("f3", "940");
        map12.put("f4", "41.84%");
        map12.put("_parentId", 1);
        lists.add(map12);
        Map<String, Object> map121 = new HashMap<String, Object>();
        map121.put("id", 111);
        map121.put("region", "2#变压器");
        map121.put("f1", "1125");
        map121.put("f2", "601");
        map121.put("f3", "524");
        map121.put("f4", "46.58%");
        map121.put("_parentId", 12);
        lists.add(map121);

        Map<String, Object> map13 = new HashMap<String, Object>();
        map13.put("id", 13);
        map13.put("region", "10kV进线");
        map13.put("f1", "2247");
        map13.put("f2", "1307");
        map13.put("f3", "940");
        map13.put("f4", "41.84%");
        map13.put("_parentId", 1);
        lists.add(map13);
        Map<String, Object> map131 = new HashMap<String, Object>();
        map131.put("id", 131);
        map131.put("region", "3#变压器");
        map131.put("f1", "1125");
        map131.put("f2", "601");
        map131.put("f3", "524");
        map131.put("f4", "46.58%");
        map131.put("_parentId", 13);
        lists.add(map131);

        DataGrid dg = new DataGrid();
        long count = lists.size();
        dg.setTotal(count);
        dg.setRows(lists);
        //return JSONObject.fromObject(dg).toString();
        return new ErrorMsg(Error.SUCCESS, "success", dg);
    }
}