package com.elefirst.report.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.Page2;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.DataF25FrozenMinuteWithF5;
import com.elefirst.power.service.iface.IDataF25FrozenMinuteService;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 2017/7/23.
 */
@Controller
@RequestMapping("/report/t003")
@Api(value = "data", description = "示数操作")
public class ReportT003Controller extends BaseController {
    @Autowired
    private IDataF25FrozenMinuteService dataF25FrozenMinuteService;

    @Autowired
    private IPnInfoService pnInfoService;


    @RequestMapping(value = "/daily/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDailyList(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "areaId", required = false) String areaId,
                                 @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                 @RequestParam(value = "pn", required = false) String pn,
                                 @RequestParam(value = "startTime", required = false) String startTime,
                                 @RequestParam(value = "endTime", required = false) String endTime,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "rows", required = false) Integer rows
    ) throws Exception {
        DataF25FrozenMinute template = new DataF25FrozenMinute();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        template.setPn(pn);

        PnInfo pnInfoTpl = new PnInfo();
        pnInfoTpl.setAreaId(areaId);
        pnInfoTpl.setConcentratorId(concentratorId);
        pnInfoTpl.setPn(pn);

        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

        List<DataF25FrozenMinuteWithF5> dataF25FrozenMinuteWithF5s = dataF25FrozenMinuteService.getDataF25FrozenMinuteStatisticsWithF5List(template, startTime, endTime);

        List<Map<String, String>> report = new ArrayList<>();

        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);

            DataF25FrozenMinuteWithF5 dataF25FrozenMinuteWithF5 = getDataF25FrozenMinuteWithF5(dataF25FrozenMinuteWithF5s, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn());

            if (null != dataF25FrozenMinuteWithF5) {
                Map<String, String> item = new LinkedHashMap<>();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat oformat = new SimpleDateFormat("yyyy-MM-dd");
                String time = oformat.format(format.parse(dataF25FrozenMinuteWithF5.getClientoperationtime()));
                item.put("监测点", pnInfo.getName());
                item.put("时点", time);
                item.put("最大负荷", dataF25FrozenMinuteWithF5.getMaxtotalActivePower());
                item.put("最小负荷", dataF25FrozenMinuteWithF5.getMintotalActivePower());
                item.put("平均负荷", div(dataF25FrozenMinuteWithF5.getTotalpositiveactivepower(), "24", 1D, 3));
                report.add(item);
            }

        }


        Page2 result = new Page2(report, rows);
        return new ErrorMsg(Error.SUCCESS, "success", result.getPages(page));
    }


    private DataF25FrozenMinuteWithF5 getDataF25FrozenMinuteWithF5(List<DataF25FrozenMinuteWithF5> list, String areaId, String concentratorId, String pn) {
        for (int i = 0; i < list.size(); i++) {
            String itemAreaId = list.get(i).getAreaId();
            String itemConcentratorId = list.get(i).getConcentratorId();
            String itemPn = list.get(i).getPn();
            if (itemAreaId.equals(areaId) && itemConcentratorId.equals(concentratorId) && itemPn.equals(pn)) {
                return list.get(i);
            }
        }
        return null;
    }

    private String calc(String org, Double num, Integer precision) {
        if (null != org) {
            if (null == precision) {
                return String.valueOf(Double.valueOf(org) * num);
            } else {
                BigDecimal n1 = new BigDecimal(Double.valueOf(org));
                BigDecimal n2 = new BigDecimal(num);
                double d = n1.multiply(n2).setScale(precision, RoundingMode.HALF_UP).doubleValue();
                return String.valueOf(d);
            }
        }
        return null;
    }

    private String div(String org, String total, Double num, Integer precision) {
        if (null != org) {
            if (null == precision) {
                BigDecimal n1 = new BigDecimal(Double.valueOf(org));
                BigDecimal n2 = new BigDecimal(Double.valueOf(total));
                BigDecimal n3 = new BigDecimal(Double.valueOf(num));
                double d = n1.divide(n2, 10, RoundingMode.HALF_UP).multiply(n3).doubleValue();
                return String.valueOf(d);
            } else {
                BigDecimal n1 = new BigDecimal(Double.valueOf(org));
                BigDecimal n2 = new BigDecimal(Double.valueOf(total));
                BigDecimal n3 = new BigDecimal(Double.valueOf(num));

                double d = n1.divide(n2, precision, RoundingMode.HALF_UP).multiply(n3).doubleValue();
                return String.valueOf(d);
            }
        }
        return null;
    }
}
