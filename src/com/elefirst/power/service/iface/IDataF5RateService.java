package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF5Rate;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF5RateService {
    List<DataF5Rate> getDataF5RateList(DataF5Rate template);

    List<DataF5Rate> getDataF5RateList(List<DataF5Rate> node, String startDate, String endDate);

    //

    List<DataF5Rate> getDataF5RateSumList(DataF5Rate template);

    List<DataF5Rate> getDataF5RateSumList(List<DataF5Rate> nodes, String startTime, String endTime);
    //

    long getDataF5RateListCount(DataF5Rate template);

    List<DataF5Rate> getDataF5RateDetail(String id);

    int addDataF5Rate(DataF5Rate template);

    int updateDataF5Rate(DataF5Rate template);

    int delDataF5Rate(String id);


    List<DataF5Rate> format(List<DataF5Rate> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF5Rate item);

    String calc(String org, Double num, Integer precision);
}
