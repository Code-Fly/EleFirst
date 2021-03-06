package com.elefirst.report.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.report.dao.iface.IReportEnergyByHourDAO;
import com.elefirst.report.mapper.ReportEnergyByHourMapper;
import com.elefirst.report.po.ReportEnergyByHour;
import com.elefirst.report.po.ReportEnergyByHourExample;
import com.elefirst.report.service.iface.IReportEnergyByHourService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class ReportEnergyByHourService extends BaseService implements IReportEnergyByHourService {
    @Autowired
    private IReportEnergyByHourDAO reportEnergyByHourDAO;

    @Resource(name = "reportEnergyByHourMapper")
	private ReportEnergyByHourMapper reportEnergyByHourMapper;
    
    @Override
    public List<ReportEnergyByHour> getReportEnergyByHourList(ReportEnergyByHour template) {
        ReportEnergyByHourExample condition = new ReportEnergyByHourExample();
        ReportEnergyByHourExample.Criteria criteria = condition.createCriteria();

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
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`operation_time` ASC");
        return reportEnergyByHourDAO.getReportEnergyByHourList(condition);
    }

    @Override
    public List<ReportEnergyByHour> getReportEnergyByHourList(List<ReportEnergyByHour> nodes, String startDate, String endDate) {
        ReportEnergyByHourExample condition = new ReportEnergyByHourExample();
        for (int i = 0; i < nodes.size(); i++) {
            ReportEnergyByHour node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andOperationTimeGreaterThanOrEqualTo(startDate)
                    .andOperationTimeLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`operation_time` ASC");
        return reportEnergyByHourDAO.getReportEnergyByHourList(condition);
    }

    @Override
    public long getReportEnergyByHourListCount(ReportEnergyByHour template) {
        ReportEnergyByHourExample condition = new ReportEnergyByHourExample();
        ReportEnergyByHourExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return reportEnergyByHourDAO.getReportEnergyByHourListCount(condition);
    }

    @Override
    public List<ReportEnergyByHour> getReportEnergyByHourDetail(String id) {
        ReportEnergyByHourExample condition = new ReportEnergyByHourExample();
        ReportEnergyByHourExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return reportEnergyByHourDAO.getReportEnergyByHourList(condition);
    }

    @Override
    public int addReportEnergyByHour(ReportEnergyByHour template) {
        return reportEnergyByHourDAO.addReportEnergyByHour(template);
    }

    @Override
    public int updateReportEnergyByHour(ReportEnergyByHour template) {
        ReportEnergyByHourExample condition = new ReportEnergyByHourExample();
        ReportEnergyByHourExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return reportEnergyByHourDAO.updateReportEnergyByHour(condition, template);
    }

    @Override
    public int delReportEnergyByHour(String id) {
        ReportEnergyByHourExample condition = new ReportEnergyByHourExample();
        ReportEnergyByHourExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return reportEnergyByHourDAO.delReportEnergyByHour(condition);
    }

	@Override
	public List<ReportEnergyByHour> fetchAllReportEnergyByHour(String date, String areaId,
			List<Concentrator> concentrators, int rows, int page,
			boolean isPagination) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
			params.put("date", vdate);
		}
		if (rows > 0 && page > 0) {
			params.put("limitStart", (page - 1) * rows);
			params.put("limitEnd", rows);
		}
		
		List<ReportEnergyByHour> reportEnergyByHour = reportEnergyByHourMapper.myselectByExample(params);
		return reportEnergyByHour;
	}
	
	@Override
	public int fetchAllReportEnergyByHourCount(String date, String areaId,
			List<Concentrator> concentrators) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
			
		if(date != null && date.length() > 0){
			String vdate = com.elefirst.base.utils.DateUtil.StringPattern(date, "yyyy-MM-dd", "yyyyMMdd");
			params.put("date", vdate);
		}
		int count = reportEnergyByHourMapper.mycountByExample(params);
		return count;
	}
}