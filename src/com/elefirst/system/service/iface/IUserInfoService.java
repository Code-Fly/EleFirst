package com.elefirst.system.service.iface;

import com.elefirst.system.po.UserInfo;
import com.elefirst.system.po.UserInfoCustom;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IUserInfoService {
    List<UserInfo> getUserInfoList(UserInfo template);

    List<UserInfo> getUserInfoListByInfos(List<UserInfo> templates);

    List<UserInfoCustom> getUserInfoExtends(UserInfo template);

    long getUserInfoListCount(UserInfo template);

    List<UserInfo> getUserInfoDetail(String id);

    int addUserInfo(UserInfo template);

    int updateUserInfo(UserInfo template);

    int delUserInfo(String id);
}
