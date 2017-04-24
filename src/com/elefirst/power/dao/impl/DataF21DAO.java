package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF21DAO;
import com.elefirst.power.mapper.DataF21CustomMapper;
import com.elefirst.power.mapper.DataF21Mapper;
import com.elefirst.power.po.DataF21;
import com.elefirst.power.po.DataF21Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF21DAO extends BaseDAO implements IDataF21DAO {
    @Autowired
    private DataF21Mapper dataF21Mapper;

    @Autowired
    private DataF21CustomMapper dataF21CustomMapper;

    @Override
    public List<DataF21> getDataF21List(DataF21Example example) {
        return dataF21Mapper.selectByExample(example);
    }

    @Override
    public List<DataF21> getDataF21SumList(DataF21Example example) {
        return dataF21CustomMapper.selectSumByExample(example);
    }

    @Override
    public long getDataF21ListCount(DataF21Example example) {
        return dataF21Mapper.countByExample(example);
    }


    @Override
    public int addDataF21(DataF21 template) {
        return dataF21Mapper.insertSelective(template);
    }

    @Override
    public int updateDataF21(DataF21Example example, DataF21 template) {
        return dataF21Mapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF21(DataF21Example example) {
        return dataF21Mapper.deleteByExample(example);
    }
}