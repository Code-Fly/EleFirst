package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataF21;
import com.elefirst.power.po.DataF21Example;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IDataF21DAO {
    List<DataF21> getDataF21List(DataF21Example example);

    List<DataF21> getDataF21SumList(DataF21Example example);

    long getDataF21ListCount(DataF21Example example);

    int addDataF21(DataF21 template);

    int updateDataF21(DataF21Example example, DataF21 template);

    int delDataF21(DataF21Example example);
}
