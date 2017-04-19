package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF6RateDAO;
import com.elefirst.power.mapper.DataF6RateMapper;
import com.elefirst.power.po.DataF6Rate;
import com.elefirst.power.po.DataF6RateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF6RateDAO extends BaseDAO implements IDataF6RateDAO {
    @Autowired
    private DataF6RateMapper dataF6RateMapper;


    @Override
    public List<DataF6Rate> getDataF6RateList(DataF6RateExample example) {
        return dataF6RateMapper.selectByExample(example);
    }

    @Override
    public long getDataF6RateListCount(DataF6RateExample example) {
        return dataF6RateMapper.countByExample(example);
    }


    @Override
    public int addDataF6Rate(DataF6Rate template) {
        return dataF6RateMapper.insertSelective(template);
    }

    @Override
    public int updateDataF6Rate(DataF6RateExample example, DataF6Rate template) {
        return dataF6RateMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF6Rate(DataF6RateExample example) {
        return dataF6RateMapper.deleteByExample(example);
    }
}