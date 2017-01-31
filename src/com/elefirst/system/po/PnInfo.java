package com.elefirst.system.po;

import com.elefirst.base.entity.Page;

import java.util.Date;

public class PnInfo extends Page {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.id
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.area_id
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private String areaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.concentrator_id
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private String concentratorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.pn
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private String pn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.name
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.pt
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private Double pt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.ct
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private Double ct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.power_factor_standard
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private Double powerFactorStandard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.create_person
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private String createPerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.create_date
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.update_person
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private String updatePerson;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_202_pn_info.update_date
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    private Date updateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.id
     *
     * @return the value of t_202_pn_info.id
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.id
     *
     * @param id the value for t_202_pn_info.id
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.area_id
     *
     * @return the value of t_202_pn_info.area_id
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.area_id
     *
     * @param areaId the value for t_202_pn_info.area_id
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.concentrator_id
     *
     * @return the value of t_202_pn_info.concentrator_id
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public String getConcentratorId() {
        return concentratorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.concentrator_id
     *
     * @param concentratorId the value for t_202_pn_info.concentrator_id
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setConcentratorId(String concentratorId) {
        this.concentratorId = concentratorId == null ? null : concentratorId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.pn
     *
     * @return the value of t_202_pn_info.pn
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public String getPn() {
        return pn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.pn
     *
     * @param pn the value for t_202_pn_info.pn
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setPn(String pn) {
        this.pn = pn == null ? null : pn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.name
     *
     * @return the value of t_202_pn_info.name
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.name
     *
     * @param name the value for t_202_pn_info.name
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.pt
     *
     * @return the value of t_202_pn_info.pt
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public Double getPt() {
        return pt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.pt
     *
     * @param pt the value for t_202_pn_info.pt
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setPt(Double pt) {
        this.pt = pt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.ct
     *
     * @return the value of t_202_pn_info.ct
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public Double getCt() {
        return ct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.ct
     *
     * @param ct the value for t_202_pn_info.ct
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setCt(Double ct) {
        this.ct = ct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.power_factor_standard
     *
     * @return the value of t_202_pn_info.power_factor_standard
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public Double getPowerFactorStandard() {
        return powerFactorStandard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.power_factor_standard
     *
     * @param powerFactorStandard the value for t_202_pn_info.power_factor_standard
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setPowerFactorStandard(Double powerFactorStandard) {
        this.powerFactorStandard = powerFactorStandard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.create_person
     *
     * @return the value of t_202_pn_info.create_person
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.create_person
     *
     * @param createPerson the value for t_202_pn_info.create_person
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.create_date
     *
     * @return the value of t_202_pn_info.create_date
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.create_date
     *
     * @param createDate the value for t_202_pn_info.create_date
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.update_person
     *
     * @return the value of t_202_pn_info.update_person
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public String getUpdatePerson() {
        return updatePerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.update_person
     *
     * @param updatePerson the value for t_202_pn_info.update_person
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson == null ? null : updatePerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_202_pn_info.update_date
     *
     * @return the value of t_202_pn_info.update_date
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_202_pn_info.update_date
     *
     * @param updateDate the value for t_202_pn_info.update_date
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
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
        PnInfo other = (PnInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
            && (this.getConcentratorId() == null ? other.getConcentratorId() == null : this.getConcentratorId().equals(other.getConcentratorId()))
                && (this.getPn() == null ? other.getPn() == null : this.getPn().equals(other.getPn()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getPt() == null ? other.getPt() == null : this.getPt().equals(other.getPt()))
                && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
                && (this.getPowerFactorStandard() == null ? other.getPowerFactorStandard() == null : this.getPowerFactorStandard().equals(other.getPowerFactorStandard()))
                && (this.getCreatePerson() == null ? other.getCreatePerson() == null : this.getCreatePerson().equals(other.getCreatePerson()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getUpdatePerson() == null ? other.getUpdatePerson() == null : this.getUpdatePerson().equals(other.getUpdatePerson()))
                && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getConcentratorId() == null) ? 0 : getConcentratorId().hashCode());
        result = prime * result + ((getPn() == null) ? 0 : getPn().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPt() == null) ? 0 : getPt().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getPowerFactorStandard() == null) ? 0 : getPowerFactorStandard().hashCode());
        result = prime * result + ((getCreatePerson() == null) ? 0 : getCreatePerson().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdatePerson() == null) ? 0 : getUpdatePerson().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Tue Jan 31 00:07:07 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", areaId=").append(areaId);
        sb.append(", concentratorId=").append(concentratorId);
        sb.append(", pn=").append(pn);
        sb.append(", name=").append(name);
        sb.append(", pt=").append(pt);
        sb.append(", ct=").append(ct);
        sb.append(", powerFactorStandard=").append(powerFactorStandard);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}