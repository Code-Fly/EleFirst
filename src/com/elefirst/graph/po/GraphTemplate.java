package com.elefirst.graph.po;

import com.elefirst.base.entity.Page;

import java.util.Date;

public class GraphTemplate extends Page {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_205_graph_template.id
     *
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_205_graph_template.name
     *
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_205_graph_template.create_person
     *
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    private String createPerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_205_graph_template.create_date
     *
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_205_graph_template.update_person
     *
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    private String updatePerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_205_graph_template.update_date
     *
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    private Date updateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_205_graph_template.id
     *
     * @return the value of t_205_graph_template.id
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_205_graph_template.id
     *
     * @param id the value for t_205_graph_template.id
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_205_graph_template.name
     *
     * @return the value of t_205_graph_template.name
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_205_graph_template.name
     *
     * @param name the value for t_205_graph_template.name
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_205_graph_template.create_person
     *
     * @return the value of t_205_graph_template.create_person
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_205_graph_template.create_person
     *
     * @param createPerson the value for t_205_graph_template.create_person
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_205_graph_template.create_date
     *
     * @return the value of t_205_graph_template.create_date
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_205_graph_template.create_date
     *
     * @param createDate the value for t_205_graph_template.create_date
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_205_graph_template.update_person
     *
     * @return the value of t_205_graph_template.update_person
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public String getUpdatePerson() {
        return updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_205_graph_template.update_person
     *
     * @param updatePerson the value for t_205_graph_template.update_person
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_205_graph_template.update_date
     *
     * @return the value of t_205_graph_template.update_date
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_205_graph_template.update_date
     *
     * @param updateDate the value for t_205_graph_template.update_date
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_205_graph_template
     *
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
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
        GraphTemplate other = (GraphTemplate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCreatePerson() == null ? other.getCreatePerson() == null : this.getCreatePerson().equals(other.getCreatePerson()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getUpdatePerson() == null ? other.getUpdatePerson() == null : this.getUpdatePerson().equals(other.getUpdatePerson()))
                && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_205_graph_template
     *
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreatePerson() == null) ? 0 : getCreatePerson().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdatePerson() == null) ? 0 : getUpdatePerson().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_205_graph_template
     *
     * @mbggenerated Thu Feb 02 16:44:30 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}