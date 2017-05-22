package com.elefirst.system.po;

import com.elefirst.base.entity.Page;

import java.util.Date;

public class RoleInfo extends Page {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_208_sys_role.id
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_208_sys_role.role_code
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    private String roleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_208_sys_role.role_seq
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    private String roleSeq;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_208_sys_role.role_name
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_208_sys_role.create_person
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    private String createPerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_208_sys_role.create_date
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_208_sys_role.update_person
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    private String updatePerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_208_sys_role.update_date
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_208_sys_role.description
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_208_sys_role.id
     *
     * @return the value of t_208_sys_role.id
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_208_sys_role.id
     *
     * @param id the value for t_208_sys_role.id
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_208_sys_role.role_code
     *
     * @return the value of t_208_sys_role.role_code
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_208_sys_role.role_code
     *
     * @param roleCode the value for t_208_sys_role.role_code
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_208_sys_role.role_seq
     *
     * @return the value of t_208_sys_role.role_seq
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public String getRoleSeq() {
        return roleSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_208_sys_role.role_seq
     *
     * @param roleSeq the value for t_208_sys_role.role_seq
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public void setRoleSeq(String roleSeq) {
        this.roleSeq = roleSeq == null ? null : roleSeq.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_208_sys_role.role_name
     *
     * @return the value of t_208_sys_role.role_name
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_208_sys_role.role_name
     *
     * @param roleName the value for t_208_sys_role.role_name
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_208_sys_role.create_person
     *
     * @return the value of t_208_sys_role.create_person
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_208_sys_role.create_person
     *
     * @param createPerson the value for t_208_sys_role.create_person
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_208_sys_role.create_date
     *
     * @return the value of t_208_sys_role.create_date
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_208_sys_role.create_date
     *
     * @param createDate the value for t_208_sys_role.create_date
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_208_sys_role.update_person
     *
     * @return the value of t_208_sys_role.update_person
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public String getUpdatePerson() {
        return updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_208_sys_role.update_person
     *
     * @param updatePerson the value for t_208_sys_role.update_person
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_208_sys_role.update_date
     *
     * @return the value of t_208_sys_role.update_date
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_208_sys_role.update_date
     *
     * @param updateDate the value for t_208_sys_role.update_date
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_208_sys_role.description
     *
     * @return the value of t_208_sys_role.description
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_208_sys_role.description
     *
     * @param description the value for t_208_sys_role.description
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_208_sys_role
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
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
        RoleInfo other = (RoleInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
                && (this.getRoleSeq() == null ? other.getRoleSeq() == null : this.getRoleSeq().equals(other.getRoleSeq()))
                && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
                && (this.getCreatePerson() == null ? other.getCreatePerson() == null : this.getCreatePerson().equals(other.getCreatePerson()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getUpdatePerson() == null ? other.getUpdatePerson() == null : this.getUpdatePerson().equals(other.getUpdatePerson()))
                && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_208_sys_role
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getRoleSeq() == null) ? 0 : getRoleSeq().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getCreatePerson() == null) ? 0 : getCreatePerson().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdatePerson() == null) ? 0 : getUpdatePerson().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_208_sys_role
     *
     * @mbg.generated Mon May 22 16:19:16 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleCode=").append(roleCode);
        sb.append(", roleSeq=").append(roleSeq);
        sb.append(", roleName=").append(roleName);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }
}