package com.elefirst.poweranalysis.po;

/**
 * Created by barrie on 17/1/28.
 */
public class PowerAnalysisCurrentDailyChartF25 extends PowerAnalysisF25 {
    private String maxA_Current;
    private String maxB_Current;
    private String maxC_Current;
    private String toDaysClientOperationTime;
    private String hourClientOperationTime;

    public String getMaxA_Current() {
        return maxA_Current;
    }

    public void setMaxA_Current(String maxA_Current) {
        this.maxA_Current = maxA_Current;
    }

    public String getMaxB_Current() {
        return maxB_Current;
    }

    public void setMaxB_Current(String maxB_Current) {
        this.maxB_Current = maxB_Current;
    }

    public String getMaxC_Current() {
        return maxC_Current;
    }

    public void setMaxC_Current(String maxC_Current) {
        this.maxC_Current = maxC_Current;
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
