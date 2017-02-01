package com.elefirst.powerdetail.service;

import java.util.List;

import com.elefirst.powerdetail.po.WeeklyCurrent;
import com.elefirst.powerdetail.po.WeeklyLoad;
import com.elefirst.powerdetail.po.WeeklyPowerFactor;
import com.elefirst.powerdetail.po.WeeklyVoltage;

public interface IWeeklyPowerService {
	/**
	 * 查询按周统计负荷相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<WeeklyLoad> fetchAllWeeklyLoad(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按周统计负荷相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public WeeklyLoad fetchSingleWeeklyLoad(String date,String areaId,String ctrIds,String pn) throws Exception;
	
	/**
	 * 查询按周统计电压相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<WeeklyVoltage> fetchAllWeeklyVoltage(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按周统计电压相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public WeeklyVoltage fetchSingleVoltage(String date,String 周,String ctrIds,String pn) throws Exception;
	
	/**
	 * 查询按周统计电流相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<WeeklyCurrent> fetchAllWeeklyCurrent(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按周统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public WeeklyCurrent fetchSingleWeeklyCurrent(String date,String 周,String ctrId,String pn) throws Exception;
	
	/**
	 * 查询按周统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<WeeklyPowerFactor> fetchAllWeeklyPowerFactor(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按周统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public WeeklyPowerFactor fetchSingleWeeklyPowerFactor(String date,String areaId,String ctrId,String pn) throws Exception;
}
