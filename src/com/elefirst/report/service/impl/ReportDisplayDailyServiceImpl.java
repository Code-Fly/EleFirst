package com.elefirst.report.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.report.mapper.ReportDisplayByDailyMapper;
import com.elefirst.report.po.ReportDisplayByDaily;
import com.elefirst.report.po.ReportDisplayByDailyExample;
import com.elefirst.report.po.ReportEnergyByHour;
import com.elefirst.report.po.ReportEnergyByHourExample;
import com.elefirst.report.service.IReportDisplayDailyService;

@Service
public class ReportDisplayDailyServiceImpl implements IReportDisplayDailyService{

	@Resource(name = "reportDisplayByDailyMapper")
	private ReportDisplayByDailyMapper reportDisplayByDailyMapper;
	
	@Override
	public List<ReportDisplayByDaily> getReportDisplayByDailyList(
			ReportDisplayByDaily template) {
		ReportDisplayByDailyExample condition = new ReportDisplayByDailyExample();
		ReportDisplayByDailyExample.Criteria criteria = condition.createCriteria();
		if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        if (null != template && null != template.getOperationTime()) {
            criteria.andOperationTimeEqualTo(template.getOperationTime());
        }
		return reportDisplayByDailyMapper.selectByExample(condition);
	}

	@Override
	public List<ReportDisplayByDaily> getReportDisplayByDailyList(
			List<ReportDisplayByDaily> node, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getReportDisplayByDailyListCount(ReportDisplayByDaily template) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReportDisplayByDaily> getReportDisplayByDailyDetail(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addReportDisplayByDaily(ReportDisplayByDaily template) {
		return reportDisplayByDailyMapper.insertSelective(template);
	}

	@Override
	public int updateReportDisplayByDaily(ReportDisplayByDaily template) {
		ReportDisplayByDailyExample condition = new ReportDisplayByDailyExample();
		ReportDisplayByDailyExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return reportDisplayByDailyMapper.updateByExampleSelective(template, condition);
	}

	@Override
	public int delReportDisplayByDaily(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReportDisplayByDaily> fetchAllReportDisplayByDaily(String date, String areaId,
			List<Concentrator> concentrators, int rows, int page,
			boolean isPagination) throws Exception {
		ReportDisplayByDailyExample condition = new ReportDisplayByDailyExample();
		ReportDisplayByDailyExample.Criteria criteria = condition.createCriteria();
		/*for (Concentrator concentrator : concentrators) {
			ReportDisplayByDailyExample.Criteria criteria = condition.createCriteria();
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
		List<ReportDisplayByDaily> reportDisplayByDaily = reportDisplayByDailyMapper.selectByExample(condition);
		return reportDisplayByDaily;
	}

	@Override
	public int fetchAllReportDisplayByDailyCount(String date, String areaId,
			List<Concentrator> concentrators) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM", "yyyyMM");
			params.put("date", vdate);
		}
		int count = reportDisplayByDailyMapper.mycountByExample(params);
		return count;
	}

	@Override
	public List<ReportDisplayByDaily> fetchAllReportDisplayByDaily2(String date,
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
		
		List<ReportDisplayByDaily> reportDisplayByDaily = reportDisplayByDailyMapper.myselectByExample(params);
		return reportDisplayByDaily;
	}

}
