package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF25FrozenDay;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF25FrozenDayService {
    List<DataF25FrozenDay> getDataF25FrozenDayList(DataF25FrozenDay template);

    List<DataF25FrozenDay> getDataF25FrozenDayList(List<DataF25FrozenDay> node, String startTime, String endTime);

    int getDataF25FrozenDayListCount(DataF25FrozenDay template);

    List<DataF25FrozenDay> getDataF25FrozenDayDetail(String id);

    int addDataF25FrozenDay(DataF25FrozenDay template);

    int updateDataF25FrozenDay(DataF25FrozenDay template);

    int delDataF25FrozenDay(String id);
}
