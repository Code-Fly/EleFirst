package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF33FrozenDay;

import java.text.ParseException;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF33FrozenDayService {
    List<DataF33FrozenDay> getDataF33FrozenDayList(DataF33FrozenDay template);

    List<DataF33FrozenDay> getDataF33FrozenDayList(List<DataF33FrozenDay> nodes, String startTime, String endTime);

    List<DataF33FrozenDay> getDataF33FrozenDayList(DataF33FrozenDay node, String time) throws ParseException;

    List<DataF33FrozenDay> getDataF33FrozenDayList(List<DataF33FrozenDay> nodes, List<String> times) throws ParseException;

    int getDataF33FrozenDayListCount(DataF33FrozenDay template);

    List<DataF33FrozenDay> getDataF33FrozenDayDetail(String id);

    int addDataF33FrozenDay(DataF33FrozenDay template);

    int updateDataF33FrozenDay(DataF33FrozenDay template);

    int delDataF33FrozenDay(String id);

    List<DataF33FrozenDay> format(List<DataF33FrozenDay> data);
}
