package com.elefirst.system.service.iface;

import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IPnInfoService {
    List<PnInfo> getPnInfoList(PnInfo template);

    List<PnInfo> getPnInfoListByInfos(List<PnInfo> templates);

    int getPnInfoListCount(PnInfo template);

    List<PnInfo> getPnInfoDetail(String id);

    int addPnInfo(PnInfo template);

    int updatePnInfo(PnInfo template);

    int delPnInfo(String id);
}
