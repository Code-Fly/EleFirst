package com.elefirst.login.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.*;
import com.elefirst.power.service.iface.IDataF21Service;
import com.elefirst.power.service.iface.IDataF25FrozenMinuteService;
import com.elefirst.power.service.iface.IDataF33FrozenDayService;
import com.elefirst.power.service.iface.IDataF5Service;
import com.elefirst.system.po.AreaInfoWithBLOBs;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IAreaInfoService;
import com.elefirst.system.service.iface.IPnInfoService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by barrie on 17/2/14.
 */
@Controller
@RequestMapping("/index")
@Api(value = "index", description = "首页")
public class IndexController extends BaseController {
    @Autowired
    private IPnInfoService pnInfoService;

    @Autowired
    private IAreaInfoService areaInfoService;

    @Autowired
    private IDataF5Service dataF5Service;

    @Autowired
    private IDataF21Service dataF21Service;

    @Autowired
    private IDataF33FrozenDayService dataF33FrozenDayService;

    @Autowired
    private IDataF25FrozenMinuteService dataF25FrozenMinuteService;

    @RequestMapping(value = "/summary/info.do")
    @ApiOperation(value = "统计信息", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getSummaryInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "areaId") String areaId
    ) {
        JSONObject result = new JSONObject();
        AreaInfoWithBLOBs areaTemplate = new AreaInfoWithBLOBs();
        areaTemplate.setAreaId(areaId);
        List<AreaInfoWithBLOBs> areas = areaInfoService.getAreaInfoList(areaTemplate);
        if (areas.size() > 0) {
            String sTransformers = areas.get(0).getTransformers();
            if (sTransformers != null && !sTransformers.isEmpty()) {
                JSONArray transformers = JSONArray.fromObject(sTransformers);
                result.put("transformersInfo", sTransformers);
                result.put("transformers", transformers.size());
                double ratedCapacity = 0;
                for (int i = 0; i < transformers.size(); i++) {
                    ratedCapacity += transformers.getJSONObject(i).getDouble("transformerRatedCapacity");
                }
                result.put("ratedCapacity", ratedCapacity);


                PnInfo pnTemplate = new PnInfo();
                pnTemplate.setAreaId(areaId);
                List<PnInfo> pns = pnInfoService.getPnInfoList(pnTemplate);
                result.put("pns", pns.size());


                List<DataF33> nodes = new ArrayList<>();
                String masterPnId = areas.get(0).getMasterPnId();
                result.put("masterPnId", masterPnId);
                DataF33 item = new DataF33();
                List<PnInfo> pn = pnInfoService.getPnInfoDetail(masterPnId);
                if (pn.size() > 0) {
                    item.setAreaId(pn.get(0).getAreaId());
                    item.setConcentratorId(pn.get(0).getConcentratorId());
                    item.setPn(pn.get(0).getPn());
                    nodes.add(item);
                }

                List<DataF5> nodeF5List = new ArrayList<>();
                for (int i = 0; i < nodes.size(); i++) {
                    DataF5 n = new DataF5();
                    n.setAreaId(nodes.get(i).getAreaId());
                    n.setConcentratorId(nodes.get(i).getConcentratorId());
                    n.setPn(nodes.get(i).getPn());
                    nodeF5List.add(n);
                }

                //电量
                //本月
                Calendar thisMonth = Calendar.getInstance();
                Calendar nextMonth = Calendar.getInstance();
                nextMonth.add(Calendar.MONTH, 1);

                String thisMonthStr = new SimpleDateFormat("yyyyMM").format(thisMonth.getTime()) + "01";
                String nextMonthStr = new SimpleDateFormat("yyyyMM").format(nextMonth.getTime()) + "01";

                result.put("electricityThisMonth", getElectricityF5(pns, dataF5Service.getDataF5SumList(nodeF5List, thisMonthStr, nextMonthStr)));


                List<DataF21> nodeF21List = new ArrayList<>();
                for (int i = 0; i < nodes.size(); i++) {
                    DataF21 n = new DataF21();
                    n.setAreaId(nodes.get(i).getAreaId());
                    n.setConcentratorId(nodes.get(i).getConcentratorId());
                    n.setPn(nodes.get(i).getPn());
                    nodeF21List.add(n);
                }

                //上月
                Calendar lastMonth = Calendar.getInstance();
                lastMonth.add(Calendar.MONTH, -1);

                String lastMonthStr = new SimpleDateFormat("yyyyMM").format(thisMonth.getTime());

                result.put("electricityLastMonth", getElectricityF21(pns, dataF21Service.getDataF21SumList(nodeF21List, lastMonthStr, thisMonthStr)));

                //上上月
                Calendar lastLastMonth = Calendar.getInstance();
                lastLastMonth.add(Calendar.MONTH, -2);

                String lastLastMonthStr = new SimpleDateFormat("yyyyMM").format(thisMonth.getTime());

                result.put("electricityLastLastMonth", getElectricityF21(pns, dataF21Service.getDataF21SumList(nodeF21List, lastLastMonthStr, lastMonthStr)));


                //负荷
                //本月
                List<DataF25FrozenMinute> nodeF25FrozenMinuteList = new ArrayList<>();
                for (int i = 0; i < nodes.size(); i++) {
                    DataF25FrozenMinute n = new DataF25FrozenMinute();
                    n.setAreaId(nodes.get(i).getAreaId());
                    n.setConcentratorId(nodes.get(i).getConcentratorId());
                    n.setPn(nodes.get(i).getPn());
                    nodeF25FrozenMinuteList.add(n);
                }

                result.put("maxLoadThisMonth", getTotalMaxLoad(nodeF25FrozenMinuteList, thisMonthStr, nextMonthStr));

                //今年
                Calendar thisYear = Calendar.getInstance();
                String thisYearStr = new SimpleDateFormat("yyyy").format(thisYear.getTime()) + "0101000000";
                Calendar nextYear = Calendar.getInstance();
                nextYear.add(Calendar.YEAR, 1);
                String nextYearStr = new SimpleDateFormat("yyyy").format(nextYear.getTime()) + "0101000000";

                result.put("maxLoadThisYear", getTotalMaxLoad(nodeF25FrozenMinuteList, thisYearStr, nextYearStr));

                //历史
                Calendar firstYear = Calendar.getInstance();
                firstYear.add(Calendar.YEAR, -100);
                String firstYearStr = new SimpleDateFormat("yyyy").format(firstYear.getTime()) + "0101000000";

                result.put("maxLoadTotal", getTotalMaxLoad(nodeF25FrozenMinuteList, firstYearStr, nextMonthStr));

            }
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    @RequestMapping(value = "/load/activepower/total/statistic.do")
    @ApiOperation(value = "统计信息", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getLoadActivepowerStatistic(HttpServletRequest request,
                                                HttpServletResponse response,
                                                @RequestParam(value = "areaId") String areaId
    ) {
        AreaInfoWithBLOBs areaTemplate = new AreaInfoWithBLOBs();
        areaTemplate.setAreaId(areaId);
        List<AreaInfoWithBLOBs> areas = areaInfoService.getAreaInfoList(areaTemplate);

        List<StatisticF25TotalActivePower> result = new ArrayList<>();

        if (areas.size() > 0) {
            String sTransformers = areas.get(0).getTransformers();
            if (sTransformers != null && !sTransformers.isEmpty()) {
                List<DataF25FrozenMinute> nodes = new ArrayList<>();
                String masterPnId = areas.get(0).getMasterPnId();

                DataF25FrozenMinute item = new DataF25FrozenMinute();
                List<PnInfo> pn = pnInfoService.getPnInfoDetail(masterPnId);
                if (pn.size() > 0) {
                    item.setAreaId(pn.get(0).getAreaId());
                    item.setConcentratorId(pn.get(0).getConcentratorId());
                    item.setPn(pn.get(0).getPn());
                    nodes.add(item);
                }

                // 今日
                Calendar today = Calendar.getInstance();
                Calendar tomorrow = Calendar.getInstance();
                tomorrow.add(Calendar.DATE, 1);

                String todayStr = new SimpleDateFormat("yyyyMMdd").format(today.getTime()) + "000000";
                String tomorrowStr = new SimpleDateFormat("yyyyMMdd").format(tomorrow.getTime()) + "000000";

                StatisticF25TotalActivePower s = new StatisticF25TotalActivePower();
                s = dataF25FrozenMinuteService.getF5StatisticTotalActivePower(nodes, todayStr, tomorrowStr);
                result.add(s);

                // 昨日
                Calendar yesterday = Calendar.getInstance();
                yesterday.add(Calendar.DATE, -1);
                String yesterdayStr = new SimpleDateFormat("yyyyMMdd").format(yesterday.getTime()) + "000000";

                s = dataF25FrozenMinuteService.getF5StatisticTotalActivePower(nodes, yesterdayStr, todayStr);
                result.add(s);

            }

        }

        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    public double getElectricity(List<PnInfo> pns, List<DataF33FrozenDay> data) {
        double electricity = 0.0;
        for (int j = 0; j < pns.size(); j++) {
            String areaId = pns.get(j).getAreaId();
            String concentratorId = pns.get(j).getConcentratorId();
            String pn = pns.get(j).getPn();
            Double ct = pns.get(j).getCt();
            Double pt = pns.get(j).getPt();

            Double minVal = 1000000000.0;
            Double maxVal = -1000000000.0;
            int count = 0;

            for (int k = 0; k < data.size(); k++) {
                DataF33FrozenDay item = data.get(k);
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

        return electricity;
    }

    public double getElectricityF5(List<PnInfo> pns, List<DataF5> data) {
        double electricity = 0.0;
        for (int j = 0; j < pns.size(); j++) {
            String areaId = pns.get(j).getAreaId();
            String concentratorId = pns.get(j).getConcentratorId();
            String pn = pns.get(j).getPn();
            Double ct = pns.get(j).getCt();
            Double pt = pns.get(j).getPt();

            Double sum = 0D;
            int count = 0;

            for (int k = 0; k < data.size(); k++) {
                DataF5 item = data.get(k);
                if (item.getAreaId().equals(areaId) && item.getConcentratorId().equals(concentratorId) && item.getPn().equals(pn)) {
                    count++;

                    if (item.getTotalpositiveactivepower() != null) {
                        sum += Double.valueOf(item.getTotalpositiveactivepower());
                    }
                }
            }

            if (count > 0) {
                electricity += sum * ct * pt;
            }

        }

        return electricity;
    }

    public double getElectricityF21(List<PnInfo> pns, List<DataF21> data) {
        double electricity = 0.0;
        for (int j = 0; j < pns.size(); j++) {
            String areaId = pns.get(j).getAreaId();
            String concentratorId = pns.get(j).getConcentratorId();
            String pn = pns.get(j).getPn();
            Double ct = pns.get(j).getCt();
            Double pt = pns.get(j).getPt();

            Double sum = 0D;
            int count = 0;

            for (int k = 0; k < data.size(); k++) {
                DataF21 item = data.get(k);
                if (item.getAreaId().equals(areaId) && item.getConcentratorId().equals(concentratorId) && item.getPn().equals(pn)) {
                    count++;

                    if (item.getTotalpositiveactivepower() != null) {
                        sum += Double.valueOf(item.getTotalpositiveactivepower());
                    }
                }
            }

            if (count > 0) {
                electricity += sum * ct * pt;
            }

        }

        return electricity;
    }

    public String getTotalMaxLoad(List<DataF25FrozenMinute> nodes, String startTime, String endTime) {
        StatisticF25TotalActivePower item = dataF25FrozenMinuteService.getF5StatisticTotalActivePower(nodes, startTime, endTime);

        return item.getMaxTotalActivePower();
    }
}
