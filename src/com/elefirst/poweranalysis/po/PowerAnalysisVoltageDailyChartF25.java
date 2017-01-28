package com.elefirst.poweranalysis.po;

/**
 * Created by barrie on 17/1/28.
 */
public class PowerAnalysisVoltageDailyChartF25 extends PowerAnalysisF25 {
    private String maxA_Voltage;
    private String maxB_Voltage;
    private String maxC_Voltage;
    private String toDaysClientOperationTime;
    private String hourClientOperationTime;

    public String getMaxA_Voltage() {
        return maxA_Voltage;
    }

    public void setMaxA_Voltage(String maxA_Voltage) {
        this.maxA_Voltage = maxA_Voltage;
    }

    public String getMaxB_Voltage() {
        return maxB_Voltage;
    }

    public void setMaxB_Voltage(String maxB_Voltage) {
        this.maxB_Voltage = maxB_Voltage;
    }

    public String getMaxC_Voltage() {
        return maxC_Voltage;
    }

    public void setMaxC_Voltage(String maxC_Voltage) {
        this.maxC_Voltage = maxC_Voltage;
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
