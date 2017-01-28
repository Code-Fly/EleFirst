package com.elefirst.system.service.iface;

import com.elefirst.system.po.AreaInfo;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IAreaInfoService {
    List<AreaInfo> getAreaInfoList(AreaInfo template);

    int getAreaInfoListCount(AreaInfo template);

    List<AreaInfo> getAreaInfoDetail(String id);

    int addAreaInfo(AreaInfo template);

    int updateAreaInfo(AreaInfo template);

    int delAreaInfo(String id);
}
