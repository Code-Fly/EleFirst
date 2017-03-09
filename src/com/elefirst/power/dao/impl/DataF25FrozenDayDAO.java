package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF25FrozenDayDAO;
import com.elefirst.power.mapper.DataF25FrozenDayCustomMapper;
import com.elefirst.power.mapper.DataF25FrozenDayMapper;
import com.elefirst.power.po.DataF25FrozenDay;
import com.elefirst.power.po.DataF25FrozenDayExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF25FrozenDayDAO extends BaseDAO implements IDataF25FrozenDayDAO {
    @Autowired
    private DataF25FrozenDayMapper dataF25FrozenDayMapper;

    @Autowired
    private DataF25FrozenDayCustomMapper dataF25FrozenDayCustomMapper;

    @Override
    public List<DataF25FrozenDay> getDataF25FrozenDayList(DataF25FrozenDayExample example) {
        return dataF25FrozenDayMapper.selectByExample(example);
    }

    @Override
    public List<DataF25FrozenDay> getDataF25FrozenDaySumList(DataF25FrozenDayExample example) {
        return dataF25FrozenDayCustomMapper.selectSumByExample(example);
    }

    @Override
    public int getDataF25FrozenDayListCount(DataF25FrozenDayExample example) {
        return dataF25FrozenDayMapper.countByExample(example);
    }

    @Override
    public int addDataF25FrozenDay(DataF25FrozenDay template) {
        return dataF25FrozenDayMapper.insertSelective(template);
    }

    @Override
    public int updateDataF25FrozenDay(DataF25FrozenDayExample example, DataF25FrozenDay template) {
        return dataF25FrozenDayMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF25FrozenDay(DataF25FrozenDayExample example) {
        return dataF25FrozenDayMapper.deleteByExample(example);
    }
}