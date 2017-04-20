package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataF105;
import com.elefirst.power.po.DataF105Example;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IDataF105DAO {
    List<DataF105> getDataF105List(DataF105Example example);

    List<DataF105> getDataF105SumList(DataF105Example example);

    long getDataF105ListCount(DataF105Example example);

    int addDataF105(DataF105 template);

    int updateDataF105(DataF105Example example, DataF105 template);

    int delDataF105(DataF105Example example);
}
