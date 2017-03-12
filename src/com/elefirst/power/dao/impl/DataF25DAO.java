package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF25DAO;
import com.elefirst.power.mapper.DataF25CustomMapper;
import com.elefirst.power.mapper.DataF25Mapper;
import com.elefirst.power.po.DataF25;
import com.elefirst.power.po.DataF25Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF25DAO extends BaseDAO implements IDataF25DAO {
    @Autowired
    private DataF25Mapper dataF25Mapper;

    @Autowired
    private DataF25CustomMapper dataF25CustomMapper;

    @Override
    public List<DataF25> getDataF25List(DataF25Example example) {
        return dataF25Mapper.selectByExample(example);
    }

    @Override
    public List<DataF25> getDataF25SumList(DataF25Example example) {
        return dataF25CustomMapper.selectSumByExample(example);
    }
    
    @Override
    public int getDataF25ListCount(DataF25Example example) {
        return dataF25Mapper.countByExample(example);
    }

    @Override
    public int addDataF25(DataF25 template) {
        return dataF25Mapper.insertSelective(template);
    }

    @Override
    public int updateDataF25(DataF25Example example, DataF25 template) {
        return dataF25Mapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF25(DataF25Example example) {
        return dataF25Mapper.deleteByExample(example);
    }
}