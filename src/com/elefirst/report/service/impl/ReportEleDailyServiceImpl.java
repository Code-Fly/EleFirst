package com.elefirst.report.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.elefirst.powerdetail.mapper.DailyLoadMapper;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.DailyVoltage;
import com.elefirst.powerdetail.po.DailyVoltageExample;
import com.elefirst.report.mapper.ReportEleByDailyMapper;
import com.elefirst.report.po.ReportDisplayByDaily;
import com.elefirst.report.po.ReportEleByDaily;
import com.elefirst.report.po.ReportEleByDailyExample;
import com.elefirst.report.service.IReportEleDailyService;

@Service
public class ReportEleDailyServiceImpl implements IReportEleDailyService{

	@Resource(name = "reportEleByDailyMapper")
	private ReportEleByDailyMapper reportEleByDailyMapper;
	
	@Override
	public List<ReportEleByDaily> getReportEleByDailyList(
			ReportEleByDaily template) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReportEleByDaily> getReportEleByDailyList(
			List<ReportEleByDaily> node, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getReportEleByDailyListCount(ReportEleByDaily template) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReportEleByDaily> getReportEleByDailyDetail(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addReportEleByDaily(ReportEleByDaily template) {
		return reportEleByDailyMapper.insertSelective(template);
	}

	@Override
	public int updateReportEleByDaily(ReportEleByDaily template) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delReportEleByDaily(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReportEleByDaily> fetchAllReportEleByDaily(String date, String areaId,
			List<Concentrator> concentrators, int rows, int page,
			boolean isPagination) throws Exception {
		ReportEleByDailyExample condition = new ReportEleByDailyExample();
		ReportEleByDailyExample.Criteria criteria = condition.createCriteria();
		/*for (Concentrator concentrator : concentrators) {
			ReportEleByDailyExample.Criteria criteria = condition.createCriteria();
			if(date != null && date.length() > 0){
				String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
				criteria.andOperationTimeEqualTo(vdate);
			}
			criteria.andAreaIdEqualTo(areaId);
			criteria.andConcentratorIdEqualTo(concentrator.getConcentratorId());
			criteria.andPnIn(concentrator.getPns());
			condition.or(criteria);
		}*/
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
			criteria.andOperationTimeEqualTo(vdate);
		}
		
		//排序后只取第一条记录返回
		condition.setOrderByClause("operation_time DESC");
		//是否分页
		if(true == isPagination){
			if (rows > 0 && page > 0) {
				condition.setLimitStart((page - 1) * rows);
				condition.setLimitEnd(rows);
			}
		}
		List<ReportEleByDaily> reportEleByDaily = reportEleByDailyMapper.selectByExample(condition);
		return reportEleByDaily;
	}

	@Override
	public List<ReportEleByDaily> fetchAllReportEleByDaily2(String date,
			String areaId, List<Concentrator> concentrators, int rows,
			int page, boolean isPagination) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
			params.put("date", vdate);
		}
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		
		List<ReportEleByDaily> reportEleByDaily = reportEleByDailyMapper.myselectByExample(params);
		return reportEleByDaily;
	}

	@Override
	public int fetchAllReportEleByDailyCount(String date, String areaId,
			List<Concentrator> concentrators) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
