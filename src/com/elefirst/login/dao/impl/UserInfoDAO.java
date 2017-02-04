package com.elefirst.login.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.login.dao.iface.IUserInfoDAO;
import com.elefirst.login.mapper.UserInfoMapper;
import com.elefirst.login.po.UserInfo;
import com.elefirst.login.po.UserInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/5.
 */
@Repository
public class UserInfoDAO extends BaseDAO implements IUserInfoDAO {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getUserInfoList(UserInfoExample example) {
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public int getUserInfoListCount(UserInfoExample example) {
        return userInfoMapper.countByExample(example);
    }

    @Override
    public int addUserInfo(UserInfo template) {
        return userInfoMapper.insertSelective(template);
    }

    @Override
    public int updateUserInfo(UserInfoExample example, UserInfo template) {
        return userInfoMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delUserInfo(UserInfoExample example) {
        return userInfoMapper.deleteByExample(example);
    }
}
