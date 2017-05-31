package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF21Rate;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF21RateService {
    List<DataF21Rate> getDataF21RateList(DataF21Rate template);

    List<DataF21Rate> getDataF21RateList(List<DataF21Rate> node, String startDate, String endDate);

    //

    List<DataF21Rate> getDataF21RateSumList(DataF21Rate template);

    List<DataF21Rate> getDataF21RateSumList(List<DataF21Rate> nodes, String startTime, String endTime);
    //

    long getDataF21RateListCount(DataF21Rate template);

    List<DataF21Rate> getDataF21RateDetail(String id);

    int addDataF21Rate(DataF21Rate template);

    int updateDataF21Rate(DataF21Rate template);

    int delDataF21Rate(String id);


    List<DataF21Rate> format(List<DataF21Rate> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF21Rate item);

    String calc(String org, Double num, Integer precision);
}
