package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF25FrozenDay;
import com.elefirst.power.service.iface.IDataF25FrozenDayService;
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
public class DataF25FrozenDayController extends BaseController {
    @Autowired
    private IDataF25FrozenDayService dataF25FrozenDayService;

    @RequestMapping(value = "/f25/frozen/day/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenDayList(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestParam(value = "areaId", required = false) String areaId,
                                            @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF25FrozenDay template = new DataF25FrozenDay();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF25FrozenDay> result = dataF25FrozenDayService.getDataF25FrozenDayList(template);

            DataGrid dg = new DataGrid();
            int count = dataF25FrozenDayService.getDataF25FrozenDayListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF25FrozenDay> result = dataF25FrozenDayService.getDataF25FrozenDayList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }
    }

    @RequestMapping(value = "/f25/frozen/day/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenDayListByNodes(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   @RequestParam(value = "node", required = false) String node,
                                                   @RequestParam(value = "startTime", required = false) String startTime,
                                                   @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF25FrozenDay> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenDay>>() {
        }.getType());

        List<DataF25FrozenDay> result = dataF25FrozenDayService.getDataF25FrozenDayList(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/f25/frozen/day/node/time/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF25FrozenDayListByNodesAndTime(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          @RequestParam(value = "node", required = false) String node,
                                                          @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        List<DataF25FrozenDay> nodes = new Gson().fromJson(node, new TypeToken<List<DataF25FrozenDay>>() {
        }.getType());

        List<String> times = new Gson().fromJson(time, new TypeToken<List<String>>() {
        }.getType());

        List<List<DataF25FrozenDay>> result = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < times.size(); j++) {
                List<DataF25FrozenDay> item = dataF25FrozenDayService.getDataF25FrozenDayList(nodes.get(i), times.get(j));
                result.add(item);
            }
        }

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}