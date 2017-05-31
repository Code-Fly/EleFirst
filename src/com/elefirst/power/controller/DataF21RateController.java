package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF21Rate;
import com.elefirst.power.service.iface.IDataF21RateService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Controller
@RequestMapping("/power/data")
@Api(value = "data", description = "区域操作")
public class DataF21RateController extends BaseController {
    @Autowired
    private IDataF21RateService dataF21RateService;

    @RequestMapping(value = "/f21/rate/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF21RateList(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestParam(value = "areaId", required = false) String areaId,
                                       @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                       @RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF21Rate template = new DataF21Rate();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF21Rate> result = dataF21RateService.getDataF21RateList(template);

            DataGrid dg = new DataGrid();
            long count = dataF21RateService.getDataF21RateListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF21Rate> result = dataF21RateService.getDataF21RateList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/f21/rate/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF21RateListByNodes(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestParam(value = "node", required = false) String node,
                                              @RequestParam(value = "startTime", required = false) String startTime,
                                              @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF21Rate> nodes = new Gson().fromJson(node, new TypeToken<List<DataF21Rate>>() {
        }.getType());

        List<DataF21Rate> result = dataF21RateService.getDataF21RateList(nodes, startTime, endTime);

        return new ErrorMsg(Error.SUCCESS, "success", dataF21RateService.format(result));
    }

    @RequestMapping(value = "/f21/rate/node/time/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF21RateSum(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(value = "node", required = false) String node,
                                      @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        JSONArray nodes = JSONArray.fromObject(node);

//        List<List<DataF5>> nodes = new Gson().fromJson(time, new TypeToken<List<List<DataF5>>>() {
//        }.getType());

        List<JSONObject> times = new Gson().fromJson(time, new TypeToken<List<JSONObject>>() {
        }.getType());

        List<List<List<DataF21Rate>>> result = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            List<List<DataF21Rate>> rs = new ArrayList<>();

            List<DataF21Rate> ns = new Gson().fromJson(nodes.getString(i), new TypeToken<List<DataF21Rate>>() {
            }.getType());

//            List<DataF5> ns = nodes.get(i);

            for (int j = 0; j < times.size(); j++) {
                List<DataF21Rate> item = dataF21RateService.getDataF21RateSumList(ns, times.get(j).getString("startTime").substring(0, 8), times.get(j).getString("endTime").substring(0, 8));
                rs.add(dataF21RateService.format(item));
            }

            result.add(rs);
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}