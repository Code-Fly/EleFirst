package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF161DAO;
import com.elefirst.power.mapper.DataF161Mapper;
import com.elefirst.power.po.DataF161;
import com.elefirst.power.po.DataF161Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF161DAO extends BaseDAO implements IDataF161DAO {
    @Autowired
    private DataF161Mapper dataF161Mapper;

    @Override
    public List<DataF161> getDataF161List(DataF161Example example) {
        return dataF161Mapper.selectByExample(example);
    }

    @Override
    public long getDataF161ListCount(DataF161Example example) {
        return dataF161Mapper.countByExample(example);
    }

    @Override
    public int addDataF161(DataF161 template) {
        return dataF161Mapper.insertSelective(template);
    }

    @Override
    public int updateDataF161(DataF161Example example, DataF161 template) {
        return dataF161Mapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF161(DataF161Example example) {
        return dataF161Mapper.deleteByExample(example);
    }
}