package com.elefirst.report.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.ExcelUtil;
import com.elefirst.power.po.DataF161;
import com.elefirst.power.po.DataT031;
import com.elefirst.power.service.iface.IDataF161Service;
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
@RequestMapping("/report/t033")
@Api(value = "data", description = "示数操作")
public class ReportT033Controller extends BaseController {
    @Autowired
    private IDataF161Service dataF161Service;

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
        DataF161 template = new DataF161();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        template.setPn(pn);

        PnInfo pnInfoTpl = new PnInfo();
        if (null != areaId) {
            pnInfoTpl.setAreaId(areaId);
            pnInfoTpl.setConcentratorId(concentratorId);
            pnInfoTpl.setPn(pn);
        }

        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

        List<DataF161> dataF161s = dataF161Service.getDataF161List(template, startTime, endTime);

        List<Map<String, String>> report = new ArrayList<>();

        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);

            for (int j = 1; j < dataF161s.size(); j++) {
                DataF161 lastData = dataF161s.get(j - 1);
                DataF161 currentData = dataF161s.get(j);
                Map<String, String> item = new LinkedHashMap<>();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat oformat = new SimpleDateFormat("MM-dd");
                String time = oformat.format(format.parse(currentData.getClientoperationtime()));
                item.put("时点", time);
                item.put("本次示数", currentData.getTotalpositiveactivepower());
                item.put("上次示数", lastData.getTotalpositiveactivepower());
                String differ = diff(currentData.getTotalpositiveactivepower(), lastData.getTotalpositiveactivepower(), 2);
                item.put("示值差", differ);
                item.put("倍率", String.valueOf(pnInfo.getCt() * pnInfo.getPt()));
                item.put("电量", calc(differ, pnInfo.getCt() * pnInfo.getPt(), 4));


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
                                @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                @RequestParam(value = "pn", required = false) String pn,
                                @RequestParam(value = "startTime", required = false) String startTime,
                                @RequestParam(value = "endTime", required = false) String endTime,
                                @RequestParam(value = "page", required = false) Integer page,
                                @RequestParam(value = "rows", required = false) Integer rows
    ) {
        try {
            String fileName = "report";
            File tplFile = new File(ConfigUtil.getProperty("settings.properties", "report.tpls.t033daily"));

            // 声明一个工作薄
            Map<String, String> blankFieldMap = new HashMap<>();

            // 5行数据
            List<List<String>> rowList = new ArrayList<>(5);
            // 开始组织每行数据,

            DataF161 template = new DataF161();
            template.setAreaId(areaId);
            template.setConcentratorId(concentratorId);
            template.setPn(pn);

            PnInfo pnInfoTpl = new PnInfo();
            if (null != areaId) {
                pnInfoTpl.setAreaId(areaId);
                pnInfoTpl.setConcentratorId(concentratorId);
                pnInfoTpl.setPn(pn);
            }

            List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

            List<DataF161> dataF161s = dataF161Service.getDataF161List(template, startTime, endTime);


            //生成header
            if (pnInfos.size() > 0) {
                List<String> item = new ArrayList<>();
                item.add("时点");
                item.add("本次示数");
                item.add("上次示数");
                item.add("示值差");
                item.add("倍率");
                item.add("电量");

                rowList.add(item);
            }

            //生成data
            for (int i = 0; i < pnInfos.size(); i++) {
                PnInfo pnInfo = pnInfos.get(i);


                for (int j = 1; j < dataF161s.size(); j++) {
                    List<String> item = new ArrayList<>();

                    DataF161 lastData = dataF161s.get(j - 1);
                    DataF161 currentData = dataF161s.get(j);
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                    SimpleDateFormat oformat = new SimpleDateFormat("MM-dd");
                    String time = oformat.format(format.parse(currentData.getClientoperationtime()));
                    item.add(time);
                    item.add(currentData.getTotalpositiveactivepower());
                    item.add(lastData.getTotalpositiveactivepower());
                    String differ = diff(currentData.getTotalpositiveactivepower(), lastData.getTotalpositiveactivepower(), 2);
                    item.add(differ);
                    item.add(String.valueOf(pnInfo.getCt() * pnInfo.getPt()));
                    item.add(calc(differ, pnInfo.getCt() * pnInfo.getPt(), 4));

                    rowList.add(item);
                }
            }

            Double total = 0D;
            Double min = 1000000000.0;
            String minTime = null;
            Double max = -1000000000.0;
            String maxTime = null;

            for (int i = 0; i < pnInfos.size(); i++) {
                PnInfo pnInfo = pnInfos.get(i);

                for (int j = 1; j < dataF161s.size(); j++) {
                    DataF161 lastData = dataF161s.get(j - 1);
                    DataF161 currentData = dataF161s.get(j);

                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                    SimpleDateFormat oformat = new SimpleDateFormat("MM-dd");
                    String time = oformat.format(format.parse(currentData.getClientoperationtime()));
                    String differ = diff(currentData.getTotalpositiveactivepower(), lastData.getTotalpositiveactivepower(), 2);

                    if (null != differ) {
                        if (Double.valueOf(differ) < min) {
                            min = Double.valueOf(differ);
                            minTime = time;
                        }
                        if (Double.valueOf(differ) > max) {
                            max = Double.valueOf(differ);
                            maxTime = time;
                        }
                        total += Double.valueOf(differ);
                    }
                }

                blankFieldMap.put("total", calc(total.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
                if (null == minTime) {
                    blankFieldMap.put("min", null);
                } else {
                    blankFieldMap.put("min", calc(min.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
                }
                blankFieldMap.put("minTime", minTime);

                if (null == minTime) {
                    blankFieldMap.put("max", null);
                } else {
                    blankFieldMap.put("max", calc(max.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
                }
                blankFieldMap.put("maxTime", maxTime);
            }


            //生成sheet页
            ExcelUtil util = new ExcelUtil(tplFile);
            util.buildData(blankFieldMap, 4, rowList);
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

    @RequestMapping(value = "/daily/statistic.do")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    @ResponseBody
    public ErrorMsg getDailyStatistic(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(value = "areaId", required = false) String areaId,
                                      @RequestParam(value = "concentratorId", required = false) String concentratorId,
                                      @RequestParam(value = "pn", required = false) String pn,
                                      @RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime,
                                      @RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "rows", required = false) Integer rows
    ) throws Exception {
        DataF161 template = new DataF161();
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        template.setPn(pn);

        PnInfo pnInfoTpl = new PnInfo();
        if (null != areaId) {
            pnInfoTpl.setAreaId(areaId);
            pnInfoTpl.setConcentratorId(concentratorId);
            pnInfoTpl.setPn(pn);
        }

        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

        List<DataF161> dataF161s = dataF161Service.getDataF161List(template, startTime, endTime);

        Map<String, String> item = new LinkedHashMap<>();

        Double total = 0D;
        Double min = 1000000000.0;
        String minTime = null;
        Double max = -1000000000.0;
        String maxTime = null;

        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);

            for (int j = 1; j < dataF161s.size(); j++) {
                DataF161 lastData = dataF161s.get(j - 1);
                DataF161 currentData = dataF161s.get(j);

                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat oformat = new SimpleDateFormat("MM-dd");
                String time = oformat.format(format.parse(currentData.getClientoperationtime()));
                String differ = diff(currentData.getTotalpositiveactivepower(), lastData.getTotalpositiveactivepower(), 2);

                if (null != differ) {
                    if (Double.valueOf(differ) < min) {
                        min = Double.valueOf(differ);
                        minTime = time;
                    }
                    if (Double.valueOf(differ) > max) {
                        max = Double.valueOf(differ);
                        maxTime = time;
                    }
                    total += Double.valueOf(differ);
                }
            }

            item.put("total", calc(total.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
            if (null == minTime) {
                item.put("min", null);
            } else {
                item.put("min", calc(min.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
            }
            item.put("minTime", minTime);

            if (null == minTime) {
                item.put("max", null);
            } else {
                item.put("max", calc(max.toString(), pnInfo.getCt() * pnInfo.getPt(), 4));
            }
            item.put("maxTime", maxTime);

        }

        return new ErrorMsg(Error.SUCCESS, "success", item);
    }

    private PnInfo getPnInfo(List<PnInfo> pnInfos, PnInfo pnInfo) {
        for (int i = 0; i < pnInfos.size(); i++) {
            if (pnInfos.get(i).getAreaId().equals(pnInfo.getAreaId()) && pnInfos.get(i).getConcentratorId().equals(pnInfo.getConcentratorId()) && pnInfos.get(i).getPn().equals(pnInfo.getPn())) {
                return pnInfos.get(i);
            }
        }

        return null;
    }

    private DataT031 getDataT031(List<DataT031> list, String areaId, String concentratorId, String pn, String date) {
        for (int i = 0; i < list.size(); i++) {
            String itemTime = list.get(i).getClientoperationtime();
            String itemAreaId = list.get(i).getAreaId();
            String itemConcentratorId = list.get(i).getConcentratorId();
            String itemPn = list.get(i).getPn();
            if (itemTime.substring(0, 8).equals(date.substring(0, 8)) && itemAreaId.equals(areaId) && itemConcentratorId.equals(concentratorId) && itemPn.equals(pn)) {
                return list.get(i);
            }
        }
        return new DataT031();
    }

    private String diff(String org1, String org2, Integer precision) {
        if (null != org1 && null != org2) {
            if (null == precision) {
                BigDecimal n1 = new BigDecimal(Double.valueOf(org1));
                BigDecimal n2 = new BigDecimal(Double.valueOf(org2));

                double d = n1.subtract(n2).doubleValue();
                return String.valueOf(d);
            } else {
                BigDecimal n1 = new BigDecimal(Double.valueOf(org1));
                BigDecimal n2 = new BigDecimal(Double.valueOf(org2));

                double d = n1.subtract(n2).setScale(precision, RoundingMode.HALF_UP).doubleValue();
                return String.valueOf(d);
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
}
