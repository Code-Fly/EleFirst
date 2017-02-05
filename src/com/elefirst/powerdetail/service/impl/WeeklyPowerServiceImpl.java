package com.elefirst.powerdetail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.elefirst.powerdetail.mapper.WeeklyCurrentMapper;
import com.elefirst.powerdetail.mapper.WeeklyDemandDetailMapper;
import com.elefirst.powerdetail.mapper.WeeklyDemandMapper;
import com.elefirst.powerdetail.mapper.WeeklyLoadMapper;
import com.elefirst.powerdetail.mapper.WeeklyPowerFactorMapper;
import com.elefirst.powerdetail.mapper.WeeklyVoltageMapper;
import com.elefirst.powerdetail.po.MonthlyDemand;
import com.elefirst.powerdetail.po.MonthlyDemandDetail;
import com.elefirst.powerdetail.po.WeeklyCurrent;
import com.elefirst.powerdetail.po.WeeklyCurrentExample;
import com.elefirst.powerdetail.po.WeeklyDemand;
import com.elefirst.powerdetail.po.WeeklyDemandDetail;
import com.elefirst.powerdetail.po.WeeklyLoad;
import com.elefirst.powerdetail.po.WeeklyLoadExample;
import com.elefirst.powerdetail.po.WeeklyPowerFactor;
import com.elefirst.powerdetail.po.WeeklyPowerFactorExample;
import com.elefirst.powerdetail.po.WeeklyVoltage;
import com.elefirst.powerdetail.po.WeeklyVoltageExample;
import com.elefirst.powerdetail.service.IWeeklyPowerService;
@Service
public class WeeklyPowerServiceImpl implements IWeeklyPowerService{

	@Resource(name = "weeklyLoadMapper")
	private WeeklyLoadMapper weeklyLoadMapper;
	
	@Resource(name = "weeklyVoltageMapper")
	private WeeklyVoltageMapper weeklyVoltageMapper;
	
	@Resource(name = "weeklyCurrentMapper")
	private WeeklyCurrentMapper weeklyCurrentMapper;
	
	@Resource(name = "weeklyPowerFactorMapper")
	private WeeklyPowerFactorMapper weeklyPowerFactorMapper;
	
	@Resource(name = "weeklyDemandMapper")
	private WeeklyDemandMapper weeklyDemandMapper;
	
	@Resource(name = "weeklyDemandDetailMapper")
	private WeeklyDemandDetailMapper weeklyDemandDetailMapper;
	
	@Override
	public List<WeeklyLoad> fetchAllWeeklyLoad(String date, String areaId,
			List<String> ctrIds, int rows, int page,boolean isPagination) throws Exception {
		WeeklyLoadExample condition = new WeeklyLoadExample();
		WeeklyLoadExample.Criteria criteria = condition.createCriteria();
		criteria.andWeekstartLessThanOrEqualTo(date);
		criteria.andWeekendGreaterThanOrEqualTo(date);
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdIn(ctrIds);
		//排序后只取第一条记录返回
		condition.setOrderByClause("weekstart DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<WeeklyLoad> weeklyLoads = weeklyLoadMapper.selectByExample(condition);
		return weeklyLoads;
	}
	
	@Override
	public WeeklyLoad fetchSingleWeeklyLoad(String date, String areaId,
			String ctrIds, String pn) throws Exception {
		WeeklyLoadExample condition = new WeeklyLoadExample();
		WeeklyLoadExample.Criteria criteria = condition.createCriteria();
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrIds);
		criteria.andPnEqualTo(pn);
		criteria.andWeekstartLessThanOrEqualTo(date);
		criteria.andWeekendGreaterThanOrEqualTo(date);
		
		List<WeeklyLoad> weeklyLoads = weeklyLoadMapper.selectByExample(condition);
		return weeklyLoads.get(0);
	}

	@Override
	public List<WeeklyVoltage> fetchAllWeeklyVoltage(String date, String areaId,
			List<String> ctrIds, int rows, int page, boolean isPagination)
			throws Exception {
		WeeklyVoltageExample condition = new WeeklyVoltageExample();
		WeeklyVoltageExample.Criteria criteria = condition.createCriteria();
		criteria.andWeekstartLessThanOrEqualTo(date);
		criteria.andWeekendGreaterThanOrEqualTo(date);
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdIn(ctrIds);
		//排序后只取第一条记录返回
		condition.setOrderByClause("weekstart DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<WeeklyVoltage> weeklyVoltages = weeklyVoltageMapper.selectByExample(condition);
		return weeklyVoltages;
	}
	
	@Override
	public WeeklyVoltage fetchSingleVoltage(String date, String areaId,
			String ctrId, String pn) throws Exception {
		WeeklyVoltageExample condition = new WeeklyVoltageExample();
		WeeklyVoltageExample.Criteria criteria = condition.createCriteria();
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrId);
		criteria.andPnEqualTo(pn);
		criteria.andWeekstartLessThanOrEqualTo(date);
		criteria.andWeekendGreaterThanOrEqualTo(date);
		List<WeeklyVoltage> weeklyVoltages = weeklyVoltageMapper.selectByExample(condition);
		return weeklyVoltages.get(0);
	}
	
	@Override
	public List<WeeklyCurrent> fetchAllWeeklyCurrent(String date, String areaId,
			List<String> ctrIds, int rows, int page, boolean isPagination)
			throws Exception {
		WeeklyCurrentExample condition = new WeeklyCurrentExample();
		WeeklyCurrentExample.Criteria criteria = condition.createCriteria();
		criteria.andWeekstartLessThanOrEqualTo(date);
		criteria.andWeekendGreaterThanOrEqualTo(date);
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdIn(ctrIds);
		//排序后只取第一条记录返回
		condition.setOrderByClause("weekstart DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<WeeklyCurrent> weeklyCurrents = weeklyCurrentMapper.selectByExample(condition);
		return weeklyCurrents;
	}
	
	@Override
	public WeeklyCurrent fetchSingleWeeklyCurrent(String date, String areaId,
			String ctrId, String pn) throws Exception {
		WeeklyCurrentExample condition = new WeeklyCurrentExample();
		WeeklyCurrentExample.Criteria criteria = condition.createCriteria();
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrId);
		criteria.andPnEqualTo(pn);
		criteria.andWeekstartLessThanOrEqualTo(date);
		criteria.andWeekendGreaterThanOrEqualTo(date);
		List<WeeklyCurrent> weeklyCurrents = weeklyCurrentMapper.selectByExample(condition);
		return weeklyCurrents.get(0);
	}

	@Override
	public List<WeeklyPowerFactor> fetchAllWeeklyPowerFactor(String date,
			String areaId, List<String> ctrIds, int rows, int page,
			boolean isPagination) throws Exception {
		WeeklyPowerFactorExample condition = new WeeklyPowerFactorExample();
		WeeklyPowerFactorExample.Criteria criteria = condition.createCriteria();
		criteria.andWeekstartLessThanOrEqualTo(date);
		criteria.andWeekendGreaterThanOrEqualTo(date);
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdIn(ctrIds);
		//排序后只取第一条记录返回
		condition.setOrderByClause("weekstart DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<WeeklyPowerFactor> weeklyPowerFactors = weeklyPowerFactorMapper.selectByExample(condition);
		return weeklyPowerFactors;
	}

	@Override
	public WeeklyPowerFactor fetchSingleWeeklyPowerFactor(String date,
			String areaId, String ctrId,String pn) throws Exception {
		WeeklyPowerFactorExample condition = new WeeklyPowerFactorExample();
		WeeklyPowerFactorExample.Criteria criteria = condition.createCriteria();
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrId);
		criteria.andPnEqualTo(pn);
		criteria.andWeekstartLessThanOrEqualTo(date);
		criteria.andWeekendGreaterThanOrEqualTo(date);
		List<WeeklyPowerFactor> weeklyPowerFactors = weeklyPowerFactorMapper.selectByExample(condition);
		return weeklyPowerFactors.get(0);
	}

	@Override
	public List<WeeklyDemand> fetchAllWeeklyDemand(String date, String areaId,
			List<String> ctrIds, int rows, int page) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		
		if(date != null && date.length() > 0){
			params.put("date", date);
		}
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		List<WeeklyDemand> weeklyDemands = weeklyDemandMapper.selectByExample(params);
		return weeklyDemands;
	}

	@Override
	public int fetchAllWeeklyDemandCount(String date, String areaId,
			List<String> ctrIds) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		
		if(date != null && date.length() > 0){
			params.put("date", date);
		}
		int count = weeklyDemandMapper.countByExample(params);
		return count;
	}

	@Override
	public List<WeeklyDemandDetail> fetchAllWeeklyDetailDemand(String date,
			String areaId, List<String> ctrIds, int rows, int page, String pn)
			throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
 		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		params.put("pn", pn);
		if(date != null && date.length() > 0){
			params.put("date", date);
		} 
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		List<WeeklyDemandDetail> weeklyDetailDemands = weeklyDemandDetailMapper.selectByExample(params);
		return weeklyDetailDemands;
	}

	@Override
	public int fetchAllWeeklyDetailDemandCount(String date, String areaId,
			List<String> ctrIds, String pn) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", ctrIds);
		params.put("areaId", areaId);
		params.put("pn", pn);
		if(date != null && date.length() > 0){
			params.put("date", date);
		}
		int count = weeklyDemandDetailMapper.countByExample(params);
		return count;
	}
}
