package com.elefirst.graph.service.iface;

import com.elefirst.power.po.DataF25;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IGraphUpdateService {
    List<DataF25> getLatestCurrentPnList(DataF25 template);

    int getLatestCurrentPnListCount(DataF25 template);
}
