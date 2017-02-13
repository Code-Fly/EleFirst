package com.elefirst.powerdetail.service;

import java.util.List;

import com.elefirst.powerdetail.po.DailyCurrent;
import com.elefirst.powerdetail.po.DailyElectricity;
import com.elefirst.powerdetail.po.DailyLoad;
import com.elefirst.powerdetail.po.DailyHarmonic;
import com.elefirst.powerdetail.po.DailyPowerFactor;
import com.elefirst.powerdetail.po.DailyVoltage;

public interface IDailyPowerService {
	/**
	 * 查询按日统计负荷相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyLoad> fetchAllDailyLoad(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按日统计负荷相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public DailyLoad fetchSingleDailyLoad(String date,String areaId,String ctrIds,String pn) throws Exception;
	
	/**
	 * 查询按日统计电压相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyVoltage> fetchAllDailyVoltage(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按日统计电压相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public DailyVoltage fetchSingleVoltage(String date,String areaId,String ctrIds,String pn) throws Exception;
	
	/**
	 * 查询按日统计电流相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyCurrent> fetchAllDailyCurrent(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按日统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public DailyCurrent fetchSingleDailyCurrent(String date,String areaId,String ctrId,String pn) throws Exception;
	
	/**
	 * 查询按日统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyPowerFactor> fetchAllDailyPowerFactor(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按日统计功率因素相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public DailyPowerFactor fetchSingleDailyPowerFactor(String date,String areaId,String ctrId,String pn) throws Exception;
	
	/**
	 * 查询按日统计电量相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyElectricity> fetchAllDailyElectricity(String date,String areaId,List<String> ctrIds,int rows,int page) throws Exception;
	
	/**
	 *  查询按日统计电量相关数据条数
	 * @param date
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @param isPagination
	 * @return
	 * @throws Exception
	 */
	public int fetchAllDailyElectricityCount(String date,String areaId,List<String> ctrIds) throws Exception;
	
	/**
	 * 查询单条按日统计电量相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public DailyElectricity fetchSingleDailyElectricity(String date,String areaId,List<String> ctrIds,String pn) throws Exception;
	
	/**
	 * 查询按日统计分谐波相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyHarmonic> fetchAllPartionHarmonic(String date,String areaId,List<String> ctrIds,String harmonicseq,int rows,int page) throws Exception;
	
	/**
	 *  查询按日统计分谐波相关数据条数
	 * @param date
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @param isPagination
	 * @return
	 * @throws Exception
	 */
	public int fetchAllPartionHarmonicCount(String date,String areaId,List<String> ctrIds,String harmonicseq) throws Exception;
	
	/**
	 * 查询按日统计总谐波相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<DailyHarmonic> fetchAllTotalHarmonic(String date,String areaId,List<String> ctrIds,String harmonicseq,int rows,int page) throws Exception;
	
	/**
	 *  查询按日统计总谐波相关数据条数
	 * @param date
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @param isPagination
	 * @return
	 * @throws Exception
	 */
	public int fetchAllTotalHarmonicCount(String date,String areaId,List<String> ctrIds,String harmonicseq) throws Exception;
}
