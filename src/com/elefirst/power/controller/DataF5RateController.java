package com.elefirst.power.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF5Rate;
import com.elefirst.power.service.iface.IDataF5RateService;
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
public class DataF5RateController extends BaseController {
    @Autowired
    private IDataF5RateService dataF5RateService;

    @RequestMapping(value = "/f5/rate/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF5RateList(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(value = "areaId", required = false) String areaId,
                                      @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                      @RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "rows", required = false) Integer rows
    ) {
        DataF5Rate template = new DataF5Rate();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);

        if (null != page && null != rows) {
            template.setPage(page);
            template.setRows(rows);
            List<DataF5Rate> result = dataF5RateService.getDataF5RateList(template);

            DataGrid dg = new DataGrid();
            long count = dataF5RateService.getDataF5RateListCount(template);
            dg.setTotal(count);
            dg.setRows(result);

            return new ErrorMsg(Error.SUCCESS, "success", dg);
        } else {
            List<DataF5Rate> result = dataF5RateService.getDataF5RateList(template);
            return new ErrorMsg(Error.SUCCESS, "success", result);
        }

    }

    @RequestMapping(value = "/f5/rate/node/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF5RateListByNodes(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestParam(value = "node", required = false) String node,
                                             @RequestParam(value = "startTime", required = false) String startTime,
                                             @RequestParam(value = "endTime", required = false) String endTime
    ) {
        List<DataF5Rate> nodes = new Gson().fromJson(node, new TypeToken<List<DataF5Rate>>() {
        }.getType());

        List<DataF5Rate> result = dataF5RateService.getDataF5RateList(nodes, startTime.substring(0, 8), endTime.substring(0, 8));

        return new ErrorMsg(Error.SUCCESS, "success", dataF5RateService.format(result));
    }

    @RequestMapping(value = "/f5/rate/node/time/sum.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDataF5RateSum(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam(value = "node", required = false) String node,
                                     @RequestParam(value = "time", required = false) String time
    ) throws ParseException {
        JSONArray nodes = JSONArray.fromObject(node);

//        List<List<DataF5>> nodes = new Gson().fromJson(time, new TypeToken<List<List<DataF5>>>() {
//        }.getType());

        List<JSONObject> times = new Gson().fromJson(time, new TypeToken<List<JSONObject>>() {
        }.getType());

        List<List<List<DataF5Rate>>> result = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            List<List<DataF5Rate>> rs = new ArrayList<>();

            List<DataF5Rate> ns = new Gson().fromJson(nodes.getString(i), new TypeToken<List<DataF5Rate>>() {
            }.getType());

//            List<DataF5> ns = nodes.get(i);

            for (int j = 0; j < times.size(); j++) {
                List<DataF5Rate> item = dataF5RateService.getDataF5RateSumList(ns, times.get(j).getString("startTime").substring(0, 8), times.get(j).getString("endTime").substring(0, 8));
                rs.add(dataF5RateService.format(item));
            }

            result.add(rs);
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }
}