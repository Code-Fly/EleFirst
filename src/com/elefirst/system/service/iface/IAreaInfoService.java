package com.elefirst.system.service.iface;

import com.elefirst.system.po.AreaInfoWithBLOBs;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IAreaInfoService {
    List<AreaInfoWithBLOBs> getAreaInfoList(AreaInfoWithBLOBs template);

    long getAreaInfoListCount(AreaInfoWithBLOBs template);

    List<AreaInfoWithBLOBs> getAreaInfoDetail(String id);

    int addAreaInfo(AreaInfoWithBLOBs template);

    int updateAreaInfo(AreaInfoWithBLOBs template);

    int delAreaInfo(String id);
}
