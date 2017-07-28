package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF5;
import com.elefirst.power.po.DataF5WithRate;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF5Service {
    List<DataF5> getDataF5List(DataF5 template);

    List<DataF5> getDataF5List(List<DataF5> node, String startDate, String endDate);

    //

    List<DataF5> getDataF5SumList(DataF5 template);

    List<DataF5> getDataF5SumList(List<DataF5> nodes, String startTime, String endTime);

    //
    List<DataF5WithRate> getDataF5WithRateList(DataF5 template);

    List<DataF5WithRate> getDataF5WithRateList(List<DataF5> node, String startDate, String endDate);
    //

    long getDataF5WithRateListCount(DataF5 template);

    long getDataF5ListCount(DataF5 template);

    List<DataF5> getDataF5Detail(String id);

    int addDataF5(DataF5 template);

    int updateDataF5(DataF5 template);

    int delDataF5(String id);


    List<DataF5> format(List<DataF5> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF5 item);

    String calc(String org, Double num, Integer precision);

    //

    String getDifferTotalPositiveActivePower(List<DataF5> nodes, String startTime, String endTime);
}
