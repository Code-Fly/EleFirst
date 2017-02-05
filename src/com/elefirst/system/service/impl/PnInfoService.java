package com.elefirst.system.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.system.dao.iface.IPnInfoDAO;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.po.PnInfoExample;
import com.elefirst.system.service.iface.IPnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
@Service
public class PnInfoService extends BaseService implements IPnInfoService {
    @Autowired
    private IPnInfoDAO pnInfoDAO;

    @Override
    public List<PnInfo> getPnInfoList(PnInfo template) {
        PnInfoExample condition = new PnInfoExample();
        PnInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`create_date` ASC");
        return pnInfoDAO.getPnInfoList(condition);
    }

    @Override
    public int getPnInfoListCount(PnInfo template) {
        PnInfoExample condition = new PnInfoExample();
        PnInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getPn()) {
            criteria.andPnEqualTo(template.getPn());
        }
        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        return pnInfoDAO.getPnInfoListCount(condition);
    }

    @Override
    public List<PnInfo> getPnInfoDetail(String id) {
        PnInfoExample condition = new PnInfoExample();
        PnInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return pnInfoDAO.getPnInfoList(condition);
    }

    @Override
    public int addPnInfo(PnInfo template) {
        return pnInfoDAO.addPnInfo(template);
    }

    @Override
    public int updatePnInfo(PnInfo template) {
        PnInfoExample condition = new PnInfoExample();
        PnInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return pnInfoDAO.updatePnInfo(condition, template);
    }

    @Override
    public int delPnInfo(String id) {
        PnInfoExample condition = new PnInfoExample();
        PnInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return pnInfoDAO.delPnInfo(condition);
    }
}
