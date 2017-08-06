package com.elefirst.power.po;


public class DataF25FrozenMinuteWithF21 extends DataF25FrozenMinute {
    private String rate;

    private String totalpositiveactivepower;

    private String frozenMonth;

    private String frozenTime;

    private String maxtotalActivePower;

    private String mintotalActivePower;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTotalpositiveactivepower() {
        return totalpositiveactivepower;
    }

    public void setTotalpositiveactivepower(String totalpositiveactivepower) {
        this.totalpositiveactivepower = totalpositiveactivepower;
    }

    public String getFrozenMonth() {
        return frozenMonth;
    }

    public void setFrozenMonth(String frozenMonth) {
        this.frozenMonth = frozenMonth;
    }

    public String getFrozenTime() {
        return frozenTime;
    }

    public void setFrozenTime(String frozenTime) {
        this.frozenTime = frozenTime;
    }

    public String getMaxtotalActivePower() {
        return maxtotalActivePower;
    }

    public void setMaxtotalActivePower(String maxtotalActivePower) {
        this.maxtotalActivePower = maxtotalActivePower;
    }

    public String getMintotalActivePower() {
        return mintotalActivePower;
    }

    public void setMintotalActivePower(String mintotalActivePower) {
        this.mintotalActivePower = mintotalActivePower;
    }
}