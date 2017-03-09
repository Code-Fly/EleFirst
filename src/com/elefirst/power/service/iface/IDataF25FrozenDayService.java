package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF25FrozenDay;

import java.text.ParseException;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF25FrozenDayService {
    List<DataF25FrozenDay> getDataF25FrozenDayList(DataF25FrozenDay template);

    List<DataF25FrozenDay> getDataF25FrozenDayList(List<DataF25FrozenDay> nodes, String startTime, String endTime);

    List<DataF25FrozenDay> getDataF25FrozenDayList(DataF25FrozenDay node, String time) throws ParseException;

    List<DataF25FrozenDay> getDataF25FrozenDayList(List<DataF25FrozenDay> nodes, List<String> times) throws ParseException;

    int getDataF25FrozenDayListCount(DataF25FrozenDay template);

    List<DataF25FrozenDay> getDataF25FrozenDayDetail(String id);

    int addDataF25FrozenDay(DataF25FrozenDay template);

    int updateDataF25FrozenDay(DataF25FrozenDay template);

    int delDataF25FrozenDay(String id);

    List<DataF25FrozenDay> format(List<DataF25FrozenDay> data);
}
