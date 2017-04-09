package com.elefirst.power.po;

/**
 * Created by barrie on 2017/4/9.
 */
public class StatisticTotalActivePower {
    private String maxTotalActivePower;
    private String maxTotalActivePowerTime;
    private String minTotalActivePower;
    private String minTotalActivePowerTime;
    private String avgTotalActivePower;
    private String differ;
    private String differRate;
    private String loadRate;

    public String getMaxTotalActivePower() {
        return maxTotalActivePower;
    }

    public void setMaxTotalActivePower(String maxTotalActivePower) {
        this.maxTotalActivePower = maxTotalActivePower;
    }

    public String getMaxTotalActivePowerTime() {
        return maxTotalActivePowerTime;
    }

    public void setMaxTotalActivePowerTime(String maxTotalActivePowerTime) {
        this.maxTotalActivePowerTime = maxTotalActivePowerTime;
    }

    public String getMinTotalActivePower() {
        return minTotalActivePower;
    }

    public void setMinTotalActivePower(String minTotalActivePower) {
        this.minTotalActivePower = minTotalActivePower;
    }

    public String getMinTotalActivePowerTime() {
        return minTotalActivePowerTime;
    }

    public void setMinTotalActivePowerTime(String minTotalActivePowerTime) {
        this.minTotalActivePowerTime = minTotalActivePowerTime;
    }

    public String getAvgTotalActivePower() {
        return avgTotalActivePower;
    }

    public void setAvgTotalActivePower(String avgTotalActivePower) {
        this.avgTotalActivePower = avgTotalActivePower;
    }

    public String getDiffer() {
        return differ;
    }

    public void setDiffer(String differ) {
        this.differ = differ;
    }

    public String getDifferRate() {
        return differRate;
    }

    public void setDifferRate(String differRate) {
        this.differRate = differRate;
    }

    public String getLoadRate() {
        return loadRate;
    }

    public void setLoadRate(String loadRate) {
        this.loadRate = loadRate;
    }
}
