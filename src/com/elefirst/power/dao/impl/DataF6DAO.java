package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF6DAO;
import com.elefirst.power.mapper.DataF6Mapper;
import com.elefirst.power.po.DataF6;
import com.elefirst.power.po.DataF6Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF6DAO extends BaseDAO implements IDataF6DAO {
    @Autowired
    private DataF6Mapper dataF6Mapper;


    @Override
    public List<DataF6> getDataF6List(DataF6Example example) {
        return dataF6Mapper.selectByExample(example);
    }

    @Override
    public long getDataF6ListCount(DataF6Example example) {
        return dataF6Mapper.countByExample(example);
    }


    @Override
    public int addDataF6(DataF6 template) {
        return dataF6Mapper.insertSelective(template);
    }

    @Override
    public int updateDataF6(DataF6Example example, DataF6 template) {
        return dataF6Mapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF6(DataF6Example example) {
        return dataF6Mapper.deleteByExample(example);
    }
}