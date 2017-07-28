package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF5DAO;
import com.elefirst.power.mapper.DataF5CustomMapper;
import com.elefirst.power.mapper.DataF5Mapper;
import com.elefirst.power.po.DataF5;
import com.elefirst.power.po.DataF5Example;
import com.elefirst.power.po.DataF5WithRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF5DAO extends BaseDAO implements IDataF5DAO {
    @Autowired
    private DataF5Mapper dataF5Mapper;

    @Autowired
    private DataF5CustomMapper dataF5CustomMapper;

    @Override
    public List<DataF5> getDataF5List(DataF5Example example) {
        return dataF5Mapper.selectByExample(example);
    }

    @Override
    public List<DataF5> getDataF5SumList(DataF5Example example) {
        return dataF5CustomMapper.selectSumByExample(example);
    }

    @Override
    public List<DataF5WithRate> getDataF5WithRateList(DataF5Example example) {
        return dataF5CustomMapper.selectWithRateByExample(example);
    }

    @Override
    public long getDataF5ListCount(DataF5Example example) {
        return dataF5Mapper.countByExample(example);
    }

    @Override
    public long getDataF5WithRateListCount(DataF5Example example) {
        return dataF5CustomMapper.countWithRateByExample(example);
    }


    @Override
    public int addDataF5(DataF5 template) {
        return dataF5Mapper.insertSelective(template);
    }

    @Override
    public int updateDataF5(DataF5Example example, DataF5 template) {
        return dataF5Mapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF5(DataF5Example example) {
        return dataF5Mapper.deleteByExample(example);
    }
}