package com.elefirst.report.controller;

import com.elefirst.base.controller.BaseController;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.Page2;
import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.DateUtil;
import com.elefirst.base.utils.ExportUtil;
import com.elefirst.power.po.DataT031;
import com.elefirst.power.service.iface.IDataT031Service;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by barrie on 2017/7/23.
 */
@Controller
@RequestMapping("/report/t031")
@Api(value = "data", description = "示数操作")
public class ReportT031Controller extends BaseController {
    @Autowired
    private IDataT031Service dataT031Service;

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
                                 @RequestParam(value = "hour", required = false) String hour,
                                 @RequestParam(value = "minute", required = false) String minute,
                                 @RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "rows", required = false) Integer rows
    ) throws Exception {
        DataT031 template = new DataT031();
        template.setAreaId(areaId);

        PnInfo pnInfoTpl = new PnInfo();
        if (null != areaId) {
            pnInfoTpl.setAreaId(areaId);
        }

        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

        List<DataT031> dataT031s = dataT031Service.getDataT031List(template, startTime, endTime, hour, minute);

        String[] days = DateUtil.getAllDays(startTime, endTime);

        List<Map<String, String>> report = new ArrayList<>();

        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);
            Map<String, String> item = new LinkedHashMap<>();
            item.put("监测点", pnInfo.getName());

            for (int j = 0; j < days.length; j++) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat oformat = new SimpleDateFormat("MM-dd");
                String title = oformat.format(format.parse(days[j]));

                DataT031 dataT031 = getDataT031(dataT031s, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn(), days[j]);
                item.put(title, dataT031.getTotalpositiveactivepower());
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
                                            @RequestParam(value = "hour", required = false) String hour,
                                            @RequestParam(value = "minute", required = false) String minute,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "rows", required = false) Integer rows
    ) {
        try {
            String fileName = "report-" + startTime.substring(0, 8) + "-" + endTime.substring(0, 8) + "-" + hour + "-" + minute;
            File tplFile = new File(ConfigUtil.getProperty("settings.properties", "report.tpls.t031daily"));

            // 声明一个工作薄
            Map<String, String> blankFieldMap = new HashMap<>();

            // 5行数据
            List<List<String>> rowList = new ArrayList<>(5);
            // 开始组织每行数据,

            DataT031 template = new DataT031();
            template.setAreaId(areaId);

            PnInfo pnInfoTpl = new PnInfo();
            if (null != areaId) {
                pnInfoTpl.setAreaId(areaId);
            }

            List<PnInfo> pnInfos = pnInfoService.getPnInfoList(pnInfoTpl);

            List<DataT031> dataT031s = dataT031Service.getDataT031List(template, startTime, endTime, hour, minute);

            String[] days = DateUtil.getAllDays(startTime, endTime);

            //生成header
            if (pnInfos.size() > 0) {
                PnInfo pnInfo = pnInfos.get(0);
                List<String> item = new ArrayList<>();

                item.add("监测点");

                for (int j = 0; j < days.length; j++) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                    SimpleDateFormat oformat = new SimpleDateFormat("MM-dd");
                    String title = oformat.format(format.parse(days[j]));

                    item.add(title);
                }
                rowList.add(item);
            }

            //生成data
            for (int i = 0; i < pnInfos.size(); i++) {
                PnInfo pnInfo = pnInfos.get(i);
                List<String> item = new ArrayList<>();

                item.add(pnInfo.getName());

                for (int j = 0; j < days.length; j++) {
                    DataT031 dataT031 = getDataT031(dataT031s, pnInfo.getAreaId(), pnInfo.getConcentratorId(), pnInfo.getPn(), days[j]);
                    item.add(dataT031.getTotalpositiveactivepower());
                }
                rowList.add(item);
            }

            //生成sheet页
            Workbook wb = ExportUtil.doExport(tplFile, blankFieldMap, 1, rowList);

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
}
