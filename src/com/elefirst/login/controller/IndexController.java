package com.elefirst.login.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.power.po.DataF33;
import com.elefirst.power.service.iface.IDataF33Service;
import com.elefirst.poweranalysis.po.PowerAnalysisLoadMaxF25;
import com.elefirst.poweranalysis.service.iface.IPowerAnalysisService;
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
import java.util.*;

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
    private IDataF33Service dataF33Service;

    @Autowired
    private IPowerAnalysisService powerAnalysisService;

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
                result.put("transformers", transformers.size());
                double ratedCapacity = 0;
                for (int i = 0; i < transformers.size(); i++) {
                    ratedCapacity += transformers.getJSONObject(i).getDouble("ratedCapacity");
                }
                result.put("ratedCapacity", ratedCapacity);


                PnInfo pnTemplate = new PnInfo();
                pnTemplate.setAreaId(areaId);
                List<PnInfo> pns = pnInfoService.getPnInfoList(pnTemplate);
                result.put("pns", pns.size());


                List<DataF33> nodes = new ArrayList<>();
                for (int j = 0; j < transformers.size(); j++) {
                    DataF33 item = new DataF33();
                    List<PnInfo> pn = pnInfoService.getPnInfoDetail(transformers.getJSONObject(j).getString("pnId"));
                    if (pn.size() > 0) {
                        item.setAreaId(pn.get(0).getAreaId());
                        item.setConcentratorId(pn.get(0).getConcentratorId());
                        item.setPn(pn.get(0).getPn());
                        nodes.add(item);
                    }
                }

                // 本月
                Calendar thisMonth = Calendar.getInstance();
                Calendar nextMonth = Calendar.getInstance();
                nextMonth.add(Calendar.MONTH, 1);

                String thisMonthStr = new SimpleDateFormat("yyyyMM").format(thisMonth.getTime()) + "01000000";
                String nextMonthStr = new SimpleDateFormat("yyyyMM").format(nextMonth.getTime()) + "01000000";

                result.put("electricityThisMonth", getElectricity(pns, dataF33Service.getDataF33List(nodes, thisMonthStr, nextMonthStr)));

                //上月
                Calendar lastMonth = Calendar.getInstance();
                lastMonth.add(Calendar.MONTH, -1);

                String lastMonthStr = new SimpleDateFormat("yyyyMM").format(thisMonth.getTime()) + "01000000";

                result.put("electricityLastMonth", getElectricity(pns, dataF33Service.getDataF33List(nodes, lastMonthStr, thisMonthStr)));

                //上上月
                Calendar lastLastMonth = Calendar.getInstance();
                lastLastMonth.add(Calendar.MONTH, -2);

                String lastLastMonthStr = new SimpleDateFormat("yyyyMM").format(thisMonth.getTime()) + "01000000";

                result.put("electricityLastLastMonth", getElectricity(pns, dataF33Service.getDataF33List(nodes, lastLastMonthStr, lastMonthStr)));


                //本月
                Map<String, Object> param = new HashMap();
                param.put("node", nodes);
                param.put("start", thisMonthStr);
                param.put("end", nextMonthStr);

                result.put("maxLoadThisMonth", getTotalMaxLoad(pns, param));

                //今年
                Calendar thisYear = Calendar.getInstance();
                String thisYearStr = new SimpleDateFormat("yyyy").format(thisYear.getTime()) + "0101000000";
                Calendar nextYear = Calendar.getInstance();
                nextYear.add(Calendar.YEAR, 1);
                String nextYearStr = new SimpleDateFormat("yyyy").format(nextYear.getTime()) + "0101000000";

                param = new HashMap();
                param.put("node", nodes);
                param.put("start", thisYearStr);
                param.put("end", nextYearStr);

                result.put("maxLoadThisYear", getTotalMaxLoad(pns, param));

                //历史
                param = new HashMap();
                param.put("node", nodes);
                param.put("start", null);
                param.put("end", null);

                result.put("maxLoadTotal", getTotalMaxLoad(pns, param));

            }
        }


        return new ErrorMsg(Error.SUCCESS, "success", result);
    }

    public double getElectricity(List<PnInfo> pns, List<DataF33> data) {
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
                DataF33 item = data.get(k);
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

    public double getTotalMaxLoad(List<PnInfo> pns, Map<String, Object> param) {
        double totalLoad = 0;

        List<PowerAnalysisLoadMaxF25> list = powerAnalysisService.getLoadMax(param);

        for (int j = 0; j < pns.size(); j++) {
            String areaId = pns.get(j).getAreaId();
            String concentratorId = pns.get(j).getConcentratorId();
            String pn = pns.get(j).getPn();
            Double ct = pns.get(j).getCt();
            Double pt = pns.get(j).getPt();
            for (int i = 0; i < list.size(); i++) {
                PowerAnalysisLoadMaxF25 item = list.get(i);
                if (item.getAreaId().equals(areaId) && item.getConcentratorId().equals(concentratorId) && item.getPn().equals(pn)) {
                    totalLoad += (Double.valueOf(list.get(i).getMaxTotalActivePower()) * ct * pt);
                }
            }
        }
        return totalLoad;
    }
}
