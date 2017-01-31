package com.elefirst.powerdetail.service;

import java.util.List;

import com.elefirst.powerdetail.po.DailyCurrent;
import com.elefirst.powerdetail.po.DailyLoad;
import com.elefirst.powerdetail.po.DailyPowerFactor;
import com.elefirst.powerdetail.po.DailyVoltage;

public interface IDailyPowerServie {
	/**
	 * 查询按日统计负荷相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyLoad> fetchAllDailyLoad(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询按日统计电压相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyVoltage> fetchAllDailyVoltage(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询按日统计电流相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyCurrent> fetchAllDailyCurrent(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询按日统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyPowerFactor> fetchAllDailyPowerFactor(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
}
