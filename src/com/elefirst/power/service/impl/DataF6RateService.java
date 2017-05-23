package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF6RateDAO;
import com.elefirst.power.po.DataF6Rate;
import com.elefirst.power.po.DataF6RateExample;
import com.elefirst.power.service.iface.IDataF6RateService;
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
public class DataF6RateService extends BaseService implements IDataF6RateService {
    @Autowired
    private IDataF6RateDAO dataF6RateDAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF6Rate> getDataF6RateList(DataF6Rate template) {
        DataF6RateExample condition = new DataF6RateExample();
        DataF6RateExample.Criteria criteria = condition.createCriteria();

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
        condition.setOrderByClause("`frozen_day` ASC");
        return dataF6RateDAO.getDataF6RateList(condition);
    }

    @Override
    public List<DataF6Rate> getDataF6RateList(List<DataF6Rate> nodes, String startDate, String endDate) {
        DataF6RateExample condition = new DataF6RateExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF6Rate node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenDayGreaterThanOrEqualTo(startDate)
                    .andFrozenDayLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`frozen_day` ASC");
        return dataF6RateDAO.getDataF6RateList(condition);
    }

    @Override
    public long getDataF6RateListCount(DataF6Rate template) {
        DataF6RateExample condition = new DataF6RateExample();
        DataF6RateExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF6RateDAO.getDataF6RateListCount(condition);
    }

    @Override
    public List<DataF6Rate> getDataF6RateDetail(String id) {
        DataF6RateExample condition = new DataF6RateExample();
        DataF6RateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF6RateDAO.getDataF6RateList(condition);
    }

    @Override
    public int addDataF6Rate(DataF6Rate template) {
        return dataF6RateDAO.addDataF6Rate(template);
    }

    @Override
    public int updateDataF6Rate(DataF6Rate template) {
        DataF6RateExample condition = new DataF6RateExample();
        DataF6RateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF6RateDAO.updateDataF6Rate(condition, template);
    }

    @Override
    public int delDataF6Rate(String id) {
        DataF6RateExample condition = new DataF6RateExample();
        DataF6RateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF6RateDAO.delDataF6Rate(condition);
    }

    @Override
    public List<DataF6Rate> format(List<DataF6Rate> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF6Rate> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF6Rate item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setPositivereactivepower(calc(item.getPositivereactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF6Rate item) {
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