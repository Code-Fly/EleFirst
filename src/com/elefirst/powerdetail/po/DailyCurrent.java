package com.elefirst.powerdetail.po;

public class DailyCurrent {
    private String days;

    private String areaId;

    private String concentratorId;

    private String name;

    private String pn;

    private Double maxacurrent;

    private Double maxbcurrent;

    private Double maxccurrent;

    private Double minacurrent;

    private Double minbcurrent;

    private Double minccurrent;

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

    public Double getMaxacurrent() {
        return maxacurrent;
    }

    public void setMaxacurrent(Double maxacurrent) {
        this.maxacurrent = maxacurrent;
    }

    public Double getMaxbcurrent() {
        return maxbcurrent;
    }

    public void setMaxbcurrent(Double maxbcurrent) {
        this.maxbcurrent = maxbcurrent;
    }

    public Double getMaxccurrent() {
        return maxccurrent;
    }

    public void setMaxccurrent(Double maxccurrent) {
        this.maxccurrent = maxccurrent;
    }

    public Double getMinacurrent() {
        return minacurrent;
    }

    public void setMinacurrent(Double minacurrent) {
        this.minacurrent = minacurrent;
    }

    public Double getMinbcurrent() {
        return minbcurrent;
    }

    public void setMinbcurrent(Double minbcurrent) {
        this.minbcurrent = minbcurrent;
    }

    public Double getMinccurrent() {
        return minccurrent;
    }

    public void setMinccurrent(Double minccurrent) {
        this.minccurrent = minccurrent;
    }
}