package com.elefirst.graph.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.graph.service.iface.IGraphUpdateService;
import com.elefirst.power.dao.iface.IDataF25DAO;
import com.elefirst.power.po.DataF25;
import com.elefirst.power.po.DataF25Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class GraphUpdateService extends BaseService implements IGraphUpdateService {
    @Autowired
    private IDataF25DAO dataF25DAO;


    @Override
    public List<DataF25> getLatestCurrentPnList(DataF25 template) {
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
        condition.setOrderByClause("`sendTime` DESC");
        return dataF25DAO.getDataF25List(condition);
    }

    @Override
    public int getLatestCurrentPnListCount(DataF25 template) {
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

        condition.setOrderByClause("`clientOperationTime` DESC");
        return dataF25DAO.getDataF25ListCount(condition);
    }
}