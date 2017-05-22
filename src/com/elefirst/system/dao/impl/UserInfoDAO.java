package com.elefirst.system.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.system.dao.iface.IUserInfoDAO;
import com.elefirst.system.mapper.UserInfoMapper;
import com.elefirst.system.po.UserInfo;
import com.elefirst.system.po.UserInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
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
    public long getUserInfoListCount(UserInfoExample example) {
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
