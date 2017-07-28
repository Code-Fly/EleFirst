package com.elefirst.report.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.Page2;
import com.elefirst.base.utils.DateUtil;
import com.elefirst.power.po.DataF5;
import com.elefirst.power.po.DataF5WithRate;
import com.elefirst.power.service.iface.IDataF5Service;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 2017/7/23.
 */
@Controller
@RequestMapping("/report/t030")
@Api(value = "data", description = "示数操作")
public class ReportT030Controller extends BaseController {
    @Autowired
    private IDataF5Service dataF5Service;

    @Autowired
    private IPnInfoService pnInfoService;

    @RequestMapping(value = "/daily/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDailyList(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "areaId", required = false) String areaId,
                                 @RequestParam(value = "startTime", required = false) String startTime,
                                 @RequestParam(value = "endTime", required = false) String endTime,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "rows", required = false) Integer rows
    ) throws Exception {
        DataF5 template = new DataF5();
        template.setAreaId(areaId);

        PnInfo pnInfoTpl = new PnInfo();
        if (null != areaId) {
            pnInfoTpl.setAreaId(areaId);
        }

        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

        List<DataF5WithRate> dataF5WithRates = dataF5Service.getDataF5WithRateList(template, startTime.substring(0, 8), endTime.substring(0, 8));

        String[] days = DateUtil.getAllDays(startTime, endTime);

        List<Map<String, String>> report = new ArrayList<>();

        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);
            Map<String, String> item = new LinkedHashMap<>();
            item.put("监测点", pnInfo.getName());
            item.put("时段", "合计");

            Double total0 = 0D;
            Double total1 = 0D;
            Double total2 = 0D;
            Double total3 = 0D;
            Double total4 = 0D;

            for (int j = 0; j < days.length; j++) {

                DataF5WithRate dataF5WithRate = getDataF5WithRate(dataF5WithRates, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn(), days[j]);
                if (null != dataF5WithRate.getTotalpositiveactivepower()) {
                    total0 += Double.valueOf(dataF5WithRate.getTotalpositiveactivepower());
                }
                if (null != dataF5WithRate.getRate1()) {
                    total1 += Double.valueOf(dataF5WithRate.getRate1());
                }
                if (null != dataF5WithRate.getRate2()) {
                    total2 += Double.valueOf(dataF5WithRate.getRate2());
                }
                if (null != dataF5WithRate.getRate3()) {
                    total3 += Double.valueOf(dataF5WithRate.getRate3());
                }
                if (null != dataF5WithRate.getRate4()) {
                    total4 += Double.valueOf(dataF5WithRate.getRate4());
                }
            }
            item.put("total-0", total0.toString());
            item.put("total-1", total1.toString());
            item.put("total-2", total2.toString());
            item.put("total-3", total3.toString());
            item.put("total-4", total4.toString());
            report.add(item);

            for (int j = 0; j < days.length; j++) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat oformat = new SimpleDateFormat("MM-dd");
                String title = oformat.format(format.parse(days[j]));

                DataF5WithRate dataF5WithRate = getDataF5WithRate(dataF5WithRates, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn(), days[j]);
                item.put(title + "-0", dataF5WithRate.getTotalpositiveactivepower());
                item.put(title + "-1", dataF5WithRate.getRate1());
                item.put(title + "-2", dataF5WithRate.getRate2());
                item.put(title + "-3", dataF5WithRate.getRate3());
                item.put(title + "-4", dataF5WithRate.getRate4());
            }
            report.add(item);
        }

        Page2 result = new Page2(report, rows);
        return new ErrorMsg(Error.SUCCESS, "success", result.getPages(page));
    }

    private DataF5WithRate getDataF5WithRate(List<DataF5WithRate> list, String areaId, String concentratorId, String pn, String date) {
        for (int i = 0; i < list.size(); i++) {
            String itemTime = list.get(i).getFrozenDay();
            String itemAreaId = list.get(i).getAreaId();
            String itemConcentratorId = list.get(i).getConcentratorId();
            String itemPn = list.get(i).getPn();
            if (itemTime.substring(0, 8).equals(date.substring(0, 8)) && itemAreaId.equals(areaId) && itemConcentratorId.equals(concentratorId) && itemPn.equals(pn)) {
                return list.get(i);
            }
        }
        return new DataF5WithRate();
    }
}
