package com.elefirst.system.dao.iface;

import com.elefirst.system.po.RoleInfo;
import com.elefirst.system.po.RoleInfoExample;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IRoleInfoDAO {
    List<RoleInfo> getRoleInfoList(RoleInfoExample example);

    long getRoleInfoListCount(RoleInfoExample example);

    int addRoleInfo(RoleInfo template);

    int updateRoleInfo(RoleInfoExample example, RoleInfo template);

    int delRoleInfo(RoleInfoExample example);
}
