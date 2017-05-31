package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF21RateDAO;
import com.elefirst.power.mapper.DataF21RateCustomMapper;
import com.elefirst.power.mapper.DataF21RateMapper;
import com.elefirst.power.po.DataF21Rate;
import com.elefirst.power.po.DataF21RateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF21RateDAO extends BaseDAO implements IDataF21RateDAO {
    @Autowired
    private DataF21RateMapper dataF21RateRateMapper;

    @Autowired
    private DataF21RateCustomMapper dataF21RateRateCustomMapper;

    @Override
    public List<DataF21Rate> getDataF21RateList(DataF21RateExample example) {
        return dataF21RateRateMapper.selectByExample(example);
    }

    @Override
    public List<DataF21Rate> getDataF21RateSumList(DataF21RateExample example) {
        return dataF21RateRateCustomMapper.selectSumByExample(example);
    }

    @Override
    public long getDataF21RateListCount(DataF21RateExample example) {
        return dataF21RateRateMapper.countByExample(example);
    }


    @Override
    public int addDataF21Rate(DataF21Rate template) {
        return dataF21RateRateMapper.insertSelective(template);
    }

    @Override
    public int updateDataF21Rate(DataF21RateExample example, DataF21Rate template) {
        return dataF21RateRateMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF21Rate(DataF21RateExample example) {
        return dataF21RateRateMapper.deleteByExample(example);
    }
}