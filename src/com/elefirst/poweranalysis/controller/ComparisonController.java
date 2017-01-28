package com.elefirst.poweranalysis.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.poweranalysis.po.*;
import com.elefirst.poweranalysis.service.iface.IPowerAnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/1/28.
 */
@Controller
@RequestMapping("/poweranalysis/comparison")
@Api(value = "poweranalysis", description = "对比分析")
public class ComparisonController extends BaseController {
    @Autowired
    private IPowerAnalysisService powerAnalysisService;

    @RequestMapping(value = "/load/daily/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadDailyChart(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF25> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF25 item = new PowerAnalysisF25();
            item.setAreaId(jNode.getJSONObject(i).getString("areaId"));
            item.setConcentratorId(jNode.getJSONObject(i).getString("concentratorId"));
            item.setPn(jNode.getJSONObject(i).getString("pn"));
            node.add(item);
        }

        List<String> time = new ArrayList<>();
        JSONArray jTime = jParam.getJSONArray("time");
        for (int i = 0; i < jTime.size(); i++) {
            time.add(jTime.getString(i));
        }

//        PowerAnalysisF25 item1 = new PowerAnalysisF25();
//        item1.setAreaId("1");
//        item1.setConcentratorId("417");
//        item1.setPn("1");
//        node.add(item1);
//
//        PowerAnalysisF25 item2 = new PowerAnalysisF25();
//        item2.setAreaId("1");
//        item2.setConcentratorId("417");
//        item2.setPn("2");
//        node.add(item2);
//
//        List<String> time = new ArrayList<>();
//        time.add("20170126000000");
//        time.add("20170127000000");


        Map<String, Object> param = new HashMap();
        param.put("node", node);
        param.put("time", time);

        List<PowerAnalysisLoadDailyChartF25> list = powerAnalysisService.getLoadDailyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/load/daily/table.do")
    @ApiOperation(value = "表格", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadDailyTable(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF25> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF25 item = new PowerAnalysisF25();
            item.setAreaId(jNode.getJSONObject(i).getString("areaId"));
            item.setConcentratorId(jNode.getJSONObject(i).getString("concentratorId"));
            item.setPn(jNode.getJSONObject(i).getString("pn"));
            node.add(item);
        }

        List<String> time = new ArrayList<>();
        JSONArray jTime = jParam.getJSONArray("time");
        for (int i = 0; i < jTime.size(); i++) {
            time.add(jTime.getString(i));
        }

//        PowerAnalysisF25 item1 = new PowerAnalysisF25();
//        item1.setAreaId("1");
//        item1.setConcentratorId("417");
//        item1.setPn("1");
//        node.add(item1);
//
//        PowerAnalysisF25 item2 = new PowerAnalysisF25();
//        item2.setAreaId("1");
//        item2.setConcentratorId("417");
//        item2.setPn("2");
//        node.add(item2);
//
//        time.add("20170126000000");
//        time.add("20170127000000");


        Map<String, Object> param = new HashMap();
        param.put("node", node);
        param.put("time", time);


        List<PowerAnalysisLoadDailyTableF25> list = powerAnalysisService.getLoadDailyTable(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/voltage/daily/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getVoltageDailyChart(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF25> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF25 item = new PowerAnalysisF25();
            item.setAreaId(jNode.getJSONObject(i).getString("areaId"));
            item.setConcentratorId(jNode.getJSONObject(i).getString("concentratorId"));
            item.setPn(jNode.getJSONObject(i).getString("pn"));
            node.add(item);
        }

        List<String> time = new ArrayList<>();
        JSONArray jTime = jParam.getJSONArray("time");
        for (int i = 0; i < jTime.size(); i++) {
            time.add(jTime.getString(i));
        }

        Map<String, Object> param = new HashMap();
        param.put("node", node);
        param.put("time", time);

        List<PowerAnalysisVoltageDailyChartF25> list = powerAnalysisService.getVoltageDailyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/current/daily/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getCurrentDailyChart(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF25> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF25 item = new PowerAnalysisF25();
            item.setAreaId(jNode.getJSONObject(i).getString("areaId"));
            item.setConcentratorId(jNode.getJSONObject(i).getString("concentratorId"));
            item.setPn(jNode.getJSONObject(i).getString("pn"));
            node.add(item);
        }

        List<String> time = new ArrayList<>();
        JSONArray jTime = jParam.getJSONArray("time");
        for (int i = 0; i < jTime.size(); i++) {
            time.add(jTime.getString(i));
        }

        Map<String, Object> param = new HashMap();
        param.put("node", node);
        param.put("time", time);

        List<PowerAnalysisCurrentDailyChartF25> list = powerAnalysisService.getCurrentDailyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/powerFactor/daily/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getPowerFactorDailyChart(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF25> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF25 item = new PowerAnalysisF25();
            item.setAreaId(jNode.getJSONObject(i).getString("areaId"));
            item.setConcentratorId(jNode.getJSONObject(i).getString("concentratorId"));
            item.setPn(jNode.getJSONObject(i).getString("pn"));
            node.add(item);
        }

        List<String> time = new ArrayList<>();
        JSONArray jTime = jParam.getJSONArray("time");
        for (int i = 0; i < jTime.size(); i++) {
            time.add(jTime.getString(i));
        }

        Map<String, Object> param = new HashMap();
        param.put("node", node);
        param.put("time", time);

        List<PowerAnalysisPowerFactorDailyChartF25> list = powerAnalysisService.getPowerFactorDailyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }
}
