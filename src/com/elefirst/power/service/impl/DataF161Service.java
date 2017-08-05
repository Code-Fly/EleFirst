package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF161DAO;
import com.elefirst.power.po.DataF161;
import com.elefirst.power.po.DataF161Example;
import com.elefirst.power.service.iface.IDataF161Service;
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
public class DataF161Service extends BaseService implements IDataF161Service {
    @Autowired
    private IDataF161DAO dataF161DAO;

    @Autowired
    private IPnInfoService pnInfoService;

    @Override
    public List<DataF161> getDataF161List(DataF161 template) {
        DataF161Example condition = new DataF161Example();
        DataF161Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        if (null != template && null != template.getClientoperationtime()) {
            criteria.andClientoperationtimeEqualTo(template.getClientoperationtime());
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF161DAO.getDataF161List(condition);
    }

    @Override
    public List<DataF161> getDataF161List(DataF161 template, String startDate, String endDate) {
        DataF161Example condition = new DataF161Example();
        DataF161Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }

        if (null != startDate) {
            criteria.andClientoperationtimeGreaterThanOrEqualTo(startDate);
        }

        if (null != endDate) {
            criteria.andClientoperationtimeLessThan(endDate);
        }

        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF161DAO.getDataF161List(condition);
    }

    @Override
    public List<DataF161> getDataF161List(List<DataF161> nodes, String startDate, String endDate) {
        DataF161Example condition = new DataF161Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF161 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startDate)
                    .andClientoperationtimeLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF161DAO.getDataF161List(condition);
    }


    @Override
    public long getDataF161ListCount(DataF161 template) {
        DataF161Example condition = new DataF161Example();
        DataF161Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        if (null != template && null != template.getClientoperationtime()) {
            criteria.andClientoperationtimeEqualTo(template.getClientoperationtime());
        }
        return dataF161DAO.getDataF161ListCount(condition);
    }

    @Override
    public List<DataF161> getDataF161Detail(String id) {
        DataF161Example condition = new DataF161Example();
        DataF161Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF161DAO.getDataF161List(condition);
    }

    @Override
    public int addDataF161(DataF161 template) {
        return dataF161DAO.addDataF161(template);
    }

    @Override
    public int updateDataF161(DataF161 template) {
        DataF161Example condition = new DataF161Example();
        DataF161Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF161DAO.updateDataF161(condition, template);
    }

    @Override
    public int delDataF161(String id) {
        DataF161Example condition = new DataF161Example();
        DataF161Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF161DAO.delDataF161(condition);
    }


    @Override
    public List<DataF161> format(List<DataF161> data) {
        PnInfo template = new PnInfo();
        List<PnInfo> pnInfos = pnInfoService.getPnInfoList(template);
        List<DataF161> result = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DataF161 item = data.get(i);
            PnInfo pnInfo = getPnInfo(pnInfos, item);
            if (null != pnInfo) {
                item.setTotalpositiveactivepower(calc(item.getTotalpositiveactivepower(), pnInfo.getCt() * pnInfo.getPt(), 3));
            }
            result.add(item);
        }
        return result;
    }

    @Override
    public PnInfo getPnInfo(List<PnInfo> pnInfos, DataF161 item) {
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