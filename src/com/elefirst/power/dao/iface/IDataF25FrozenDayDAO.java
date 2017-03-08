package com.elefirst.power.dao.iface;


import com.elefirst.power.po.DataF25FrozenDay;
import com.elefirst.power.po.DataF25FrozenDayExample;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF25FrozenDayDAO {
    List<DataF25FrozenDay> getDataF25FrozenDayList(DataF25FrozenDayExample example);

    int getDataF25FrozenDayListCount(DataF25FrozenDayExample example);

    int addDataF25FrozenDay(DataF25FrozenDay template);

    int updateDataF25FrozenDay(DataF25FrozenDayExample example, DataF25FrozenDay template);

    int delDataF25FrozenDay(DataF25FrozenDayExample example);
}
