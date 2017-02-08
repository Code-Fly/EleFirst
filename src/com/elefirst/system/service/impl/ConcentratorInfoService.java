package com.elefirst.system.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.system.dao.iface.IConcentratorInfoDAO;
import com.elefirst.system.po.ConcentratorInfo;
import com.elefirst.system.po.ConcentratorInfoExample;
import com.elefirst.system.service.iface.IConcentratorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/1/28.
 */
@Service
public class ConcentratorInfoService extends BaseService implements IConcentratorInfoService {
    @Autowired
    private IConcentratorInfoDAO concentratorInfoDAO;

    @Override
    public List<ConcentratorInfo> getConcentratorInfoList(ConcentratorInfo template) {
        ConcentratorInfoExample condition = new ConcentratorInfoExample();
        ConcentratorInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`create_date` ASC");
        return concentratorInfoDAO.getConcentratorInfoList(condition);
    }

    @Override
    public List<ConcentratorInfo> getConcentratorInfoListByInfos(List<ConcentratorInfo> templates) {
        ConcentratorInfoExample condition = new ConcentratorInfoExample();

        for (int i = 0; i < templates.size(); i++) {
            ConcentratorInfoExample.Criteria criteria = condition.createCriteria();
            ConcentratorInfo template = templates.get(i);
            if (null != template && null != template.getAreaId()) {
                criteria.andAreaIdEqualTo(template.getAreaId());
            }
            if (null != template && null != template.getConcentratorId()) {
                criteria.andConcentratorIdEqualTo(template.getConcentratorId());
            }
            if (null != template && null != template.getName()) {
                criteria.andNameLike("%" + template.getName() + "%");
            }
            if (template.getRows() > 0 && template.getPage() > 0) {
                condition.setLimitStart((template.getPage() - 1) * template.getRows());
                condition.setLimitEnd(template.getRows());
            }

            condition.or(criteria);
        }
        condition.setOrderByClause("`create_date` ASC");
        return concentratorInfoDAO.getConcentratorInfoList(condition);
    }

    @Override
    public int getConcentratorInfoListCount(ConcentratorInfo template) {
        ConcentratorInfoExample condition = new ConcentratorInfoExample();
        ConcentratorInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        return concentratorInfoDAO.getConcentratorInfoListCount(condition);
    }

    @Override
    public List<ConcentratorInfo> getConcentratorInfoDetail(String id) {
        ConcentratorInfoExample condition = new ConcentratorInfoExample();
        ConcentratorInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return concentratorInfoDAO.getConcentratorInfoList(condition);
    }

    @Override
    public int addConcentratorInfo(ConcentratorInfo template) {
        return concentratorInfoDAO.addConcentratorInfo(template);
    }

    @Override
    public int updateConcentratorInfo(ConcentratorInfo template) {
        ConcentratorInfoExample condition = new ConcentratorInfoExample();
        ConcentratorInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return concentratorInfoDAO.updateConcentratorInfo(condition, template);
    }

    @Override
    public int updateConcentratorInfoByInfo(ConcentratorInfo template) {
        ConcentratorInfoExample condition = new ConcentratorInfoExample();
        ConcentratorInfoExample.Criteria criteria = condition.createCriteria();
        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        return concentratorInfoDAO.updateConcentratorInfo(condition, template);
    }

    @Override
    public int delConcentratorInfo(String id) {
        ConcentratorInfoExample condition = new ConcentratorInfoExample();
        ConcentratorInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return concentratorInfoDAO.delConcentratorInfo(condition);
    }

    @Override
    public int delConcentratorInfoByInfo(ConcentratorInfo template) {
        ConcentratorInfoExample condition = new ConcentratorInfoExample();
        ConcentratorInfoExample.Criteria criteria = condition.createCriteria();
        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getConcentratorId()) {
            criteria.andConcentratorIdEqualTo(template.getConcentratorId());
        }
        return concentratorInfoDAO.delConcentratorInfo(condition);
    }
}
