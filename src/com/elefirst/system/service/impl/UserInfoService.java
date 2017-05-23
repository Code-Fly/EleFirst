package com.elefirst.system.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.system.dao.iface.IUserInfoDAO;
import com.elefirst.system.po.UserInfo;
import com.elefirst.system.po.UserInfoCustom;
import com.elefirst.system.po.UserInfoExample;
import com.elefirst.system.service.iface.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/1/29.
 */
@Service
public class UserInfoService extends BaseService implements IUserInfoService {
    @Autowired
    private IUserInfoDAO userInfoDAO;

    @Override
    public List<UserInfo> getUserInfoList(UserInfo template) {
        UserInfoExample condition = new UserInfoExample();
        UserInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getUserCode()) {
            criteria.andUserCodeEqualTo(template.getUserCode());
        }
        if (null != template && null != template.getUserName()) {
            criteria.andUserNameEqualTo(template.getUserName());
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`create_date` ASC");
        return userInfoDAO.getUserInfoList(condition);
    }

    @Override
    public List<UserInfo> getUserInfoListByInfos(List<UserInfo> templates) {
        UserInfoExample condition = new UserInfoExample();
        for (int i = 0; i < templates.size(); i++) {
            UserInfoExample.Criteria criteria = condition.createCriteria();
            UserInfo template = templates.get(i);

            if (null != template && null != template.getId()) {
                criteria.andIdEqualTo(template.getId());
            }
            if (null != template && null != template.getUserCode()) {
                criteria.andUserCodeEqualTo(template.getUserCode());
            }
            if (null != template && null != template.getUserName()) {
                criteria.andUserNameEqualTo(template.getUserName());
            }

            if (template.getRows() > 0 && template.getPage() > 0) {
                condition.setLimitStart((template.getPage() - 1) * template.getRows());
                condition.setLimitEnd(template.getRows());
            }
            condition.or(criteria);
        }

        condition.setOrderByClause("`create_date` ASC");
        return userInfoDAO.getUserInfoList(condition);
    }

    @Override
    public List<UserInfoCustom> getUserInfoExtends(UserInfo template) {
        UserInfoExample condition = new UserInfoExample();
        UserInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andUserNameEqualTo(template.getUserName());
        condition.setOrderByClause("`create_date` ASC");
        return userInfoDAO.getUserInfoExtends(condition);
    }

    @Override
    public long getUserInfoListCount(UserInfo template) {
        UserInfoExample condition = new UserInfoExample();
        UserInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getId()) {
            criteria.andIdEqualTo(template.getId());
        }
        if (null != template && null != template.getUserCode()) {
            criteria.andUserCodeEqualTo(template.getUserCode());
        }
        if (null != template && null != template.getUserName()) {
            criteria.andUserNameEqualTo(template.getUserName());
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
