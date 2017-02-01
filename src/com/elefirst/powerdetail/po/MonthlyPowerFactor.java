package com.elefirst.powerdetail.po;

public class MonthlyPowerFactor {
    private String days;

    private String areaId;

    private String concentratorId;

    private String name;

    private String pn;

    private Double powerFactorStandard;

    private Double avgtotalpowerfactor;

    private Double amaxpowerfactor;

    private Double bmaxpowerfactor;

    private Double cmaxpowerfactor;

    private Double maxtotalpowerfactor;

    private Double aminpowerfactor;

    private Double bminpowerfactor;

    private Double cminpowerfactor;

    private Double mintotalpowerfactor;

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

    public Double getPowerFactorStandard() {
        return powerFactorStandard;
    }

    public void setPowerFactorStandard(Double powerFactorStandard) {
        this.powerFactorStandard = powerFactorStandard;
    }

    public Double getAvgtotalpowerfactor() {
        return avgtotalpowerfactor;
    }

    public void setAvgtotalpowerfactor(Double avgtotalpowerfactor) {
        this.avgtotalpowerfactor = avgtotalpowerfactor;
    }

    public Double getAmaxpowerfactor() {
        return amaxpowerfactor;
    }

    public void setAmaxpowerfactor(Double amaxpowerfactor) {
        this.amaxpowerfactor = amaxpowerfactor;
    }

    public Double getBmaxpowerfactor() {
        return bmaxpowerfactor;
    }

    public void setBmaxpowerfactor(Double bmaxpowerfactor) {
        this.bmaxpowerfactor = bmaxpowerfactor;
    }

    public Double getCmaxpowerfactor() {
        return cmaxpowerfactor;
    }

    public void setCmaxpowerfactor(Double cmaxpowerfactor) {
        this.cmaxpowerfactor = cmaxpowerfactor;
    }

    public Double getMaxtotalpowerfactor() {
        return maxtotalpowerfactor;
    }

    public void setMaxtotalpowerfactor(Double maxtotalpowerfactor) {
        this.maxtotalpowerfactor = maxtotalpowerfactor;
    }

    public Double getAminpowerfactor() {
        return aminpowerfactor;
    }

    public void setAminpowerfactor(Double aminpowerfactor) {
        this.aminpowerfactor = aminpowerfactor;
    }

    public Double getBminpowerfactor() {
        return bminpowerfactor;
    }

    public void setBminpowerfactor(Double bminpowerfactor) {
        this.bminpowerfactor = bminpowerfactor;
    }

    public Double getCminpowerfactor() {
        return cminpowerfactor;
    }

    public void setCminpowerfactor(Double cminpowerfactor) {
        this.cminpowerfactor = cminpowerfactor;
    }

    public Double getMintotalpowerfactor() {
        return mintotalpowerfactor;
    }

    public void setMintotalpowerfactor(Double mintotalpowerfactor) {
        this.mintotalpowerfactor = mintotalpowerfactor;
    }
}