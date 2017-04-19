package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF6;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF6Service {
    List<DataF6> getDataF6List(DataF6 template);

    List<DataF6> getDataF6List(List<DataF6> node, String startDate, String endDate);

    //

    long getDataF6ListCount(DataF6 template);

    List<DataF6> getDataF6Detail(String id);

    int addDataF6(DataF6 template);

    int updateDataF6(DataF6 template);

    int delDataF6(String id);


    List<DataF6> format(List<DataF6> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF6 item);

    String calc(String org, Double num, Integer precision);
}
