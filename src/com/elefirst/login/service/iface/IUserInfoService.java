package com.elefirst.login.service.iface;

import com.elefirst.login.po.UserInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/4.
 */
public interface IUserInfoService {
    List<UserInfo> getUserInfoList(UserInfo template);

    int getUserInfoListCount(UserInfo template);

    List<UserInfo> getUserInfoDetailByUserName(String userName);

    List<UserInfo> getUserInfoDetailById(String id);

    int addUserInfo(UserInfo template);

    int updateUserInfo(UserInfo template);

    int delUserInfo(String id);
}
