package com.elefirst.power.dao.iface;

import com.elefirst.power.po.DataF33;
import com.elefirst.power.po.DataF33Example;

import java.util.List;

/**
 * Created by VM on 2/11/2017.
 */
public interface IDataF33DAO {
    List<DataF33> getDataF33List(DataF33Example example);

    long getDataF33ListCount(DataF33Example example);

    int addDataF33(DataF33 template);

    int updateDataF33(DataF33Example example, DataF33 template);

    int delDataF33(DataF33Example example);
}
