package com.elefirst.system.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.system.dao.iface.IAreaInfoDAO;
import com.elefirst.system.po.AreaInfo;
import com.elefirst.system.po.AreaInfoExample;
import com.elefirst.system.service.iface.IAreaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
@Service
public class AreaInfoService extends BaseService implements IAreaInfoService {
    @Autowired
    private IAreaInfoDAO areaInfoDAO;

    @Override
    public List<AreaInfo> getAreaInfoList(AreaInfo template) {
        AreaInfoExample condition = new AreaInfoExample();
        AreaInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`create_date` ASC");
        return areaInfoDAO.getAreaInfoList(condition);
    }

    @Override
    public int getAreaInfoListCount(AreaInfo template) {
        AreaInfoExample condition = new AreaInfoExample();
        AreaInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getAreaId()) {
            criteria.andAreaIdEqualTo(template.getAreaId());
        }
        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        return areaInfoDAO.getAreaInfoListCount(condition);
    }

    @Override
    public List<AreaInfo> getAreaInfoDetail(String id) {
        AreaInfoExample condition = new AreaInfoExample();
        AreaInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return areaInfoDAO.getAreaInfoList(condition);
    }

    @Override
    public int addAreaInfo(AreaInfo template) {
        return areaInfoDAO.addAreaInfo(template);
    }

    @Override
    public int updateAreaInfo(AreaInfo template) {
        AreaInfoExample condition = new AreaInfoExample();
        AreaInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return areaInfoDAO.updateAreaInfo(condition, template);
    }

    @Override
    public int delAreaInfo(String id) {
        AreaInfoExample condition = new AreaInfoExample();
        AreaInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return areaInfoDAO.delAreaInfo(condition);
    }
}
