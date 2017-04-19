package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataF6;
import com.elefirst.power.po.DataF6Example;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IDataF6DAO {
    List<DataF6> getDataF6List(DataF6Example example);

    long getDataF6ListCount(DataF6Example example);

    int addDataF6(DataF6 template);

    int updateDataF6(DataF6Example example, DataF6 template);

    int delDataF6(DataF6Example example);
}
