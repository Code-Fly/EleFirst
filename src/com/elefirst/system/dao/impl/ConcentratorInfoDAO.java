package com.elefirst.system.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.system.dao.iface.IConcentratorInfoDAO;
import com.elefirst.system.mapper.ConcentratorInfoMapper;
import com.elefirst.system.po.ConcentratorInfo;
import com.elefirst.system.po.ConcentratorInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/1/28.
 */
@Repository
public class ConcentratorInfoDAO extends BaseDAO implements IConcentratorInfoDAO {
    @Autowired
    private ConcentratorInfoMapper concentratorInfoMapper;

    @Override
    public List<ConcentratorInfo> getConcentratorInfoList(ConcentratorInfoExample example) {
        return concentratorInfoMapper.selectByExample(example);
    }

    @Override
    public int getConcentratorInfoListCount(ConcentratorInfoExample example) {
        return concentratorInfoMapper.countByExample(example);
    }

    @Override
    public int addConcentratorInfo(ConcentratorInfo template) {
        return concentratorInfoMapper.insertSelective(template);
    }

    @Override
    public int updateConcentratorInfo(ConcentratorInfoExample example, ConcentratorInfo template) {
        return concentratorInfoMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delConcentratorInfo(ConcentratorInfoExample example) {
        return concentratorInfoMapper.deleteByExample(example);
    }
}
