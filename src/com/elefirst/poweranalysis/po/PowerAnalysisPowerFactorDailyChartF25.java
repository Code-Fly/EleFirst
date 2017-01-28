package com.elefirst.poweranalysis.po;

/**
 * Created by barrie on 17/1/28.
 */
public class PowerAnalysisPowerFactorDailyChartF25 extends PowerAnalysisF25 {
    private String maxA_PowerFactor;
    private String maxB_PowerFactor;
    private String maxC_PowerFactor;
    private String toDaysClientOperationTime;
    private String hourClientOperationTime;

    public String getMaxA_PowerFactor() {
        return maxA_PowerFactor;
    }

    public void setMaxA_PowerFactor(String maxA_PowerFactor) {
        this.maxA_PowerFactor = maxA_PowerFactor;
    }

    public String getMaxB_PowerFactor() {
        return maxB_PowerFactor;
    }

    public void setMaxB_PowerFactor(String maxB_PowerFactor) {
        this.maxB_PowerFactor = maxB_PowerFactor;
    }

    public String getMaxC_PowerFactor() {
        return maxC_PowerFactor;
    }

    public void setMaxC_PowerFactor(String maxC_PowerFactor) {
        this.maxC_PowerFactor = maxC_PowerFactor;
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
