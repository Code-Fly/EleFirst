package com.elefirst.powerdetail.po;

public class WeeklyDemand {
    private String weekstart;
    
    private String weekend;

    private String areaId;

    private String concentratorId;

    private String pn;

    private Double maxtotalpositivemaxactivepower;

    private String totalpositivemaxactivepowertime;

    private String name;


    public String getWeekstart() {
		return weekstart;
	}

	public void setWeekstart(String weekstart) {
		this.weekstart = weekstart;
	}

	public String getWeekend() {
		return weekend;
	}

	public void setWeekend(String weekend) {
		this.weekend = weekend;
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