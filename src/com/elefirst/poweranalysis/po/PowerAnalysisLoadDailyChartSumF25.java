package com.elefirst.poweranalysis.po;

/**
 * Created by barrie on 17/1/28.
 */
public class PowerAnalysisLoadDailyChartSumF25 extends PowerAnalysisF25 {
    private String sumTotalActivePower;
    private String toDaysClientOperationTime;
    private String hourClientOperationTime;

    public String getSumTotalActivePower() {
        return sumTotalActivePower;
    }

    public void setSumTotalActivePower(String sumTotalActivePower) {
        this.sumTotalActivePower = sumTotalActivePower;
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
