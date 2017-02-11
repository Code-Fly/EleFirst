package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.power.dao.iface.IDataF33DAO;
import com.elefirst.power.mapper.DataF33Mapper;
import com.elefirst.power.po.DataF33;
import com.elefirst.power.po.DataF33Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by VM on 2/11/2017.
 */
@Repository
public class DataF33DAO extends BaseDAO implements IDataF33DAO {
    @Autowired
    private DataF33Mapper dataF33Mapper;

    @Override
    public List<DataF33> getDataF33List(DataF33Example example) {
        return dataF33Mapper.selectByExample(example);
    }

    @Override
    public long getDataF33ListCount(DataF33Example example) {
        return dataF33Mapper.countByExample(example);
    }

    @Override
    public int addDataF33(DataF33 template) {
        return dataF33Mapper.insertSelective(template);
    }

    @Override
    public int updateDataF33(DataF33Example example, DataF33 template) {
        return dataF33Mapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF33(DataF33Example example) {
        return dataF33Mapper.deleteByExample(example);
    }
}