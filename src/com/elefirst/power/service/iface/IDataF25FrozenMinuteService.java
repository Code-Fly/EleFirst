package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.DataF25FrozenMinuteWithF21;
import com.elefirst.power.po.DataF25FrozenMinuteWithF5;
import com.elefirst.power.po.StatisticF25TotalActivePower;
import com.elefirst.system.po.PnInfo;

import java.text.ParseException;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF25FrozenMinuteService {
    List<DataF25FrozenMinute> getDataF25FrozenMinuteList(DataF25FrozenMinute template);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteList(List<DataF25FrozenMinute> nodes, String startTime, String endTime);

    //

    List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(DataF25FrozenMinute template);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(List<DataF25FrozenMinute> nodes, String startTime, String endTime);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteSumList(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String orderBy, String order);

    List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteSumWithF5List(List<DataF25FrozenMinute> nodes, String startTime, String endTime);

    List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteStatisticsWithF5List(DataF25FrozenMinute template, String startTime, String endTime);

    List<DataF25FrozenMinuteWithF5> getDataF25FrozenMinuteSumWithF5List(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String orderBy, String order);

    List<DataF25FrozenMinuteWithF21> getDataF25FrozenMinuteSumWithF21List(List<DataF25FrozenMinute> nodes, String startTime, String endTime);

    List<DataF25FrozenMinuteWithF21> getDataF25FrozenMinuteSumWithF21List(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String orderBy, String order);

    //

    int getDataF25FrozenMinuteListCount(DataF25FrozenMinute template);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteDetail(String id);

    int addDataF25FrozenMinute(DataF25FrozenMinute template);

    int updateDataF25FrozenMinute(DataF25FrozenMinute template);

    int delDataF25FrozenMinute(String id);

    List<DataF25FrozenMinute> format(List<DataF25FrozenMinute> data);

    List<DataF25FrozenMinuteWithF5> formatWithF5(List<DataF25FrozenMinuteWithF5> data);

    List<DataF25FrozenMinuteWithF21> formatWithF21(List<DataF25FrozenMinuteWithF21> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF25FrozenMinute item);

    String calc(String org, Double num, Integer precision);

    //

    List<DataF25FrozenMinute> getMaxValue(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String key);

    List<DataF25FrozenMinute> getMinValue(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String key);

    //

    StatisticF25TotalActivePower getF5StatisticTotalActivePower(List<DataF25FrozenMinute> nodes, String startTime, String endTime);

    StatisticF25TotalActivePower getF21StatisticTotalActivePower(List<DataF25FrozenMinute> nodes, String startTime, String endTime) throws ParseException;

}
