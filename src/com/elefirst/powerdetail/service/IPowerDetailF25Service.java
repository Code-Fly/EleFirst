package com.elefirst.powerdetail.service;

import java.util.List;

import com.elefirst.powerdetail.po.PowerDetailF25;

/**
 * 
 * @author jinlu
 *
 */
public interface IPowerDetailF25Service {
	
	/**
	 * 根据区域ID查询所属的所有监测点,每个监测点取新电力参数记录
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public List<PowerDetailF25> fetchLastPowerDetailF25ByAreaId(String areaId,int rows,int page) throws Exception;
	
	/**
	 * 根据区域ID,查询多个集线器ID对应的监测点,每个监测点取新电力参数记录
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public List<PowerDetailF25> fetchLastPowerDetailF25ByCtrId(String areaId,List<String> ctrIds,int rows,int page) throws Exception;
	
	/**
	 * 获取每个区域下相关集线器对应的监测点总数
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int fetchLastPowerDetailF25CountByCtrId(String areaId,List<String> ctrIds) throws Exception;

	
	/**
	 * 根据区域ID,集线器ID,监测点ID获取该监测点所有电力参数记录
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public List<PowerDetailF25> fetchAllPnPowerDetail(String areaId,String ctrId,String pnId,int rows,int page) throws Exception;
	
}
