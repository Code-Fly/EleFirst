package com.elefirst.poweranalysis.po;

/**
 * Created by barrie on 17/2/2.
 */
public class PowerAnalysisLoadMonthlyChartIntervalMonthF25 extends PowerAnalysisF25 {
    private String maxTotalActivePower;

    private String minTotalActivePower;

    private String avgTotalActivePower;

    private String yearClientOperationTime;

    private String monthClientOperationTime;

    public String getMaxTotalActivePower() {
        return maxTotalActivePower;
    }

    public void setMaxTotalActivePower(String maxTotalActivePower) {
        this.maxTotalActivePower = maxTotalActivePower;
    }

    public String getMinTotalActivePower() {
        return minTotalActivePower;
    }

    public void setMinTotalActivePower(String minTotalActivePower) {
        this.minTotalActivePower = minTotalActivePower;
    }

    public String getAvgTotalActivePower() {
        return avgTotalActivePower;
    }

    public void setAvgTotalActivePower(String avgTotalActivePower) {
        this.avgTotalActivePower = avgTotalActivePower;
    }

    public String getYearClientOperationTime() {
        return yearClientOperationTime;
    }

    public void setYearClientOperationTime(String yearClientOperationTime) {
        this.yearClientOperationTime = yearClientOperationTime;
    }

    public String getMonthClientOperationTime() {
        return monthClientOperationTime;
    }

    public void setMonthClientOperationTime(String monthClientOperationTime) {
        this.monthClientOperationTime = monthClientOperationTime;
    }
}
