package com.elefirst.system.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.system.dao.iface.IRoleInfoDAO;
import com.elefirst.system.mapper.RoleInfoMapper;
import com.elefirst.system.po.RoleInfo;
import com.elefirst.system.po.RoleInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
@Repository
public class RoleInfoDAO extends BaseDAO implements IRoleInfoDAO {
    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Override
    public List<RoleInfo> getRoleInfoList(RoleInfoExample example) {
        return roleInfoMapper.selectByExample(example);
    }

    @Override
    public long getRoleInfoListCount(RoleInfoExample example) {
        return roleInfoMapper.countByExample(example);
    }

    @Override
    public int addRoleInfo(RoleInfo template) {
        return roleInfoMapper.insertSelective(template);
    }

    @Override
    public int updateRoleInfo(RoleInfoExample example, RoleInfo template) {
        return roleInfoMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delRoleInfo(RoleInfoExample example) {
        return roleInfoMapper.deleteByExample(example);
    }
}
