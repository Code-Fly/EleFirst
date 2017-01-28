package com.elefirst.system.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.system.dao.iface.IAreaInfoDAO;
import com.elefirst.system.mapper.AreaInfoMapper;
import com.elefirst.system.po.AreaInfo;
import com.elefirst.system.po.AreaInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
@Repository
public class AreaInfoDAO extends BaseDAO implements IAreaInfoDAO {
    @Autowired
    private AreaInfoMapper areaInfoMapper;

    @Override
    public List<AreaInfo> getAreaInfoList(AreaInfoExample example) {
        return areaInfoMapper.selectByExample(example);
    }

    @Override
    public int getAreaInfoListCount(AreaInfoExample example) {
        return areaInfoMapper.countByExample(example);
    }

    @Override
    public int addAreaInfo(AreaInfo template) {
        return areaInfoMapper.insertSelective(template);
    }

    @Override
    public int updateAreaInfo(AreaInfoExample example, AreaInfo template) {
        return areaInfoMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delAreaInfo(AreaInfoExample example) {
        return areaInfoMapper.deleteByExample(example);
    }
}
