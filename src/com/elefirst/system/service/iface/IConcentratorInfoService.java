package com.elefirst.system.service.iface;

import com.elefirst.system.po.ConcentratorInfo;

import java.util.List;

/**
 * Created by barrie on 17/1/28.
 */
public interface IConcentratorInfoService {
    List<ConcentratorInfo> getConcentratorInfoList(ConcentratorInfo template);

    List<ConcentratorInfo> getConcentratorInfoListByInfos(List<ConcentratorInfo> templates);

    int getConcentratorInfoListCount(ConcentratorInfo template);

    List<ConcentratorInfo> getConcentratorInfoDetail(String id);

    int addConcentratorInfo(ConcentratorInfo template);

    int updateConcentratorInfo(ConcentratorInfo template);

    int updateConcentratorInfoByInfo(ConcentratorInfo template);

    int delConcentratorInfo(String id);

    int delConcentratorInfoByInfo(ConcentratorInfo template);
}
