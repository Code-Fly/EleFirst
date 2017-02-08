package com.elefirst.system.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.system.dao.iface.ITreeInfoDAO;
import com.elefirst.system.po.TreeInfo;
import com.elefirst.system.po.TreeInfoExample;
import com.elefirst.system.service.iface.ITreeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by barrie on 17/1/30.
 */
@Service
public class TreeInfoService extends BaseService implements ITreeInfoService {
    @Autowired
    private ITreeInfoDAO treeInfoDAO;

    @Override
    public List<TreeInfo> getTreeInfoList(TreeInfo template) {
        TreeInfoExample condition = new TreeInfoExample();
        TreeInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getTreeId()) {
            criteria.andTreeIdEqualTo(template.getTreeId());
        }
        if (template.getRows() > 0 && template.getPage() > 0) {
            condition.setLimitStart((template.getPage() - 1) * template.getRows());
            condition.setLimitEnd(template.getRows());
        }
        condition.setOrderByClause("`create_date` ASC");
        return treeInfoDAO.getTreeInfoList(condition);
    }

    @Override
    public int getTreeInfoListCount(TreeInfo template) {
        TreeInfoExample condition = new TreeInfoExample();
        TreeInfoExample.Criteria criteria = condition.createCriteria();

        if (null != template && null != template.getTreeId()) {
            criteria.andTreeIdEqualTo(template.getTreeId());
        }
        return treeInfoDAO.getTreeInfoListCount(condition);
    }

    @Override
    public List<TreeInfo> getTreeInfoDetail(String id) {
        TreeInfoExample condition = new TreeInfoExample();
        TreeInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return treeInfoDAO.getTreeInfoList(condition);
    }

    @Override
    public int addTreeInfo(TreeInfo template) {
        return treeInfoDAO.addTreeInfo(template);
    }

    @Override
    public int updateTreeInfo(TreeInfo template) {
        TreeInfoExample condition = new TreeInfoExample();
        TreeInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(template.getId());
        return treeInfoDAO.updateTreeInfo(condition, template);
    }

    @Override
    public int delTreeInfo(String id) {
        TreeInfoExample condition = new TreeInfoExample();
        TreeInfoExample.Criteria criteria = condition.createCriteria();
        criteria.andIdEqualTo(id);
        return treeInfoDAO.delTreeInfo(condition);
    }
}