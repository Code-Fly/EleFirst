package com.elefirst.powerdetail.service;

import java.util.List;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.YearlyElectricity;
import com.elefirst.powerdetail.po.YearlyLoad;

public interface IYearlyPowerService {
	/**
	 * 查询按年统计负荷相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<YearlyLoad> fetchAllYearlyLoad(String date,String areaId,List<Concentrator> concentrators,int rows,int page,boolean isPagination) throws Exception;
	
	/**
	 * 查询单条按年统计负荷相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public YearlyLoad fetchSingleYearlyLoad(String date,String areaId,String ctrIds,String pn) throws Exception;
	
	
	/**
	 * 查询按年统计电量相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<YearlyElectricity> fetchAllYearlyElectricity(String date,String areaId,List<Concentrator> concentrators,int rows,int page) throws Exception;
	
	/**
	 *  查询按年统计电量相关数据条数
	 * @param date
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @param isPagination
	 * @return
	 * @throws Exception
	 */
	public int fetchAllYearlyElectricityCount(String date,String areaId,List<Concentrator> concentrators) throws Exception;
	
	/**
	 * 查询单条按年统计电量相关数据
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public YearlyElectricity fetchSingleYearlyElectricity(String date,String areaId,List<Concentrator> concentrators,String pn) throws Exception;
}
