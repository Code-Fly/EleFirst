package com.elefirst.system.po;

/**
 * Created by barrie on 17/1/30.
 */
public class TreeNode {
    private Long id;

    private Long pid;

    private String text;

    private String iconcls;

    private String state;

    private String attributes;

    private TreeChildren children = new TreeChildren();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
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

    // 先序遍历，拼接JSON字符串
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("{");
        result.append("id : '" + id + "'");
        result.append(", ");
        result.append("text : '" + text + "'");
        result.append(", ");
        result.append("iconCls : '" + iconcls + "'");
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
