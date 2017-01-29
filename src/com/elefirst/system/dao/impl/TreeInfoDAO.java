package com.elefirst.system.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.system.dao.iface.ITreeInfoDAO;
import com.elefirst.system.mapper.TreeInfoMapper;
import com.elefirst.system.po.TreeInfo;
import com.elefirst.system.po.TreeInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/1/30.
 */
@Repository
public class TreeInfoDAO extends BaseDAO implements ITreeInfoDAO {
    @Autowired
    private TreeInfoMapper treeInfoMapper;

    @Override
    public List<TreeInfo> getTreeInfoList(TreeInfoExample example) {
        return treeInfoMapper.selectByExample(example);
    }

    @Override
    public int getTreeInfoListCount(TreeInfoExample example) {
        return treeInfoMapper.countByExample(example);
    }

    @Override
    public int addTreeInfo(TreeInfo template) {
        return treeInfoMapper.insertSelective(template);
    }

    @Override
    public int updateTreeInfo(TreeInfoExample example, TreeInfo template) {
        return treeInfoMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delTreeInfo(TreeInfoExample example) {
        return treeInfoMapper.deleteByExample(example);
    }
}