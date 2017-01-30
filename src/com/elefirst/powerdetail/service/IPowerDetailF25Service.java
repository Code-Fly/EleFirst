package com.elefirst.powerdetail.service;

import java.util.List;

import com.elefirst.powerdetail.po.CurrentDetail;
import com.elefirst.powerdetail.po.PowerDetailF25;
import com.elefirst.powerdetail.po.PowerFactorDetail;
import com.elefirst.powerdetail.po.TotalActivePowerDetail;
import com.elefirst.powerdetail.po.ViewDisplayF33F34;
import com.elefirst.powerdetail.po.VoltageDetail;
import com.elefirst.system.po.PnInfo;

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
	 * 根据区域ID,集线器ID,监测点ID 查询指定监测点的所有示数记录
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public List<ViewDisplayF33F34> fetchAllDisplayDetailByPn(String date,String areaId,String ctrId,String pn,int rows,int page) throws Exception;
	
	/**
	 * 根据区域ID,集线器ID,监测点ID 查询指定监测点的所有示数记录总数
	 * @param areaId
	 * @param ctrIds
	 * @param rows
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public int fetchAllDisplayDetailCountByPn(String date,String areaId,String ctrId,String pn) throws Exception;
	
	/**
	 * 根据ua,ub,uc，ia,ib,ic,pfa,pfb,pfc,tpf条件分别获取监测点对应的最新一条记录
	 * @param areaId
	 * @param ctrId
	 * @param pnId
	 * @param ua
	 * @param ub
	 * @param uc
	 * @param ia
	 * @param ib
	 * @param ic
	 * @param pfa  A功率因素
	 * @param pfb
	 * @param pfc
	 * @param tpf  总功率因素
	 * @param tap  总负荷
	 * @return
	 */
	public PowerDetailF25 fetchLastPowerDetailF25(String date,String areaId,String ctrId,String pnId,String ua,String ub,String uc,String ia,String ib,String ic,String pfa,String pfb,String pfc,String tpf,String tap);
	
	/**
	 * 获取当前监测点电压最大和最小值
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public VoltageDetail fetchVoltageDetail(String areaId,String ctrId,String pnId,String date) throws Exception;
	
	/**
	 * 获取当前监测点电流最大和最小值
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public CurrentDetail fetchCurrentDetail(String areaId,String ctrId,String pnId,String date) throws Exception;
	
	/**
	 * 获取当前监测点功率最大和最小值
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public PowerFactorDetail fetchPowerFactorDetail(String areaId,String ctrId,String pnId,String date) throws Exception;
	
	/**
	 * 获取每个监测点下最大,最小以及平均负荷
	 * @param areaId
	 * @param ctrIds
	 * @return
	 * @throws Exception
	 */
	public TotalActivePowerDetail fetchTotalActivePowerDetail(String areaId,String ctrId,String pnId,String date) throws Exception;
	
}
