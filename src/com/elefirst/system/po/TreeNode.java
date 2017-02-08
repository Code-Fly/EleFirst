package com.elefirst.system.po;

import java.util.Date;

/**
 * Created by barrie on 17/1/30.
 */
public class TreeNode {
    private String id;

    private String pid;

    private String text;

    private String iconCls;

    private String state;

    private String attributes;

    private TreeChildren children = new TreeChildren();

    private Date createDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public TreeChildren getChildren() {
        return children;
    }

    public void setChildren(TreeChildren children) {
        this.children = children;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    // 先序遍历，拼接JSON字符串
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("{");
        result.append("id : '" + id + "'");
        result.append(", ");
        result.append("text : '" + text + "'");
        result.append(", ");
        result.append("iconCls : '" + iconCls + "'");
        if (state != null && !state.isEmpty()) {
            if (children != null && children.getSize() != 0) {
                result.append(", ");
                result.append("state : '" + state + "'");
            }
        }
        if (attributes != null && !attributes.isEmpty()) {
            result.append(", ");
            result.append("attributes : '" + attributes + "'");
        }

        if (children != null && children.getSize() != 0) {
            result.append(", ");
            result.append("children : " + children.toString());
        } else {
            result.append(", ");
            result.append("leaf : true");
        }
        result.append("}");

        return result.toString();
    }

    // 兄弟节点横向排序
    public void sortChildren() {
        if (children != null && children.getSize() != 0) {
            children.sortChildren();
        }
    }

    // 添加孩子节点
    public void addChild(TreeNode node) {
        this.children.addChild(node);
    }
}
