package com.elefirst.powerdetail.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.elefirst.base.entity.DataGrid;
import com.elefirst.base.entity.Error;
import com.elefirst.base.entity.ErrorMsg;
import com.elefirst.base.entity.GeneralMessage;
import com.elefirst.base.utils.Arith;
import com.elefirst.powerdetail.po.Area;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.DailyCurrent;
import com.elefirst.powerdetail.po.DailyLoad;
import com.elefirst.powerdetail.po.DailyPowerFactor;
import com.elefirst.powerdetail.po.DailyVoltage;
import com.elefirst.powerdetail.service.IDailyPowerService;

@Controller
@RequestMapping("/dailypower/")
public class DailyPowerController {
private static final Logger logger = LoggerFactory.getLogger(DailyPowerController.class);
	
	@Resource(name = "dailyPowerServiceImpl")
	private IDailyPowerService dailyPowerServiceImpl;
	
	/**
	 * 根据日期查询相关的按日负荷统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listDailyLoad.do")
	public @ResponseBody ErrorMsg queryDailyLoad(String date,String page, String rows,String jasonStr)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		List<String> ctrIds = new ArrayList<String>();
		
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			
			Area area = JSON.parseObject(jasonStr,Area.class);
			
			List<Concentrator> concentrators = area.getConcentrators();
			if(concentrators == null || concentrators.size() == 0){
				return null;
			}
			for (Concentrator concentrator : concentrators) {
				String tmpCId = concentrator.getConcentratorId();
				ctrIds.add(tmpCId);
			}
			String areaId = area.getAreaId();
			
			List<DailyLoad> dailyLoads = dailyPowerServiceImpl.fetchAllDailyLoad(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = dailyPowerServiceImpl.fetchAllDailyLoad(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按日负荷统计数据成功！");
			dg.setRows(dailyLoads);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按日负荷统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据日期查询相关的按电压荷统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listDailyVoltage.do")
	public @ResponseBody ErrorMsg queryDailyVoltage(String date,String page, String rows,String jasonStr)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		List<String> ctrIds = new ArrayList<String>();
		
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			
			Area area = JSON.parseObject(jasonStr,Area.class);
			
			List<Concentrator> concentrators = area.getConcentrators();
			if(concentrators == null || concentrators.size() == 0){
				return null;
			}
			for (Concentrator concentrator : concentrators) {
				String tmpCId = concentrator.getConcentratorId();
				ctrIds.add(tmpCId);
			}
			String areaId = area.getAreaId();
			
			List<DailyVoltage> dailyVoltages = dailyPowerServiceImpl.fetchAllDailyVoltage(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = dailyPowerServiceImpl.fetchAllDailyVoltage(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按日电压统计数据成功！");
			dg.setRows(dailyVoltages);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按电压荷统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据日期查询相关的按电流统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listDailyCurrent.do")
	public @ResponseBody ErrorMsg queryDailyCurrent(String date,String page, String rows,String jasonStr)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		List<String> ctrIds = new ArrayList<String>();
		
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			
			Area area = JSON.parseObject(jasonStr,Area.class);
			
			List<Concentrator> concentrators = area.getConcentrators();
			if(concentrators == null || concentrators.size() == 0){
				return null;
			}
			for (Concentrator concentrator : concentrators) {
				String tmpCId = concentrator.getConcentratorId();
				ctrIds.add(tmpCId);
			}
			String areaId = area.getAreaId();
			
			List<DailyCurrent> dailyCurrents = dailyPowerServiceImpl.fetchAllDailyCurrent(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = dailyPowerServiceImpl.fetchAllDailyCurrent(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按日电流统计数据成功！");
			dg.setRows(dailyCurrents);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按电流统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据日期查询相关的按功率因数统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listDailyPowerFactor.do")
	public @ResponseBody ErrorMsg queryDailyPowerFactor(String date,String page, String rows,String jasonStr)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		List<String> ctrIds = new ArrayList<String>();
		
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			
			Area area = JSON.parseObject(jasonStr,Area.class);
			
			List<Concentrator> concentrators = area.getConcentrators();
			if(concentrators == null || concentrators.size() == 0){
				return null;
			}
			for (Concentrator concentrator : concentrators) {
				String tmpCId = concentrator.getConcentratorId();
				ctrIds.add(tmpCId);
			}
			String areaId = area.getAreaId();
			
			List<DailyPowerFactor> dailyPowerFactors = dailyPowerServiceImpl.fetchAllDailyPowerFactor(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = dailyPowerServiceImpl.fetchAllDailyPowerFactor(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按日功率因数统计数据成功！");
			dg.setRows(dailyPowerFactors);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按功率因数统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	
	
	@RequestMapping("queryDailyPower.do")
	public @ResponseBody ErrorMsg queryAllDailyPower(String tabName,String areaId,String concentratorId,String pn,String date)
			throws Exception {
		Map<String,String> paramMap = new HashMap<String,String>();
		try {
			//处理日期
			String dateStr = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
			if("负荷".equals(tabName)){
				setTotalActivePowerDetail(areaId, concentratorId, pn, paramMap,dateStr);
			}else if("电压".equals(tabName)){
				setVoltageDetail(areaId, concentratorId, pn, paramMap,dateStr);
			}else if("电流".equals(tabName)){
				setCurrentDetail(areaId, concentratorId, pn, paramMap,dateStr);
			}else if("功率因数".equals(tabName)){
				setPowerFactorDetail(areaId, concentratorId, pn, paramMap,dateStr);
			}
			logger.error("查询日用电信息成功！");
			return new ErrorMsg(Error.SUCCESS, "success",paramMap);
		} catch (Exception e) {
			logger.error("查询日用电信息失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "failed", null);
		}
	}
	
	private void setTotalActivePowerDetail(String areaId,
			String concentratorId, String pn, Map<String, String> paramMap,String date)
			throws Exception {
		//maxTotalActivePower minTotalActivePower  avgTotalActivePower
		DailyLoad dailyload = dailyPowerServiceImpl.fetchSingleDailyLoad(date, areaId, concentratorId,pn);
		//最大负荷
		paramMap.put("maxTotalActivePower", dailyload.getMaxactivepower()  +"(kW)");
		paramMap.put("maxTotalActivePowerTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyload.getDays(), "yyyyMMdd", "yyyy-MM-dd"));
		
		//最小负荷
		paramMap.put("minTotalActivePower", dailyload.getMinactivepower()+"(kW)");
		paramMap.put("minTotalActivePowerTime", com.elefirst.base.utils.DateUtil.StringPattern(dailyload.getDays(), "yyyyMMdd", "yyyy-MM-dd"));
		
		//平均负荷
		paramMap.put("avgTotalActivePower", dailyload.getAvgactivepower()+"(kW)");
		
		//峰谷差:最高负荷与最低负荷之差  peak-valley difference
		double peakValleyDifference = Arith.sub(dailyload.getMaxactivepower(), dailyload.getMinactivepower());
		paramMap.put("peakValleyDifference","" + peakValleyDifference +"(kW)");
		
		//峰谷差率:峰谷差与最高负荷的比率
		paramMap.put("peakValleyDifferenceRate","" + dailyload.getPeakrate()+"(%)");
		//负荷率:平均负荷与最高负荷的比率 load factor
		paramMap.put("loadFactorRate","" + dailyload.getLoadrate()+"(%)");
		paramMap.put("type", "totalactivepower");
	}
    
	private void setPowerFactorDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap,String date) throws Exception {
		DailyPowerFactor dailyPowerFactor = dailyPowerServiceImpl.fetchSingleDailyPowerFactor(date, areaId, concentratorId, pn);
		paramMap.put("maxAPowerFactor", dailyPowerFactor.getAmaxpowerfactor()+"(%)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("maxBPowerFactor", dailyPowerFactor.getBmaxpowerfactor()+"(%)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("maxCPowerFactor", dailyPowerFactor.getCmaxpowerfactor()+"(%)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("maxTotalPowerFactor", dailyPowerFactor.getMaxtotalpowerfactor()+"(%)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		
		
		paramMap.put("minAPowerFactor", dailyPowerFactor.getAminpowerfactor()+"(%)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("minBPowerFactor", dailyPowerFactor.getAminpowerfactor()+"(%)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("minCPowerFactor", dailyPowerFactor.getAminpowerfactor()+"(%)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("minTotalPowerFactor", dailyPowerFactor.getMintotalpowerfactor()+"(%)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyPowerFactor.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("type", "powerfactor");
	}
	
	private void setCurrentDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap,String date) throws Exception {
		DailyCurrent dailyCurrent = dailyPowerServiceImpl.fetchSingleDailyCurrent(date, areaId, concentratorId, pn);
		
		paramMap.put("maxACurrent", dailyCurrent.getMaxacurrent()+"(A)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("maxBCurrent", dailyCurrent.getMaxbcurrent()+"(A)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("maxCCurrent", dailyCurrent.getMaxccurrent()+"(A)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("minACurrent", dailyCurrent.getMinacurrent()+"(A)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("minBCurrent", dailyCurrent.getMinbcurrent()+"(A)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("minCCurrent", dailyCurrent.getMinccurrent()+"(A)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyCurrent.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		
		paramMap.put("type", "current");
	}
	
	private void setVoltageDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap,String date) throws Exception {
		DailyVoltage dailyVoltage = dailyPowerServiceImpl.fetchSingleVoltage(date, areaId, concentratorId, pn);
		
		paramMap.put("maxAVoltage", dailyVoltage.getMaxavoltage()+"(V)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("maxBVoltage", dailyVoltage.getMaxbvoltage()+"(V)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("maxCVoltage", dailyVoltage.getMaxcvoltage()+"(V)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("minAVoltage", dailyVoltage.getMinavoltage()+"(V)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("minBVoltage", dailyVoltage.getMinbvoltage()+"(V)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		paramMap.put("minCVoltage", dailyVoltage.getMincvoltage()+"(V)"+"(" + com.elefirst.base.utils.DateUtil.StringPattern(dailyVoltage.getDays(), "yyyyMMdd", "yyyy-MM-dd") +")");
		
		paramMap.put("type", "voltage");
	}
	
}
