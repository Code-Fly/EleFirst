package com.elefirst.scheduler;

import com.elefirst.base.utils.ConfigUtil;
import com.elefirst.base.utils.Const;
import com.elefirst.base.utils.TimeUtil;
import com.elefirst.power.po.DataF105;
import com.elefirst.power.service.iface.IDataF105Service;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.DailyElectricity;
import com.elefirst.powerdetail.po.MonthlyDemandDetail;
import com.elefirst.powerdetail.service.IDailyPowerService;
import com.elefirst.powerdetail.service.IMonthlyPowerService;
import com.elefirst.report.po.ReportDisplayByDaily;
import com.elefirst.report.po.ReportEleByDaily;
import com.elefirst.report.po.ReportEnergyByHour;
import com.elefirst.report.service.IReportDisplayDailyService;
import com.elefirst.report.service.IReportEleDailyService;
import com.elefirst.report.service.iface.IReportEnergyByHourService;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

/**
 * Created by barrie on 2017/3/13.
 */
@Component
public class JobElecByDaily {
	@Autowired
	private IDataF105Service dataF105Service;

	@Autowired
	private IPnInfoService pnInfoService;

	@Resource(name = "dailyPowerServiceImpl")
	private IDailyPowerService dailyPowerServiceImpl;

	@Resource(name = "reportEleDailyServiceImpl")
	private IReportEleDailyService reportEleDailyServiceImpl;

	@Scheduled(cron = "0/50 * * * * ?")
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
					Const.CONFIG_KEY_REPORT_ELEC_BY_DAILY_ENABLE);

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
					List<DailyElectricity> dailyElectricity = dailyPowerServiceImpl
							.fetchAllDailyElectricity(startdate, enddate,
									item.getAreaId(), concentrators, -1, -1,
									item.getPn());

					// 每个监测点检查完后插入处理好的数据到报表表中
					ReportEleByDaily reportEleByDaily = setTemplate(
							item.getAreaId(), item.getConcentratorId(),
							item.getPn(), dailyElectricity);

					// 判断监测点+指定日期报表记录是否存在，存在则更新，不存在则添加
					if (null != reportEleByDaily.getOperationTime()
							&& reportEleByDaily.getAreaId() != null
							&& reportEleByDaily.getConcentratorId() != null
							&& reportEleByDaily.getPn() != null) {
						List<ReportEleByDaily> delList = reportEleDailyServiceImpl.getReportEleByDailyList(reportEleByDaily);
						
						if (delList.size() > 0) {
							reportEleByDaily.setId(delList.get(0)
									.getId());
							reportEleDailyServiceImpl.updateReportEleByDaily(reportEleByDaily);
						} else {
							reportEleDailyServiceImpl.addReportEleByDaily(reportEleByDaily);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ReportEleByDaily setTemplate(String areaId,
			String concentratorId, String pn, List<DailyElectricity> data) {
		ReportEleByDaily template = new ReportEleByDaily();
		template.setId(UUID.randomUUID().toString());
		template.setAreaId(areaId);
		template.setConcentratorId(concentratorId);
		template.setPn(pn);
		template.setUpdateTime(TimeUtil.formatDbDate(new Date(),
				"yyyyMMddHHmmss"));
		Double total = null;
		for (int i = 0; i < data.size(); i++) {
			if (null != data.get(i).getDays()) {
				if (null != data.get(i).getTotalpositiveactivePower()) {
					if (null == total) {
                        total = 0D;
                    }
					total += Double.valueOf(data.get(i).getTotalpositiveactivePower());
					template.setOperationTime(data.get(i).getDays()
							.substring(0, 6));
					String day = data.get(i).getDays().substring(6, 8);
					switch (day) {
					case "01":
						template.setP01(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "02":
						template.setP02(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "03":
						template.setP03(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "04":
						template.setP04(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "05":
						template.setP05(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "06":
						template.setP06(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "07":
						template.setP07(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "08":
						template.setP08(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "09":
						template.setP09(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "10":
						template.setP10(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "11":
						template.setP11(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "12":
						template.setP12(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "13":
						template.setP13(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "14":
						template.setP14(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "15":
						template.setP15(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "16":
						template.setP16(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "17":
						template.setP17(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "18":
						template.setP18(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "19":
						template.setP19(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "20":
						template.setP20(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "21":
						template.setP21(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "22":
						template.setP22(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "23":
						template.setP23(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "24":
						template.setP24(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "25":
						template.setP25(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "26":
						template.setP26(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "27":
						template.setP27(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "28":
						template.setP28(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "29":
						template.setP29(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "30":
						template.setP30(""+data.get(i).getTotalpositiveactivePower());
						break;
					case "31":
						template.setP31(""+data.get(i).getTotalpositiveactivePower());
						break;
					}
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
			return String.valueOf(String.format("%." + 2 + "f",
					Double.valueOf(num)));
		}
		return null;
	}
}
