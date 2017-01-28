package com.elefirst.system.dao.iface;

import com.elefirst.system.po.PnInfo;
import com.elefirst.system.po.PnInfoExample;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IPnInfoDAO {
    List<PnInfo> getPnInfoList(PnInfoExample example);

    int getPnInfoListCount(PnInfoExample example);

    int addPnInfo(PnInfo template);

    int updatePnInfo(PnInfoExample example, PnInfo template);

    int delPnInfo(PnInfoExample example);
}
