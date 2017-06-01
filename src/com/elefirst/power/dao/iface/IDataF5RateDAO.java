package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataF5Rate;
import com.elefirst.power.po.DataF5RateExample;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IDataF5RateDAO {
    List<DataF5Rate> getDataF5RateList(DataF5RateExample example);

    List<DataF5Rate> getDataF5RateSumList(DataF5RateExample example);

    long getDataF5RateListCount(DataF5RateExample example);

    int addDataF5Rate(DataF5Rate template);

    int updateDataF5Rate(DataF5RateExample example, DataF5Rate template);

    int delDataF5Rate(DataF5RateExample example);
}
