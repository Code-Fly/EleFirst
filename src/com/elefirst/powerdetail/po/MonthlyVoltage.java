package com.elefirst.powerdetail.po;

public class MonthlyVoltage {
    private String days;

    private String areaId;

    private String concentratorId;

    private String name;

    private String pn;

    private Double maxavoltage;

    private Double maxbvoltage;

    private Double maxcvoltage;

    private Double minavoltage;

    private Double minbvoltage;

    private Double mincvoltage;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days == null ? null : days.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getConcentratorId() {
        return concentratorId;
    }

    public void setConcentratorId(String concentratorId) {
        this.concentratorId = concentratorId == null ? null : concentratorId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn == null ? null : pn.trim();
    }

    public Double getMaxavoltage() {
        return maxavoltage;
    }

    public void setMaxavoltage(Double maxavoltage) {
        this.maxavoltage = maxavoltage;
    }

    public Double getMaxbvoltage() {
        return maxbvoltage;
    }

    public void setMaxbvoltage(Double maxbvoltage) {
        this.maxbvoltage = maxbvoltage;
    }

    public Double getMaxcvoltage() {
        return maxcvoltage;
    }

    public void setMaxcvoltage(Double maxcvoltage) {
        this.maxcvoltage = maxcvoltage;
    }

    public Double getMinavoltage() {
        return minavoltage;
    }

    public void setMinavoltage(Double minavoltage) {
        this.minavoltage = minavoltage;
    }

    public Double getMinbvoltage() {
        return minbvoltage;
    }

    public void setMinbvoltage(Double minbvoltage) {
        this.minbvoltage = minbvoltage;
    }

    public Double getMincvoltage() {
        return mincvoltage;
    }

    public void setMincvoltage(Double mincvoltage) {
        this.mincvoltage = mincvoltage;
    }
}