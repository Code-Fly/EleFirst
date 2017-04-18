package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF25;
import com.elefirst.system.po.PnInfo;

import java.text.ParseException;
import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF25Service {
    List<DataF25> getDataF25List(DataF25 template);

    List<DataF25> getDataF25List(List<DataF25> node, String startDate, String endDate);

    List<DataF25> getDataF25SumList(DataF25 template);

    List<DataF25> getDataF25SumList(List<DataF25> nodes, String startTime, String endTime);

    List<DataF25> getDataF25SumList(DataF25 node, String time) throws ParseException;

    List<DataF25> getDataF25SumList(List<DataF25> nodes, List<String> times) throws ParseException;

    List<DataF25> getDataF25SumList(List<DataF25> nodes, String time) throws ParseException;
    //

    int getDataF25ListCount(DataF25 template);

    List<DataF25> getDataF25Detail(String id);

    int addDataF25(DataF25 template);

    int updateDataF25(DataF25 template);

    int delDataF25(String id);

    List<DataF25> format(List<DataF25> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF25 item);

    String calc(String org, Double num, Integer precision);

    //

    List<DataF25> getMaxValue(List<DataF25> nodes, String startTime, String endTime, String key);

    List<DataF25> getMinValue(List<DataF25> nodes, String startTime, String endTime, String key);

}
