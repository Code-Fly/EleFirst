package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF33;

import java.util.List;

/**
 * Created by VM on 2/11/2017.
 */
public interface IDataF33Service {
    List<DataF33> getDataF33List(DataF33 template);

    List<DataF33> getDataF33List(List<DataF33> nodes, String startDate, String endDate);

    long getDataF33ListCount(DataF33 template);

    List<DataF33> getDataF33Detail(String id);

    int addDataF33(DataF33 template);

    int updateDataF33(DataF33 template);

    int delDataF33(String id);
}
