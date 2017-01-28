package com.elefirst.system.dao.iface;

import com.elefirst.system.po.AreaInfo;
import com.elefirst.system.po.AreaInfoExample;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IAreaInfoDAO {
    List<AreaInfo> getAreaInfoList(AreaInfoExample example);

    int getAreaInfoListCount(AreaInfoExample example);

    int addAreaInfo(AreaInfo template);

    int updateAreaInfo(AreaInfoExample example, AreaInfo template);

    int delAreaInfo(AreaInfoExample example);
}
