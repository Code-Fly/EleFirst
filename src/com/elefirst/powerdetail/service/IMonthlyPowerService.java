package com.elefirst.powerdetail.service;

import java.util.List;

import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.DailyElectricity;
import com.elefirst.powerdetail.po.MonthlyCurrent;
import com.elefirst.powerdetail.po.MonthlyDemand;
import com.elefirst.powerdetail.po.MonthlyDemandDetail;
import com.elefirst.powerdetail.po.MonthlyElectricity;
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
	public List<MonthlyDemand> fetchAllDailyDemand(String date,String areaId,List<String> ctrIds,int rows,int page) throws Exception;
	
	/**
	 *  查询按月统计需量相关数据条数
	 * @param date
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @param isPagination
	 * @return
	 * @throws Exception
	 */
	public int fetchAllMonthlyDemandCount(String date,String areaId,List<String> ctrIds) throws Exception;
	
	/**
	 * 查询按月统计需量详情示数相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<MonthlyDemandDetail> fetchAllMonthlyDetailDemand(String date,String areaId,List<Concentrator> concentrators,int rows,int page,String pn) throws Exception;
	
	/**
	 *  查查询按月统计需量详情示数相关数据条数
	 * @param date
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @param isPagination
	 * @return
	 * @throws Exception
	 */
	public int fetchAllDailyDetailDemandCount(String date,String areaId,List<Concentrator> concentrators,String pn) throws Exception;
	
	/**
	 * 查询按月统计电量相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<MonthlyElectricity> fetchAllMonthlyElectricity(String date,String areaId,List<String> ctrIds,int rows,int page) throws Exception;
	
	/**
	 *  查询按月统计电量相关数据条数
	 * @param date
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @param isPagination
	 * @return
	 * @throws Exception
	 */
	public int fetchAllMonthlyElectricityCount(String date,String areaId,List<String> ctrIds) throws Exception;
	
	/**
	 * 查询单条按月统计电量相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public MonthlyElectricity fetchSingleMonthlyElectricity(String date,String areaId,List<String> ctrIds,String pn) throws Exception;
}
