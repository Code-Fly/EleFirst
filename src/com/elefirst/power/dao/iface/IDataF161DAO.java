package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataF161;
import com.elefirst.power.po.DataF161Example;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IDataF161DAO {
    List<DataF161> getDataF161List(DataF161Example example);

    long getDataF161ListCount(DataF161Example example);

    int addDataF161(DataF161 template);

    int updateDataF161(DataF161Example example, DataF161 template);

    int delDataF161(DataF161Example example);
}
