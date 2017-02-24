package com.elefirst.power.po;

import javax.persistence.*;

/**
 * Created by barrie on 17/2/24.
 */
@Entity
@Table(name = "t_012_type_one_data_fn57", schema = "power", catalog = "")
public class DataF57 {
    private String id;
    private String areaId;
    private String concentratorId;
    private String pn;
    private String harmonicTimes;
    private String sendTime;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "area_id")
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "concentrator_id")
    public String getConcentratorId() {
        return concentratorId;
    }

    public void setConcentratorId(String concentratorId) {
        this.concentratorId = concentratorId;
    }

    @Basic
    @Column(name = "pn")
    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    @Basic
    @Column(name = "harmonicTimes")
    public String getHarmonicTimes() {
        return harmonicTimes;
    }

    public void setHarmonicTimes(String harmonicTimes) {
        this.harmonicTimes = harmonicTimes;
    }

    @Basic
    @Column(name = "sendTime")
    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataF57 that = (DataF57) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (areaId != null ? !areaId.equals(that.areaId) : that.areaId != null) return false;
        if (concentratorId != null ? !concentratorId.equals(that.concentratorId) : that.concentratorId != null)
            return false;
        if (pn != null ? !pn.equals(that.pn) : that.pn != null) return false;
        if (harmonicTimes != null ? !harmonicTimes.equals(that.harmonicTimes) : that.harmonicTimes != null)
            return false;
        if (sendTime != null ? !sendTime.equals(that.sendTime) : that.sendTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (areaId != null ? areaId.hashCode() : 0);
        result = 31 * result + (concentratorId != null ? concentratorId.hashCode() : 0);
        result = 31 * result + (pn != null ? pn.hashCode() : 0);
        result = 31 * result + (harmonicTimes != null ? harmonicTimes.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        return result;
    }
}
