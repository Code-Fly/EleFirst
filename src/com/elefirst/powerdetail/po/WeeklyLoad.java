package com.elefirst.powerdetail.po;

public class WeeklyLoad {
    private String weekstart;

    private String weekend;

    private String areaId;

    private String concentratorId;

    private String name;

    private String pn;

    private Double maxactivepower;

    private Double minactivepower;

    private Double avgactivepower;

    private Double loadrate;

    private Double peakrate;

    public String getWeekstart() {
        return weekstart;
    }

    public void setWeekstart(String weekstart) {
        this.weekstart = weekstart == null ? null : weekstart.trim();
    }

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend == null ? null : weekend.trim();
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

    public Double getMaxactivepower() {
        return maxactivepower;
    }

    public void setMaxactivepower(Double maxactivepower) {
        this.maxactivepower = maxactivepower;
    }

    public Double getMinactivepower() {
        return minactivepower;
    }

    public void setMinactivepower(Double minactivepower) {
        this.minactivepower = minactivepower;
    }

    public Double getAvgactivepower() {
        return avgactivepower;
    }

    public void setAvgactivepower(Double avgactivepower) {
        this.avgactivepower = avgactivepower;
    }

    public Double getLoadrate() {
        return loadrate;
    }

    public void setLoadrate(Double loadrate) {
        this.loadrate = loadrate;
    }

    public Double getPeakrate() {
        return peakrate;
    }

    public void setPeakrate(Double peakrate) {
        this.peakrate = peakrate;
    }
}