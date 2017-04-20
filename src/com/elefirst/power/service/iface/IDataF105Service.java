package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataF105;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataF105Service {
    List<DataF105> getDataF105List(DataF105 template);

    List<DataF105> getDataF105List(List<DataF105> node, String startDate, String endDate);

    //

    List<DataF105> getDataF105SumList(DataF105 template);

    List<DataF105> getDataF105SumList(List<DataF105> nodes, String startTime, String endTime);

    //

    long getDataF105ListCount(DataF105 template);

    List<DataF105> getDataF105Detail(String id);

    int addDataF105(DataF105 template);

    int updateDataF105(DataF105 template);

    int delDataF105(String id);


    List<DataF105> format(List<DataF105> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataF105 item);

    String calc(String org, Double num, Integer precision);
}
