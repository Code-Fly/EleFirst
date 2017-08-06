package com.elefirst.report.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.ExcelUtil;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.DataF25FrozenMinuteWithF21;
import com.elefirst.power.po.DataF25FrozenMinuteWithF5;
import com.elefirst.power.service.iface.IDataF25FrozenMinuteService;
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
                                 @RequestParam(value = "startTime", required = false) String startTime,
                                 @RequestParam(value = "endTime", required = false) String endTime,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "rows", required = false) Integer rows
    ) throws Exception {
        DataF25FrozenMinute template = new DataF25FrozenMinute();
        template.setAreaId(areaId);

        PnInfo pnInfoTpl = new PnInfo();
        pnInfoTpl.setAreaId(areaId);

        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

        List<DataF25FrozenMinuteWithF5> dataF25FrozenMinuteWithF5s = dataF25FrozenMinuteService.getDataF25FrozenMinuteStatisticsWithF5DailyList(template, startTime, endTime);

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
                item.put("日期", time);
                item.put("最大负荷", dataF25FrozenMinuteWithF5.getMaxtotalActivePower());
                item.put("最小负荷", dataF25FrozenMinuteWithF5.getMintotalActivePower());
                item.put("平均负荷", div(dataF25FrozenMinuteWithF5.getTotalpositiveactivepower(), "24", 1D, 3));

                if (null == dataF25FrozenMinuteWithF5.getMaxtotalActivePower() || 0D == Double.valueOf(dataF25FrozenMinuteWithF5.getMaxtotalActivePower()) || null == dataF25FrozenMinuteWithF5.getMintotalActivePower()) {
                    item.put("峰谷差率", null);
                } else {
                    Double differ = Double.valueOf(dataF25FrozenMinuteWithF5.getMaxtotalActivePower()) - Double.valueOf(dataF25FrozenMinuteWithF5.getMintotalActivePower());
                    item.put("峰谷差率", div(differ.toString(), dataF25FrozenMinuteWithF5.getMaxtotalActivePower(), 100D, 3));
                }

                if (null == dataF25FrozenMinuteWithF5.getMaxtotalActivePower() || 0D == Double.valueOf(dataF25FrozenMinuteWithF5.getMaxtotalActivePower()) || null == dataF25FrozenMinuteWithF5.getMintotalActivePower()) {
                    item.put("负荷率", null);
                } else {
                    String avg = div(dataF25FrozenMinuteWithF5.getTotalpositiveactivepower(), "24", 1D, 3);
                    item.put("负荷率", div(avg, dataF25FrozenMinuteWithF5.getMaxtotalActivePower(), 100D, 3));
                }

                report.add(item);
            }

        }


        return new ErrorMsg(Error.SUCCESS, "success", report);
    }

    @RequestMapping(value = "/daily/export.do")
    @ApiOperation(value = "导出", notes = "", httpMethod = "GET")
    @ResponseBody
    public void exportDailyList(HttpServletRequest request,
                                HttpServletResponse response,
                                @RequestParam(value = "areaId", required = false) String areaId,
                                @RequestParam(value = "startTime", required = false) String startTime,
                                @RequestParam(value = "endTime", required = false) String endTime,
                                @RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "rows", required = false) Integer rows
    ) {
        try {
            String fileName = "report";
            File tplFile = new File(ConfigUtil.getProperty("settings.properties", "report.tpls.t003daily"));

            // 声明一个工作薄
            Map<String, String> blankFieldMap = new HashMap<>();

            // 5行数据
            List<List<String>> rowList = new ArrayList<>(5);
            // 开始组织每行数据,

            DataF25FrozenMinute template = new DataF25FrozenMinute();
            template.setAreaId(areaId);

            PnInfo pnInfoTpl = new PnInfo();
            pnInfoTpl.setAreaId(areaId);

            List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

            List<DataF25FrozenMinuteWithF5> dataF25FrozenMinuteWithF5s = dataF25FrozenMinuteService.getDataF25FrozenMinuteStatisticsWithF5DailyList(template, startTime, endTime);

            for (int i = 0; i < pnInfos.size(); i++) {
                PnInfo pnInfo = pnInfos.get(i);

                DataF25FrozenMinuteWithF5 dataF25FrozenMinuteWithF5 = getDataF25FrozenMinuteWithF5(dataF25FrozenMinuteWithF5s, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn());

                if (null != dataF25FrozenMinuteWithF5) {
                    List<String> item = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                    SimpleDateFormat oformat = new SimpleDateFormat("yyyy-MM-dd");
                    String time = oformat.format(format.parse(dataF25FrozenMinuteWithF5.getClientoperationtime()));
                    item.add(pnInfo.getName());
                    item.add(time);
                    item.add(dataF25FrozenMinuteWithF5.getMaxtotalActivePower());
                    item.add(dataF25FrozenMinuteWithF5.getMintotalActivePower());
                    item.add(div(dataF25FrozenMinuteWithF5.getTotalpositiveactivepower(), "24", 1D, 3));

                    if (null == dataF25FrozenMinuteWithF5.getMaxtotalActivePower() || 0D == Double.valueOf(dataF25FrozenMinuteWithF5.getMaxtotalActivePower()) || null == dataF25FrozenMinuteWithF5.getMintotalActivePower()) {
                        item.add(null);
                    } else {
                        Double differ = Double.valueOf(dataF25FrozenMinuteWithF5.getMaxtotalActivePower()) - Double.valueOf(dataF25FrozenMinuteWithF5.getMintotalActivePower());
                        item.add(div(differ.toString(), dataF25FrozenMinuteWithF5.getMaxtotalActivePower(), 100D, 3));
                    }

                    if (null == dataF25FrozenMinuteWithF5.getMaxtotalActivePower() || 0D == Double.valueOf(dataF25FrozenMinuteWithF5.getMaxtotalActivePower()) || null == dataF25FrozenMinuteWithF5.getMintotalActivePower()) {
                        item.add(null);
                    } else {
                        String avg = div(dataF25FrozenMinuteWithF5.getTotalpositiveactivepower(), "24", 1D, 3);
                        item.add(div(avg, dataF25FrozenMinuteWithF5.getMaxtotalActivePower(), 100D, 3));
                    }

                    rowList.add(item);
                }

            }


            //生成sheet页
            ExcelUtil util = new ExcelUtil(tplFile);
            util.buildData(blankFieldMap, 3, rowList);
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

    @RequestMapping(value = "/monthly/list.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getMonthlyList(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @RequestParam(value = "areaId", required = false) String areaId,
                                   @RequestParam(value = "startTime", required = false) String startTime,
                                   @RequestParam(value = "endTime", required = false) String endTime,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "rows", required = false) Integer rows
    ) throws Exception {
        DataF25FrozenMinute template = new DataF25FrozenMinute();
        template.setAreaId(areaId);

        PnInfo pnInfoTpl = new PnInfo();
        pnInfoTpl.setAreaId(areaId);

        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

        List<DataF25FrozenMinuteWithF21> dataF25FrozenMinuteWithF21s = dataF25FrozenMinuteService.getDataF25FrozenMinuteStatisticsWithF21MonthlyList(template, startTime, endTime);

        List<Map<String, String>> report = new ArrayList<>();

        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);

            DataF25FrozenMinuteWithF21 dataF25FrozenMinuteWithF21 = getDataF25FrozenMinuteWithF21(dataF25FrozenMinuteWithF21s, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn());

            if (null != dataF25FrozenMinuteWithF21) {
                Map<String, String> item = new LinkedHashMap<>();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat oformat = new SimpleDateFormat("yyyy-MM");
                String time = oformat.format(format.parse(dataF25FrozenMinuteWithF21.getClientoperationtime()));
                item.put("监测点", pnInfo.getName());
                item.put("日期", time);
                item.put("最大负荷", dataF25FrozenMinuteWithF21.getMaxtotalActivePower());
                item.put("最小负荷", dataF25FrozenMinuteWithF21.getMintotalActivePower());

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(format.parse(dataF25FrozenMinuteWithF21.getClientoperationtime()));
                Integer hours = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) * 24;

                item.put("平均负荷", div(dataF25FrozenMinuteWithF21.getTotalpositiveactivepower(), hours.toString(), 1D, 3));

                if (null == dataF25FrozenMinuteWithF21.getMaxtotalActivePower() || 0D == Double.valueOf(dataF25FrozenMinuteWithF21.getMaxtotalActivePower()) || null == dataF25FrozenMinuteWithF21.getMintotalActivePower()) {
                    item.put("峰谷差率", null);
                } else {
                    Double differ = Double.valueOf(dataF25FrozenMinuteWithF21.getMaxtotalActivePower()) - Double.valueOf(dataF25FrozenMinuteWithF21.getMintotalActivePower());
                    item.put("峰谷差率", div(differ.toString(), dataF25FrozenMinuteWithF21.getMaxtotalActivePower(), 100D, 3));
                }

                if (null == dataF25FrozenMinuteWithF21.getMaxtotalActivePower() || 0D == Double.valueOf(dataF25FrozenMinuteWithF21.getMaxtotalActivePower()) || null == dataF25FrozenMinuteWithF21.getMintotalActivePower()) {
                    item.put("负荷率", null);
                } else {
                    String avg = div(dataF25FrozenMinuteWithF21.getTotalpositiveactivepower(), hours.toString(), 1D, 3);
                    item.put("负荷率", div(avg, dataF25FrozenMinuteWithF21.getMaxtotalActivePower(), 100D, 3));
                }

                report.add(item);
            }

        }


        return new ErrorMsg(Error.SUCCESS, "success", report);
    }

    @RequestMapping(value = "/monthly/export.do")
    @ApiOperation(value = "导出", notes = "", httpMethod = "GET")
    @ResponseBody
    public void exportMonthlyList(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @RequestParam(value = "areaId", required = false) String areaId,
                                  @RequestParam(value = "startTime", required = false) String startTime,
                                  @RequestParam(value = "endTime", required = false) String endTime,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "rows", required = false) Integer rows
    ) {
        try {
            String fileName = "report";
            File tplFile = new File(ConfigUtil.getProperty("settings.properties", "report.tpls.t003monthly"));

            // 声明一个工作薄
            Map<String, String> blankFieldMap = new HashMap<>();

            // 5行数据
            List<List<String>> rowList = new ArrayList<>(5);
            // 开始组织每行数据,

            DataF25FrozenMinute template = new DataF25FrozenMinute();
            template.setAreaId(areaId);

            PnInfo pnInfoTpl = new PnInfo();
            pnInfoTpl.setAreaId(areaId);

            List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

            List<DataF25FrozenMinuteWithF21> dataF25FrozenMinuteWithF21s = dataF25FrozenMinuteService.getDataF25FrozenMinuteStatisticsWithF21MonthlyList(template, startTime, endTime);

            for (int i = 0; i < pnInfos.size(); i++) {
                PnInfo pnInfo = pnInfos.get(i);

                DataF25FrozenMinuteWithF21 dataF25FrozenMinuteWithF21 = getDataF25FrozenMinuteWithF21(dataF25FrozenMinuteWithF21s, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn());

                if (null != dataF25FrozenMinuteWithF21) {
                    List<String> item = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                    SimpleDateFormat oformat = new SimpleDateFormat("yyyy-MM");
                    String time = oformat.format(format.parse(dataF25FrozenMinuteWithF21.getClientoperationtime()));
                    item.add(pnInfo.getName());
                    item.add(time);
                    item.add(dataF25FrozenMinuteWithF21.getMaxtotalActivePower());
                    item.add(dataF25FrozenMinuteWithF21.getMintotalActivePower());

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(format.parse(dataF25FrozenMinuteWithF21.getClientoperationtime()));
                    Integer hours = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) * 24;

                    item.add(div(dataF25FrozenMinuteWithF21.getTotalpositiveactivepower(), hours.toString(), 1D, 3));

                    if (null == dataF25FrozenMinuteWithF21.getMaxtotalActivePower() || 0D == Double.valueOf(dataF25FrozenMinuteWithF21.getMaxtotalActivePower()) || null == dataF25FrozenMinuteWithF21.getMintotalActivePower()) {
                        item.add(null);
                    } else {
                        Double differ = Double.valueOf(dataF25FrozenMinuteWithF21.getMaxtotalActivePower()) - Double.valueOf(dataF25FrozenMinuteWithF21.getMintotalActivePower());
                        item.add(div(differ.toString(), dataF25FrozenMinuteWithF21.getMaxtotalActivePower(), 100D, 3));
                    }

                    if (null == dataF25FrozenMinuteWithF21.getMaxtotalActivePower() || 0D == Double.valueOf(dataF25FrozenMinuteWithF21.getMaxtotalActivePower()) || null == dataF25FrozenMinuteWithF21.getMintotalActivePower()) {
                        item.add(null);
                    } else {
                        String avg = div(dataF25FrozenMinuteWithF21.getTotalpositiveactivepower(), hours.toString(), 1D, 3);
                        item.add(div(avg, dataF25FrozenMinuteWithF21.getMaxtotalActivePower(), 100D, 3));
                    }

                    rowList.add(item);
                }

            }


            //生成sheet页
            ExcelUtil util = new ExcelUtil(tplFile);
            util.buildData(blankFieldMap, 3, rowList);
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

    private DataF25FrozenMinuteWithF21 getDataF25FrozenMinuteWithF21(List<DataF25FrozenMinuteWithF21> list, String areaId, String concentratorId, String pn) {
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
