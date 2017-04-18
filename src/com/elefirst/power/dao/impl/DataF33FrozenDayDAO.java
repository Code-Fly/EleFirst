package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF33FrozenDayDAO;
import com.elefirst.power.mapper.DataF33FrozenDayCustomMapper;
import com.elefirst.power.mapper.DataF33FrozenDayMapper;
import com.elefirst.power.po.DataF33FrozenDay;
import com.elefirst.power.po.DataF33FrozenDayExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF33FrozenDayDAO extends BaseDAO implements IDataF33FrozenDayDAO {
    @Autowired
    private DataF33FrozenDayMapper dataF33FrozenDayMapper;

    @Autowired
    private DataF33FrozenDayCustomMapper dataF33FrozenDayCustomMapper;

    @Override
    public List<DataF33FrozenDay> getDataF33FrozenDayList(DataF33FrozenDayExample example) {
        return dataF33FrozenDayMapper.selectByExample(example);
    }

    @Override
    public List<DataF33FrozenDay> getDataF33FrozenDaySumList(DataF33FrozenDayExample example) {
        return dataF33FrozenDayCustomMapper.selectSumByExample(example);
    }

    @Override
    public int getDataF33FrozenDayListCount(DataF33FrozenDayExample example) {
        return dataF33FrozenDayMapper.countByExample(example);
    }

    @Override
    public int addDataF33FrozenDay(DataF33FrozenDay template) {
        return dataF33FrozenDayMapper.insertSelective(template);
    }

    @Override
    public int updateDataF33FrozenDay(DataF33FrozenDayExample example, DataF33FrozenDay template) {
        return dataF33FrozenDayMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF33FrozenDay(DataF33FrozenDayExample example) {
        return dataF33FrozenDayMapper.deleteByExample(example);
    }
}