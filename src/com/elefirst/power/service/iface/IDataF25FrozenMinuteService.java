package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF25FrozenMinute;

import java.text.ParseException;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF25FrozenMinuteService {
    List<DataF25FrozenMinute> getDataF25FrozenMinuteList(DataF25FrozenMinute template);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteList(List<DataF25FrozenMinute> nodes, String startTime, String endTime);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteList(DataF25FrozenMinute node, String time) throws ParseException;

    List<DataF25FrozenMinute> getDataF25FrozenMinuteList(List<DataF25FrozenMinute> nodes, List<String> times) throws ParseException;

    //

    List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(DataF25FrozenMinute template);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(List<DataF25FrozenMinute> nodes, String startTime, String endTime);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(DataF25FrozenMinute node, String time) throws ParseException;

    List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(List<DataF25FrozenMinute> nodes, List<String> times) throws ParseException;

    List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(List<DataF25FrozenMinute> nodes, String time) throws ParseException;
    //

    int getDataF25FrozenMinuteListCount(DataF25FrozenMinute template);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteDetail(String id);

    int addDataF25FrozenMinute(DataF25FrozenMinute template);

    int updateDataF25FrozenMinute(DataF25FrozenMinute template);

    int delDataF25FrozenMinute(String id);

    List<DataF25FrozenMinute> format(List<DataF25FrozenMinute> data);
}
