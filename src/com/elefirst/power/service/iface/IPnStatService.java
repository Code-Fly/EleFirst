package com.elefirst.power.service.iface;

import com.elefirst.power.po.PnStat;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IPnStatService {
    List<PnStat> getPnStatList(PnStat template);

    int getPnStatListCount(PnStat template);

    List<PnStat> getPnStatDetail(String id);

    int addPnStat(PnStat template);

    int updatePnStat(PnStat template);

    int delPnStat(String id);
}
