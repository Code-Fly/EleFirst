package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataF25FrozenMinuteDAO;
import com.elefirst.power.mapper.DataF25FrozenMinuteCustomMapper;
import com.elefirst.power.mapper.DataF25FrozenMinuteMapper;
import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.DataF25FrozenMinuteExample;
import com.elefirst.power.po.DataF25FrozenMinuteWithF21;
import com.elefirst.power.po.DataF25FrozenMinuteWithF5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataF25FrozenMinuteDAO extends BaseDAO implements IDataF25FrozenMinuteDAO {
    @Autowired
    private DataF25FrozenMinuteMapper dataF25FrozenMinuteMapper;

    @Autowired
    private DataF25FrozenMinuteCustomMapper dataF25FrozenMinuteCustomMapper;

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteList(DataF25FrozenMinuteExample example) {
        return dataF25FrozenMinuteMapper.selectByExample(example);
    }

    @Override
    public List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(DataF25FrozenMinuteExample example) {
        return dataF25FrozenMinuteCustomMapper.selectSumByExample(example);
    }

    @Override
    public List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteSumWithF5List(DataF25FrozenMinuteExample example) {
        return dataF25FrozenMinuteCustomMapper.selectSumWithF5ByExample(example);
    }

    @Override
    public List<DataF25FrozenMinuteWithF21> getDataF25FrozenMinuteSumWithF21List(DataF25FrozenMinuteExample example) {
        return dataF25FrozenMinuteCustomMapper.selectSumWithF21ByExample(example);
    }

    @Override
    public List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteStatisticsWithF5List(DataF25FrozenMinuteExample example) {
        return dataF25FrozenMinuteCustomMapper.selectStatisticsWithF5ByExample(example);
    }

    @Override
    public int getDataF25FrozenMinuteListCount(DataF25FrozenMinuteExample example) {
        return dataF25FrozenMinuteMapper.countByExample(example);
    }

    @Override
    public int addDataF25FrozenMinute(DataF25FrozenMinute template) {
        return dataF25FrozenMinuteMapper.insertSelective(template);
    }

    @Override
    public int updateDataF25FrozenMinute(DataF25FrozenMinuteExample example, DataF25FrozenMinute template) {
        return dataF25FrozenMinuteMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataF25FrozenMinute(DataF25FrozenMinuteExample example) {
        return dataF25FrozenMinuteMapper.deleteByExample(example);
    }
}