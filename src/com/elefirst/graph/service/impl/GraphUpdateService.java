package com.elefirst.graph.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.graph.service.iface.IGraphUpdateService;
import com.elefirst.power.dao.iface.IDataF25FrozenMinuteDAO;
import com.elefirst.power.dao.iface.IPnStatDAO;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.DataF25FrozenMinuteExample;
import com.elefirst.power.po.PnStat;
import com.elefirst.power.po.PnStatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class GraphUpdateService extends BaseService implements IGraphUpdateService {
    @Autowired
    private IDataF25FrozenMinuteDAO dataF25FrozenMinuteDAO;

    @Autowired
    private IPnStatDAO pnStatDAO;


    @Override
    public List<DataF25FrozenMinute> getLatestCurrentPnList(DataF25FrozenMinute template) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        criteria.andACurrentIsNotNull();
        criteria.andBCurrentIsNotNull();
        criteria.andCCurrentIsNotNull();
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`clientOperationTime` DESC");
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteList(condition);
    }

    @Override
    public int getLatestCurrentPnListCount(DataF25FrozenMinute template) {
        DataF25FrozenMinuteExample condition = new DataF25FrozenMinuteExample();
        DataF25FrozenMinuteExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        condition.setOrderByClause("`clientOperationTime` DESC");
        return dataF25FrozenMinuteDAO.getDataF25FrozenMinuteListCount(condition);
    }

    @Override
    public List<PnStat> getLatestPnStatList(PnStat template) {
        PnStatExample condition = new PnStatExample();
        PnStatExample.Criteria criteria = condition.createCriteria();

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
        return pnStatDAO.getPnStatList(condition);
    }

    @Override
    public int getLatestPnStatListCount(PnStat template) {
        PnStatExample condition = new PnStatExample();
        PnStatExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        return pnStatDAO.getPnStatListCount(condition);
    }
}
