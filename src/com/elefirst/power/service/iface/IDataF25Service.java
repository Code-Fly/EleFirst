package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF25;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF25Service {
    List<DataF25> getDataF25List(DataF25 template);

    List<DataF25> getDataF25List(List<DataF25> node, String startDate, String endDate);

    int getDataF25ListCount(DataF25 template);

    List<DataF25> getDataF25Detail(String id);

    int addDataF25(DataF25 template);

    int updateDataF25(DataF25 template);

    int delDataF25(String id);
}
