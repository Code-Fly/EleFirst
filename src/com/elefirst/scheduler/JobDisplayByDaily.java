package com.elefirst.scheduler;

import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.Const;
import com.elefirst.base.utils.TimeUtil;
import com.elefirst.power.service.iface.IDataF105Service;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.MonthlyDemandDetail;
import com.elefirst.powerdetail.service.IMonthlyPowerService;
import com.elefirst.report.po.ReportDisplayByDaily;
import com.elefirst.report.service.IReportDisplayDailyService;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by barrie on 2017/3/13.
 */
@Component
public class JobDisplayByDaily {
	@Autowired
	private IDataF105Service dataF105Service;

	@Autowired
	private IPnInfoService pnInfoService;

	@Resource(name = "monthlyPowerServiceImpl")
	private IMonthlyPowerService monthlyPowerServiceImpl;

	@Resource(name = "reportDisplayDailyServiceImpl")
	private IReportDisplayDailyService reportDisplayDailyServiceImpl;

	@Scheduled(cron = "0 0 0 * * ?")
    public void job() {
		try {
			PnInfo pnTemplate = new PnInfo();

			// 所有PN
			List<PnInfo> pnInfoList = pnInfoService.getPnInfoList(pnTemplate);

			// 最大天间隔时间
			Integer interval = Integer.valueOf(ConfigUtil.getProperty(
					Const.CONFIG_PATH_SETTING,
					Const.CONFIG_KEY_REPORT_MAX_DAY_INTERVAL));

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			String enddate = format.format(c.getTime());
			System.out.println("当前结束日期：" + enddate);

			c.add(Calendar.DATE, -(interval));
			String startdate = format.format(c.getTime());
			System.out.println("当前开始日期：" + startdate);

			// 启用标志位
			String enable = ConfigUtil.getProperty(Const.CONFIG_PATH_SETTING,
					Const.CONFIG_KEY_REPORT_DISPLAY_BY_DAILY_ENABLE);

			if ("true".equals(enable)) {
				// 获取所有节点
				for (int i = 0; i < pnInfoList.size(); i++) {
					// 获取更新间隔
					PnInfo item = pnInfoList.get(i);
					List<Concentrator> concentrators = new ArrayList<Concentrator>();
					List<String> pns = new ArrayList<String>();
					pns.add(item.getPn());
					Concentrator concentrator = new Concentrator();
					concentrator.setConcentratorId(item.getConcentratorId());
					concentrator.setPns(pns);
					concentrators.add(concentrator);
					// 根据开始，结束日期及地址查询示数信息
					List<MonthlyDemandDetail> dailylyDemandDetails = monthlyPowerServiceImpl
							.fetchAllMonthlyDetailDemand3(startdate, enddate,
									item.getAreaId(), concentrators, -1, -1,
									item.getPn());

					// 每个监测点检查完后插入处理好的数据到报表表中
					ReportDisplayByDaily reportDisplayByDaily = setTemplate(
							item.getAreaId(), item.getConcentratorId(),
							item.getPn(), dailylyDemandDetails);

					// 判断监测点+指定日期报表记录是否存在，存在则更新，不存在则添加
					if (null != reportDisplayByDaily.getOperationTime()
							&& reportDisplayByDaily.getAreaId() != null
							&& reportDisplayByDaily.getConcentratorId() != null
							&& reportDisplayByDaily.getPn() != null) {
						List<ReportDisplayByDaily> delList = reportDisplayDailyServiceImpl.getReportDisplayByDailyList(reportDisplayByDaily);
						
						if (delList.size() > 0) {
							reportDisplayByDaily.setId(delList.get(0)
									.getId());
							reportDisplayDailyServiceImpl.updateReportDisplayByDaily(reportDisplayByDaily);
						} else {
							reportDisplayDailyServiceImpl.addReportDisplayByDaily(reportDisplayByDaily);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ReportDisplayByDaily setTemplate(String areaId,
			String concentratorId, String pn, List<MonthlyDemandDetail> data) {
		ReportDisplayByDaily template = new ReportDisplayByDaily();
		template.setId(UUID.randomUUID().toString());
		template.setAreaId(areaId);
		template.setConcentratorId(concentratorId);
		template.setPn(pn);
		template.setUpdateTime(TimeUtil.formatDbDate(new Date(),
				"yyyyMMddHHmmss"));

		for (int i = 0; i < data.size(); i++) {
			if (null != data.get(i).getDays()) {
				if (null != data.get(i).getTotalpositiveactivePower()) {
					template.setOperationTime(data.get(i).getDays()
							.substring(0, 6));
					String day = data.get(i).getDays().substring(6, 8);
					switch (day) {
					case "01":
						template.setP01(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "02":
						template.setP02(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "03":
						template.setP03(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "04":
						template.setP04(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "05":
						template.setP05(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "06":
						template.setP06(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "07":
						template.setP07(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "08":
						template.setP08(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "09":
						template.setP09(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "10":
						template.setP10(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "11":
						template.setP11(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "12":
						template.setP12(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "13":
						template.setP13(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "14":
						template.setP14(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "15":
						template.setP15(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "16":
						template.setP16(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "17":
						template.setP17(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "18":
						template.setP18(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "19":
						template.setP19(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "20":
						template.setP20(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "21":
						template.setP21(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "22":
						template.setP22(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "23":
						template.setP23(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "24":
						template.setP24(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "25":
						template.setP25(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "26":
						template.setP26(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "27":
						template.setP27(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "28":
						template.setP28(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "29":
						template.setP29(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "30":
						template.setP30(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					case "31":
						template.setP31(formatDouble(data.get(i)
								.getTotalpositiveactivePower()));
						break;
					}
				}
			}
		}
		return template;
	}

	private String formatDouble(String num) {
		if (null != num) {
			return String.valueOf(String.format("%." + 2 + "f",
					Double.valueOf(num)));
		}
		return null;
	}
}
