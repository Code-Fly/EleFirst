package com.elefirst.power.dao.iface;


import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.DataF25FrozenMinuteExample;
import com.elefirst.power.po.DataF25FrozenMinuteWithF21;
import com.elefirst.power.po.DataF25FrozenMinuteWithF5;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF25FrozenMinuteDAO {
    List<DataF25FrozenMinute> getDataF25FrozenMinuteList(DataF25FrozenMinuteExample example);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(DataF25FrozenMinuteExample example);

    List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteSumWithF5List(DataF25FrozenMinuteExample example);

    List<DataF25FrozenMinuteWithF21> getDataF25FrozenMinuteSumWithF21List(DataF25FrozenMinuteExample example);

    List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteStatisticsWithF5DailyList(DataF25FrozenMinuteExample example);


    int getDataF25FrozenMinuteListCount(DataF25FrozenMinuteExample example);

    int addDataF25FrozenMinute(DataF25FrozenMinute template);

    int updateDataF25FrozenMinute(DataF25FrozenMinuteExample example, DataF25FrozenMinute template);

    int delDataF25FrozenMinute(DataF25FrozenMinuteExample example);
}
