package com.elefirst.powerdetail.po;

public class TwoRealtimeDisplay {
    private String areaId;

    private String concentratorId;

    private String pn;

    private String name;

    private String state;

    private Double pt;

    private Double ct;

    private String clientoperationtime;

    private String sendtime;

    private String totalpositiveactivepower;

    private String totalpositivereactivepower;

    private String totalreverseactivepower;

    private String totalreversereactivepower;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Double getPt() {
        return pt;
    }

    public void setPt(Double pt) {
        this.pt = pt;
    }

    public Double getCt() {
        return ct;
    }

    public void setCt(Double ct) {
        this.ct = ct;
    }

    public String getClientoperationtime() {
        return clientoperationtime;
    }

    public void setClientoperationtime(String clientoperationtime) {
        this.clientoperationtime = clientoperationtime == null ? null : clientoperationtime.trim();
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime == null ? null : sendtime.trim();
    }

    public String getTotalpositiveactivepower() {
        return totalpositiveactivepower;
    }

    public void setTotalpositiveactivepower(String totalpositiveactivepower) {
        this.totalpositiveactivepower = totalpositiveactivepower == null ? null : totalpositiveactivepower.trim();
    }

    public String getTotalpositivereactivepower() {
        return totalpositivereactivepower;
    }

    public void setTotalpositivereactivepower(String totalpositivereactivepower) {
        this.totalpositivereactivepower = totalpositivereactivepower == null ? null : totalpositivereactivepower.trim();
    }

    public String getTotalreverseactivepower() {
        return totalreverseactivepower;
    }

    public void setTotalreverseactivepower(String totalreverseactivepower) {
        this.totalreverseactivepower = totalreverseactivepower == null ? null : totalreverseactivepower.trim();
    }

    public String getTotalreversereactivepower() {
        return totalreversereactivepower;
    }

    public void setTotalreversereactivepower(String totalreversereactivepower) {
        this.totalreversereactivepower = totalreversereactivepower == null ? null : totalreversereactivepower.trim();
    }
}