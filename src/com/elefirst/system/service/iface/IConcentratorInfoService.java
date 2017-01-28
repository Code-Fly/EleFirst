package com.elefirst.system.service.iface;

import com.elefirst.system.po.ConcentratorInfo;

import java.util.List;

/**
 * Created by barrie on 17/1/28.
 */
public interface IConcentratorInfoService {
    List<ConcentratorInfo> getConcentratorInfoList(ConcentratorInfo template);

    int getConcentratorInfoListCount(ConcentratorInfo template);

    List<ConcentratorInfo> getConcentratorInfoDetail(String id);

    int addConcentratorInfo(ConcentratorInfo template);

    int updateConcentratorInfo(ConcentratorInfo template);

    int delConcentratorInfo(String id);
}
