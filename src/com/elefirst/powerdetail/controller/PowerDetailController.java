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
import com.elefirst.powerdetail.po.Area;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.CurrentDetail;
import com.elefirst.powerdetail.po.PowerDetailF25;
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
	
	@RequestMapping("queryPowerDetail.do")
	public @ResponseBody ErrorMsg queryAllPowerDetail(String tabName,String areaId,String concentratorId,String pn)
			throws Exception {
		Map<String,String> paramMap = new HashMap<String,String>();
		try {
			if("负荷".equals(tabName)){
				
			}else if("示数".equals(tabName)){
				
			}else if("电压".equals(tabName)){
				PowerDetailF25 powerDetailF25 = null;
				VoltageDetail voltageDetail = powerDetailF25ServiceImpl.fetchVoltageDetail(areaId, concentratorId, pn);
				String maxAVoltage = voltageDetail.getMaxAVoltage();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,maxAVoltage,null,null,null,null,null,null,null,null,null);
				String maxAVoltageTime = powerDetailF25.getClientoperationtime();
				paramMap.put("maxAVoltage", maxAVoltage+"(" + maxAVoltageTime +")");
				
				String maxBVoltage = voltageDetail.getMaxBVoltage();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,maxBVoltage,null,null,null,null,null,null,null,null);
				String maxBVoltageTime = powerDetailF25.getClientoperationtime();
				paramMap.put("maxBVoltage", maxBVoltage+"(" + maxBVoltageTime +")");
				
				String maxCVoltage = voltageDetail.getMaxCVoltage();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,maxCVoltage,null,null,null,null,null,null,null);
				String maxCVoltageTime = powerDetailF25.getClientoperationtime();
				paramMap.put("maxCVoltage", maxCVoltage+"(" + maxCVoltageTime +")");
				
				String minAVoltage = voltageDetail.getMinAVoltage();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,minAVoltage,null,null,null,null,null,null,null,null,null);
				String minAVoltageTime = powerDetailF25.getClientoperationtime();
				paramMap.put("minAVoltage", minAVoltage+"(" + minAVoltageTime +")");
				
				String minBVoltage = voltageDetail.getMinBVoltage();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,minBVoltage,null,null,null,null,null,null,null,null);
				String minBVoltageTime = powerDetailF25.getClientoperationtime();
				paramMap.put("minBVoltage", minBVoltage+"(" + minBVoltageTime +")");
				
				String minCVoltage = voltageDetail.getMinCVoltage();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,minCVoltage,null,null,null,null,null,null,null);
				String minCVoltageTime = powerDetailF25.getClientoperationtime();
				paramMap.put("minCVoltage", minCVoltage+"(" + minCVoltageTime +")");
				paramMap.put("type", "voltage");
			}else if("电流".equals(tabName)){
				PowerDetailF25 powerDetailF25 = null;
				CurrentDetail currentDetail = powerDetailF25ServiceImpl.fetchCurrentDetail(areaId, concentratorId, pn);
				
				String maxACurrent = currentDetail.getMaxACurrent();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,maxACurrent,null,null,null,null,null,null);
				String maxACurrentTime = powerDetailF25.getClientoperationtime();
				paramMap.put("maxACurrent", maxACurrent+"(A)"+"(" + maxACurrentTime +")");
				
				String maxBCurrent = currentDetail.getMaxBCurrent();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,maxBCurrent,null,null,null,null,null);
				String maxBCurrentTime = powerDetailF25.getClientoperationtime();
				paramMap.put("maxBCurrent", maxBCurrent+"(A)"+"(" + maxBCurrentTime +")");
				
				String maxCCurrent = currentDetail.getMaxCCurrent();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,maxCCurrent,null,null,null,null);
				String maxCCurrentTime = powerDetailF25.getClientoperationtime();
				paramMap.put("maxCCurrent", maxCCurrent+"(A)"+"(" + maxCCurrentTime +")");
				
				
				String minACurrent = currentDetail.getMinACurrent();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,minACurrent,null,null,null,null,null,null);
				String minACurrentTime = powerDetailF25.getClientoperationtime();
				paramMap.put("minACurrent", minACurrent+"(A)"+"(" + minACurrentTime +")");
				
				String minBCurrent = currentDetail.getMinBCurrent();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,minBCurrent,null,null,null,null,null);
				String minBCurrentTime = powerDetailF25.getClientoperationtime();
				paramMap.put("minBCurrent", minBCurrent+"(A)"+"(" + minBCurrentTime +")");
				
				String minCCurrent = currentDetail.getMinCCurrent();
				powerDetailF25 = powerDetailF25ServiceImpl.fetchLastPowerDetailF25(areaId, concentratorId, pn,null,null,null,null,null,minCCurrent,null,null,null,null);
				String minCCurrentTime = powerDetailF25.getClientoperationtime();
				paramMap.put("minCCurrent", minCCurrent+"(A)"+"(" + minCCurrentTime +")");
				
				paramMap.put("type", "current");
			}else if("功率因数".equals(tabName)){
				
			}
			logger.error("查询实时用电信息成功！");
			return new ErrorMsg(Error.SUCCESS, "success",paramMap);
		} catch (Exception e) {
			logger.error("查询实时用电信息失败！", e);
			return new ErrorMsg(Error.UNKNOW_EXCEPTION, "failed", null);
		}
	}
}
