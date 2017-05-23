package com.elefirst.system.dao.iface;

import com.elefirst.system.po.UserInfo;
import com.elefirst.system.po.UserInfoCustom;
import com.elefirst.system.po.UserInfoExample;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
public interface IUserInfoDAO {
    List<UserInfo> getUserInfoList(UserInfoExample example);

    List<UserInfoCustom> getUserInfoExtends(UserInfoExample example);

    long getUserInfoListCount(UserInfoExample example);

    int addUserInfo(UserInfo template);

    int updateUserInfo(UserInfoExample example, UserInfo template);

    int delUserInfo(UserInfoExample example);
}
