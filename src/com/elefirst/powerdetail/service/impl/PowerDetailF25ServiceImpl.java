package com.elefirst.powerdetail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.elefirst.powerdetail.mapper.PowerDetailF25Mapper;
import com.elefirst.powerdetail.mapper.ViewDisplayF33F34Mapper;
import com.elefirst.powerdetail.po.CurrentDetail;
import com.elefirst.powerdetail.po.PowerDetailF25;
import com.elefirst.powerdetail.po.PowerDetailF25Example;
import com.elefirst.powerdetail.po.PowerFactorDetail;
import com.elefirst.powerdetail.po.TotalActivePowerDetail;
import com.elefirst.powerdetail.po.ViewDisplayF33F34;
import com.elefirst.powerdetail.po.ViewDisplayF33F34Example;
import com.elefirst.powerdetail.po.VoltageDetail;
import com.elefirst.powerdetail.service.IPowerDetailF25Service;

@Service
public class PowerDetailF25ServiceImpl implements IPowerDetailF25Service {

	private static final Logger logger = LoggerFactory.getLogger(PowerDetailF25ServiceImpl.class);

	@Resource(name = "powerDetailF25Mapper")
	private PowerDetailF25Mapper powerDetailF25Mapper;
	
	@Resource(name = "viewDisplayF33F34Mapper")
	private ViewDisplayF33F34Mapper viewDisplayF33F34Mapper;
	
	@Override
	public List<PowerDetailF25> fetchLastPowerDetailF25ByAreaId(String areaId,int rows,int page)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PowerDetailF25> fetchLastPowerDetailF25ByCtrId(String areaId,
			List<String> ctrIds,int rows,int page) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		List<PowerDetailF25> powerDetailF25s = powerDetailF25Mapper.myselectByExample(params);
		return powerDetailF25s;
	}

	@Override
	public int fetchLastPowerDetailF25CountByCtrId(String areaId,
			List<String> ctrIds) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		int num = powerDetailF25Mapper.myselectByExampleCount(params);
		return num;
	}

	@Override
	public List<ViewDisplayF33F34> fetchLastDisplayDetailByCtrId(
			String areaId, List<String> ctrIds, int rows, int page)
			throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		List<ViewDisplayF33F34> viewDisplayF33F34s = viewDisplayF33F34Mapper.myselectByExample(params);
		return viewDisplayF33F34s;
	}

	@Override
	public int fetchLastDisplayDetailCountByCtrId(String areaId,
			List<String> ctrIds) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		int num = viewDisplayF33F34Mapper.myselectByExampleCount(params);
		return num;
	}

	@Override
	public VoltageDetail fetchVoltageDetail(String areaId, String ctrId,
			String pnId) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pnId);
		VoltageDetail voltageDetail = powerDetailF25Mapper.queryVoltageDetail(params);
		return voltageDetail;
	}

	@Override
	public PowerDetailF25 fetchLastPowerDetailF25(String areaId,String ctrId,String pnId,String ua,String ub,String uc,String ia,String ib,String ic,String pfa,String pfb,String pfc,String tpf,String tap){
		PowerDetailF25Example condition = new PowerDetailF25Example();
		if(ua != null && ua.length() > 0){
			condition.or().andAVoltageEqualTo(ua);
		}else if(ub != null && ub.length() > 0){
			condition.or().andBVoltageEqualTo(ub);
		}else if(uc != null && uc.length() > 0){
			condition.or().andCVoltageEqualTo(uc);
		}else if(ia != null && ia.length() > 0){
			condition.or().andACurrentEqualTo(ia);
		}else if(ib != null && ib.length() > 0){
			condition.or().andBCurrentEqualTo(ib);
		}else if(ic != null && ic.length() > 0){
			condition.or().andCCurrentEqualTo(ic);
		}else if(pfa != null && pfa.length() > 0){
			condition.or().andAPowerfactorEqualTo(pfa);
		}else if(pfb != null && pfb.length() > 0){
			condition.or().andBPowerfactorEqualTo(pfb);
		}else if(pfc != null && pfc.length() > 0){
			condition.or().andCPowerfactorEqualTo(pfc);
		}else if(tpf != null && tpf.length() > 0){
			condition.or().andTotalpowerfactorEqualTo(tpf);
		}else if(tap != null && tap.length() > 0){
			condition.or().andTotalactivepowerEqualTo(tap);
		}
		condition.or().andAreaIdEqualTo(areaId);
		condition.or().andConcentratorIdEqualTo(ctrId);
		condition.or().andPnEqualTo(pnId);
		//排序后只取第一条记录返回
		condition.setOrderByClause("clientOperationTime DESC");
		condition.setLimitStart(0);
		condition.setLimitEnd(1);
		List<PowerDetailF25> powerDetailF25 = powerDetailF25Mapper.selectByExample(condition);
		return powerDetailF25.get(0);
	}

	@Override
	public CurrentDetail fetchCurrentDetail(String areaId, String ctrId,
			String pnId) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pnId);
		CurrentDetail currentDetail = powerDetailF25Mapper.queryCurrentDetail(params);
		return currentDetail;
	}

	@Override
	public PowerFactorDetail fetchPowerFactorDetail(String areaId,
			String ctrId, String pnId) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pnId);
		PowerFactorDetail powerFactorDetail = powerDetailF25Mapper.queryPowerFactorDetail(params);
		return powerFactorDetail;
	}

	@Override
	public TotalActivePowerDetail fetchTotalActivePowerDetail(String areaId,
			String ctrId, String pnId) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pnId);
		TotalActivePowerDetail totalActivePowerDetail = powerDetailF25Mapper.queryTotalActivePowerDetail(params);
		return totalActivePowerDetail;
	}

	@Override
	public List<ViewDisplayF33F34> fetchAllDisplayDetailByPn(String areaId,
			String ctrId, String pn, int rows, int page)
			throws Exception {
		ViewDisplayF33F34Example condition = new ViewDisplayF33F34Example();
		condition.or().andConcentratorId33EqualTo(ctrId);
		condition.or().andAreaId33EqualTo(areaId);
		condition.or().andPn33EqualTo(pn);
		condition.setOrderByClause("clientOperationTime33 DESC");
		if(rows > 0 && page > 0){
			condition.setLimitStart((page - 1) * rows);
            condition.setLimitEnd(rows);
		}
		List<ViewDisplayF33F34>  viewDisplayF33F34s = viewDisplayF33F34Mapper.selectByExample(condition);
		return viewDisplayF33F34s;
	}

	@Override
	public int fetchAllDisplayDetailCountByPn(String areaId,
			String ctrId, String pn) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorId", ctrId);
		params.put("areaId", areaId);
		params.put("pn", pn);
		int num = viewDisplayF33F34Mapper.displayDetailCount(params);
		return num;
	}
}
