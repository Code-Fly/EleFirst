package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF6Rate;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF6RateService {
    List<DataF6Rate> getDataF6RateList(DataF6Rate template);

    List<DataF6Rate> getDataF6RateList(List<DataF6Rate> node, String startDate, String endDate);

    //

    long getDataF6RateListCount(DataF6Rate template);

    List<DataF6Rate> getDataF6RateDetail(String id);

    int addDataF6Rate(DataF6Rate template);

    int updateDataF6Rate(DataF6Rate template);

    int delDataF6Rate(String id);


    List<DataF6Rate> format(List<DataF6Rate> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF6Rate item);

    String calc(String org, Double num, Integer precision);
}
