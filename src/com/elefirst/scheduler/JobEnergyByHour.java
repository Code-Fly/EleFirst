package com.elefirst.scheduler;

import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.Const;
import com.elefirst.base.utils.TimeUtil;
import com.elefirst.power.po.DataF105;
import com.elefirst.power.service.iface.IDataF105Service;
import com.elefirst.report.po.ReportEnergyByHour;
import com.elefirst.report.service.iface.IReportEnergyByHourService;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by barrie on 2017/3/13.
 */
@Component
public class JobEnergyByHour {
    @Autowired
    private IDataF105Service dataF105Service;

    @Autowired
    private IPnInfoService pnInfoService;

    @Autowired
    private IReportEnergyByHourService reportEnergyByHourService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void job() {
        PnInfo pnTemplate = new PnInfo();

        // 所有PN
        List<PnInfo> pnInfoList = pnInfoService.getPnInfoList(pnTemplate);

        // 最大遍历时间
        Integer interval = Integer.valueOf(ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_REPORT_MAX_DAY_INTERVAL));

        // 启用标志位
        String enable = ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING, Const.CONFIG_KEY_REPORT_ENERGY_BY_HOUR_ENABLE);

        if("true".equals(enable)){
            // 获取所有节点
            for (int i = 0; i < pnInfoList.size(); i++) {
                // 获取更新间隔
                for (int j = 0; j < interval + 1; j++) {
                    List<DataF105> list = new ArrayList<>();

                    PnInfo item = pnInfoList.get(i);

                    DataF105 node = new DataF105();
                    node.setAreaId(item.getAreaId());
                    node.setConcentratorId(item.getConcentratorId());
                    node.setPn(item.getPn());
                    list.add(node);

                    Date startDate = TimeUtil.getDate(new Date(), 0, 0, 0 - j);
                    Date endDate = TimeUtil.getDate(new Date(), 0, 0, 1 - j);

                    // 获取按小时统计电量
                    List<DataF105> result = dataF105Service.getDataF105ByHourList(list, TimeUtil.formatDbDate(startDate, "yyyyMMdd"), TimeUtil.formatDbDate(endDate, "yyyyMMdd"));

                    ReportEnergyByHour energy = setTemplate(item.getAreaId(), item.getConcentratorId(), item.getPn(), result);

                    // 判断记录是否存在，存在则更新，不存在则添加
                    if (null != energy.getOperationTime() && null != energy.getTotal()) {
                        List<ReportEnergyByHour> delList = reportEnergyByHourService.getReportEnergyByHourList(energy);
                        if (delList.size() > 0) {
                            if (null != energy.getTotal()) {
                                energy.setId(delList.get(0).getId());
                                reportEnergyByHourService.updateReportEnergyByHour(energy);
                            }
                        } else {
                            if (null != energy.getTotal()) {
                                reportEnergyByHourService.addReportEnergyByHour(energy);
                            }
                        }
                    }
                }
            }
        }
    }

    private ReportEnergyByHour setTemplate(String areaId, String concentratorId, String pn, List<DataF105> data) {
        ReportEnergyByHour template = new ReportEnergyByHour();
        template.setId(UUID.randomUUID().toString());
        template.setAreaId(areaId);
        template.setConcentratorId(concentratorId);
        template.setPn(pn);
        template.setUpdateTime(TimeUtil.formatDbDate(new Date(), "yyyyMMddHHmmss"));

        Double total = null;

        for (int i = 0; i < data.size(); i++) {
            if (null != data.get(i).getFrozentime()) {
                if (null != data.get(i).getActivepower()) {
                    if (null == total) {
                        total = 0D;
                    }
                    total += Double.valueOf(data.get(i).getActivepower());
                }
                template.setOperationTime(data.get(i).getFrozentime().substring(0, 8));
                String hour = data.get(i).getFrozentime().substring(8, 10);
                switch (hour) {
                    case "00":
                        template.setP00(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "01":
                        template.setP01(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "02":
                        template.setP02(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "03":
                        template.setP03(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "04":
                        template.setP04(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "05":
                        template.setP05(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "06":
                        template.setP06(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "07":
                        template.setP07(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "08":
                        template.setP08(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "09":
                        template.setP09(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "10":
                        template.setP10(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "11":
                        template.setP11(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "12":
                        template.setP12(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "13":
                        template.setP13(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "14":
                        template.setP14(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "15":
                        template.setP15(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "16":
                        template.setP16(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "17":
                        template.setP17(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "18":
                        template.setP18(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "19":
                        template.setP19(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "20":
                        template.setP20(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "21":
                        template.setP21(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "22":
                        template.setP22(formatDouble(data.get(i).getActivepower()));
                        break;
                    case "23":
                        template.setP23(formatDouble(data.get(i).getActivepower()));
                        break;
                }
            }
        }
        if (null != total) {
            template.setTotal(formatDouble(String.valueOf(total)));
        }

        return template;
    }

    private String formatDouble(String num) {
        if (null != num) {
            return String.valueOf(String.format("%." + 4 + "f", Double.valueOf(num)));
        }
        return null;
    }
}
