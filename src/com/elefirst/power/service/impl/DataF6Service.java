package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF6DAO;
import com.elefirst.power.po.DataF6;
import com.elefirst.power.po.DataF6Example;
import com.elefirst.power.service.iface.IDataF6Service;
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
public class DataF6Service extends BaseService implements IDataF6Service {
    @Autowired
    private IDataF6DAO dataF6DAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF6> getDataF6List(DataF6 template) {
        DataF6Example condition = new DataF6Example();
        DataF6Example.Criteria criteria = condition.createCriteria();

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
        return dataF6DAO.getDataF6List(condition);
    }

    @Override
    public List<DataF6> getDataF6List(List<DataF6> nodes, String startDate, String endDate) {
        DataF6Example condition = new DataF6Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF6 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenDayGreaterThanOrEqualTo(startDate)
                    .andFrozenDayLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`frozen_day` ASC");
        return dataF6DAO.getDataF6List(condition);
    }

    @Override
    public long getDataF6ListCount(DataF6 template) {
        DataF6Example condition = new DataF6Example();
        DataF6Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF6DAO.getDataF6ListCount(condition);
    }

    @Override
    public List<DataF6> getDataF6Detail(String id) {
        DataF6Example condition = new DataF6Example();
        DataF6Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF6DAO.getDataF6List(condition);
    }

    @Override
    public int addDataF6(DataF6 template) {
        return dataF6DAO.addDataF6(template);
    }

    @Override
    public int updateDataF6(DataF6 template) {
        DataF6Example condition = new DataF6Example();
        DataF6Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF6DAO.updateDataF6(condition, template);
    }

    @Override
    public int delDataF6(String id) {
        DataF6Example condition = new DataF6Example();
        DataF6Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF6DAO.delDataF6(condition);
    }

    @Override
    public List<DataF6> format(List<DataF6> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF6> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF6 item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalpositivereactivepower(calc(item.getTotalpositivereactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF6 item) {
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