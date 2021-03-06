package com.elefirst.power.po;

import com.elefirst.base.entity.Page;

public class PnStat extends Page {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_020_pn_stat.id
     *
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_020_pn_stat.area_id
     *
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    private String areaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_020_pn_stat.concentrator_id
     *
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    private String concentratorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_020_pn_stat.pn
     *
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    private String pn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_020_pn_stat.stat
     *
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    private String stat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_020_pn_stat.id
     *
     * @return the value of t_020_pn_stat.id
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_020_pn_stat.id
     *
     * @param id the value for t_020_pn_stat.id
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_020_pn_stat.area_id
     *
     * @return the value of t_020_pn_stat.area_id
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_020_pn_stat.area_id
     *
     * @param areaId the value for t_020_pn_stat.area_id
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_020_pn_stat.concentrator_id
     *
     * @return the value of t_020_pn_stat.concentrator_id
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public String getConcentratorId() {
        return concentratorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_020_pn_stat.concentrator_id
     *
     * @param concentratorId the value for t_020_pn_stat.concentrator_id
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public void setConcentratorId(String concentratorId) {
        this.concentratorId = concentratorId == null ? null : concentratorId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_020_pn_stat.pn
     *
     * @return the value of t_020_pn_stat.pn
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public String getPn() {
        return pn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_020_pn_stat.pn
     *
     * @param pn the value for t_020_pn_stat.pn
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public void setPn(String pn) {
        this.pn = pn == null ? null : pn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_020_pn_stat.stat
     *
     * @return the value of t_020_pn_stat.stat
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public String getStat() {
        return stat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_020_pn_stat.stat
     *
     * @param stat the value for t_020_pn_stat.stat
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_020_pn_stat
     *
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
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
        PnStat other = (PnStat) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAreaId() == null ? other.getAreaId() == null : this.getAreaId().equals(other.getAreaId()))
                && (this.getConcentratorId() == null ? other.getConcentratorId() == null : this.getConcentratorId().equals(other.getConcentratorId()))
                && (this.getPn() == null ? other.getPn() == null : this.getPn().equals(other.getPn()))
                && (this.getStat() == null ? other.getStat() == null : this.getStat().equals(other.getStat()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_020_pn_stat
     *
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAreaId() == null) ? 0 : getAreaId().hashCode());
        result = prime * result + ((getConcentratorId() == null) ? 0 : getConcentratorId().hashCode());
        result = prime * result + ((getPn() == null) ? 0 : getPn().hashCode());
        result = prime * result + ((getStat() == null) ? 0 : getStat().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_020_pn_stat
     *
     * @mbggenerated Mon Feb 06 20:11:27 CST 2017
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
        sb.append(", stat=").append(stat);
        sb.append("]");
        return sb.toString();
    }
}