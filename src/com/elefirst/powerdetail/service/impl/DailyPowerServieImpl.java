package com.elefirst.powerdetail.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.elefirst.powerdetail.mapper.DailyCurrentMapper;
import com.elefirst.powerdetail.mapper.DailyLoadMapper;
import com.elefirst.powerdetail.mapper.DailyPowerFactorMapper;
import com.elefirst.powerdetail.mapper.DailyVoltageMapper;
import com.elefirst.powerdetail.po.DailyCurrent;
import com.elefirst.powerdetail.po.DailyCurrentExample;
import com.elefirst.powerdetail.po.DailyLoad;
import com.elefirst.powerdetail.po.DailyLoadExample;
import com.elefirst.powerdetail.po.DailyPowerFactor;
import com.elefirst.powerdetail.po.DailyPowerFactorExample;
import com.elefirst.powerdetail.po.DailyVoltage;
import com.elefirst.powerdetail.po.DailyVoltageExample;
import com.elefirst.powerdetail.service.IDailyPowerServie;
@Service
public class DailyPowerServieImpl implements IDailyPowerServie{

	@Resource(name = "dailyLoadMapper")
	private DailyLoadMapper dailyLoadMapper;
	
	@Resource(name = "dailyVoltageMapper")
	private DailyVoltageMapper dailyVoltageMapper;
	
	@Resource(name = "dailyCurrentMapper")
	private DailyCurrentMapper dailyCurrentMapper;
	
	@Resource(name = "dailyPowerFactorMapper")
	private DailyPowerFactorMapper dailyPowerFactorMapper;
	
	@Override
	public List<DailyLoad> fetchAllDailyLoad(String date, String areaId,
			List<String> ctrIds, int rows, int page,boolean isPagination) throws Exception {
		DailyLoadExample condition = new DailyLoadExample();
		DailyLoadExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
			criteria.andDaysEqualTo(vdate);
		}
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdIn(ctrIds);
		//排序后只取第一条记录返回
		condition.setOrderByClause("days DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<DailyLoad> dailyLoads = dailyLoadMapper.selectByExample(condition);
		return dailyLoads;
	}

	@Override
	public List<DailyVoltage> fetchAllDailyVoltage(String date, String areaId,
			List<String> ctrIds, int rows, int page, boolean isPagination)
			throws Exception {
		DailyVoltageExample condition = new DailyVoltageExample();
		DailyVoltageExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
			criteria.andDaysEqualTo(vdate);
		}
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdIn(ctrIds);
		//排序后只取第一条记录返回
		condition.setOrderByClause("days DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<DailyVoltage> dailyVoltages = dailyVoltageMapper.selectByExample(condition);
		return dailyVoltages;
	}
	
	@Override
	public List<DailyCurrent> fetchAllDailyCurrent(String date, String areaId,
			List<String> ctrIds, int rows, int page, boolean isPagination)
			throws Exception {
		DailyCurrentExample condition = new DailyCurrentExample();
		DailyCurrentExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
			criteria.andDaysEqualTo(vdate);
		}
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdIn(ctrIds);
		//排序后只取第一条记录返回
		condition.setOrderByClause("days DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<DailyCurrent> dailyCurrents = dailyCurrentMapper.selectByExample(condition);
		return dailyCurrents;
	}

	@Override
	public List<DailyPowerFactor> fetchAllDailyPowerFactor(String date,
			String areaId, List<String> ctrIds, int rows, int page,
			boolean isPagination) throws Exception {
		DailyPowerFactorExample condition = new DailyPowerFactorExample();
		DailyPowerFactorExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
			criteria.andDaysEqualTo(vdate);
		}
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdIn(ctrIds);
		//排序后只取第一条记录返回
		condition.setOrderByClause("days DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<DailyPowerFactor> dailyPowerFactors = dailyPowerFactorMapper.selectByExample(condition);
		return dailyPowerFactors;
	}

}
