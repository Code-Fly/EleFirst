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
}
