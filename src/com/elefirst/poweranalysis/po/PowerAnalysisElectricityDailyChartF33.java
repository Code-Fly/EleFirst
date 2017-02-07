package com.elefirst.poweranalysis.po;

public class PowerAnalysisElectricityDailyChartF33 extends PowerAnalysisF33 {
    private String firstClientOperationTime;
    private String firstTotalPositiveActivePower;
    private String lastClientOperationTime;
    private String lastTotalPositiveActivePower;

    private String toDaysClientOperationTime;
    private String hourClientOperationTime;

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