package com.elefirst.login.dao.iface;

import com.elefirst.login.po.UserInfo;
import com.elefirst.login.po.UserInfoExample;

import java.util.List;

/**
 * Created by barrie on 17/2/5.
 */
public interface IUserInfoDAO {
    List<UserInfo> getUserInfoList(UserInfoExample example);

    int getUserInfoListCount(UserInfoExample example);

    int addUserInfo(UserInfo template);

    int updateUserInfo(UserInfoExample example, UserInfo template);

    int delUserInfo(UserInfoExample example);
}
