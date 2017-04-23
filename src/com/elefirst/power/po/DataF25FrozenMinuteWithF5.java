package com.elefirst.power.po;


public class DataF25FrozenMinuteWithF5 extends DataF25FrozenMinute {
    private String rate;

    private String totalpositiveactivepower;

    private String frozenDay;

    private String frozenTime;

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

    public String getFrozenDay() {
        return frozenDay;
    }

    public void setFrozenDay(String frozenDay) {
        this.frozenDay = frozenDay;
    }

    public String getFrozenTime() {
        return frozenTime;
    }

    public void setFrozenTime(String frozenTime) {
        this.frozenTime = frozenTime;
    }
}