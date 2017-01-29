package com.elefirst.system.po;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by barrie on 17/1/30.
 */
public class TreeChildren {
    private List list = new ArrayList();

    public int getSize() {
        return list.size();
    }

    public void addChild(TreeNode node) {
        list.add(node);
    }

    // 拼接孩子节点的JSON字符串
    public String toString() {
        String result = "[";
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            result += ((TreeNode) it.next()).toString();
            result += ",";
        }
        result = result.substring(0, result.length() - 1);
        result += "]";
        return result;
    }

    // 孩子节点排序
    public void sortChildren() {
        // 对本层节点进行排序
        // 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器
        Collections.sort(list, new TreeNodeIDComparator());
        // 对每个节点的下一层节点进行排序
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            ((TreeNode) it.next()).sortChildren();
        }
    }
}
