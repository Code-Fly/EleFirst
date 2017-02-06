package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.power.dao.iface.IPnStatDAO;
import com.elefirst.power.mapper.PnStatMapper;
import com.elefirst.power.po.PnStat;
import com.elefirst.power.po.PnStatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class PnStatDAO extends BaseDAO implements IPnStatDAO {
    @Autowired
    private PnStatMapper pnStatMapper;

    @Override
    public List<PnStat> getPnStatList(PnStatExample example) {
        return pnStatMapper.selectByExample(example);
    }

    @Override
    public int getPnStatListCount(PnStatExample example) {
        return pnStatMapper.countByExample(example);
    }

    @Override
    public int addPnStat(PnStat template) {
        return pnStatMapper.insertSelective(template);
    }

    @Override
    public int updatePnStat(PnStatExample example, PnStat template) {
        return pnStatMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delPnStat(PnStatExample example) {
        return pnStatMapper.deleteByExample(example);
    }
}