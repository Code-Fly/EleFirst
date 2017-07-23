package com.elefirst.power.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.power.dao.iface.IDataT031DAO;
import com.elefirst.power.mapper.DataT031Mapper;
import com.elefirst.power.po.DataT031;
import com.elefirst.power.po.DataT031Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class DataT031DAO extends BaseDAO implements IDataT031DAO {
    @Autowired
    private DataT031Mapper dataT031Mapper;

    @Override
    public List<DataT031> getDataT031List(DataT031Example example) {
        return dataT031Mapper.selectByExample(example);
    }

    @Override
    public long getDataT031ListCount(DataT031Example example) {
        return dataT031Mapper.countByExample(example);
    }

    @Override
    public int addDataT031(DataT031 template) {
        return dataT031Mapper.insertSelective(template);
    }

    @Override
    public int updateDataT031(DataT031Example example, DataT031 template) {
        return dataT031Mapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delDataT031(DataT031Example example) {
        return dataT031Mapper.deleteByExample(example);
    }
}