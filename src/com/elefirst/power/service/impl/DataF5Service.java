package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF5DAO;
import com.elefirst.power.po.DataF5;
import com.elefirst.power.po.DataF5Example;
import com.elefirst.power.po.DataF5WithRate;
import com.elefirst.power.service.iface.IDataF5Service;
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
public class DataF5Service extends BaseService implements IDataF5Service {
    @Autowired
    private IDataF5DAO dataF5DAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF5> getDataF5List(DataF5 template) {
        DataF5Example condition = new DataF5Example();
        DataF5Example.Criteria criteria = condition.createCriteria();

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
        condition.setOrderByClause("`sendTime` ASC");
        return dataF5DAO.getDataF5List(condition);
    }

    @Override
    public List<DataF5> getDataF5List(List<DataF5> nodes, String startDate, String endDate) {
        DataF5Example condition = new DataF5Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF5 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenDayGreaterThanOrEqualTo(startDate)
                    .andFrozenDayLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`frozen_day` ASC");
        return dataF5DAO.getDataF5List(condition);
    }

    @Override
    public List<DataF5> getDataF5SumList(DataF5 template) {
        DataF5Example condition = new DataF5Example();
        DataF5Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        criteria.andFrozenDayIsNotNull();
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`frozen_day` ASC");
        return dataF5DAO.getDataF5SumList(condition);
    }

    @Override
    public List<DataF5> getDataF5SumList(List<DataF5> nodes, String startTime, String endTime) {
        DataF5Example condition = new DataF5Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF5 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenDayGreaterThanOrEqualTo(startTime)
                    .andFrozenDayLessThan(endTime)
                    .andFrozenDayIsNotNull()
                    //
                    .andTotalpositiveactivepowerIsNotNull()
            ;
        }
        condition.setOrderByClause("`frozen_day` ASC");
        return dataF5DAO.getDataF5SumList(condition);
    }

    @Override
    public List<DataF5WithRate> getDataF5WithRateList(DataF5 template) {
        DataF5Example condition = new DataF5Example();
        DataF5Example.Criteria criteria = condition.createCriteria();

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
        condition.setOrderByClause("`sendTime` ASC");
        return dataF5DAO.getDataF5WithRateList(condition);
    }

    @Override
    public List<DataF5WithRate> getDataF5WithRateList(List<DataF5> nodes, String startDate, String endDate) {
        DataF5Example condition = new DataF5Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF5 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozenDayGreaterThanOrEqualTo(startDate)
                    .andFrozenDayLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`frozen_day` ASC");
        return dataF5DAO.getDataF5WithRateList(condition);
    }

    @Override
    public long getDataF5WithRateListCount(DataF5 template) {
        DataF5Example condition = new DataF5Example();
        DataF5Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF5DAO.getDataF5WithRateListCount(condition);
    }

    @Override
    public long getDataF5ListCount(DataF5 template) {
        DataF5Example condition = new DataF5Example();
        DataF5Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF5DAO.getDataF5ListCount(condition);
    }

    @Override
    public List<DataF5> getDataF5Detail(String id) {
        DataF5Example condition = new DataF5Example();
        DataF5Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF5DAO.getDataF5List(condition);
    }

    @Override
    public int addDataF5(DataF5 template) {
        return dataF5DAO.addDataF5(template);
    }

    @Override
    public int updateDataF5(DataF5 template) {
        DataF5Example condition = new DataF5Example();
        DataF5Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF5DAO.updateDataF5(condition, template);
    }

    @Override
    public int delDataF5(String id) {
        DataF5Example condition = new DataF5Example();
        DataF5Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF5DAO.delDataF5(condition);
    }

    @Override
    public String getDifferTotalPositiveActivePower(List<DataF5> nodes, String startTime, String endTime) {
        Double total = null;

        List<DataF5> totalActivePowerList = format(getDataF5SumList(nodes, startTime, endTime));

        for (int i = 0; i < totalActivePowerList.size(); i++) {
            if (null == total) {
                total = 0D;
            }
            total += Double.valueOf(totalActivePowerList.get(i).getTotalpositiveactivepower());
        }

        return null == total ? null : String.valueOf(total);
    }

    @Override
    public List<DataF5> format(List<DataF5> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF5> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF5 item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalpositiveactivepower(calc(item.getTotalpositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF5 item) {
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