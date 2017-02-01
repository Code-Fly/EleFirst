package com.elefirst.powerdetail.po;

public class MonthlyDemand {
    private String days;

    private String areaId;

    private String concentratorId;

    private String pn;

    private Double maxtotalpositivemaxactivepower;

    private String totalpositivemaxactivepowertime;

    private String name;

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

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn == null ? null : pn.trim();
    }

    public Double getMaxtotalpositivemaxactivepower() {
        return maxtotalpositivemaxactivepower;
    }

    public void setMaxtotalpositivemaxactivepower(Double maxtotalpositivemaxactivepower) {
        this.maxtotalpositivemaxactivepower = maxtotalpositivemaxactivepower;
    }

    public String getTotalpositivemaxactivepowertime() {
        return totalpositivemaxactivepowertime;
    }

    public void setTotalpositivemaxactivepowertime(String totalpositivemaxactivepowertime) {
        this.totalpositivemaxactivepowertime = totalpositivemaxactivepowertime == null ? null : totalpositivemaxactivepowertime.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}