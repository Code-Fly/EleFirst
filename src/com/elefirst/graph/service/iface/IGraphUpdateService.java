package com.elefirst.graph.service.iface;

import com.elefirst.power.po.DataF25FrozenMinute;
import com.elefirst.power.po.PnStat;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IGraphUpdateService {
    List<DataF25FrozenMinute> getLatestCurrentPnList(DataF25FrozenMinute template);

    int getLatestCurrentPnListCount(DataF25FrozenMinute template);

    List<PnStat> getLatestPnStatList(PnStat template);

    int getLatestPnStatListCount(PnStat template);
}
