package com.elefirst.power.service.iface;

import com.elefirst.power.po.DataT031;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IDataT031Service {
    List<DataT031> getDataT031List(DataT031 template);

    List<DataT031> getDataT031List(DataT031 template, String startDate, String endDate, String hour, String minute);

    List<DataT031> getDataT031List(List<DataT031> node, String startDate, String endDate);

    //

    long getDataT031ListCount(DataT031 template);

    List<DataT031> getDataT031Detail(String id);

    int addDataT031(DataT031 template);

    int updateDataT031(DataT031 template);

    int delDataT031(String id);


    List<DataT031> format(List<DataT031> data);

    PnInfo getPnInfo(List<PnInfo> pnInfos, DataT031 item);

    String calc(String org, Double num, Integer precision);

    //
}
