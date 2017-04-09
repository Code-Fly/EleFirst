package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.service.iface.IDataF25FrozenMinuteService;
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
}