package com.elefirst.system.service.iface;

import com.elefirst.system.po.RoleInfo;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IRoleInfoService {
    List<RoleInfo> getRoleInfoList(RoleInfo template);

    List<RoleInfo> getRoleInfoListByInfos(List<RoleInfo> templates);

    long getRoleInfoListCount(RoleInfo template);

    List<RoleInfo> getRoleInfoDetail(String id);

    int addRoleInfo(RoleInfo template);

    int updateRoleInfo(RoleInfo template);

    int delRoleInfo(String id);
}
