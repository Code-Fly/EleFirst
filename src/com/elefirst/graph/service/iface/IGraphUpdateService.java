package com.elefirst.graph.service.iface;

import com.elefirst.power.po.DataF25;
import com.elefirst.power.po.PnStat;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IGraphUpdateService {
    List<DataF25> getLatestCurrentPnList(DataF25 template);

    int getLatestCurrentPnListCount(DataF25 template);

    List<PnStat> getLatestPnStatList(PnStat template);

    int getLatestPnStatListCount(PnStat template);
}
