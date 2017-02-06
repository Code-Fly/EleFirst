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
import com.elefirst.powerdetail.po.MonthlyDemandDetail;
import com.elefirst.powerdetail.po.MonthlyElectricity;
import com.elefirst.powerdetail.po.WeeklyCurrent;
import com.elefirst.powerdetail.po.WeeklyDemand;
import com.elefirst.powerdetail.po.WeeklyDemandDetail;
import com.elefirst.powerdetail.po.WeeklyElectricity;
import com.elefirst.powerdetail.po.WeeklyLoad;
import com.elefirst.powerdetail.po.WeeklyPowerFactor;
import com.elefirst.powerdetail.po.WeeklyVoltage;
import com.elefirst.powerdetail.service.IWeeklyPowerService;

@Controller
@RequestMapping("/weeklypower/")
public class WeeklyPowerController {
private static final Logger logger = LoggerFactory.getLogger(WeeklyPowerController.class);
	
	@Resource(name = "weeklyPowerServiceImpl")
	private IWeeklyPowerService weeklyPowerServiceImpl;
	
	/**
	 * 根据周期查询相关的按周负荷统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listWeeklyLoad.do")
	public @ResponseBody ErrorMsg queryWeeklyLoad(String date,String page, String rows,String jasonStr)
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
			
			List<WeeklyLoad> weeklyLoads = weeklyPowerServiceImpl.fetchAllWeeklyLoad(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = weeklyPowerServiceImpl.fetchAllWeeklyLoad(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按周负荷统计数据成功！");
			dg.setRows(weeklyLoads);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按周负荷统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据周期查询相关的按电压荷统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listWeeklyVoltage.do")
	public @ResponseBody ErrorMsg queryWeeklyVoltage(String date,String page, String rows,String jasonStr)
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
			
			List<WeeklyVoltage> weeklyVoltages = weeklyPowerServiceImpl.fetchAllWeeklyVoltage(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = weeklyPowerServiceImpl.fetchAllWeeklyVoltage(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按周电压统计数据成功！");
			dg.setRows(weeklyVoltages);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按电压荷统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据周期查询相关的按电流统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listWeeklyCurrent.do")
	public @ResponseBody ErrorMsg queryWeeklyCurrent(String date,String page, String rows,String jasonStr)
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
			
			List<WeeklyCurrent> weeklyCurrents = weeklyPowerServiceImpl.fetchAllWeeklyCurrent(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = weeklyPowerServiceImpl.fetchAllWeeklyCurrent(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按周电流统计数据成功！");
			dg.setRows(weeklyCurrents);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按电流统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据周期查询相关的按功率因数统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listWeeklyPowerFactor.do")
	public @ResponseBody ErrorMsg queryWeeklyPowerFactor(String date,String page, String rows,String jasonStr)
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
			
			List<WeeklyPowerFactor> weeklyPowerFactors = weeklyPowerServiceImpl.fetchAllWeeklyPowerFactor(date, areaId, ctrIds, rowsNum, pageNum,true);
			int total = weeklyPowerServiceImpl.fetchAllWeeklyPowerFactor(date, areaId, ctrIds, rowsNum, pageNum,false).size();
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按周功率因数统计数据成功！");
			dg.setRows(weeklyPowerFactors);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按功率因数统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	
	/**
	 * 根据周期查询相关的按需量统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listWeeklyDemand.do")
	public @ResponseBody ErrorMsg queryWeeklyDemand(String date,String page, String rows,String jasonStr)
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
			
			List<WeeklyDemand> weeklyDemands = weeklyPowerServiceImpl.fetchAllWeeklyDemand(date, areaId, ctrIds, rowsNum, pageNum);
			int total = weeklyPowerServiceImpl.fetchAllWeeklyDemandCount(date, areaId, ctrIds);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按周需量统计数据成功！");
			dg.setRows(weeklyDemands);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按需量统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	
	/**
	 * 根据日期查询相关的按周需量详情荷统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listWeeklyDetailDemand.do")
	public @ResponseBody ErrorMsg queryWeeklyDemandDetail(String date,String page, String rows,String areaId,String concentratorId,String pn)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			List<String> ctrIds = new ArrayList<String>();
			ctrIds.add(concentratorId);
			
			List<WeeklyDemandDetail> weeklyDemandDetails = weeklyPowerServiceImpl.fetchAllWeeklyDetailDemand(date, areaId, ctrIds, rowsNum, pageNum,pn);
			int total = weeklyPowerServiceImpl.fetchAllWeeklyDetailDemandCount(date, areaId, ctrIds,pn);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按周需量详情统计数据成功！");
			dg.setRows(weeklyDemandDetails);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按需量详情统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	/**
	 * 根据日期查询相关的电量统计数据
	 * @param date
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listWeeklyElectricity.do")
	public @ResponseBody ErrorMsg queryWeeklyElectricity(String date,String page, String rows,String jasonStr)
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
			
			List<WeeklyElectricity> weeklyElectricity = weeklyPowerServiceImpl.fetchAllWeeklyElectricity(date, areaId, ctrIds, rowsNum, pageNum);
			int total = weeklyPowerServiceImpl.fetchAllWeeklyElectricityCount(date, areaId, ctrIds);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询相关的按周的电量统计数据成功！");
			dg.setRows(weeklyElectricity);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询相关的按周的电量统计数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	@RequestMapping("queryWeeklyPower.do")
	public @ResponseBody ErrorMsg queryAllWeeklyPower(String tabName,String areaId,String concentratorId,String pn,String date)
			throws Exception {
		Map<String,String> paramMap = new HashMap<String,String>();
		try {
			if("负荷".equals(tabName)){
				setTotalActivePowerDetail(areaId, concentratorId, pn, paramMap,date);
			}else if("电压".equals(tabName)){
				setVoltageDetail(areaId, concentratorId, pn, paramMap,date);
			}else if("电流".equals(tabName)){
				setCurrentDetail(areaId, concentratorId, pn, paramMap,date);
			}else if("功率因数".equals(tabName)){
				setPowerFactorDetail(areaId, concentratorId, pn, paramMap,date);
			}else if("电量".equals(tabName)){
				setElectricityDetail(areaId, concentratorId, pn, paramMap,date);
			}
			logger.error("查询周用电信息成功！");
			return new ErrorMsg(Error.SUCCESS, "success",paramMap);
		} catch (Exception e) {
			logger.error("查询周用电信息失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "failed", null);
		}
	}
	
	private void setTotalActivePowerDetail(String areaId,
			String concentratorId, String pn, Map<String, String> paramMap,String date)
			throws Exception {
		//maxTotalActivePower minTotalActivePower  avgTotalActivePower
		WeeklyLoad weeklyload = weeklyPowerServiceImpl.fetchSingleWeeklyLoad(date, areaId, concentratorId,pn);
		//最大负荷
		paramMap.put("maxTotalActivePower", weeklyload.getMaxactivepower()  +"(kW)");
		paramMap.put("maxTotalActivePowerTime", weeklyload.getWeekstart());
		
		//最小负荷
		paramMap.put("minTotalActivePower", weeklyload.getMinactivepower()+"(kW)");
		paramMap.put("minTotalActivePowerTime", weeklyload.getWeekstart());
		
		//平均负荷
		paramMap.put("avgTotalActivePower", weeklyload.getAvgactivepower()+"(kW)");
		
		//峰谷差:最高负荷与最低负荷之差  peak-valley difference
		double peakValleyDifference = Arith.sub(weeklyload.getMaxactivepower(), weeklyload.getMinactivepower());
		paramMap.put("peakValleyDifference","" + peakValleyDifference +"(kW)");
		
		//峰谷差率:峰谷差与最高负荷的比率
		paramMap.put("peakValleyDifferenceRate","" + weeklyload.getPeakrate()+"(%)");
		//负荷率:平均负荷与最高负荷的比率 load factor
		paramMap.put("loadFactorRate","" + weeklyload.getLoadrate()+"(%)");
		paramMap.put("type", "totalactivepower");
	}
    
	private void setPowerFactorDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap,String date) throws Exception {
		WeeklyPowerFactor weeklyPowerFactor = weeklyPowerServiceImpl.fetchSingleWeeklyPowerFactor(date, areaId, concentratorId, pn);
		paramMap.put("maxAPowerFactor", weeklyPowerFactor.getAmaxpowerfactor()+"(%)");
		paramMap.put("maxBPowerFactor", weeklyPowerFactor.getBmaxpowerfactor()+"(%)");
		paramMap.put("maxCPowerFactor", weeklyPowerFactor.getCmaxpowerfactor()+"(%)");
		paramMap.put("maxTotalPowerFactor", weeklyPowerFactor.getMaxtotalpowerfactor()+"(%)");
		
		
		paramMap.put("minAPowerFactor", weeklyPowerFactor.getAminpowerfactor()+"(%)");
		paramMap.put("minBPowerFactor", weeklyPowerFactor.getBminpowerfactor()+"(%)");
		paramMap.put("minCPowerFactor", weeklyPowerFactor.getCminpowerfactor()+"(%)");
		paramMap.put("minTotalPowerFactor", weeklyPowerFactor.getMintotalpowerfactor()+"(%)");
		paramMap.put("type", "powerfactor");
	}
	
	private void setCurrentDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap,String date) throws Exception {
		WeeklyCurrent weeklyCurrent = weeklyPowerServiceImpl.fetchSingleWeeklyCurrent(date, areaId, concentratorId, pn);
		
		paramMap.put("maxACurrent", weeklyCurrent.getMaxacurrent()+"(A)");
		paramMap.put("maxBCurrent", weeklyCurrent.getMaxbcurrent()+"(A)");
		paramMap.put("maxCCurrent", weeklyCurrent.getMaxccurrent()+"(A)");
		paramMap.put("minACurrent", weeklyCurrent.getMinacurrent()+"(A)");
		paramMap.put("minBCurrent", weeklyCurrent.getMinbcurrent()+"(A)");
		paramMap.put("minCCurrent", weeklyCurrent.getMinccurrent()+"(A)");
		
		paramMap.put("type", "current");
	}
	
	private void setVoltageDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap,String date) throws Exception {
		WeeklyVoltage weeklyVoltage = weeklyPowerServiceImpl.fetchSingleVoltage(date, areaId, concentratorId, pn);
		
		paramMap.put("maxAVoltage", weeklyVoltage.getMaxavoltage()+"(V)");
		paramMap.put("maxBVoltage", weeklyVoltage.getMaxbvoltage()+"(V)");
		paramMap.put("maxCVoltage", weeklyVoltage.getMaxcvoltage()+"(V)");
		paramMap.put("minAVoltage", weeklyVoltage.getMinavoltage()+"(V)");
		paramMap.put("minBVoltage", weeklyVoltage.getMinbvoltage()+"(V)");
		paramMap.put("minCVoltage", weeklyVoltage.getMincvoltage()+"(V)");
		
		paramMap.put("type", "voltage");
	}
	
	private void setElectricityDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap, String date) throws Exception{
		List<String> ctrIds = new ArrayList<String>();
		ctrIds.add(concentratorId);
		WeeklyElectricity weeklyElectricity = weeklyPowerServiceImpl.fetchSingleWeeklyElectricity(date, areaId, ctrIds, pn);
		paramMap.put("totalpositiveactivePower", weeklyElectricity.getTotalpositiveactivePower()+"(kWh)");
		paramMap.put("rateseq1", weeklyElectricity.getRateseq1()+"(kWh)");
		paramMap.put("rateseq2", weeklyElectricity.getRateseq2()+"(kWh)");
		paramMap.put("rateseq3", weeklyElectricity.getRateseq3()+"(kWh)");
		paramMap.put("rateseq4", weeklyElectricity.getRateseq4()+"(kWh)");
		paramMap.put("type", "electricity");
	}
}
