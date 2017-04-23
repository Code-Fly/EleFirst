package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.DataF25FrozenMinuteWithF5;
import com.elefirst.power.po.StatisticF25TotalActivePower;
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


        List<JSONObject> times = new Gson().fromJson(time, new TypeToken<List<JSONObject>>() {
        }.getType());

        List<List<DataF25FrozenMinute>> result = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < times.size(); j++) {
                List<DataF25FrozenMinute> singleList = new ArrayList<>();
                singleList.add(nodes.get(i));
                List<DataF25FrozenMinute> item = dataF25FrozenMinuteService.getDataF25FrozenMinuteList(singleList, times.get(j).getString("startTime"), times.get(j).getString("endTime"));
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

        List<JSONObject> times = new Gson().fromJson(time, new TypeToken<List<JSONObject>>() {
        }.getType());

        List<List<DataF25FrozenMinute>> result = new ArrayList<>();

        for (int j = 0; j < times.size(); j++) {
            List<DataF25FrozenMinute> item = dataF25FrozenMinuteService.getDataF25FrozenMinuteSumList(nodes, times.get(j).getString("startTime"), times.get(j).getString("endTime"));
            result.add(dataF25FrozenMinuteService.format(item));
        }

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/f25f5/frozen/minute/node/time/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteSumWithF5ByNodesAndTime(HttpServletRequest request,
                                                                  HttpServletResponse response,
                                                                  @RequestParam(value = "node", required = false) String node,
                                                                  @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        List<DataF25FrozenMinute> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenMinute>>() {
        }.getType());

        List<JSONObject> times = new Gson().fromJson(time, new TypeToken<List<JSONObject>>() {
        }.getType());

        List<List<DataF25FrozenMinuteWithF5>> result = new ArrayList<>();

        for (int j = 0; j < times.size(); j++) {
            List<DataF25FrozenMinuteWithF5> item = dataF25FrozenMinuteService.getDataF25FrozenMinuteSumWithF5List(nodes, times.get(j).getString("startTime"), times.get(j).getString("endTime"));
            result.add(dataF25FrozenMinuteService.formatWithF5(item));
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
        List<DataF25FrozenMinute> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenMinute>>() {
        }.getType());

        StatisticF25TotalActivePower result = dataF25FrozenMinuteService.getStatisticTotalActivePower(nodes, startTime, endTime);
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

        List<DataF25FrozenMinute> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenMinute>>() {
        }.getType());

        List<StatisticF25TotalActivePower> result = new ArrayList<>();

        for (int i = 0; i < times.size(); i++) {
            StatisticF25TotalActivePower item = dataF25FrozenMinuteService.getStatisticTotalActivePower(nodes, times.get(i).getString("startTime"), times.get(i).getString("endTime"));
            if (null != item.getMaxTotalActivePower() && null != item.getMaxTotalActivePowerTime()) {
                result.add(item);
            }
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/f25/frozen/minute/load/activepower/total/statistic/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenMinuteLoadStatisticListByNodes(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   @RequestParam(value = "node", required = false) String node,
                                                                   @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        List<DataF25FrozenMinute> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenMinute>>() {
        }.getType());

        List<JSONObject> times = new Gson().fromJson(time, new TypeToken<List<JSONObject>>() {
        }.getType());

        List<StatisticF25TotalActivePower> result = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < times.size(); j++) {
                List<DataF25FrozenMinute> singleList = new ArrayList<>();
                singleList.add(nodes.get(i));
                StatisticF25TotalActivePower item = dataF25FrozenMinuteService.getStatisticTotalActivePower(singleList, times.get(j).getString("startTime"), times.get(j).getString("endTime"));
                if (null != item.getMaxTotalActivePower() && null != item.getMaxTotalActivePowerTime()) {
                    item.setAreaId(nodes.get(i).getAreaId());
                    item.setConcentratorId(nodes.get(i).getConcentratorId());
                    item.setPn(nodes.get(i).getPn());
                    result.add(item);
                }
            }
        }

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }



}