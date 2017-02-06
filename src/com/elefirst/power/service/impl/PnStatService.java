package com.elefirst.power.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.power.dao.iface.IPnStatDAO;
import com.elefirst.power.po.PnStat;
import com.elefirst.power.po.PnStatExample;
import com.elefirst.power.service.iface.IPnStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Service
public class PnStatService extends BaseService implements IPnStatService {
    @Autowired
    private IPnStatDAO pnStatDAO;

    @Override
    public List<PnStat> getPnStatList(PnStat template) {
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
    public int getPnStatListCount(PnStat template) {
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

    @Override
    public List<PnStat> getPnStatDetail(String id) {
        PnStatExample condition = new PnStatExample();
        PnStatExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return pnStatDAO.getPnStatList(condition);
    }

    @Override
    public int addPnStat(PnStat template) {
        return pnStatDAO.addPnStat(template);
    }

    @Override
    public int updatePnStat(PnStat template) {
        PnStatExample condition = new PnStatExample();
        PnStatExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return pnStatDAO.updatePnStat(condition, template);
    }

    @Override
    public int delPnStat(String id) {
        PnStatExample condition = new PnStatExample();
        PnStatExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return pnStatDAO.delPnStat(condition);
    }
}