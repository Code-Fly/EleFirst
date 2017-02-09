package com.elefirst.system.dao.iface;

import com.elefirst.system.po.AreaInfoExample;
import com.elefirst.system.po.AreaInfoWithBLOBs;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IAreaInfoDAO {
    List<AreaInfoWithBLOBs> getAreaInfoList(AreaInfoExample example);

    long getAreaInfoListCount(AreaInfoExample example);

    int addAreaInfo(AreaInfoWithBLOBs template);

    int updateAreaInfo(AreaInfoExample example, AreaInfoWithBLOBs template);

    int delAreaInfo(AreaInfoExample example);
}
