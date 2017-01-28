package com.elefirst.system.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.system.dao.iface.IPnInfoDAO;
import com.elefirst.system.mapper.PnInfoMapper;
import com.elefirst.system.po.PnInfo;
import com.elefirst.system.po.PnInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
@Repository
public class PnInfoDAO extends BaseDAO implements IPnInfoDAO {
    @Autowired
    private PnInfoMapper pnInfoMapper;

    @Override
    public List<PnInfo> getPnInfoList(PnInfoExample example) {
        return pnInfoMapper.selectByExample(example);
    }

    @Override
    public int getPnInfoListCount(PnInfoExample example) {
        return pnInfoMapper.countByExample(example);
    }

    @Override
    public int addPnInfo(PnInfo template) {
        return pnInfoMapper.insertSelective(template);
    }

    @Override
    public int updatePnInfo(PnInfoExample example, PnInfo template) {
        return pnInfoMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delPnInfo(PnInfoExample example) {
        return pnInfoMapper.deleteByExample(example);
    }
}
