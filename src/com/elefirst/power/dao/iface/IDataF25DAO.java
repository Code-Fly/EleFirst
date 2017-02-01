package com.elefirst.power.dao.iface;


import com.elefirst.power.po.DataF25;
import com.elefirst.power.po.DataF25Example;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF25DAO {
    List<DataF25> getDataF25List(DataF25Example example);

    int getDataF25ListCount(DataF25Example example);

    int addDataF25(DataF25 template);

    int updateDataF25(DataF25Example example, DataF25 template);

    int delDataF25(DataF25Example example);
}
