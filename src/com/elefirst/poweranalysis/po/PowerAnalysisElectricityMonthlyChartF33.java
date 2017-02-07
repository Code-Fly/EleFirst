package com.elefirst.poweranalysis.po;

public class PowerAnalysisElectricityMonthlyChartF33 extends PowerAnalysisF33 {
    private String firstClientOperationTime;
    private String firstTotalPositiveActivePower;
    private String lastClientOperationTime;
    private String lastTotalPositiveActivePower;
    private String dayClientOperationTime;

    public String getFirstClientOperationTime() {
        return firstClientOperationTime;
    }

    public void setFirstClientOperationTime(String firstClientOperationTime) {
        this.firstClientOperationTime = firstClientOperationTime;
    }

    public String getFirstTotalPositiveActivePower() {
        return firstTotalPositiveActivePower;
    }

    public void setFirstTotalPositiveActivePower(String firstTotalPositiveActivePower) {
        this.firstTotalPositiveActivePower = firstTotalPositiveActivePower;
    }

    public String getLastClientOperationTime() {
        return lastClientOperationTime;
    }

    public void setLastClientOperationTime(String lastClientOperationTime) {
        this.lastClientOperationTime = lastClientOperationTime;
    }

    public String getLastTotalPositiveActivePower() {
        return lastTotalPositiveActivePower;
    }

    public void setLastTotalPositiveActivePower(String lastTotalPositiveActivePower) {
        this.lastTotalPositiveActivePower = lastTotalPositiveActivePower;
    }

    public String getDayClientOperationTime() {
        return dayClientOperationTime;
    }

    public void setDayClientOperationTime(String dayClientOperationTime) {
        this.dayClientOperationTime = dayClientOperationTime;
    }
}