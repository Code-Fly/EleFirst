package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF25DAO;
import com.elefirst.power.po.DataF25;
import com.elefirst.power.po.DataF25Example;
import com.elefirst.power.service.iface.IDataF25Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class DataF25Service extends BaseService implements IDataF25Service {
    @Autowired
    private IDataF25DAO dataF25DAO;

    @Override
    public List<DataF25> getDataF25List(DataF25 template) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();

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
        return dataF25DAO.getDataF25List(condition);
    }

    @Override
    public int getDataF25ListCount(DataF25 template) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();

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
        return dataF25DAO.getDataF25ListCount(condition);
    }

    @Override
    public List<DataF25> getDataF25Detail(String id) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF25DAO.getDataF25List(condition);
    }

    @Override
    public int addDataF25(DataF25 template) {
        return dataF25DAO.addDataF25(template);
    }

    @Override
    public int updateDataF25(DataF25 template) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF25DAO.updateDataF25(condition, template);
    }

    @Override
    public int delDataF25(String id) {
        DataF25Example condition = new DataF25Example();
        DataF25Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF25DAO.delDataF25(condition);
    }
}