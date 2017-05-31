package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF5RateDAO;
import com.elefirst.power.mapper.DataF5RateCustomMapper;
import com.elefirst.power.mapper.DataF5RateMapper;
import com.elefirst.power.po.DataF5Rate;
import com.elefirst.power.po.DataF5RateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF5RateDAO extends BaseDAO implements IDataF5RateDAO {
    @Autowired
    private DataF5RateMapper dataF5RateMapper;

    @Autowired
    private DataF5RateCustomMapper dataF5RateCustomMapper;

    @Override
    public List<DataF5Rate> getDataF5RateList(DataF5RateExample example) {
        return dataF5RateMapper.selectByExample(example);
    }

    @Override
    public List<DataF5Rate> getDataF5RateSumList(DataF5RateExample example) {
        return dataF5RateCustomMapper.selectSumByExample(example);
    }

    @Override
    public long getDataF5RateListCount(DataF5RateExample example) {
        return dataF5RateMapper.countByExample(example);
    }


    @Override
    public int addDataF5Rate(DataF5Rate template) {
        return dataF5RateMapper.insertSelective(template);
    }

    @Override
    public int updateDataF5Rate(DataF5RateExample example, DataF5Rate template) {
        return dataF5RateMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF5Rate(DataF5RateExample example) {
        return dataF5RateMapper.deleteByExample(example);
    }
}