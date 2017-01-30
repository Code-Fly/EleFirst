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
import com.elefirst.powerdetail.po.CurrentDetail;
import com.elefirst.powerdetail.po.PowerDetailF25;
import com.elefirst.powerdetail.po.PowerFactorDetail;
import com.elefirst.powerdetail.po.TotalActivePowerDetail;
import com.elefirst.powerdetail.po.ViewDisplayF33F34;
import com.elefirst.powerdetail.po.VoltageDetail;
import com.elefirst.powerdetail.service.IPowerDetailF25Service;


@Controller
@RequestMapping("/powerdetail/")
public class PowerDetailController {
	
	private static final Logger logger = LoggerFactory.getLogger(PowerDetailController.class);
	
	@Resource(name = "powerDetailF25ServiceImpl")
	private IPowerDetailF25Service powerDetailF25ServiceImpl;
	
	
	/**
	 * 根据传入的条件,获取每个区域下相关集线器对应的监测点最新一条电力记录数(根据抄表时间)
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listCurrentDetailPower.do")
	public @ResponseBody ErrorMsg queryCurrentDetailPower(String page, String rows,String jasonStr)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		StringBuffer sb = new StringBuffer();
		List<String> ctrIds = new ArrayList<String>();
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			
			Area area = JSON.parseObject(jasonStr,Area.class);
			
			List<Concentrator> concentrators = area.getConcentrators();
			if(concentrators == null && concentrators.size() == 0){
				return null;
			}
			for (Concentrator concentrator : concentrators) {
				String tmpCId = concentrator.getConcentratorId();
				ctrIds.add(tmpCId);
			}
			String areaId = area.getAreaId();
			
			List<PowerDetailF25> powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25ByCtrId(areaId, ctrIds, rowsNum, pageNum);
			for (PowerDetailF25 powerDetailF252 : powerDetailF25) {
				//获取电压、功率、电量 系数
				double pt = powerDetailF252.getPt();
				//电流、功率、电量 系数
				double ct = powerDetailF252.getCt();
				
				double pct = Arith.mul(pt, ct);
				
				//处理负荷
				handerLoad(powerDetailF252, pct);
				
				//处理电压
				handerVoltage(powerDetailF252, pt);
				
				//处理电流
				handerCurrent(powerDetailF252, ct);
				
				//处理日期
				String dateStr = powerDetailF252.getClientoperationtime();
				dateStr = com.elefirst.base.utils.DateUtil.StringPattern(dateStr, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm");
				powerDetailF252.setClientoperationtime(dateStr);
			}
			int total = powerDetailF25ServiceImpl.fetchLastPowerDetailF25CountByCtrId(areaId, ctrIds);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询实时监测点用电数据成功！");
			dg.setRows(powerDetailF25);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询实时监测点用电数据失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}

	
	
	/**
	 * 根据传入的条件,获取每个区域下相关集线器对应的监测点最新示数记录数(根据抄表时间)
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listCurrentDisplayDetail.do")
	public @ResponseBody ErrorMsg queryCurrentDisplayDetail(String page, String rows,String jasonStr)
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		StringBuffer sb = new StringBuffer();
		List<String> ctrIds = new ArrayList<String>();
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			
			Area area = JSON.parseObject(jasonStr,Area.class);
			
			List<Concentrator> concentrators = area.getConcentrators();
			if(concentrators == null && concentrators.size() == 0){
				return null;
			}
			for (Concentrator concentrator : concentrators) {
				String tmpCId = concentrator.getConcentratorId();
				ctrIds.add(tmpCId);
			}
			String areaId = area.getAreaId();
			
			List<ViewDisplayF33F34> viewDisplayF33F34s = powerDetailF25ServiceImpl.fetchLastDisplayDetailByCtrId(areaId, ctrIds, rowsNum, pageNum);
			
			for (ViewDisplayF33F34 viewDisplayF33F34 : viewDisplayF33F34s) {
				//获取电压、功率、电量 系数
				double pt = viewDisplayF33F34.getPt();
				//电流、功率、电量 系数
				double ct = viewDisplayF33F34.getCt();
				double pct = Arith.mul(pt, ct);
				
				//示数计算
				handerDisplay(viewDisplayF33F34, pct);
				
				//处理日期
				String dateStr = viewDisplayF33F34.getClientoperationtime33();
				dateStr = com.elefirst.base.utils.DateUtil.StringPattern(dateStr, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm");
				viewDisplayF33F34.setClientoperationtime33(dateStr);
			}
			
			int total = powerDetailF25ServiceImpl.fetchLastDisplayDetailCountByCtrId(areaId, ctrIds);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询实时监测点用电示数成功！");
			dg.setRows(viewDisplayF33F34s);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询实时监测点用电示数失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}

	
	
	/**
	 * 根据传入的条件,获取每个区域下相关集线器对应的监测点最新示数记录数(根据抄表时间)
	 * @param page
	 * @param rows
	 * @param jasonStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listAllDisplayDetailByPn.do")
	public @ResponseBody ErrorMsg queryAllDisplayDetailByPn(String page, String rows,String areaId,String concentratorId,String pn )
			throws Exception {
		DataGrid dg = new DataGrid();
		GeneralMessage gm = new GeneralMessage();
		try {
			int pageNum = Integer.valueOf(page == null ? "1" : page);
			int rowsNum = Integer.valueOf(rows == null ? "10" : rows);
			
			List<ViewDisplayF33F34> viewDisplayF33F34s = powerDetailF25ServiceImpl.fetchAllDisplayDetailByPn(areaId, concentratorId, pn, rowsNum, pageNum);
			
			for (ViewDisplayF33F34 viewDisplayF33F34 : viewDisplayF33F34s) {
				//获取电压、功率、电量 系数
				double pt = viewDisplayF33F34.getPt();
				//电流、功率、电量 系数
				double ct = viewDisplayF33F34.getCt();
				double pct = Arith.mul(pt, ct);
				
				//示数计算
				handerDisplay(viewDisplayF33F34, pct);
				//处理日期
				String dateStr = viewDisplayF33F34.getClientoperationtime33();
				dateStr = com.elefirst.base.utils.DateUtil.StringPattern(dateStr, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm");
				viewDisplayF33F34.setClientoperationtime33(dateStr);
			}
			
			int total = powerDetailF25ServiceImpl.fetchAllDisplayDetailCountByPn(areaId, concentratorId, pn);
			gm.setFlag(GeneralMessage.Result.SUCCESS);
			gm.setMsg("查询实时监测点用电示数成功！");
			dg.setRows(viewDisplayF33F34s);
			dg.setTotal(total);
			dg.setGm(gm);
			return new ErrorMsg(Error.SUCCESS, "success", dg);
		} catch (Exception e) {
			logger.error("查询实时监测点用电示数失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "faile", null);
		}
	}
	
	@RequestMapping("queryPowerDetail.do")
	public @ResponseBody ErrorMsg queryAllPowerDetail(String tabName,String areaId,String concentratorId,String pn)
			throws Exception {
		Map<String,String> paramMap = new HashMap<String,String>();
		try {
			if("负荷".equals(tabName)){
				setTotalActivePowerDetail(areaId, concentratorId, pn, paramMap);
			}else if("电压".equals(tabName)){
				setVoltageDetail(areaId, concentratorId, pn, paramMap);
			}else if("电流".equals(tabName)){
				setCurrentDetail(areaId, concentratorId, pn, paramMap);
			}else if("功率因数".equals(tabName)){
				setPowerFactorDetail(areaId, concentratorId, pn, paramMap);
			}
			logger.error("查询实时用电信息成功！");
			return new ErrorMsg(Error.SUCCESS, "success",paramMap);
		} catch (Exception e) {
			logger.error("查询实时用电信息失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "failed", null);
		}
	}

	private void setTotalActivePowerDetail(String areaId,
			String concentratorId, String pn, Map<String, String> paramMap)
			throws Exception {
		//maxTotalActivePower minTotalActivePower  avgTotalActivePower
		PowerDetailF25 powerDetailF25 = null;
		
		TotalActivePowerDetail totalActivePowerDetail = powerDetailF25ServiceImpl.fetchTotalActivePowerDetail(areaId, concentratorId, pn);
		
		String maxTotalActivePower = totalActivePowerDetail.getMaxTotalActivePower();
		String orgMaxTotalActivePower = totalActivePowerDetail.getOrgMaxTotalActivePower();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,null,null,null,null,null,orgMaxTotalActivePower);
		String maxTotalActivePowerTime = powerDetailF25.getClientoperationtime();
		maxTotalActivePowerTime = com.elefirst.base.utils.DateUtil.StringPattern(maxTotalActivePowerTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		//最大负荷
		paramMap.put("maxTotalActivePower", maxTotalActivePower+"(kW)");
		paramMap.put("maxTotalActivePowerTime", maxTotalActivePowerTime);
		
		String minTotalActivePower = totalActivePowerDetail.getMinTotalActivePower();
		String orgMinTotalActivePower = totalActivePowerDetail.getOrgMinTotalActivePower();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,null,null,null,null,null,orgMinTotalActivePower);
		String minTotalActivePowerTime = powerDetailF25.getClientoperationtime();
		minTotalActivePowerTime = com.elefirst.base.utils.DateUtil.StringPattern(minTotalActivePowerTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		//最小负荷
		paramMap.put("minTotalActivePower", minTotalActivePower+"(kW)");
		paramMap.put("minTotalActivePowerTime", minTotalActivePowerTime);
		
		String avgTotalActivePowerStr = totalActivePowerDetail.getAvgTotalActivePower();
		//平均负荷
		paramMap.put("avgTotalActivePower", avgTotalActivePowerStr+"(kW)");
		
		//峰谷差:最高负荷与最低负荷之差  peak-valley difference
		double peakValleyDifference = Arith.sub(Double.parseDouble(maxTotalActivePower), Double.parseDouble(minTotalActivePower));
		paramMap.put("peakValleyDifference","" + peakValleyDifference +"(kW)");
		
		//峰谷差率:峰谷差与最高负荷的比率
		peakValleyDifference = Arith.mul(peakValleyDifference, new Double(100));
		double peakValleyDifferenceRate = Arith.div(peakValleyDifference, Double.parseDouble(maxTotalActivePower), 2);
		paramMap.put("peakValleyDifferenceRate","" + peakValleyDifferenceRate+"(%)");
		
		//负荷率:平均负荷与最高负荷的比率 load factor
		double avgTotalActivePower = Arith.mul(Double.parseDouble(avgTotalActivePowerStr), new Double(100));
		double loadFactorRate = Arith.div(avgTotalActivePower, Double.parseDouble(maxTotalActivePower),2);
		paramMap.put("loadFactorRate","" + loadFactorRate+"(%)");
		
		paramMap.put("type", "totalactivepower");
	}

	private void setPowerFactorDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap) throws Exception {
		PowerDetailF25 powerDetailF25 = null;
		PowerFactorDetail powerFactorDetail = powerDetailF25ServiceImpl.fetchPowerFactorDetail(areaId, concentratorId, pn);
		
		String maxAPowerFactor = powerFactorDetail.getMaxAPowerFactor();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,maxAPowerFactor,null,null,null,null,null,null,null);
		String maxAPowerFactorTime = powerDetailF25.getClientoperationtime();
		maxAPowerFactorTime = com.elefirst.base.utils.DateUtil.StringPattern(maxAPowerFactorTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxAPowerFactor", maxAPowerFactor+"(%)"+"(" + maxAPowerFactorTime +")");
		
		String maxBPowerFactor = powerFactorDetail.getMaxBPowerFactor();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,maxBPowerFactor,null,null,null,null,null,null);
		String maxBPowerFactorTime = powerDetailF25.getClientoperationtime();
		maxBPowerFactorTime = com.elefirst.base.utils.DateUtil.StringPattern(maxBPowerFactorTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxBPowerFactor", maxBPowerFactor+"(%)"+"(" + maxBPowerFactorTime +")");
		
		String maxCPowerFactor = powerFactorDetail.getMaxCPowerFactor();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,maxCPowerFactor,null,null,null,null,null);
		String maxCPowerFactorTime = powerDetailF25.getClientoperationtime();
		maxCPowerFactorTime = com.elefirst.base.utils.DateUtil.StringPattern(maxCPowerFactorTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxCPowerFactor", maxCPowerFactor+"(%)"+"(" + maxCPowerFactorTime +")");
		
		String maxTotalPowerFactor = powerFactorDetail.getMaxTotalPowerFactor();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,maxTotalPowerFactor,null,null,null,null,null);
		String maxTotalPowerFactorTime = powerDetailF25.getClientoperationtime();
		maxTotalPowerFactorTime = com.elefirst.base.utils.DateUtil.StringPattern(maxTotalPowerFactorTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxTotalPowerFactor", maxTotalPowerFactor+"(%)"+"(" + maxTotalPowerFactorTime +")");
		
		
		String minAPowerFactor = powerFactorDetail.getMinAPowerFactor();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,minAPowerFactor,null,null,null,null,null,null,null);
		String minAPowerFactorTime = powerDetailF25.getClientoperationtime();
		minAPowerFactorTime = com.elefirst.base.utils.DateUtil.StringPattern(minAPowerFactorTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minAPowerFactor", minAPowerFactor+"(%)"+"(" + minAPowerFactorTime +")");
		
		String minBPowerFactor = powerFactorDetail.getMinBPowerFactor();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,minBPowerFactor,null,null,null,null,null,null);
		String minBPowerFactorTime = powerDetailF25.getClientoperationtime();
		minBPowerFactorTime = com.elefirst.base.utils.DateUtil.StringPattern(minBPowerFactorTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minBPowerFactor", minBPowerFactor+"(%)"+"(" + minBPowerFactorTime +")");
		
		String minCPowerFactor = powerFactorDetail.getMinCPowerFactor();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,minCPowerFactor,null,null,null,null,null);
		String minCPowerFactorTime = powerDetailF25.getClientoperationtime();
		minCPowerFactorTime = com.elefirst.base.utils.DateUtil.StringPattern(minCPowerFactorTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minCPowerFactor", minCPowerFactor+"(%)"+"(" + minCPowerFactorTime +")");
		
		String minTotalPowerFactor = powerFactorDetail.getMinTotalPowerFactor();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,minTotalPowerFactor,null,null,null,null,null);
		String minTotalPowerFactorTime = powerDetailF25.getClientoperationtime();
		minTotalPowerFactorTime = com.elefirst.base.utils.DateUtil.StringPattern(minTotalPowerFactorTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minTotalPowerFactor", minTotalPowerFactor+"(%)"+"(" + minTotalPowerFactorTime +")");
		
		paramMap.put("type", "powerfactor");
	}

	private void setCurrentDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap) throws Exception {
		PowerDetailF25 powerDetailF25 = null;
		CurrentDetail currentDetail = powerDetailF25ServiceImpl.fetchCurrentDetail(areaId, concentratorId, pn);
		
		String maxACurrent = currentDetail.getMaxACurrent();
		String orgMaxACurrent = currentDetail.getOrgMaxACurrent();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,orgMaxACurrent,null,null,null,null,null,null,null);
		String maxACurrentTime = powerDetailF25.getClientoperationtime();
		maxACurrentTime = com.elefirst.base.utils.DateUtil.StringPattern(maxACurrentTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxACurrent", maxACurrent+"(A)"+"(" + maxACurrentTime +")");
		
		String maxBCurrent = currentDetail.getMaxBCurrent();
		String orgMaxBCurrent = currentDetail.getOrgMaxBCurrent();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,orgMaxBCurrent,null,null,null,null,null,null);
		String maxBCurrentTime = powerDetailF25.getClientoperationtime();
		maxBCurrentTime = com.elefirst.base.utils.DateUtil.StringPattern(maxBCurrentTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxBCurrent", maxBCurrent+"(A)"+"(" + maxBCurrentTime +")");
		
		String maxCCurrent = currentDetail.getMaxCCurrent();
		String orgMaxCCurrent = currentDetail.getOrgMaxCCurrent();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,orgMaxCCurrent,null,null,null,null,null);
		String maxCCurrentTime = powerDetailF25.getClientoperationtime();
		maxCCurrentTime = com.elefirst.base.utils.DateUtil.StringPattern(maxCCurrentTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxCCurrent", maxCCurrent+"(A)"+"(" + maxCCurrentTime +")");
		
		
		String minACurrent = currentDetail.getMinACurrent();
		String orgMinACurrent = currentDetail.getMinACurrent();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,orgMinACurrent,null,null,null,null,null,null,null);
		String minACurrentTime = powerDetailF25.getClientoperationtime();
		minACurrentTime = com.elefirst.base.utils.DateUtil.StringPattern(minACurrentTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minACurrent", minACurrent+"(A)"+"(" + minACurrentTime +")");
		
		String minBCurrent = currentDetail.getMinBCurrent();
		String orgMinBCurrent = currentDetail.getMinBCurrent();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,orgMinBCurrent,null,null,null,null,null,null);
		String minBCurrentTime = powerDetailF25.getClientoperationtime();
		minBCurrentTime = com.elefirst.base.utils.DateUtil.StringPattern(minBCurrentTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minBCurrent", minBCurrent+"(A)"+"(" + minBCurrentTime +")");
		
		String minCCurrent = currentDetail.getMinCCurrent();
		String orgMinCCurrent = currentDetail.getMinCCurrent();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,orgMinCCurrent,null,null,null,null,null);
		String minCCurrentTime = powerDetailF25.getClientoperationtime();
		minCCurrentTime = com.elefirst.base.utils.DateUtil.StringPattern(minCCurrentTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minCCurrent", minCCurrent+"(A)"+"(" + minCCurrentTime +")");
		
		paramMap.put("type", "current");
	}

	private void setVoltageDetail(String areaId, String concentratorId,
			String pn, Map<String, String> paramMap) throws Exception {
		PowerDetailF25 powerDetailF25 = null;
		VoltageDetail voltageDetail = powerDetailF25ServiceImpl.fetchVoltageDetail(areaId, concentratorId, pn);
		
		String maxAVoltage = voltageDetail.getMaxAVoltage();
		String orgMaxAVoltage = voltageDetail.getOrgMaxAVoltage();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,orgMaxAVoltage,null,null,null,null,null,null,null,null,null,null);
		String maxAVoltageTime = powerDetailF25.getClientoperationtime();
		maxAVoltageTime = com.elefirst.base.utils.DateUtil.StringPattern(maxAVoltageTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxAVoltage", maxAVoltage+"(V)"+"(" + maxAVoltageTime +")");
		
		String maxBVoltage = voltageDetail.getMaxBVoltage();
		String orgMaxBVoltage = voltageDetail.getOrgMaxBVoltage();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,orgMaxBVoltage,null,null,null,null,null,null,null,null,null);
		String maxBVoltageTime = powerDetailF25.getClientoperationtime();
		maxBVoltageTime = com.elefirst.base.utils.DateUtil.StringPattern(maxBVoltageTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxBVoltage", maxBVoltage+"(V)"+"(" + maxBVoltageTime +")");
		
		String maxCVoltage = voltageDetail.getMaxCVoltage();
		String orgMaxCVoltage = voltageDetail.getOrgMaxCVoltage();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,orgMaxCVoltage,null,null,null,null,null,null,null,null);
		String maxCVoltageTime = powerDetailF25.getClientoperationtime();
		maxCVoltageTime = com.elefirst.base.utils.DateUtil.StringPattern(maxCVoltageTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("maxCVoltage", maxCVoltage+"(V)"+"(" + maxCVoltageTime +")");
		
		String minAVoltage = voltageDetail.getMinAVoltage();
		String orgMinAVoltage = voltageDetail.getOrgMinAVoltage();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,orgMinAVoltage,null,null,null,null,null,null,null,null,null,null);
		String minAVoltageTime = powerDetailF25.getClientoperationtime();
		minAVoltageTime = com.elefirst.base.utils.DateUtil.StringPattern(minAVoltageTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minAVoltage", minAVoltage+"(V)"+"(" + minAVoltageTime +")");
		
		String minBVoltage = voltageDetail.getMinBVoltage();
		String orgMinBVoltage = voltageDetail.getOrgMinBVoltage();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,orgMinBVoltage,null,null,null,null,null,null,null,null,null);
		String minBVoltageTime = powerDetailF25.getClientoperationtime();
		minBVoltageTime = com.elefirst.base.utils.DateUtil.StringPattern(minBVoltageTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minBVoltage", minBVoltage+"(V)"+"(" + minBVoltageTime +")");
		
		String minCVoltage = voltageDetail.getMinCVoltage();
		String orgMinCVoltage = voltageDetail.getOrgMinCVoltage();
		powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,orgMinCVoltage,null,null,null,null,null,null,null,null);
		String minCVoltageTime = powerDetailF25.getClientoperationtime();
		minCVoltageTime = com.elefirst.base.utils.DateUtil.StringPattern(minCVoltageTime, "yyyyMMddHHmmss", "MM-dd HH:mm");
		paramMap.put("minCVoltage", minCVoltage+"(V)"+"(" + minCVoltageTime +")");
		
		paramMap.put("type", "voltage");
	}
	
	private void handerVoltage(PowerDetailF25 powerDetailF252, double pt) {
		String aVoltageStr = powerDetailF252.getaVoltage();
		if(aVoltageStr == null || aVoltageStr.length() == 0){
			aVoltageStr = "0";
		}
		String bVoltageStr = powerDetailF252.getbVoltage();
		if(bVoltageStr == null || bVoltageStr.length() == 0){
			bVoltageStr = "0";
		}
		String cVoltageStr = powerDetailF252.getcVoltage();
		if(cVoltageStr == null || cVoltageStr.length() == 0){
			cVoltageStr = "0";
		}

		double aVoltage = new Double(aVoltageStr);
		double bVoltage = new Double(aVoltageStr);
		double cVoltage = new Double(aVoltageStr);
		
		aVoltage = Arith.mul(aVoltage, pt);
		bVoltage = Arith.mul(bVoltage, pt);
		cVoltage = Arith.mul(cVoltage, pt);
		
		powerDetailF252.setaVoltage("" + aVoltage);
		powerDetailF252.setbVoltage("" + bVoltage);
		powerDetailF252.setcVoltage("" + cVoltage);
	}

	private void handerCurrent(PowerDetailF25 powerDetailF252, double ct) {
		String aCurrentStr = powerDetailF252.getaCurrent();
		if(aCurrentStr == null || aCurrentStr.length() == 0){
			aCurrentStr = "0";
		}
		String bCurrentStr = powerDetailF252.getbCurrent();
		if(bCurrentStr == null || bCurrentStr.length() == 0){
			bCurrentStr = "0";
		}
		String cCurrentStr = powerDetailF252.getcCurrent();
		if(cCurrentStr == null || cCurrentStr.length() == 0){
			cCurrentStr = "0";
		}

		double aCurrent = new Double(aCurrentStr);
		double bCurrent = new Double(aCurrentStr);
		double cCurrent = new Double(aCurrentStr);
		
		aCurrent = Arith.mul(aCurrent, ct);
		bCurrent = Arith.mul(bCurrent, ct);
		cCurrent = Arith.mul(cCurrent, ct);
		
		powerDetailF252.setaCurrent("" + aCurrent);
		powerDetailF252.setbCurrent("" + bCurrent);
		powerDetailF252.setcCurrent("" + cCurrent);
	}

	private void handerLoad(PowerDetailF25 powerDetailF252, double pct) {
		//计算负荷
		String totalActivePowerStr = powerDetailF252.getTotalactivepower();
		if(totalActivePowerStr == null || totalActivePowerStr.length() == 0){
			totalActivePowerStr = "0";
		}
		String aActivePowerStr = powerDetailF252.getaActivepower();
		if(aActivePowerStr == null || aActivePowerStr.length() == 0){
			aActivePowerStr = "0";
		}
		String bActivePowerStr = powerDetailF252.getbActivepower();
		if(bActivePowerStr == null || bActivePowerStr.length() == 0){
			bActivePowerStr = "0";
		}
		String cActivePowerStr = powerDetailF252.getcActivepower();
		if(cActivePowerStr == null || cActivePowerStr.length() == 0){
			cActivePowerStr = "0";
		}
		
		String totalReactivePowerStr =  powerDetailF252.getTotalreactivepower();
		if(totalReactivePowerStr == null || totalReactivePowerStr.length() == 0){
			totalReactivePowerStr = "0";
		}
		String aReactivePowerStr = powerDetailF252.getaReactivepower();
		if(aReactivePowerStr == null || aReactivePowerStr.length() == 0){
			aReactivePowerStr = "0";
		}
		String bReactivePowerStr = powerDetailF252.getbReactivepower();
		if(bReactivePowerStr == null || bReactivePowerStr.length() == 0){
			bReactivePowerStr = "0";
		}
		String cReactivePowerStr = powerDetailF252.getcReactivepower();
		if(cReactivePowerStr == null || cReactivePowerStr.length() == 0){
			cReactivePowerStr = "0";
		}
		
		double totalActivePower = new Double(totalActivePowerStr);
		double aActivePower = new Double(aActivePowerStr);
		double bActivePower = new Double(bActivePowerStr);
		double cActivePower = new Double(cActivePowerStr);
		
		double totalReactivePower = new Double(totalReactivePowerStr);
		double aReactivePower = new Double(aReactivePowerStr);
		double bReactivePower = new Double(bReactivePowerStr);
		double cReactivePower = new Double(cReactivePowerStr);
		
		totalActivePower = Arith.mul(totalActivePower, pct);
		aActivePower = Arith.mul(aActivePower, pct);
		bActivePower = Arith.mul(bActivePower, pct);
		cActivePower = Arith.mul(cActivePower, pct);
		
		totalReactivePower = Arith.mul(totalReactivePower, pct);
		aReactivePower = Arith.mul(aReactivePower, pct);
		bReactivePower = Arith.mul(bReactivePower, pct);
		cReactivePower = Arith.mul(cReactivePower, pct);
		
		//计算负荷
		powerDetailF252.setTotalactivepower("" + totalActivePower);
		powerDetailF252.setaActivepower("" + aActivePower);
		powerDetailF252.setbActivepower("" + bActivePower);
		powerDetailF252.setcActivepower("" + cActivePower);
		
		powerDetailF252.setTotalreactivepower("" + totalReactivePower);
		powerDetailF252.setaReactivepower("" + aReactivePower);
		powerDetailF252.setbReactivepower("" + bReactivePower);
		powerDetailF252.setcReactivepower("" + cReactivePower);
	}
	
	private void handerDisplay(ViewDisplayF33F34 viewDisplayF33F34, double pct) {
		String totalpositiveactivepowerStr = viewDisplayF33F34.getTotalpositiveactivepower();
		if(totalpositiveactivepowerStr == null || totalpositiveactivepowerStr.length() == 0){
			totalpositiveactivepowerStr = "0";
		}
		String totalpositivereactivepowerStr = viewDisplayF33F34.getTotalpositivereactivepower();
		if(totalpositivereactivepowerStr == null || totalpositivereactivepowerStr.length() == 0){
			totalpositivereactivepowerStr = "0";
		}
		String totalreverseactivepowerStr = viewDisplayF33F34.getTotalreverseactivepower();
		if(totalreverseactivepowerStr == null || totalreverseactivepowerStr.length() == 0){
			totalreverseactivepowerStr = "0";
		}
		String totalreversereactivepowerStr = viewDisplayF33F34.getTotalreversereactivepower();
		if(totalreversereactivepowerStr == null || totalreversereactivepowerStr.length() == 0){
			totalreversereactivepowerStr = "0";
		}

		double totalpositiveactivepower = new Double(totalpositiveactivepowerStr);
		double totalpositivereactivepower = new Double(totalpositivereactivepowerStr);
		double totalreverseactivepower = new Double(totalreverseactivepowerStr);
		double totalreversereactivepower = new Double(totalreversereactivepowerStr);
		
		totalpositiveactivepower = Arith.mul(totalpositiveactivepower, pct);
		totalpositivereactivepower = Arith.mul(totalpositivereactivepower, pct);
		totalreverseactivepower = Arith.mul(totalreverseactivepower, pct);
		totalreversereactivepower = Arith.mul(totalreversereactivepower, pct);
		
		viewDisplayF33F34.setTotalpositiveactivepower("" + totalpositiveactivepower);
		viewDisplayF33F34.setTotalpositivereactivepower("" + totalpositivereactivepower);
		viewDisplayF33F34.setTotalreverseactivepower("" + totalreverseactivepower);
		viewDisplayF33F34.setTotalreversereactivepower("" + totalreversereactivepower);
	}
}
