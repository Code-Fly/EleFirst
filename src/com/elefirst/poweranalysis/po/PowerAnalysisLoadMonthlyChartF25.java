package com.elefirst.poweranalysis.po;

/**
 * Created by barrie on 17/2/2.
 */
public class PowerAnalysisLoadMonthlyChartF25 extends PowerAnalysisF25 {
    private String maxTotalActivePower;

    private String minTotalActivePower;

    private String avgTotalActivePower;

    private String dayClientOperationTime;

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

    public String getMaxTotalActivePower() {
        return maxTotalActivePower;
    }

    public void setMaxTotalActivePower(String maxTotalActivePower) {
        this.maxTotalActivePower = maxTotalActivePower;
    }

    public String getDayClientOperationTime() {
        return dayClientOperationTime;
    }

    public void setDayClientOperationTime(String dayClientOperationTime) {
        this.dayClientOperationTime = dayClientOperationTime;
    }
}
