package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF5RateDAO;
import com.elefirst.power.po.DataF5Rate;
import com.elefirst.power.po.DataF5RateExample;
import com.elefirst.power.service.iface.IDataF5RateService;
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
public class DataF5RateService extends BaseService implements IDataF5RateService {
    @Autowired
    private IDataF5RateDAO dataF5RateDAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF5Rate> getDataF5RateList(DataF5Rate template) {
        DataF5RateExample condition = new DataF5RateExample();
        DataF5RateExample.Criteria criteria = condition.createCriteria();

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
        return dataF5RateDAO.getDataF5RateList(condition);
    }

    @Override
    public List<DataF5Rate> getDataF5RateList(List<DataF5Rate> nodes, String startDate, String endDate) {
        DataF5RateExample condition = new DataF5RateExample();
        for (int i = 0; i < nodes.size(); i++) {
            DataF5Rate node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenDayGreaterThanOrEqualTo(startDate)
                    .andFrozenDayLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`frozen_day` ASC");
        return dataF5RateDAO.getDataF5RateList(condition);
    }

    @Override
    public long getDataF5RateListCount(DataF5Rate template) {
        DataF5RateExample condition = new DataF5RateExample();
        DataF5RateExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF5RateDAO.getDataF5RateListCount(condition);
    }

    @Override
    public List<DataF5Rate> getDataF5RateDetail(String id) {
        DataF5RateExample condition = new DataF5RateExample();
        DataF5RateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF5RateDAO.getDataF5RateList(condition);
    }

    @Override
    public int addDataF5Rate(DataF5Rate template) {
        return dataF5RateDAO.addDataF5Rate(template);
    }

    @Override
    public int updateDataF5Rate(DataF5Rate template) {
        DataF5RateExample condition = new DataF5RateExample();
        DataF5RateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF5RateDAO.updateDataF5Rate(condition, template);
    }

    @Override
    public int delDataF5Rate(String id) {
        DataF5RateExample condition = new DataF5RateExample();
        DataF5RateExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF5RateDAO.delDataF5Rate(condition);
    }

    @Override
    public List<DataF5Rate> format(List<DataF5Rate> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF5Rate> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF5Rate item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setPositiveactivepower(calc(item.getPositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF5Rate item) {
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