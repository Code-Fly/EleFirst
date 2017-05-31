package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataF21Rate;
import com.elefirst.power.po.DataF21RateExample;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IDataF21RateDAO {
    List<DataF21Rate> getDataF21RateList(DataF21RateExample example);

    List<DataF21Rate> getDataF21RateSumList(DataF21RateExample example);

    long getDataF21RateListCount(DataF21RateExample example);

    int addDataF21Rate(DataF21Rate template);

    int updateDataF21Rate(DataF21RateExample example, DataF21Rate template);

    int delDataF21Rate(DataF21RateExample example);
}
