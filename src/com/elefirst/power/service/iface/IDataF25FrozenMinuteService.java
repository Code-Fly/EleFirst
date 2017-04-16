package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.StatisticTotalActivePower;
import com.elefirst.system.po.PnInfo;

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

    //

    int getDataF25FrozenMinuteListCount(DataF25FrozenMinute template);

    List<DataF25FrozenMinute> getDataF25FrozenMinuteDetail(String id);

    int addDataF25FrozenMinute(DataF25FrozenMinute template);

    int updateDataF25FrozenMinute(DataF25FrozenMinute template);

    int delDataF25FrozenMinute(String id);

    List<DataF25FrozenMinute> format(List<DataF25FrozenMinute> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF25FrozenMinute item);

    String calc(String org, Double num, Integer precision);

    //

    List<DataF25FrozenMinute> getMaxValue(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String key);

    List<DataF25FrozenMinute> getMinValue(List<DataF25FrozenMinute> nodes, String startTime, String endTime, String key);

    //

    StatisticTotalActivePower getStatisticTotalActivePower(List<DataF25FrozenMinute> nodes, String startTime, String endTime);
}
