package com.elefirst.powerdetail.service.impl;

import com.elefirst.powerdetail.mapper.YearlyElectricityMapper;
import com.elefirst.powerdetail.mapper.YearlyLoadMapper;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.YearlyElectricity;
import com.elefirst.powerdetail.po.YearlyLoad;
import com.elefirst.powerdetail.po.YearlyLoadExample;
import com.elefirst.powerdetail.service.IYearlyPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class YearlyPowerServiceImpl implements IYearlyPowerService{

	@Resource(name = "yearlyLoadMapper")
	private YearlyLoadMapper yearlyLoadMapper;
	
	@Resource(name = "yearlyElectricityMapper")
	private YearlyElectricityMapper yearlyElectricityMapper;
	
	@Override
	public List<YearlyLoad> fetchAllYearlyLoad(String date, String areaId,
			List<Concentrator> concentrators, int rows, int page,boolean isPagination) throws Exception {
		YearlyLoadExample condition = new YearlyLoadExample();
		for (Concentrator concentrator : concentrators) {
			YearlyLoadExample.Criteria criteria = condition.createCriteria();
			if(date != null && date.length() > 0){
//				String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyy");
                criteria.andDaysEqualTo(date);
            }
			criteria.andAreaIdEqualTo(areaId);
			criteria.andConcentratorIdEqualTo(concentrator.getConcentratorId());
			criteria.andPnIn(concentrator.getPns());
			condition.or(criteria);
		}
		
		//排序后只取第一条记录返回
		condition.setOrderByClause("days DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<YearlyLoad> yearlyLoads = yearlyLoadMapper.selectByExample(condition);
		return yearlyLoads;
	}
	
	@Override
	public YearlyLoad fetchSingleYearlyLoad(String date, String areaId,
			String ctrIds, String pn) throws Exception {
		YearlyLoadExample condition = new YearlyLoadExample();
		YearlyLoadExample.Criteria criteria = condition.createCriteria();
		criteria.andAreaIdEqualTo(areaId);
		criteria.andConcentratorIdEqualTo(ctrIds);
		criteria.andPnEqualTo(pn);
		criteria.andDaysEqualTo(date);
		List<YearlyLoad> yearlyLoads = yearlyLoadMapper.selectByExample(condition);
		return yearlyLoads.get(0);
	}

	@Override
	public List<YearlyElectricity> fetchAllYearlyElectricity(String date,
			String areaId, List<Concentrator> concentrators, int rows, int page)
			throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", concentrators);
		params.put("areaId", areaId);
		
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyy");
			params.put("date", vdate);
		}
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		List<YearlyElectricity> yearlyElectricity = yearlyElectricityMapper.selectByExample(params);
		return yearlyElectricity;
	}

	@Override
	public int fetchAllYearlyElectricityCount(String date, String areaId,
			List<Concentrator> concentrators) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", concentrators);
		params.put("areaId", areaId);
			
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyy");
			params.put("date", vdate);
		}
		int count = yearlyElectricityMapper.countByExample(params);
		return count;
	}

	@Override
	public YearlyElectricity fetchSingleYearlyElectricity(String date,
			String areaId, List<Concentrator> concentrators, String pn) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("concentratorIds", concentrators);
		params.put("areaId", areaId);
		params.put("pn", pn);
		if(date != null && date.length() > 0){
			params.put("date", date);
		}
		List<YearlyElectricity> yearlyElectricity = yearlyElectricityMapper.selectByExample(params);
		return yearlyElectricity.get(0);
	}
}
