package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF5;
import com.elefirst.power.po.StatisticF5TotalPositiveActivePower;
import com.elefirst.power.service.iface.IDataF5Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class DataF5Controller extends BaseController {
    @Autowired
    private IDataF5Service dataF5Service;

    @RequestMapping(value = "/f5/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF5List(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam(value = "areaId", required = false) String areaId,
                                  @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF5 template = new DataF5();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF5> result = dataF5Service.getDataF5List(template);

            DataGrid dg = new DataGrid();
            long count = dataF5Service.getDataF5ListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF5> result = dataF5Service.getDataF5List(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/f5/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF5ListByNodes(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestParam(value = "node", required = false) String node,
                                         @RequestParam(value = "startTime", required = false) String startTime,
                                         @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF5> nodes = new Gson().fromJson(node, new TypeToken<List<DataF5>>() {
        }.getType());

        List<DataF5> result = dataF5Service.getDataF5List(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF5Service.format(result));
    }

    @RequestMapping(value = "/f5/node/time/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF5Sum(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "node", required = false) String node,
                                 @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        List<DataF5> nodes = new Gson().fromJson(node, new TypeToken<List<DataF5>>() {
        }.getType());

        List<JSONObject> times = new Gson().fromJson(time, new TypeToken<List<JSONObject>>() {
        }.getType());

        List<List<DataF5>> result = new ArrayList<>();

        for (int j = 0; j < times.size(); j++) {
            List<DataF5> item = dataF5Service.getDataF5SumList(nodes, times.get(j).getString("startTime").substring(0, 8), times.get(j).getString("endTime").substring(0, 8));
            result.add(dataF5Service.format(item));
        }

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/f5/interval/day/statistic.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF5StatisticIntervalDay(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  @RequestParam(value = "node", required = false) String node,
                                                  @RequestParam(value = "time", required = false) String time,
                                                  @RequestParam(value = "interval", required = false) Integer interval
    ) throws ParseException {
        List<DataF5> nodes = new Gson().fromJson(node, new TypeToken<List<DataF5>>() {
        }.getType());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = sdf.parse(time);

        List<StatisticF5TotalPositiveActivePower> result = new ArrayList<>();

        for (int i = 0; i < interval + 1; i++) {
            Date thisMonthStartTimeDate = new Date(date.getTime());
            thisMonthStartTimeDate.setDate(date.getDate() + i);
            Date thisMonthEndTimeDate = new Date(date.getTime());
            thisMonthEndTimeDate.setDate(date.getDate() + i + 1);

            String thisMonthStartTime = sdf.format(thisMonthStartTimeDate).substring(0, 8);
            String thisMonthEndTime = sdf.format(thisMonthEndTimeDate).substring(0, 8);

            StatisticF5TotalPositiveActivePower item = new StatisticF5TotalPositiveActivePower();

            item.setFrozenDay(thisMonthStartTime);

            String differThisMonth = dataF5Service.getDifferTotalPositiveActivePower(nodes, thisMonthStartTime, thisMonthEndTime);
            item.setThisMonthTotalPositiveActivePower(dataF5Service.calc(differThisMonth, 1D, 1));

            //
            Date lastMonthStartTimeDate = new Date(date.getTime());
            lastMonthStartTimeDate.setDate(date.getDate() + i);
            lastMonthStartTimeDate.setMonth(date.getMonth() - 1);
            Date lastMonthEndTimeDate = new Date(date.getTime());
            lastMonthEndTimeDate.setDate(date.getDate() + i + 1);
            lastMonthEndTimeDate.setMonth(date.getMonth() - 1);

            String lastMonthStartTime = sdf.format(lastMonthStartTimeDate).substring(0, 8);
            String lastMonthEndTime = sdf.format(lastMonthEndTimeDate).substring(0, 8);

            String differLastMonth = dataF5Service.getDifferTotalPositiveActivePower(nodes, lastMonthStartTime, lastMonthEndTime);
            item.setLastMonthTotalPositiveActivePower(dataF5Service.calc(differLastMonth, 1D, 1));

            //
            Date lastYearStartTimeDate = new Date(date.getTime());
            lastYearStartTimeDate.setDate(date.getDate() + i);
            lastYearStartTimeDate.setYear(date.getYear() - 1);
            Date lastYearEndTimeDate = new Date(date.getTime());
            lastYearEndTimeDate.setDate(date.getDate() + i + 1);
            lastYearEndTimeDate.setYear(date.getYear() - 1);

            String lastYearStartTime = sdf.format(lastYearStartTimeDate).substring(0, 8);
            String lastYearEndTime = sdf.format(lastYearEndTimeDate).substring(0, 8);

            String differLastYear = dataF5Service.getDifferTotalPositiveActivePower(nodes, lastYearStartTime, lastYearEndTime);
            item.setLastYearTotalPositiveActivePower(dataF5Service.calc(differLastYear, 1D, 1));


            String rate1 = null;
            if (null != differThisMonth && null != differLastMonth && 0 != Double.valueOf(differLastMonth)) {
                rate1 = String.valueOf(((Double.valueOf(differThisMonth) - Double.valueOf(differLastMonth)) * 100D) / Double.valueOf(differLastMonth));
                rate1 = dataF5Service.calc(rate1, 1D, 1);
            }
            item.setRate1(rate1);

            String rate2 = null;
            if (null != differThisMonth && null != differLastYear && 0 != Double.valueOf(differLastYear)) {
                rate2 = String.valueOf(((Double.valueOf(differThisMonth) - Double.valueOf(differLastYear)) * 100D) / Double.valueOf(differLastYear));
                rate2 = dataF5Service.calc(rate2, 1D, 1);
            }
            item.setRate1(rate2);

            if (null != differThisMonth) {
                result.add(item);
            }
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}