package com.elefirst.poweranalysis.po;

/**
 * Created by barrie on 17/2/2.
 */
public class PowerAnalysisLoadMonthlyChartF25 extends PowerAnalysisF25 {
    private String maxTotalActivePower;

    private String dayClientOperationTime;

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
