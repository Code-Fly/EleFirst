package com.elefirst.report.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.Page2;
import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.DateUtil;
import com.elefirst.base.utils.ExcelUtil;
import com.elefirst.power.po.DataF5;
import com.elefirst.power.po.DataF5WithRate;
import com.elefirst.power.service.iface.IDataF5Service;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

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
            item.put("总电量|总", calc(total0.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
            item.put("总电量|峰", calc(total1.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
            item.put("总电量|平", calc(total2.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
            item.put("总电量|谷", calc(total3.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
            item.put("总电量|尖", calc(total4.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));

            for (int j = 0; j < days.length; j++) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat oformat = new SimpleDateFormat("MM-dd");
                String title = oformat.format(format.parse(days[j]));

                DataF5WithRate dataF5WithRate = getDataF5WithRate(dataF5WithRates, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn(), days[j]);
                item.put(title + "|总", calc(dataF5WithRate.getTotalpositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 4));
                item.put(title + "|峰", calc(dataF5WithRate.getRate1(), pnInfo.getCt() * pnInfo.getPt(), 4));
                item.put(title + "|平", calc(dataF5WithRate.getRate2(), pnInfo.getCt() * pnInfo.getPt(), 4));
                item.put(title + "|谷", calc(dataF5WithRate.getRate3(), pnInfo.getCt() * pnInfo.getPt(), 4));
                item.put(title + "|尖", calc(dataF5WithRate.getRate4(), pnInfo.getCt() * pnInfo.getPt(), 4));
            }
            report.add(item);
        }

        Page2 result = new Page2(report, rows);
        return new ErrorMsg(Error.SUCCESS, "success", result.getPages(page));
    }

    @RequestMapping(value = "/daily/export.do")
    @ApiOperation(value = "导出", notes = "", httpMethod = "GET")
    @ResponseBody
    public void exportDashboardTemplateList(HttpServletRequest request,
                                            HttpServletResponse response,
                                            @RequestParam(value = "areaId", required = false) String areaId,
                                            @RequestParam(value = "startTime", required = false) String startTime,
                                            @RequestParam(value = "endTime", required = false) String endTime,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "rows", required = false) Integer rows
    ) {
        try {
            String fileName = "report-" + startTime.substring(0, 8) + "-" + endTime.substring(0, 8);
            File tplFile = new File(ConfigUtil.getProperty("settings.properties", "report.tpls.t030daily"));

            // 声明一个工作薄
            Map<String, String> blankFieldMap = new HashMap<>();

            // 5行数据
            List<List<String>> rowList = new ArrayList<>(5);
            // 开始组织每行数据,

            DataF5 template = new DataF5();
            template.setAreaId(areaId);

            PnInfo pnInfoTpl = new PnInfo();
            if (null != areaId) {
                pnInfoTpl.setAreaId(areaId);
            }

            List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

            List<DataF5WithRate> dataF5WithRates = dataF5Service.getDataF5WithRateList(template, startTime.substring(0, 8), endTime.substring(0, 8));

            String[] days = DateUtil.getAllDays(startTime, endTime);

            //生成data
            for (int i = 0; i < pnInfos.size(); i++) {
                PnInfo pnInfo = pnInfos.get(i);
                List<String> item = new ArrayList<>();

                item.add(pnInfo.getName());
                item.add("合计");

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
                item.add(calc(total0.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
                item.add(calc(total1.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
                item.add(calc(total2.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
                item.add(calc(total3.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
                item.add(calc(total4.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));

                for (int j = 0; j < days.length; j++) {
                    DataF5WithRate dataF5WithRate = getDataF5WithRate(dataF5WithRates, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn(), days[j]);

                    item.add(calc(dataF5WithRate.getTotalpositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 4));
                    item.add(calc(dataF5WithRate.getRate1(), pnInfo.getCt() * pnInfo.getPt(), 4));
                    item.add(calc(dataF5WithRate.getRate2(), pnInfo.getCt() * pnInfo.getPt(), 4));
                    item.add(calc(dataF5WithRate.getRate3(), pnInfo.getCt() * pnInfo.getPt(), 4));
                    item.add(calc(dataF5WithRate.getRate4(), pnInfo.getCt() * pnInfo.getPt(), 4));

                }
                rowList.add(item);

            }

            //生成sheet页


            //生成sheet页
            ExcelUtil util = new ExcelUtil(tplFile);
            util.buildHeader(5, 5, days);
            util.buildData(blankFieldMap, 5, rowList);
            Workbook wb = util.getWb();

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();

        } catch (IOException e) {
            logger.error("导出失败(" + Error.IO_EXCEPTION + ")", e);
        } catch (Exception e) {
            logger.error("导出失败(" + Error.UNKNOW_EXCEPTION + ")", e);
        }
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
}
