package com.elefirst.system.service.iface;

import com.elefirst.system.po.TreeInfo;

import java.util.List;

/**
 * Created by barrie on 17/1/30.
 */
public interface ITreeInfoService {
    List<TreeInfo> getTreeInfoList(TreeInfo template);

    int getTreeInfoListCount(TreeInfo template);

    List<TreeInfo> getTreeInfoDetail(String id);

    int addTreeInfo(TreeInfo template);

    int updateTreeInfo(TreeInfo template);

    int delTreeInfo(String id);
}