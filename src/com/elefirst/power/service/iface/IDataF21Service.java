package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF21;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF21Service {
    List<DataF21> getDataF21List(DataF21 template);

    List<DataF21> getDataF21List(List<DataF21> node, String startDate, String endDate);

    //

    List<DataF21> getDataF21SumList(DataF21 template);

    List<DataF21> getDataF21SumList(List<DataF21> nodes, String startTime, String endTime);

    //

    long getDataF21ListCount(DataF21 template);

    List<DataF21> getDataF21Detail(String id);

    int addDataF21(DataF21 template);

    int updateDataF21(DataF21 template);

    int delDataF21(String id);


    List<DataF21> format(List<DataF21> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF21 item);

    String calc(String org, Double num, Integer precision);

    //

    String getDifferTotalPositiveActivePower(List<DataF21> nodes, String startTime, String endTime);
}
