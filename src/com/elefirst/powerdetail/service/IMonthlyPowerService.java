package com.elefirst.powerdetail.service;

import java.util.List;

import com.elefirst.powerdetail.po.MonthlyCurrent;
import com.elefirst.powerdetail.po.MonthlyDemand;
import com.elefirst.powerdetail.po.MonthlyLoad;
import com.elefirst.powerdetail.po.MonthlyPowerFactor;
import com.elefirst.powerdetail.po.MonthlyVoltage;

public interface IMonthlyPowerService {
	/**
	 * 查询按月统计负荷相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<MonthlyLoad> fetchAllMonthlyLoad(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按月统计负荷相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public MonthlyLoad fetchSingleMonthlyLoad(String date,String areaId,String ctrIds,String pn) throws Exception;
	
	/**
	 * 查询按月统计电压相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<MonthlyVoltage> fetchAllMonthlyVoltage(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按月统计电压相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public MonthlyVoltage fetchSingleVoltage(String date,String areaId,String ctrIds,String pn) throws Exception;
	
	/**
	 * 查询按月统计电流相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<MonthlyCurrent> fetchAllMonthlyCurrent(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按月统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public MonthlyCurrent fetchSingleMonthlyCurrent(String date,String areaId,String ctrId,String pn) throws Exception;
	
	/**
	 * 查询按月统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<MonthlyPowerFactor> fetchAllMonthlyPowerFactor(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按月统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public MonthlyPowerFactor fetchSingleMonthlyPowerFactor(String date,String areaId,String ctrId,String pn) throws Exception;
	
	/**
	 * 查询按月统计需量相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<MonthlyDemand> fetchAllDailyDemand(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
}
