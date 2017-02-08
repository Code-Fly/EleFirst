package com.elefirst.system.po;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by barrie on 17/1/30.
 */
public class TreeNodeIDComparator implements Comparator {
    // 按照节点编号比较
    public int compare(Object o1, Object o2) {
        Date j1 = ((TreeNode) o1).getCreateDate();
        Date j2 = ((TreeNode) o2).getCreateDate();
        return (j1.before(j2) ? -1 : (j1.equals(j2) ? 0 : 1));
    }
}
