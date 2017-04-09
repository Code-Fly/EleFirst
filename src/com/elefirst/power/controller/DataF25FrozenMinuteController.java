package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.StatisticTotalActivePower;
import com.elefirst.power.service.iface.IDataF25FrozenMinuteService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Controller
@RequestMapping("/power/data")
@Api(value = "data", description = "区域操作")
public class DataF25FrozenMinuteController extends BaseController {
    @Autowired
    private IDataF25FrozenMinuteService dataF25FrozenMinuteService;

    @RequestMapping(value = "/f25/frozen/minute/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteList(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestParam(value = "areaId", required = false) String areaId,
                                               @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                               @RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF25FrozenMinute template = new DataF25FrozenMinute();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF25FrozenMinute> result = dataF25FrozenMinuteService.getDataF25FrozenMinuteList(template);

            DataGrid dg = new DataGrid();
            int count = dataF25FrozenMinuteService.getDataF25FrozenMinuteListCount(template);
            dg.setTotal(count);
            dg.setRows(dataF25FrozenMinuteService.format(result));

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF25FrozenMinute> result = dataF25FrozenMinuteService.getDataF25FrozenMinuteList(template);
            return new ErrorMsg(Error.SUCCESS, "success", dataF25FrozenMinuteService.format(result));
        }
    }

    @RequestMapping(value = "/f25/frozen/minute/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteListByNodes(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      @RequestParam(value = "node", required = false) String node,
                                                      @RequestParam(value = "startTime", required = false) String startTime,
                                                      @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF25FrozenMinute> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenMinute>>() {
        }.getType());

        List<DataF25FrozenMinute> result = dataF25FrozenMinuteService.getDataF25FrozenMinuteList(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF25FrozenMinuteService.format(result));
    }

    @RequestMapping(value = "/f25/frozen/minute/node/time/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteListByNodesAndTime(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             @RequestParam(value = "node", required = false) String node,
                                                             @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        List<DataF25FrozenMinute> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenMinute>>() {
        }.getType());

        List<String> times = new Gson().fromJson(time, new TypeToken<List<String>>() {
        }.getType());

        List<List<DataF25FrozenMinute>> result = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < times.size(); j++) {
                List<DataF25FrozenMinute> item = dataF25FrozenMinuteService.getDataF25FrozenMinuteList(nodes.get(i), times.get(j));
                result.add(dataF25FrozenMinuteService.format(item));
            }
        }

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/f25/frozen/minute/node/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteSumByNodes(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @RequestParam(value = "node", required = false) String node,
                                                     @RequestParam(value = "startTime", required = false) String startTime,
                                                     @RequestParam(value = "endTime", required = false) String endTime
    ) throws ParseException {
        List<DataF25FrozenMinute> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenMinute>>() {
        }.getType());


        List<DataF25FrozenMinute> item = dataF25FrozenMinuteService.getDataF25FrozenMinuteSumList(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF25FrozenMinuteService.format(item));
    }

    @RequestMapping(value = "/f25/frozen/minute/node/time/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteSumByNodesAndTime(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            @RequestParam(value = "node", required = false) String node,
                                                            @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        List<DataF25FrozenMinute> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenMinute>>() {
        }.getType());

        List<String> times = new Gson().fromJson(time, new TypeToken<List<String>>() {
        }.getType());

        List<List<DataF25FrozenMinute>> result = new ArrayList<>();

        for (int j = 0; j < times.size(); j++) {
            List<DataF25FrozenMinute> item = dataF25FrozenMinuteService.getDataF25FrozenMinuteSumList(nodes, times.get(j));
            result.add(dataF25FrozenMinuteService.format(item));
        }

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/f25/frozen/minute/load/activepower/total/statistic.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteLoadStatisticByNodes(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               @RequestParam(value = "node", required = false) String node,
                                                               @RequestParam(value = "startTime", required = false) String startTime,
                                                               @RequestParam(value = "endTime", required = false) String endTime
    ) throws ParseException {
        StatisticTotalActivePower result = getStatisticTotalActivePower(node, startTime, endTime);
        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/f25/frozen/minute/load/activepower/total/statistic/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteLoadStatisticListByTime(HttpServletRequest request,
                                                                  HttpServletResponse response,
                                                                  @RequestParam(value = "node", required = false) String node,
                                                                  @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        List<JSONObject> times = new Gson().fromJson(time, new TypeToken<List<JSONObject>>() {
        }.getType());

        List<StatisticTotalActivePower> result = new ArrayList<>();

        for (int i = 0; i < times.size(); i++) {
            StatisticTotalActivePower item = getStatisticTotalActivePower(node, times.get(i).getString("startTime"), times.get(i).getString("endTime"));
            if (null != item.getMaxTotalActivePower() && null != item.getMaxTotalActivePowerTime()) {
                result.add(item);
            }
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }


    private StatisticTotalActivePower getStatisticTotalActivePower(String node, String startTime, String endTime) {

        List<DataF25FrozenMinute> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenMinute>>() {
        }.getType());

        List<DataF25FrozenMinute> maxTotalActivePowerList = dataF25FrozenMinuteService.getMaxValue(nodes, startTime, endTime, "totalActivePower");

        DataF25FrozenMinute maxTotalActivePower = new DataF25FrozenMinute();
        if (maxTotalActivePowerList.size() > 0) {
            maxTotalActivePower = dataF25FrozenMinuteService.format(maxTotalActivePowerList).get(0);
        }

        List<DataF25FrozenMinute> minTotalActivePowerList = dataF25FrozenMinuteService.getMinValue(nodes, startTime, endTime, "totalActivePower");

        DataF25FrozenMinute minTotalActivePower = new DataF25FrozenMinute();
        if (minTotalActivePowerList.size() > 0) {
            minTotalActivePower = dataF25FrozenMinuteService.format(minTotalActivePowerList).get(0);
        }

        DataF25FrozenMinute avgTotalActivePower = new DataF25FrozenMinute();
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            String avg = String.valueOf(((Double.valueOf(maxTotalActivePower.getTotalactivepower()) + Double.valueOf(minTotalActivePower.getTotalactivepower())) / 2));
            avgTotalActivePower.setTotalactivepower(dataF25FrozenMinuteService.calc(avg, 1D, 3));
        }


        StatisticTotalActivePower result = new StatisticTotalActivePower();
        result.setMaxTotalActivePower(maxTotalActivePower.getTotalactivepower());
        result.setMaxTotalActivePowerTime(maxTotalActivePower.getClientoperationtime());
        result.setMinTotalActivePower(minTotalActivePower.getTotalactivepower());
        result.setMinTotalActivePowerTime(minTotalActivePower.getClientoperationtime());
        result.setAvgTotalActivePower(avgTotalActivePower.getTotalactivepower());

        Double differ = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            differ = Double.valueOf(maxTotalActivePower.getTotalactivepower()) - Double.valueOf(minTotalActivePower.getTotalactivepower());
        }

        result.setDiffer(dataF25FrozenMinuteService.calc(null == differ ? null : String.valueOf(differ), 1D, 3));

        Double differRate = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            differRate = (differ / Double.valueOf(maxTotalActivePower.getTotalactivepower())) * 100;
        }

        result.setDifferRate(dataF25FrozenMinuteService.calc(null == differRate ? null : String.valueOf(differRate), 1D, 1));


        Double loadRate = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != avgTotalActivePower.getTotalactivepower()) {
            loadRate = (Double.valueOf(avgTotalActivePower.getTotalactivepower()) / Double.valueOf(maxTotalActivePower.getTotalactivepower())) * 100;
        }

        result.setLoadRate(dataF25FrozenMinuteService.calc(null == loadRate ? null : String.valueOf(loadRate), 1D, 1));

        return result;
    }
}