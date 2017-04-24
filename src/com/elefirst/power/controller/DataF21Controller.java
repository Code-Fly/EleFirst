package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF21;
import com.elefirst.power.po.StatisticF21TotalPositiveActivePower;
import com.elefirst.power.service.iface.IDataF21Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Controller
@RequestMapping("/power/data")
@Api(value = "data", description = "区域操作")
public class DataF21Controller extends BaseController {
    @Autowired
    private IDataF21Service dataF21Service;

    @RequestMapping(value = "/f21/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF21List(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "areaId", required = false) String areaId,
                                   @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF21 template = new DataF21();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF21> result = dataF21Service.getDataF21List(template);

            DataGrid dg = new DataGrid();
            long count = dataF21Service.getDataF21ListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF21> result = dataF21Service.getDataF21List(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/f21/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF21ListByNodes(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam(value = "node", required = false) String node,
                                          @RequestParam(value = "startTime", required = false) String startTime,
                                          @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF21> nodes = new Gson().fromJson(node, new TypeToken<List<DataF21>>() {
        }.getType());

        List<DataF21> result = dataF21Service.getDataF21List(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF21Service.format(result));
    }

    @RequestMapping(value = "/f21/node/time/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF21Sum(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam(value = "node", required = false) String node,
                                  @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        JSONArray nodes = JSONArray.fromObject(node);

//        List<List<DataF21>> nodes = new Gson().fromJson(time, new TypeToken<List<List<DataF21>>>() {
//        }.getType());

        List<JSONObject> times = new Gson().fromJson(time, new TypeToken<List<JSONObject>>() {
        }.getType());

        List<List<List<DataF21>>> result = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            List<List<DataF21>> rs = new ArrayList<>();

            List<DataF21> ns = new Gson().fromJson(nodes.getString(i), new TypeToken<List<DataF21>>() {
            }.getType());

//            List<DataF21> ns = nodes.get(i);

            for (int j = 0; j < times.size(); j++) {
                List<DataF21> item = dataF21Service.getDataF21SumList(ns, times.get(j).getString("startTime").substring(0, 8), times.get(j).getString("endTime").substring(0, 8));
                rs.add(dataF21Service.format(item));
            }

            result.add(rs);
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/f21/interval/month/statistic.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF21StatisticIntervalMonth(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @RequestParam(value = "node", required = false) String node,
                                                     @RequestParam(value = "time", required = false) String time,
                                                     @RequestParam(value = "interval", required = false) Integer interval
    ) throws ParseException {
        List<StatisticF21TotalPositiveActivePower> result = new ArrayList<>();

        if (null == node || null == time || null == interval) {
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

        List<DataF21> nodes = new Gson().fromJson(node, new TypeToken<List<DataF21>>() {
        }.getType());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = sdf.parse(time);


        for (int i = 0; i < interval + 1; i++) {
            Date thisMonthStartTimeDate = new Date(date.getTime());
            thisMonthStartTimeDate.setMonth(date.getMonth() + i);
            Date thisMonthEndTimeDate = new Date(date.getTime());
            thisMonthEndTimeDate.setMonth(date.getMonth() + i + 1);

            String thisMonthStartTime = sdf.format(thisMonthStartTimeDate).substring(0, 8);
            String thisMonthEndTime = sdf.format(thisMonthEndTimeDate).substring(0, 8);

            StatisticF21TotalPositiveActivePower item = new StatisticF21TotalPositiveActivePower();

            item.setFrozenDay(thisMonthStartTime);

            String differThisMonth = dataF21Service.getDifferTotalPositiveActivePower(nodes, thisMonthStartTime, thisMonthEndTime);
            item.setThisYearTotalPositiveActivePower(dataF21Service.calc(differThisMonth, 1D, 1));

            //
            Date lastYearStartTimeDate = new Date(date.getTime());
            lastYearStartTimeDate.setMonth(date.getMonth() + i);
            lastYearStartTimeDate.setYear(date.getYear() - 1);
            Date lastYearEndTimeDate = new Date(date.getTime());
            lastYearEndTimeDate.setMonth(date.getMonth() + i + 1);
            lastYearEndTimeDate.setYear(date.getYear() - 1);

            String lastYearStartTime = sdf.format(lastYearStartTimeDate).substring(0, 8);
            String lastYearEndTime = sdf.format(lastYearEndTimeDate).substring(0, 8);

            String differLastYear = dataF21Service.getDifferTotalPositiveActivePower(nodes, lastYearStartTime, lastYearEndTime);
            item.setLastYearTotalPositiveActivePower(dataF21Service.calc(differLastYear, 1D, 1));

            String rate2 = null;
            if (null != differThisMonth && null != differLastYear && 0 != Double.valueOf(differLastYear)) {
                rate2 = String.valueOf(((Double.valueOf(differThisMonth) - Double.valueOf(differLastYear)) * 100D) / Double.valueOf(differLastYear));
                rate2 = dataF21Service.calc(rate2, 1D, 1);
            }
            item.setRate2(rate2);

            if (null != differThisMonth) {
                result.add(item);
            }
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}