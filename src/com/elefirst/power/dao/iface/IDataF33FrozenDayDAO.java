package com.elefirst.power.dao.iface;


import com.elefirst.power.po.DataF33FrozenDay;
import com.elefirst.power.po.DataF33FrozenDayExample;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF33FrozenDayDAO {
    List<DataF33FrozenDay> getDataF33FrozenDayList(DataF33FrozenDayExample example);

    List<DataF33FrozenDay> getDataF33FrozenDaySumList(DataF33FrozenDayExample example);

    int getDataF33FrozenDayListCount(DataF33FrozenDayExample example);

    int addDataF33FrozenDay(DataF33FrozenDay template);

    int updateDataF33FrozenDay(DataF33FrozenDayExample example, DataF33FrozenDay template);

    int delDataF33FrozenDay(DataF33FrozenDayExample example);
}
