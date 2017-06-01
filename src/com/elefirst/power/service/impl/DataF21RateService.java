package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF21RateDAO;
import com.elefirst.power.po.DataF21Rate;
import com.elefirst.power.po.DataF21RateExample;
import com.elefirst.power.service.iface.IDataF21RateService;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.service.iface.IPnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class DataF21RateService extends BaseService implements IDataF21RateService {
    @Autowired
    private IDataF21RateDAO dataF21RateDAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF21Rate> getDataF21RateList(DataF21Rate template) {
        DataF21RateExample condition = new DataF21RateExample();
        DataF21RateExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`frozen_month` ASC");
        return dataF21RateDAO.getDataF21RateList(condition);
    }

    @Override
    public List<DataF21Rate> getDataF21RateList(List<DataF21Rate> nodes, String startDate, String endDate) {
        DataF21RateExample condition = new DataF21RateExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF21Rate node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenMonthGreaterThanOrEqualTo(startDate)
                    .andFrozenMonthLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`frozen_month` ASC");
        return dataF21RateDAO.getDataF21RateList(condition);
    }

    @Override
    public List<DataF21Rate> getDataF21RateSumList(DataF21Rate template) {
        DataF21RateExample condition = new DataF21RateExample();
        DataF21RateExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        criteria.andFrozenMonthIsNotNull();
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`frozen_month` ASC");
        return dataF21RateDAO.getDataF21RateSumList(condition);
    }

    @Override
    public List<DataF21Rate> getDataF21RateSumList(List<DataF21Rate> nodes, String startTime, String endTime) {
        DataF21RateExample condition = new DataF21RateExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF21Rate node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenMonthGreaterThanOrEqualTo(startTime)
                    .andFrozenMonthLessThan(endTime)
                    .andFrozenMonthIsNotNull()
                    //
                    .andPositiveactivepowerIsNotNull()
            ;
        }
        condition.setOrderByClause("`frozen_month` ASC");
        return dataF21RateDAO.getDataF21RateSumList(condition);
    }

    @Override
    public long getDataF21RateListCount(DataF21Rate template) {
        DataF21RateExample condition = new DataF21RateExample();
        DataF21RateExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF21RateDAO.getDataF21RateListCount(condition);
    }

    @Override
    public List<DataF21Rate> getDataF21RateDetail(String id) {
        DataF21RateExample condition = new DataF21RateExample();
        DataF21RateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF21RateDAO.getDataF21RateList(condition);
    }

    @Override
    public int addDataF21Rate(DataF21Rate template) {
        return dataF21RateDAO.addDataF21Rate(template);
    }

    @Override
    public int updateDataF21Rate(DataF21Rate template) {
        DataF21RateExample condition = new DataF21RateExample();
        DataF21RateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF21RateDAO.updateDataF21Rate(condition, template);
    }

    @Override
    public int delDataF21Rate(String id) {
        DataF21RateExample condition = new DataF21RateExample();
        DataF21RateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF21RateDAO.delDataF21Rate(condition);
    }

    @Override
    public List<DataF21Rate> format(List<DataF21Rate> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF21Rate> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF21Rate item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setPositiveactivepower(calc(item.getPositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF21Rate item) {
        for (int i = 0; i < pnInfos.size(); i++) {
            PnInfo pnInfo = pnInfos.get(i);
            if (pnInfo.getAreaId().equals(item.getAreaId()) && pnInfo.getConcentratorId().equals(item.getConcentratorId()) && pnInfo.getPn().equals(item.getPn())) {
                return pnInfo;
            }
        }
        return null;
    }

    @Override
    public String calc(String org, Double num, Integer precision) {
        if (null != org) {
            if (null == precision) {
                return String.valueOf(Double.valueOf(org) * num);
            } else {
                BigDecimal n1 = new BigDecimal(Double.valueOf(org));
                BigDecimal n2 = new BigDecimal(num);
                double d = n1.multiply(n2).setScale(precision, RoundingMode.HALF_UP).doubleValue();
                return String.valueOf(d);
            }
        }
        return null;
    }
}