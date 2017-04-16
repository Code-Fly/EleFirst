package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF33FrozenDay;
import com.elefirst.power.po.StatisticF33TotalPositiveActivePower;
import com.elefirst.power.service.iface.IDataF33FrozenDayService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class DataF33FrozenDayController extends BaseController {
    @Autowired
    private IDataF33FrozenDayService dataF33FrozenDayService;

    @RequestMapping(value = "/f33/frozen/day/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF33FrozenDayList(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestParam(value = "areaId", required = false) String areaId,
                                            @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                            @RequestParam(value = "pn", required = false) String pn,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF33FrozenDay template = new DataF33FrozenDay();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        template.setPn(pn);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF33FrozenDay> result = dataF33FrozenDayService.getDataF33FrozenDayList(template);

            DataGrid dg = new DataGrid();
            int count = dataF33FrozenDayService.getDataF33FrozenDayListCount(template);
            dg.setTotal(count);
            dg.setRows(dataF33FrozenDayService.format(dataF33FrozenDayService.getInterval(result)));

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF33FrozenDay> result = dataF33FrozenDayService.getDataF33FrozenDayList(template);
            return new ErrorMsg(Error.SUCCESS, "success", dataF33FrozenDayService.format(dataF33FrozenDayService.getInterval(result)));
        }
    }

    @RequestMapping(value = "/f33/frozen/day/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF33FrozenDayListByNodes(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestParam(value = "node", required = false) String node,
                                                   @RequestParam(value = "startTime", required = false) String startTime,
                                                   @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF33FrozenDay> nodes = new Gson().fromJson(node, new TypeToken<List<DataF33FrozenDay>>() {
        }.getType());

        List<DataF33FrozenDay> result = dataF33FrozenDayService.getDataF33FrozenDayList(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF33FrozenDayService.format(dataF33FrozenDayService.getInterval(result)));
    }

    @RequestMapping(value = "/f33/frozen/day/node/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF33FrozenDaySumByNodes(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  @RequestParam(value = "node", required = false) String node,
                                                  @RequestParam(value = "startTime", required = false) String startTime,
                                                  @RequestParam(value = "endTime", required = false) String endTime
    ) throws ParseException {
        List<DataF33FrozenDay> nodes = new Gson().fromJson(node, new TypeToken<List<DataF33FrozenDay>>() {
        }.getType());

        List<DataF33FrozenDay> result = dataF33FrozenDayService.getDataF33FrozenDaySumList(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF33FrozenDayService.format(dataF33FrozenDayService.getInterval(result)));

    }

    @RequestMapping(value = "/f33/frozen/day/electricity/positiveactivepower/total/interval/day/statistic.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteLoadStatisticByNodesIntervalDay(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          @RequestParam(value = "node", required = false) String node,
                                                                          @RequestParam(value = "time", required = false) String time,
                                                                          @RequestParam(value = "interval", required = false) Integer interval
    ) throws ParseException {
        List<DataF33FrozenDay> nodes = new Gson().fromJson(node, new TypeToken<List<DataF33FrozenDay>>() {
        }.getType());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = sdf.parse(time);

        List<StatisticF33TotalPositiveActivePower> result = new ArrayList<>();

        for (int i = 0; i < interval + 1; i++) {
            Date thisMonthStartTimeDate = new Date(date.getTime());
            thisMonthStartTimeDate.setDate(date.getDate() + i);
            Date thisMonthEndTimeDate = new Date(date.getTime());
            thisMonthEndTimeDate.setDate(date.getDate() + i + 2);

            String thisMonthStartTime = sdf.format(thisMonthStartTimeDate);
            String thisMonthEndTime = sdf.format(thisMonthEndTimeDate);

            StatisticF33TotalPositiveActivePower item = new StatisticF33TotalPositiveActivePower();

            item.setClientOperationTime(thisMonthStartTime);

            String differThisMonth = dataF33FrozenDayService.getDifferTotalPositiveActivePower(nodes, thisMonthStartTime, thisMonthEndTime);
            item.setThisMonthTotalPositiveActivePower(dataF33FrozenDayService.calc(differThisMonth, 1D, 0));

            //
            Date lastMonthStartTimeDate = new Date(date.getTime());
            lastMonthStartTimeDate.setDate(date.getDate() + i);
            lastMonthStartTimeDate.setMonth(date.getMonth() - 1);
            Date lastMonthEndTimeDate = new Date(date.getTime());
            lastMonthEndTimeDate.setDate(date.getDate() + i + 2);
            lastMonthEndTimeDate.setMonth(date.getMonth() - 1);

            String lastMonthStartTime = sdf.format(lastMonthStartTimeDate);
            String lastMonthEndTime = sdf.format(lastMonthEndTimeDate);

            String differLastMonth = dataF33FrozenDayService.getDifferTotalPositiveActivePower(nodes, lastMonthStartTime, lastMonthEndTime);
            item.setLastMonthTotalPositiveActivePower(dataF33FrozenDayService.calc(differLastMonth, 1D, 0));

            //
            Date lastYearStartTimeDate = new Date(date.getTime());
            lastYearStartTimeDate.setDate(date.getDate() + i);
            lastYearStartTimeDate.setYear(date.getYear() - 1);
            Date lastYearEndTimeDate = new Date(date.getTime());
            lastYearEndTimeDate.setDate(date.getDate() + i + 2);
            lastYearEndTimeDate.setYear(date.getYear() - 1);

            String lastYearStartTime = sdf.format(lastYearStartTimeDate);
            String lastYearEndTime = sdf.format(lastYearEndTimeDate);

            String differLastYear = dataF33FrozenDayService.getDifferTotalPositiveActivePower(nodes, lastYearStartTime, lastYearEndTime);
            item.setLastYearTotalPositiveActivePower(dataF33FrozenDayService.calc(differLastYear, 1D, 0));


            String rate1 = null;
            if (null != differThisMonth && null != differLastMonth && 0 != Double.valueOf(differLastMonth)) {
                rate1 = String.valueOf(((Double.valueOf(differThisMonth) - Double.valueOf(differLastMonth)) * 100D) / Double.valueOf(differLastMonth));
                rate1 = dataF33FrozenDayService.calc(rate1, 1D, 1);
            }
            item.setRate1(rate1);

            String rate2 = null;
            if (null != differThisMonth && null != differLastYear && 0 != Double.valueOf(differLastYear)) {
                rate2 = String.valueOf(((Double.valueOf(differThisMonth) - Double.valueOf(differLastYear)) * 100D) / Double.valueOf(differLastYear));
                rate2 = dataF33FrozenDayService.calc(rate2, 1D, 1);
            }
            item.setRate1(rate2);

            if (null != differThisMonth) {
                result.add(item);
            }
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}