package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataF6Rate;
import com.elefirst.power.po.DataF6RateExample;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IDataF6RateDAO {
    List<DataF6Rate> getDataF6RateList(DataF6RateExample example);

    long getDataF6RateListCount(DataF6RateExample example);

    int addDataF6Rate(DataF6Rate template);

    int updateDataF6Rate(DataF6RateExample example, DataF6Rate template);

    int delDataF6Rate(DataF6RateExample example);
}
