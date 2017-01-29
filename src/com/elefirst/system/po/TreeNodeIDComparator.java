package com.elefirst.system.po;

import java.util.Comparator;

/**
 * Created by barrie on 17/1/30.
 */
public class TreeNodeIDComparator implements Comparator {
    // 按照节点编号比较
    public int compare(Object o1, Object o2) {
        int j1 = Integer.parseInt(((TreeNode) o1).getId());
        int j2 = Integer.parseInt(((TreeNode) o2).getId());
        return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
    }
}
