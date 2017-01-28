package com.elefirst.poweranalysis.po;

/**
 * Created by barrie on 17/1/28.
 */
public class PowerAnalysisComparisonChartF25 extends PowerAnalysisF25 {
    private String maxTotalActivePower;
    private String toDaysClientOperationTime;
    private String hourClientOperationTime;

    public String getMaxTotalActivePower() {
        return maxTotalActivePower;
    }

    public void setMaxTotalActivePower(String maxTotalActivePower) {
        this.maxTotalActivePower = maxTotalActivePower;
    }

    public String getToDaysClientOperationTime() {
        return toDaysClientOperationTime;
    }

    public void setToDaysClientOperationTime(String toDaysClientOperationTime) {
        this.toDaysClientOperationTime = toDaysClientOperationTime;
    }

    public String getHourClientOperationTime() {
        return hourClientOperationTime;
    }

    public void setHourClientOperationTime(String hourClientOperationTime) {
        this.hourClientOperationTime = hourClientOperationTime;
    }
}
