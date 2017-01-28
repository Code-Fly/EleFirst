package com.elefirst.system.dao.iface;

import com.elefirst.system.po.ConcentratorInfo;
import com.elefirst.system.po.ConcentratorInfoExample;

import java.util.List;

/**
 * Created by barrie on 17/1/28.
 */
public interface IConcentratorInfoDAO {
    List<ConcentratorInfo> getConcentratorInfoList(ConcentratorInfoExample example);

    int getConcentratorInfoListCount(ConcentratorInfoExample example);

    int addConcentratorInfo(ConcentratorInfo template);

    int updateConcentratorInfo(ConcentratorInfoExample example, ConcentratorInfo template);

    int delConcentratorInfo(ConcentratorInfoExample example);

}
