package com.elefirst.powerdetail.service;

import java.util.List;

import com.elefirst.powerdetail.po.PowerDetailF25;
import com.elefirst.powerdetail.po.ViewDisplayF33F34;
import com.elefirst.powerdetail.po.VoltageDetail;

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
	 * 根据区域ID,查询多个集线器ID对应的监测点,每个监测点取最新电力参数记录
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public List<PowerDetailF25> fetchLastPowerDetailF25ByCtrId(String areaId,List<String> ctrIds,int rows,int page) throws Exception;
	
	/**
	 * 根据区域ID,查询多个集线器ID对应的监测点,每个监测点取最新电力示数记录
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public List<ViewDisplayF33F34> fetchLastDisplayDetailByCtrId(String areaId,List<String> ctrIds,int rows,int page) throws Exception;
	
	/**
	 * 获取每个区域下相关集线器对应的最新监测点示数记录总数
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int fetchLastDisplayDetailCountByCtrId(String areaId,List<String> ctrIds) throws Exception;
	
	
	/**
	 * 获取每个区域下相关集线器对应的最新监测点总数
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int fetchLastPowerDetailF25CountByCtrId(String areaId,List<String> ctrIds) throws Exception;

	
	/**
	 * 获取当前监测点电压最大和最小值
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public VoltageDetail fetchVoltageDetail(String areaId,String ctrId,String pnId) throws Exception;
	
	/**
	 * 根据ua,ub,uc条件分别获取监测点对应的最新一条记录
	 * @param areaId
	 * @param ctrId
	 * @param pnId
	 * @param ua
	 * @param ub
	 * @param uc
	 * @return
	 */
	public PowerDetailF25 fetchLastPowerDetailF25(String areaId,String ctrId,String pnId,String ua,String ub,String uc);
	
}
