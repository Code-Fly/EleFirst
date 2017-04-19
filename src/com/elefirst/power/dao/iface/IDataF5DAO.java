package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataF5;
import com.elefirst.power.po.DataF5Example;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IDataF5DAO {
    List<DataF5> getDataF5List(DataF5Example example);

    List<DataF5> getDataF5SumList(DataF5Example example);

    long getDataF5ListCount(DataF5Example example);

    int addDataF5(DataF5 template);

    int updateDataF5(DataF5Example example, DataF5 template);

    int delDataF5(DataF5Example example);
}
