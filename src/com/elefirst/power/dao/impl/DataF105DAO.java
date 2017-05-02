package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF105DAO;
import com.elefirst.power.mapper.DataF105CustomMapper;
import com.elefirst.power.mapper.DataF105Mapper;
import com.elefirst.power.po.DataF105;
import com.elefirst.power.po.DataF105Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF105DAO extends BaseDAO implements IDataF105DAO {
    @Autowired
    private DataF105Mapper dataF105Mapper;

    @Autowired
    private DataF105CustomMapper dataF105CustomMapper;

    @Override
    public List<DataF105> getDataF105List(DataF105Example example) {
        return dataF105Mapper.selectByExample(example);
    }

    @Override
    public List<DataF105> getDataF105ByHourList(DataF105Example example) {
        return dataF105CustomMapper.selectGroupByHourByExample(example);
    }

    @Override
    public List<DataF105> getDataF105SumList(DataF105Example example) {
        return dataF105CustomMapper.selectSumByExample(example);
    }

    @Override
    public long getDataF105ListCount(DataF105Example example) {
        return dataF105Mapper.countByExample(example);
    }


    @Override
    public int addDataF105(DataF105 template) {
        return dataF105Mapper.insertSelective(template);
    }

    @Override
    public int updateDataF105(DataF105Example example, DataF105 template) {
        return dataF105Mapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF105(DataF105Example example) {
        return dataF105Mapper.deleteByExample(example);
    }
}