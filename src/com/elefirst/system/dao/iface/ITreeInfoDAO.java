package com.elefirst.system.dao.iface;

import com.elefirst.system.po.TreeInfo;
import com.elefirst.system.po.TreeInfoExample;

import java.util.List;

/**
 * Created by barrie on 17/1/30.
 */
public interface ITreeInfoDAO {
    List<TreeInfo> getTreeInfoList(TreeInfoExample example);

    int getTreeInfoListCount(TreeInfoExample example);

    int addTreeInfo(TreeInfo template);

    int updateTreeInfo(TreeInfoExample example, TreeInfo template);

    int delTreeInfo(TreeInfoExample example);
}
