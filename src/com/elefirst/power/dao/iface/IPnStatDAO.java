package com.elefirst.power.dao.iface;


import com.elefirst.power.po.PnStat;
import com.elefirst.power.po.PnStatExample;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IPnStatDAO {
    List<PnStat> getPnStatList(PnStatExample example);

    int getPnStatListCount(PnStatExample example);

    int addPnStat(PnStat template);

    int updatePnStat(PnStatExample example, PnStat template);

    int delPnStat(PnStatExample example);
}
