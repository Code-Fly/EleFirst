package com.elefirst.powerdetail.controller;

import com.alibaba.fastjson.JSON;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.GeneralMessage;
import com.elefirst.base.utils.Arith;
import com.elefirst.powerdetail.po.*;
import com.elefirst.powerdetail.service.IYearlyPowerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/yearlypower/")
public class YearlyPowerController {
	private static final Logger logger = LoggerFactory
			.getLogger(YearlyPowerController.class);

	@Resource(name = "yearlyPowerServiceImpl")
	private IYearlyPowerService yearlyPowerServiceImpl;

	/**
	 * 根据年期查询相关的按年负荷统计数据
	 *
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listYearlyLoad.do")
	public @ResponseBody ErrorMsg queryYearlyLoad(String date, String page,
			String rows, String jasonStr) throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();

		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);

			Area area = JSON.parseObject(jasonStr, Area.class);

			List<Concentrator> concentrators = area.getConcentrators();
			if (concentrators == null || concentrators.size() == 0) {
				return null;
			}
			String areaId = area.getAreaId();

			List<YearlyLoad> yearlyLoads = yearlyPowerServiceImpl
					.fetchAllYearlyLoad(date, areaId, concentrators, rowsNum,
							pageNum, true);
			int total = yearlyPowerServiceImpl.fetchAllYearlyLoad(date, areaId,
					concentrators, rowsNum, pageNum, false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按年负荷统计数据成功！");
			dg.setRows(yearlyLoads);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按年负荷统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}

	/**
	 * 根据日期查询相关的电量统计数据
	 *
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listYearlyElectricity.do")
	public @ResponseBody ErrorMsg queryYearlyElectricity(String date,
			String page, String rows, String jasonStr) throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();

		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);

			Area area = JSON.parseObject(jasonStr, Area.class);

			List<Concentrator> concentrators = area.getConcentrators();
			if (concentrators == null || concentrators.size() == 0) {
				return null;
			}
			String areaId = area.getAreaId();

			List<YearlyElectricity> yearlyElectricity = yearlyPowerServiceImpl
					.fetchAllYearlyElectricity(date, areaId, concentrators,
							rowsNum, pageNum);
			int total = yearlyPowerServiceImpl.fetchAllYearlyElectricityCount(
					date, areaId, concentrators);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按年的电量统计数据成功！");
			dg.setRows(yearlyElectricity);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按年的电量统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}

	@RequestMapping("queryYearlyPower.do")
	public @ResponseBody ErrorMsg queryAllYearlyPower(String tabName,
			String areaId, String concentratorId, String pn, String date)
			throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();
		try {
			// 处理年期
			String dateStr = com.elefirst.base.utils.DateUtil.StringPattern(
					"date", "yyyy", "yyyy");
			if ("负荷".equals(tabName)) {
				setTotalActivePowerDetail(areaId, concentratorId, pn, paramMap,
						dateStr);
			} else if ("电量".equals(tabName)) {
				setElectricityDetail(areaId, concentratorId, pn, paramMap,
						dateStr);
			}
			logger.error("查询年用电信息成功！");
			return new ErrorMsg(Error.SUCCESS, "success", paramMap);
		} catch (Exception e) {
			logger.error("查询年用电信息失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "failed", null);
		}
	}

	private void setTotalActivePowerDetail(String areaId,
			String concentratorId, String pn, Map<String, String> paramMap,
			String date) throws Exception {
		// maxTotalActivePower minTotalActivePower avgTotalActivePower
		YearlyLoad yearlyload = yearlyPowerServiceImpl.fetchSingleYearlyLoad(
				date, areaId, concentratorId, pn);
		// 最大负荷
		paramMap.put("maxTotalActivePower", yearlyload.getMaxactivepower() + "");
		paramMap.put(
				"maxTotalActivePowerTime",
				com.elefirst.base.utils.DateUtil.StringPattern(
						yearlyload.getDays(), "yyyy", "yyyy"));

		// 最小负荷
		paramMap.put("minTotalActivePower", yearlyload.getMinactivepower() + "");
		paramMap.put(
				"minTotalActivePowerTime",
				com.elefirst.base.utils.DateUtil.StringPattern(
						yearlyload.getDays(), "yyyy", "yyyy"));

		// 平均负荷
		paramMap.put("avgTotalActivePower", yearlyload.getAvgactivepower() + "");

		// 峰谷差:最高负荷与最低负荷之差 peak-valley difference
		double peakValleyDifference = Arith.sub(yearlyload.getMaxactivepower(),
				yearlyload.getMinactivepower());
		paramMap.put("peakValleyDifference", "" + peakValleyDifference + "");

		// 峰谷差率:峰谷差与最高负荷的比率
		paramMap.put("peakValleyDifferenceRate", "" + yearlyload.getPeakrate()
				+ "");
		// 负荷率:平均负荷与最高负荷的比率 load factor
		paramMap.put("loadFactorRate", "" + yearlyload.getLoadrate() + "");
		paramMap.put("type", "totalactivepower");
	}

	private void setElectricityDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap, String date)
			throws Exception {
		List<Concentrator> concentrators = new ArrayList<Concentrator>();
		Concentrator singleConcentrator = new Concentrator();
		singleConcentrator.setConcentratorId(concentratorId);
		List<String> pns = new ArrayList<String>();
		pns.add(pn);
		singleConcentrator.setPns(pns);
		concentrators.add(singleConcentrator);
		YearlyElectricity yearlyElectricity = yearlyPowerServiceImpl
				.fetchSingleYearlyElectricity(date, areaId, concentrators, pn);
		paramMap.put("totalpositiveactivePower",
				yearlyElectricity.getTotalpositiveactivePower() + "");
		paramMap.put("rateseq1", yearlyElectricity.getRateseq1() + "");
		paramMap.put("rateseq2", yearlyElectricity.getRateseq2() + "");
		paramMap.put("rateseq3", yearlyElectricity.getRateseq3() + "");
		paramMap.put("rateseq4", yearlyElectricity.getRateseq4() + "");
		paramMap.put("type", "electricity");
	}
}
