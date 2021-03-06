package com.elefirst.graph.po;

public class GraphToolbarWithBLOBs extends GraphToolbar {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_204_graph_toolbar.icon_path
     *
     * @mbggenerated Thu Feb 02 04:34:23 CST 2017
     */
    private String iconPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_204_graph_toolbar.img_path
     *
     * @mbggenerated Thu Feb 02 04:34:23 CST 2017
     */
    private String imgPath;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_204_graph_toolbar.icon_path
     *
     * @return the value of t_204_graph_toolbar.icon_path
     *
     * @mbggenerated Thu Feb 02 04:34:23 CST 2017
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_204_graph_toolbar.icon_path
     *
     * @param iconPath the value for t_204_graph_toolbar.icon_path
     *
     * @mbggenerated Thu Feb 02 04:34:23 CST 2017
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath == null ? null : iconPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_204_graph_toolbar.img_path
     *
     * @return the value of t_204_graph_toolbar.img_path
     *
     * @mbggenerated Thu Feb 02 04:34:23 CST 2017
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_204_graph_toolbar.img_path
     *
     * @param imgPath the value for t_204_graph_toolbar.img_path
     *
     * @mbggenerated Thu Feb 02 04:34:23 CST 2017
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_204_graph_toolbar
     *
     * @mbggenerated Thu Feb 02 04:34:23 CST 2017
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GraphToolbarWithBLOBs other = (GraphToolbarWithBLOBs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getWidth() == null ? other.getWidth() == null : this.getWidth().equals(other.getWidth()))
                && (this.getHeight() == null ? other.getHeight() == null : this.getHeight().equals(other.getHeight()))
                && (this.getDisplayOrder() == null ? other.getDisplayOrder() == null : this.getDisplayOrder().equals(other.getDisplayOrder()))
                && (this.getCreatePerson() == null ? other.getCreatePerson() == null : this.getCreatePerson().equals(other.getCreatePerson()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getUpdatePerson() == null ? other.getUpdatePerson() == null : this.getUpdatePerson().equals(other.getUpdatePerson()))
                && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
                && (this.getIconPath() == null ? other.getIconPath() == null : this.getIconPath().equals(other.getIconPath()))
                && (this.getImgPath() == null ? other.getImgPath() == null : this.getImgPath().equals(other.getImgPath()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_204_graph_toolbar
     *
     * @mbggenerated Thu Feb 02 04:34:23 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getWidth() == null) ? 0 : getWidth().hashCode());
        result = prime * result + ((getHeight() == null) ? 0 : getHeight().hashCode());
        result = prime * result + ((getDisplayOrder() == null) ? 0 : getDisplayOrder().hashCode());
        result = prime * result + ((getCreatePerson() == null) ? 0 : getCreatePerson().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdatePerson() == null) ? 0 : getUpdatePerson().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getIconPath() == null) ? 0 : getIconPath().hashCode());
        result = prime * result + ((getImgPath() == null) ? 0 : getImgPath().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_204_graph_toolbar
     *
     * @mbggenerated Thu Feb 02 04:34:23 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", iconPath=").append(iconPath);
        sb.append(", imgPath=").append(imgPath);
        sb.append("]");
        return sb.toString();
    }
}