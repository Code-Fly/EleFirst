package com.elefirst.system.service.iface;

import com.elefirst.system.po.RoleInfo;
import com.elefirst.system.po.UserRoleMap;

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
    
    public List<RoleInfo> fetchRoleInfoByUserId(RoleInfo roleInfo, boolean isPagination, String userId)
			throws Exception;
    public List<RoleInfo> fetchRoleInfoByCond(RoleInfo roleInfo, boolean isPagination) throws Exception;
    
    public void saveUserRoleMaps(List<UserRoleMap> userRoleMaps, String personId) throws Exception;
}
