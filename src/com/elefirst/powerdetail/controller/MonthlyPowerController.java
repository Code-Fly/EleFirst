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
import com.elefirst.powerdetail.po.MonthlyCurrent;
import com.elefirst.powerdetail.po.MonthlyDemand;
import com.elefirst.powerdetail.po.MonthlyDemandDetail;
import com.elefirst.powerdetail.po.MonthlyLoad;
import com.elefirst.powerdetail.po.MonthlyPowerFactor;
import com.elefirst.powerdetail.po.MonthlyVoltage;
import com.elefirst.powerdetail.service.IMonthlyPowerService;

@Controller
@RequestMapping("/monthlypower/")
public class MonthlyPowerController {
private static final Logger logger = LoggerFactory.getLogger(MonthlyPowerController.class);
	
	@Resource(name = "monthlyPowerServiceImpl")
	private IMonthlyPowerService monthlyPowerServiceImpl;
	
	/**
	 * 根据月期查询相关的按月负荷统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listMonthlyLoad.do")
	public @ResponseBody ErrorMsg queryMonthlyLoad(String date,String page, String rows,String jasonStr)
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
			
			List<MonthlyLoad> monthlyLoads = monthlyPowerServiceImpl.fetchAllMonthlyLoad(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = monthlyPowerServiceImpl.fetchAllMonthlyLoad(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按月负荷统计数据成功！");
			dg.setRows(monthlyLoads);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按月负荷统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据月期查询相关的按电压荷统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listMonthlyVoltage.do")
	public @ResponseBody ErrorMsg queryMonthlyVoltage(String date,String page, String rows,String jasonStr)
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
			
			List<MonthlyVoltage> monthlyVoltages = monthlyPowerServiceImpl.fetchAllMonthlyVoltage(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = monthlyPowerServiceImpl.fetchAllMonthlyVoltage(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按月电压统计数据成功！");
			dg.setRows(monthlyVoltages);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按电压荷统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据月期查询相关的按电流统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listMonthlyCurrent.do")
	public @ResponseBody ErrorMsg queryMonthlyCurrent(String date,String page, String rows,String jasonStr)
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
			
			List<MonthlyCurrent> monthlyCurrents = monthlyPowerServiceImpl.fetchAllMonthlyCurrent(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = monthlyPowerServiceImpl.fetchAllMonthlyCurrent(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按月电流统计数据成功！");
			dg.setRows(monthlyCurrents);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按电流统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据月期查询相关的按功率因数统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listMonthlyPowerFactor.do")
	public @ResponseBody ErrorMsg queryMonthlyPowerFactor(String date,String page, String rows,String jasonStr)
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
			
			List<MonthlyPowerFactor> monthlyPowerFactors = monthlyPowerServiceImpl.fetchAllMonthlyPowerFactor(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = monthlyPowerServiceImpl.fetchAllMonthlyPowerFactor(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按月功率因数统计数据成功！");
			dg.setRows(monthlyPowerFactors);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按功率因数统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	@RequestMapping("queryMonthlyPower.do")
	public @ResponseBody ErrorMsg queryAllMonthlyPower(String tabName,String areaId,String concentratorId,String pn,String date)
			throws Exception {
		Map<String,String> paramMap = new HashMap<String,String>();
		try {
			//处理月期
			String dateStr = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
			if("负荷".equals(tabName)){
				setTotalActivePowerDetail(areaId, concentratorId, pn, paramMap,dateStr);
			}else if("电压".equals(tabName)){
				setVoltageDetail(areaId, concentratorId, pn, paramMap,dateStr);
			}else if("电流".equals(tabName)){
				setCurrentDetail(areaId, concentratorId, pn, paramMap,dateStr);
			}else if("功率因数".equals(tabName)){
				setPowerFactorDetail(areaId, concentratorId, pn, paramMap,dateStr);
			}
			logger.error("查询月用电信息成功！");
			return new ErrorMsg(Error.SUCCESS, "success",paramMap);
		} catch (Exception e) {
			logger.error("查询月用电信息失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "failed", null);
		}
	}
	
	

	/**
	 * 根据日期查询相关的按需量荷统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listMonthlyDemand.do")
	public @ResponseBody ErrorMsg queryMonthlyemand(String date,String page, String rows,String jasonStr)
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
			
			List<MonthlyDemand> monthlyDemands = monthlyPowerServiceImpl.fetchAllDailyDemand(date, areaId, ctrIds, rowsNum, pageNum);
			int total = monthlyPowerServiceImpl.fetchAllMonthlyDemandCount(date, areaId, ctrIds);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按月需量统计数据成功！");
			dg.setRows(monthlyDemands);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按需量统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据日期查询相关的按日需量荷统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listDailylyDetailDemand.do")
	public @ResponseBody ErrorMsg queryDailylyDemandDetail(String date,String page, String rows,String areaId,String concentratorId,String pn)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			List<String> ctrIds = new ArrayList<String>();
			ctrIds.add(concentratorId);
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
			
			List<MonthlyDemandDetail> monthlyDemandDetails = monthlyPowerServiceImpl.fetchAllMonthlyDetailDemand(vdate, areaId, ctrIds, rowsNum, pageNum,pn);
			int total = monthlyPowerServiceImpl.fetchAllDailyDetailDemandCount(vdate, areaId, ctrIds,pn);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按日需量统计数据成功！");
			dg.setRows(monthlyDemandDetails);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按需量统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	private void setTotalActivePowerDetail(String areaId,
			String concentratorId, String pn, Map<String, String> paramMap,String date)
			throws Exception {
		//maxTotalActivePower minTotalActivePower  avgTotalActivePower
		MonthlyLoad monthlyload = monthlyPowerServiceImpl.fetchSingleMonthlyLoad(date, areaId, concentratorId,pn);
		//最大负荷
		paramMap.put("maxTotalActivePower", monthlyload.getMaxactivepower()  +"(kW)");
		paramMap.put("maxTotalActivePowerTime", com.elefirst.base.utils.DateUtil.StringPattern(monthlyload.getDays(), "yyyyMM", "yyyy-MM"));
		
		//最小负荷
		paramMap.put("minTotalActivePower", monthlyload.getMinactivepower()+"(kW)");
		paramMap.put("minTotalActivePowerTime", com.elefirst.base.utils.DateUtil.StringPattern(monthlyload.getDays(), "yyyyMM", "yyyy-MM"));
		
		//平均负荷
		paramMap.put("avgTotalActivePower", monthlyload.getAvgactivepower()+"(kW)");
		
		//峰谷差:最高负荷与最低负荷之差  peak-valley difference
		double peakValleyDifference = Arith.sub(monthlyload.getMaxactivepower(), monthlyload.getMinactivepower());
		paramMap.put("peakValleyDifference","" + peakValleyDifference +"(kW)");
		
		//峰谷差率:峰谷差与最高负荷的比率
		paramMap.put("peakValleyDifferenceRate","" + monthlyload.getPeakrate()+"(%)");
		//负荷率:平均负荷与最高负荷的比率 load factor
		paramMap.put("loadFactorRate","" + monthlyload.getLoadrate()+"(%)");
		paramMap.put("type", "totalactivepower");
	}
    
	private void setPowerFactorDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap,String date) throws Exception {
		MonthlyPowerFactor monthlyPowerFactor = monthlyPowerServiceImpl.fetchSingleMonthlyPowerFactor(date, areaId, concentratorId, pn);
		paramMap.put("maxAPowerFactor", monthlyPowerFactor.getAmaxpowerfactor()+"(%)");
		paramMap.put("maxBPowerFactor", monthlyPowerFactor.getBmaxpowerfactor()+"(%)");
		paramMap.put("maxCPowerFactor", monthlyPowerFactor.getCmaxpowerfactor()+"(%)");
		paramMap.put("maxTotalPowerFactor", monthlyPowerFactor.getMaxtotalpowerfactor()+"(%)");
		
		
		paramMap.put("minAPowerFactor", monthlyPowerFactor.getAminpowerfactor()+"(%)");
		paramMap.put("minBPowerFactor", monthlyPowerFactor.getBminpowerfactor()+"(%)");
		paramMap.put("minCPowerFactor", monthlyPowerFactor.getCminpowerfactor()+"(%)");
		paramMap.put("minTotalPowerFactor", monthlyPowerFactor.getMintotalpowerfactor()+"(%)");
		paramMap.put("type", "powerfactor");
	}
	
	private void setCurrentDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap,String date) throws Exception {
		MonthlyCurrent monthlyCurrent = monthlyPowerServiceImpl.fetchSingleMonthlyCurrent(date, areaId, concentratorId, pn);
		
		paramMap.put("maxACurrent", monthlyCurrent.getMaxacurrent()+"(A)");
		paramMap.put("maxBCurrent", monthlyCurrent.getMaxbcurrent()+"(A)");
		paramMap.put("maxCCurrent", monthlyCurrent.getMaxccurrent()+"(A)");
		paramMap.put("minACurrent", monthlyCurrent.getMinacurrent()+"(A)");
		paramMap.put("minBCurrent", monthlyCurrent.getMinbcurrent()+"(A)");
		paramMap.put("minCCurrent", monthlyCurrent.getMinccurrent()+"(A)");
		
		paramMap.put("type", "current");
	}
	
	private void setVoltageDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap,String date) throws Exception {
		MonthlyVoltage monthlyVoltage = monthlyPowerServiceImpl.fetchSingleVoltage(date, areaId, concentratorId, pn);
		
		paramMap.put("maxAVoltage", monthlyVoltage.getMaxavoltage()+"(V)");
		paramMap.put("maxBVoltage", monthlyVoltage.getMaxbvoltage()+"(V)");
		paramMap.put("maxCVoltage", monthlyVoltage.getMaxcvoltage()+"(V)");
		paramMap.put("minAVoltage", monthlyVoltage.getMinavoltage()+"(V)");
		paramMap.put("minBVoltage", monthlyVoltage.getMinbvoltage()+"(V)");
		paramMap.put("minCVoltage", monthlyVoltage.getMincvoltage()+"(V)");
		
		paramMap.put("type", "voltage");
	}
	
}
