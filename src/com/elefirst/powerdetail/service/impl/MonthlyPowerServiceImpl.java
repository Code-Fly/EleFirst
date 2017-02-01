package com.elefirst.powerdetail.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.elefirst.powerdetail.mapper.MonthlyCurrentMapper;
import com.elefirst.powerdetail.mapper.MonthlyDemandMapper;
import com.elefirst.powerdetail.mapper.MonthlyLoadMapper;
import com.elefirst.powerdetail.mapper.MonthlyPowerFactorMapper;
import com.elefirst.powerdetail.mapper.MonthlyVoltageMapper;
import com.elefirst.powerdetail.po.MonthlyCurrent;
import com.elefirst.powerdetail.po.MonthlyCurrentExample;
import com.elefirst.powerdetail.po.MonthlyDemand;
import com.elefirst.powerdetail.po.MonthlyDemandExample;
import com.elefirst.powerdetail.po.MonthlyLoad;
import com.elefirst.powerdetail.po.MonthlyLoadExample;
import com.elefirst.powerdetail.po.MonthlyPowerFactor;
import com.elefirst.powerdetail.po.MonthlyPowerFactorExample;
import com.elefirst.powerdetail.po.MonthlyVoltage;
import com.elefirst.powerdetail.po.MonthlyVoltageExample;
import com.elefirst.powerdetail.service.IMonthlyPowerService;
@Service
public class MonthlyPowerServiceImpl implements IMonthlyPowerService{

	@Resource(name = "monthlyLoadMapper")
	private MonthlyLoadMapper monthlyLoadMapper;
	
	@Resource(name = "monthlyVoltageMapper")
	private MonthlyVoltageMapper monthlyVoltageMapper;
	
	@Resource(name = "monthlyCurrentMapper")
	private MonthlyCurrentMapper monthlyCurrentMapper;
	
	@Resource(name = "monthlyPowerFactorMapper")
	private MonthlyPowerFactorMapper monthlyPowerFactorMapper;
	
	@Resource(name = "monthlyDemandMapper")
	private MonthlyDemandMapper monthlyDemandMapper;
	
	@Override
	public List<MonthlyLoad> fetchAllMonthlyLoad(String date, String areaId,
			List<String> ctrIds, int rows, int page,boolean isPagination) throws Exception {
		MonthlyLoadExample condition = new MonthlyLoadExample();
		MonthlyLoadExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
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
		List<MonthlyLoad> monthlyLoads = monthlyLoadMapper.selectByExample(condition);
		return monthlyLoads;
	}
	
	@Override
	public MonthlyLoad fetchSingleMonthlyLoad(String date, String areaId,
			String ctrIds, String pn) throws Exception {
		MonthlyLoadExample condition = new MonthlyLoadExample();
		MonthlyLoadExample.Criteria criteria = condition.createCriteria();
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrIds);
		criteria.andPnEqualTo(pn);
		criteria.andDaysEqualTo(date);
		List<MonthlyLoad> monthlyLoads = monthlyLoadMapper.selectByExample(condition);
		return monthlyLoads.get(0);
	}

	@Override
	public List<MonthlyVoltage> fetchAllMonthlyVoltage(String date, String areaId,
			List<String> ctrIds, int rows, int page, boolean isPagination)
			throws Exception {
		MonthlyVoltageExample condition = new MonthlyVoltageExample();
		MonthlyVoltageExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
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
		List<MonthlyVoltage> monthlyVoltages = monthlyVoltageMapper.selectByExample(condition);
		return monthlyVoltages;
	}
	
	@Override
	public MonthlyVoltage fetchSingleVoltage(String date, String areaId,
			String ctrId, String pn) throws Exception {
		MonthlyVoltageExample condition = new MonthlyVoltageExample();
		MonthlyVoltageExample.Criteria criteria = condition.createCriteria();
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrId);
		criteria.andPnEqualTo(pn);
		criteria.andDaysEqualTo(date);
		List<MonthlyVoltage> monthlyVoltages = monthlyVoltageMapper.selectByExample(condition);
		return monthlyVoltages.get(0);
	}
	
	@Override
	public List<MonthlyCurrent> fetchAllMonthlyCurrent(String date, String areaId,
			List<String> ctrIds, int rows, int page, boolean isPagination)
			throws Exception {
		MonthlyCurrentExample condition = new MonthlyCurrentExample();
		MonthlyCurrentExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
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
		List<MonthlyCurrent> monthlyCurrents = monthlyCurrentMapper.selectByExample(condition);
		return monthlyCurrents;
	}
	
	@Override
	public MonthlyCurrent fetchSingleMonthlyCurrent(String date, String areaId,
			String ctrId, String pn) throws Exception {
		MonthlyCurrentExample condition = new MonthlyCurrentExample();
		MonthlyCurrentExample.Criteria criteria = condition.createCriteria();
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrId);
		criteria.andPnEqualTo(pn);
		criteria.andDaysEqualTo(date);
		List<MonthlyCurrent> monthlyCurrents = monthlyCurrentMapper.selectByExample(condition);
		return monthlyCurrents.get(0);
	}

	@Override
	public List<MonthlyPowerFactor> fetchAllMonthlyPowerFactor(String date,
			String areaId, List<String> ctrIds, int rows, int page,
			boolean isPagination) throws Exception {
		MonthlyPowerFactorExample condition = new MonthlyPowerFactorExample();
		MonthlyPowerFactorExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
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
		List<MonthlyPowerFactor> monthlyPowerFactors = monthlyPowerFactorMapper.selectByExample(condition);
		return monthlyPowerFactors;
	}

	@Override
	public MonthlyPowerFactor fetchSingleMonthlyPowerFactor(String date,
			String areaId, String ctrId,String pn) throws Exception {
		MonthlyPowerFactorExample condition = new MonthlyPowerFactorExample();
		MonthlyPowerFactorExample.Criteria criteria = condition.createCriteria();
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrId);
		criteria.andPnEqualTo(pn);
		criteria.andDaysEqualTo(date);
		List<MonthlyPowerFactor> monthlyPowerFactors = monthlyPowerFactorMapper.selectByExample(condition);
		return monthlyPowerFactors.get(0);
	}
	
	@Override
	public List<MonthlyDemand> fetchAllDailyDemand(String date,String areaId,List<String> ctrIds,int rows,int page,boolean isPagination) throws Exception {
		MonthlyDemandExample condition = new MonthlyDemandExample();
		MonthlyDemandExample.Criteria criteria = condition.createCriteria();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
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
		List<MonthlyDemand> monthlyDemands = monthlyDemandMapper.selectByExample(condition);
		return monthlyDemands;
	}
}
