package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF161;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF161Service {
    List<DataF161> getDataF161List(DataF161 template);

    List<DataF161> getDataF161List(DataF161 template, String startDate, String endDate);

    List<DataF161> getDataF161List(List<DataF161> node, String startDate, String endDate);

    //

    long getDataF161ListCount(DataF161 template);

    List<DataF161> getDataF161Detail(String id);

    int addDataF161(DataF161 template);

    int updateDataF161(DataF161 template);

    int delDataF161(String id);


    List<DataF161> format(List<DataF161> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF161 item);

    String calc(String org, Double num, Integer precision);

    //
}
