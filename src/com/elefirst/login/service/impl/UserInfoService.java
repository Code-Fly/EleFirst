package com.elefirst.login.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.login.dao.iface.IUserInfoDAO;
import com.elefirst.login.po.UserInfo;
import com.elefirst.login.po.UserInfoExample;
import com.elefirst.login.service.iface.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/2/5.
 */
@Service
public class UserInfoService extends BaseService implements IUserInfoService {
    @Autowired
    private IUserInfoDAO userInfoDAO;

    @Override
    public List<UserInfo> getUserInfoList(UserInfo template) {
        UserInfoExample condition = new UserInfoExample();
        UserInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getUserName()) {
            criteria.andUserNameEqualTo(template.getUserName());
        }

        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        condition.setOrderByClause("`create_date` ASC");
        return userInfoDAO.getUserInfoList(condition);
    }

    @Override
    public int getUserInfoListCount(UserInfo template) {
        UserInfoExample condition = new UserInfoExample();
        UserInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getUserName()) {
            criteria.andUserNameEqualTo(template.getUserName());
        }

        if (null != template && null != template.getName()) {
            criteria.andNameLike("%" + template.getName() + "%");
        }
        return userInfoDAO.getUserInfoListCount(condition);
    }

    @Override
    public List<UserInfo> getUserInfoDetail(String id) {
        UserInfoExample condition = new UserInfoExample();
        UserInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return userInfoDAO.getUserInfoList(condition);
    }

    @Override
    public int addUserInfo(UserInfo template) {
        return userInfoDAO.addUserInfo(template);
    }

    @Override
    public int updateUserInfo(UserInfo template) {
        UserInfoExample condition = new UserInfoExample();
        UserInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return userInfoDAO.updateUserInfo(condition, template);
    }

    @Override
    public int delUserInfo(String id) {
        UserInfoExample condition = new UserInfoExample();
        UserInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return userInfoDAO.delUserInfo(condition);
    }
}
