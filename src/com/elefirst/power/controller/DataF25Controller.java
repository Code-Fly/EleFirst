package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF25;
import com.elefirst.power.service.iface.IDataF25Service;
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
public class DataF25Controller extends BaseController {
    @Autowired
    private IDataF25Service dataF25Service;

    @RequestMapping(value = "/f25/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25List(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "areaId", required = false) String areaId,
                                   @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF25 template = new DataF25();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF25> result = dataF25Service.getDataF25List(template);

            DataGrid dg = new DataGrid();
            int count = dataF25Service.getDataF25ListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF25> result = dataF25Service.getDataF25List(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/f25/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25ListByNodes(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam(value = "node", required = false) String node,
                                          @RequestParam(value = "startTime", required = false) String startTime,
                                          @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF25> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25>>() {
        }.getType());

        List<DataF25> result = dataF25Service.getDataF25List(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF25Service.format(result));
    }

    @RequestMapping(value = "/f25/node/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25SumByNodes(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestParam(value = "node", required = false) String node,
                                         @RequestParam(value = "startTime", required = false) String startTime,
                                         @RequestParam(value = "endTime", required = false) String endTime
    ) throws ParseException {
        List<DataF25> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25>>() {
        }.getType());

        List<DataF25> item = dataF25Service.getDataF25SumList(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF25Service.format(item));
    }

    @RequestMapping(value = "/f25/node/time/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25SumByNodesAndTime(HttpServletRequest request,
                                                HttpServletResponse response,
                                                @RequestParam(value = "node", required = false) String node,
                                                @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        List<DataF25> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25>>() {
        }.getType());

        List<String> times = new Gson().fromJson(time, new TypeToken<List<String>>() {
        }.getType());

        List<List<DataF25>> result = new ArrayList<>();

        for (int j = 0; j < times.size(); j++) {
            List<DataF25> item = dataF25Service.getDataF25SumList(nodes, times.get(j));
            result.add(dataF25Service.format(item));
        }

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/f25/load/activepower/total/statistic.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25LoadAggregationByNodes(HttpServletRequest request,
                                                     HttpServletResponse response,
                                                     @RequestParam(value = "node", required = false) String node,
                                                     @RequestParam(value = "startTime", required = false) String startTime,
                                                     @RequestParam(value = "endTime", required = false) String endTime
    ) throws ParseException {
        List<DataF25> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25>>() {
        }.getType());

        List<DataF25> maxTotalActivePowerList = dataF25Service.getMaxValue(nodes, startTime, endTime, "totalActivePower");

        DataF25 maxTotalActivePower = new DataF25();
        if (maxTotalActivePowerList.size() > 0) {
            maxTotalActivePower = dataF25Service.format(maxTotalActivePowerList).get(0);
        }

        List<DataF25> minTotalActivePowerList = dataF25Service.getMinValue(nodes, startTime, endTime, "totalActivePower");

        DataF25 minTotalActivePower = new DataF25();
        if (minTotalActivePowerList.size() > 0) {
            minTotalActivePower = dataF25Service.format(minTotalActivePowerList).get(0);
        }

        DataF25 avgTotalActivePower = new DataF25();
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            String avg = String.valueOf(((Double.valueOf(maxTotalActivePower.getTotalactivepower()) + Double.valueOf(minTotalActivePower.getTotalactivepower())) / 2));
            avgTotalActivePower.setTotalactivepower(dataF25Service.calc(avg, 1D, 3));
        }


        JSONObject result = new JSONObject();

        result.put("maxTotalActivePower", maxTotalActivePower.getTotalactivepower());
        result.put("maxTotalActivePowerTime", maxTotalActivePower.getClientoperationtime());

        result.put("minTotalActivePower", minTotalActivePower.getTotalactivepower());
        result.put("minTotalActivePowerTime", minTotalActivePower.getClientoperationtime());

        result.put("avgTotalActivePower", avgTotalActivePower.getTotalactivepower());

        Double differ = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            differ = Double.valueOf(maxTotalActivePower.getTotalactivepower()) - Double.valueOf(minTotalActivePower.getTotalactivepower());
        }

        result.put("differ", dataF25Service.calc(null == differ ? null : String.valueOf(differ), 1D, 3));

        Double differRate = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != minTotalActivePower.getTotalactivepower()) {
            differRate = (differ / Double.valueOf(maxTotalActivePower.getTotalactivepower())) * 100;
        }

        result.put("differRate", dataF25Service.calc(null == differRate ? null : String.valueOf(differRate), 1D, 1));


        Double loadRate = null;
        if (null != maxTotalActivePower.getTotalactivepower() && null != avgTotalActivePower.getTotalactivepower()) {
            loadRate = (Double.valueOf(avgTotalActivePower.getTotalactivepower()) / Double.valueOf(maxTotalActivePower.getTotalactivepower())) * 100;
        }

        result.put("loadRate", dataF25Service.calc(null == loadRate ? null : String.valueOf(loadRate), 1D, 1));

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}