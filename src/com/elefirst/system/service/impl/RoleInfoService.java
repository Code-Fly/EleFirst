package com.elefirst.system.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.system.dao.iface.IRoleInfoDAO;
import com.elefirst.system.po.RoleInfo;
import com.elefirst.system.po.RoleInfoExample;
import com.elefirst.system.service.iface.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
@Service
public class RoleInfoService extends BaseService implements IRoleInfoService {
    @Autowired
    private IRoleInfoDAO roleInfoDAO;

    @Override
    public List<RoleInfo> getRoleInfoList(RoleInfo template) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getRoleCode()) {
            criteria.andRoleCodeEqualTo(template.getRoleCode());
        }
        if (null != template && null != template.getRoleName()) {
            criteria.andRoleNameEqualTo(template.getRoleName());
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`create_date` ASC");
        return roleInfoDAO.getRoleInfoList(condition);
    }

    @Override
    public List<RoleInfo> getRoleInfoListByInfos(List<RoleInfo> templates) {
        RoleInfoExample condition = new RoleInfoExample();
        for (int i = 0; i < templates.size(); i++) {
            RoleInfoExample.Criteria criteria = condition.createCriteria();
            RoleInfo template = templates.get(i);

            if (null != template && null != template.getId()) {
                criteria.andIdEqualTo(template.getId());
            }
            if (null != template && null != template.getRoleCode()) {
                criteria.andRoleCodeEqualTo(template.getRoleCode());
            }
            if (null != template && null != template.getRoleName()) {
                criteria.andRoleNameEqualTo(template.getRoleName());
            }
            if (template.getRows() > 0 && template.getPage() > 0) {
                condition.setLimitStart((template.getPage() - 1) * template.getRows());
                condition.setLimitEnd(template.getRows());
            }
            condition.or(criteria);
        }

        condition.setOrderByClause("`create_date` ASC");
        return roleInfoDAO.getRoleInfoList(condition);
    }

    @Override
    public long getRoleInfoListCount(RoleInfo template) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getRoleCode()) {
            criteria.andRoleCodeEqualTo(template.getRoleCode());
        }
        if (null != template && null != template.getRoleName()) {
            criteria.andRoleNameEqualTo(template.getRoleName());
        }
        return roleInfoDAO.getRoleInfoListCount(condition);
    }

    @Override
    public List<RoleInfo> getRoleInfoDetail(String id) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return roleInfoDAO.getRoleInfoList(condition);
    }

    @Override
    public int addRoleInfo(RoleInfo template) {
        return roleInfoDAO.addRoleInfo(template);
    }

    @Override
    public int updateRoleInfo(RoleInfo template) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return roleInfoDAO.updateRoleInfo(condition, template);
    }

    @Override
    public int delRoleInfo(String id) {
        RoleInfoExample condition = new RoleInfoExample();
        RoleInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return roleInfoDAO.delRoleInfo(condition);
    }
}
