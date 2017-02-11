package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IDataF33DAO;
import com.elefirst.power.po.DataF33;
import com.elefirst.power.po.DataF33Example;
import com.elefirst.power.service.iface.IDataF33Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VM on 2/11/2017.
 */
@Service
public class DataF33Service extends BaseService implements IDataF33Service {
    @Autowired
    private IDataF33DAO dataF33DAO;

    @Override
    public List<DataF33> getDataF33List(DataF33 template) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();

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
        return dataF33DAO.getDataF33List(condition);
    }

    @Override
    public List<DataF33> getDataF33List(List<DataF33> nodes, String startDate, String endDate) {
        DataF33Example condition = new DataF33Example();
        for (int i = 0; i < nodes.size(); i++) {
            DataF33 node = nodes.get(i);
            condition.or()
                    .andAreaIdEqualTo(node.getAreaId())
                    .andConcentratorIdEqualTo(node.getConcentratorId())
                    .andPnEqualTo(node.getPn())
                    .andClientoperationtimeGreaterThanOrEqualTo(startDate)
                    .andClientoperationtimeLessThan(endDate)
            ;
        }
        condition.setOrderByClause("`clientOperationTime` ASC");
        return dataF33DAO.getDataF33List(condition);
    }

    @Override
    public long getDataF33ListCount(DataF33 template) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return dataF33DAO.getDataF33ListCount(condition);
    }

    @Override
    public List<DataF33> getDataF33Detail(String id) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF33DAO.getDataF33List(condition);
    }

    @Override
    public int addDataF33(DataF33 template) {
        return dataF33DAO.addDataF33(template);
    }

    @Override
    public int updateDataF33(DataF33 template) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return dataF33DAO.updateDataF33(condition, template);
    }

    @Override
    public int delDataF33(String id) {
        DataF33Example condition = new DataF33Example();
        DataF33Example.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return dataF33DAO.delDataF33(condition);
    }
}
