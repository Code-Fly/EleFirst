package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF105DAO;
import com.elefirst.power.po.DataF105;
import com.elefirst.power.po.DataF105Example;
import com.elefirst.power.service.iface.IDataF105Service;
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
public class DataF105Service extends BaseService implements IDataF105Service {
    @Autowired
    private IDataF105DAO dataF105DAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF105> getDataF105List(DataF105 template) {
        DataF105Example condition = new DataF105Example();
        DataF105Example.Criteria criteria = condition.createCriteria();

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
        condition.setOrderByClause("`frozenTime` ASC");
        return dataF105DAO.getDataF105List(condition);
    }

    @Override
    public List<DataF105> getDataF105List(List<DataF105> nodes, String startDate, String endDate) {
        DataF105Example condition = new DataF105Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF105 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozentimeGreaterThanOrEqualTo(startDate)
                    .andFrozentimeLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`frozenTime` ASC");
        return dataF105DAO.getDataF105List(condition);
    }

    @Override
    public List<DataF105> getDataF105ByHourList(List<DataF105> nodes, String startDate, String endDate) {
        DataF105Example condition = new DataF105Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF105 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozentimeGreaterThanOrEqualTo(startDate)
                    .andFrozentimeLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`frozenTime` ASC");
        return dataF105DAO.getDataF105ByHourList(condition);
    }

    @Override
    public List<DataF105> getDataF105SumList(DataF105 template) {
        DataF105Example condition = new DataF105Example();
        DataF105Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        criteria.andFrozentimeIsNotNull();
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`frozenTime` ASC");
        return dataF105DAO.getDataF105SumList(condition);
    }

    @Override
    public List<DataF105> getDataF105SumList(List<DataF105> nodes, String startTime, String endTime) {
        DataF105Example condition = new DataF105Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF105 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andFrozentimeGreaterThanOrEqualTo(startTime)
                    .andFrozentimeLessThan(endTime)
                    .andFrozentimeIsNotNull()
                    //
                    .andActivepowerIsNotNull()
            ;
        }
        condition.setOrderByClause("`frozenTime` ASC");
        return dataF105DAO.getDataF105SumList(condition);
    }

    @Override
    public long getDataF105ListCount(DataF105 template) {
        DataF105Example condition = new DataF105Example();
        DataF105Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF105DAO.getDataF105ListCount(condition);
    }

    @Override
    public List<DataF105> getDataF105Detail(String id) {
        DataF105Example condition = new DataF105Example();
        DataF105Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF105DAO.getDataF105List(condition);
    }

    @Override
    public int addDataF105(DataF105 template) {
        return dataF105DAO.addDataF105(template);
    }

    @Override
    public int updateDataF105(DataF105 template) {
        DataF105Example condition = new DataF105Example();
        DataF105Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF105DAO.updateDataF105(condition, template);
    }

    @Override
    public int delDataF105(String id) {
        DataF105Example condition = new DataF105Example();
        DataF105Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF105DAO.delDataF105(condition);
    }

    @Override
    public List<DataF105> format(List<DataF105> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF105> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF105 item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setActivepower(calc(item.getActivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF105 item) {
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