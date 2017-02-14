package com.elefirst.poweranalysis.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF25;
import com.elefirst.power.po.DataF33;
import com.elefirst.power.service.iface.IDataF25Service;
import com.elefirst.power.service.iface.IDataF33Service;
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

    @Autowired
    private IDataF25Service dataF25Service;

    @Autowired
    private IDataF33Service dataF33Service;

    @RequestMapping(value = "/load/all/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadDailyChartAll(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<DataF25> nodes = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            DataF25 item = new DataF25();
            item.setAreaId(jNode.getJSONObject(i).getString("areaId"));
            item.setConcentratorId(jNode.getJSONObject(i).getString("concentratorId"));
            item.setPn(jNode.getJSONObject(i).getString("pn"));
            nodes.add(item);
        }

        JSONObject jTime = jParam.getJSONObject("time");
        String startDate = jTime.getString("start");
        String endDate = jTime.getString("end");
        List<DataF25> list = dataF25Service.getDataF25List(nodes, startDate, endDate);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

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

        Map<String, Object> param = new HashMap();
        param.put("node", node);
        param.put("time", time);

        List<PowerAnalysisLoadDailyChartF25> list = powerAnalysisService.getLoadDailyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/load/daily/sum/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadDailyChartSum(HttpServletRequest request,
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

        List<PowerAnalysisLoadDailyChartSumF25> list = powerAnalysisService.getLoadDailyChartSum(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/load/daily/interval/day/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadDailyChartIntervalDay(HttpServletRequest request,
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

        List<PowerAnalysisLoadDailyChartIntervalDayF25> list = powerAnalysisService.getLoadDailyChartIntervalDay(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/load/weekly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadWeeklyChart(HttpServletRequest request,
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

        List<PowerAnalysisLoadWeeklyChartF25> list = powerAnalysisService.getLoadWeeklyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/load/monthly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadMonthlyChart(HttpServletRequest request,
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

        List<PowerAnalysisLoadMonthlyChartF25> list = powerAnalysisService.getLoadMonthlyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/load/monthly/interval/month/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadMonthlyChartIntervalMonth(HttpServletRequest request,
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

        List<PowerAnalysisLoadMonthlyChartIntervalMonthF25> list = powerAnalysisService.getLoadMonthlyChartIntervalMonth(param);

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

        Map<String, Object> param = new HashMap();
        param.put("node", node);
        param.put("time", time);


        List<PowerAnalysisLoadDailyTableF25> list = powerAnalysisService.getLoadDailyTable(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/load/daily/max.do")
    @ApiOperation(value = "表格", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadMax(HttpServletRequest request,
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

        Map<String, Object> param = new HashMap();
        param.put("node", node);
        param.put("start", jParam.getString("start"));
        param.put("end", jParam.getString("end"));

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

    @RequestMapping(value = "/voltage/weekly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getVoltageWeeklyChart(HttpServletRequest request,
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

        List<PowerAnalysisVoltageWeeklyChartF25> list = powerAnalysisService.getVoltageWeeklyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/voltage/monthly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getVoltageMonthlyChart(HttpServletRequest request,
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

        List<PowerAnalysisVoltageMonthlyChartF25> list = powerAnalysisService.getVoltageMonthlyChart(param);

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

    @RequestMapping(value = "/current/weekly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getCurrentWeeklyChart(HttpServletRequest request,
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

        List<PowerAnalysisCurrentWeeklyChartF25> list = powerAnalysisService.getCurrentWeeklyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/current/monthly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getCurrentMonthlyChart(HttpServletRequest request,
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

        List<PowerAnalysisCurrentMonthlyChartF25> list = powerAnalysisService.getCurrentMonthlyChart(param);

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

    @RequestMapping(value = "/powerFactor/weekly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getPowerFactorWeeklyChart(HttpServletRequest request,
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

        List<PowerAnalysisPowerFactorWeeklyChartF25> list = powerAnalysisService.getPowerFactorWeeklyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/powerFactor/monthly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getPowerFactorMonthlyChart(HttpServletRequest request,
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

        List<PowerAnalysisPowerFactorMonthlyChartF25> list = powerAnalysisService.getPowerFactorMonthlyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/electricity/all/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getElectricityChartAll(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestBody String sData
    ) {
        JSONArray jParam = JSONArray.fromObject(sData);

        JSONArray result = new JSONArray();

        for (int i = 0; i < jParam.size(); i++) {
            JSONObject jItem = jParam.getJSONObject(i);

            JSONArray jNode = jItem.getJSONArray("node");
            String id = jItem.getString("id");
            String name = jItem.getString("name");
            String start = jItem.getString("start");
            String end = jItem.getString("end");

            List<DataF33> nodes = new ArrayList<>();
            for (int j = 0; j < jNode.size(); j++) {
                DataF33 item = new DataF33();
                item.setAreaId(jNode.getJSONObject(j).getString("areaId"));
                item.setConcentratorId(jNode.getJSONObject(j).getString("concentratorId"));
                item.setPn(jNode.getJSONObject(j).getString("pn"));
                nodes.add(item);
            }

            List<DataF33> list = dataF33Service.getDataF33List(nodes, start, end);

            Double electricity = 0.0;
            for (int j = 0; j < jNode.size(); j++) {
                String areaId = jNode.getJSONObject(j).getString("areaId");
                String concentratorId = jNode.getJSONObject(j).getString("concentratorId");
                String pn = jNode.getJSONObject(j).getString("pn");
                Double ct = jNode.getJSONObject(j).getDouble("ct");
                Double pt = jNode.getJSONObject(j).getDouble("pt");

                Double minVal = 1000000000.0;
                Double maxVal = -1.0;
                int count = 0;

                for (int k = 0; k < list.size(); k++) {
                    DataF33 item = list.get(k);
                    if (item.getAreaId().equals(areaId) && item.getConcentratorId().equals(concentratorId) && item.getPn().equals(pn)) {
                        count++;
                        if (item.getTotalpositiveactivepower() != null && Double.valueOf(item.getTotalpositiveactivepower()) < minVal) {
                            minVal = Double.valueOf(item.getTotalpositiveactivepower());
                        }

                        if (item.getTotalpositiveactivepower() != null && Double.valueOf(item.getTotalpositiveactivepower()) > maxVal) {
                            maxVal = Double.valueOf(item.getTotalpositiveactivepower());
                        }
                    }
                }


                if (count > 0) {
                    electricity += (maxVal - minVal) * ct * pt;
                }

            }

            JSONObject resultItem = new JSONObject();
            resultItem.put("id", id);
            resultItem.put("name", name);
            resultItem.put("electricity", electricity);

            result.add(resultItem);

        }

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/electricity/daily/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getElectricityDailyChart(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF33> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF33 item = new PowerAnalysisF33();
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

        List<PowerAnalysisElectricityDailyChartF33> list = powerAnalysisService.getElectricityDailyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/electricity/daily/interval/day/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getElectricityDailyChartIntervalDay(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF33> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF33 item = new PowerAnalysisF33();
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

        List<PowerAnalysisElectricityDailyChartIntervalDayF33> list = powerAnalysisService.getElectricityDailyChartIntervalDay(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/electricity/weekly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getElectricityWeeklyChart(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF33> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF33 item = new PowerAnalysisF33();
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

        List<PowerAnalysisElectricityWeeklyChartF33> list = powerAnalysisService.getElectricityWeeklyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/electricity/monthly/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getElectricityMonthlyChart(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF33> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF33 item = new PowerAnalysisF33();
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

        List<PowerAnalysisElectricityMonthlyChartF33> list = powerAnalysisService.getElectricityMonthlyChart(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/electricity/monthly/interval/month/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getElectricityMonthlyChartIntervalMonth(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF33> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF33 item = new PowerAnalysisF33();
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

        List<PowerAnalysisElectricityMonthlyChartIntervalMonthF33> list = powerAnalysisService.getElectricityMonthlyChartIntervalMonth(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }

    @RequestMapping(value = "/electricity/monthly/rateseq/all/chart.do")
    @ApiOperation(value = "图表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getElectricityMonthlyRateSeqChartAll(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         @RequestBody String sData
    ) {
        JSONObject jParam = JSONObject.fromObject(sData);

        List<PowerAnalysisF33> node = new ArrayList<>();
        JSONArray jNode = jParam.getJSONArray("node");
        for (int i = 0; i < jNode.size(); i++) {
            PowerAnalysisF33 item = new PowerAnalysisF33();
            item.setAreaId(jNode.getJSONObject(i).getString("areaId"));
            item.setConcentratorId(jNode.getJSONObject(i).getString("concentratorId"));
            item.setPn(jNode.getJSONObject(i).getString("pn"));
            node.add(item);
        }

        Map<String, Object> param = new HashMap();
        param.put("node", node);
        param.put("start", jParam.getString("start"));
        param.put("end", jParam.getString("end"));

        List<PowerAnalysisElectricityMonthlyRateSeqChartAllF33> list = powerAnalysisService.getElectricityMonthlyRateSeqChartAll(param);

        return new ErrorMsg(Error.SUCCESS, "success", list);
    }
}
