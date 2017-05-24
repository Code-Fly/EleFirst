package com.elefirst.system.service.iface;

import com.elefirst.system.po.AreaInfo;
import com.elefirst.system.po.AreaInfoWithBLOBs;
import com.elefirst.system.po.RoleAreaMap;
import com.elefirst.system.po.RoleInfo;
import com.elefirst.system.po.UserRoleMap;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IAreaInfoService {
    List<AreaInfoWithBLOBs> getAreaInfoList(AreaInfoWithBLOBs template);

    long getAreaInfoListCount(AreaInfoWithBLOBs template);

    List<AreaInfoWithBLOBs> getAreaInfoDetail(String id);

    int addAreaInfo(AreaInfoWithBLOBs template);

    int updateAreaInfo(AreaInfoWithBLOBs template);

    int delAreaInfo(String id);
    
    public List<AreaInfoWithBLOBs> fetchAreaInfoByRoleId(AreaInfo areaInfo,
			boolean isPagination, String roleId) throws Exception;
    
    public List<AreaInfoWithBLOBs> fetchAreaInfoByCond(AreaInfo areaInfo, boolean isPagination) throws Exception;
    
    public void saveRoleAreaMaps(List<RoleAreaMap> roleAreaMap, String roleId) throws Exception;
}
