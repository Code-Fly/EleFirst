package com.elefirst.poweranalysis.mapper;

import com.elefirst.poweranalysis.po.*;

import java.util.List;
import java.util.Map;

public interface PowerAnalysisF33ElectricityMapper {
    List<PowerAnalysisElectricityDailyChartF33> getElectricityDailyChart(Map<String, Object> param);

    List<PowerAnalysisElectricityDailyChartIntervalDayF33> getElectricityDailyChartIntervalDay(Map<String, Object> param);

    List<PowerAnalysisElectricityWeeklyChartF33> getElectricityWeeklyChart(Map<String, Object> param);

    List<PowerAnalysisElectricityMonthlyChartF33> getElectricityMonthlyChart(Map<String, Object> param);

    List<PowerAnalysisElectricityMonthlyChartIntervalMonthF33> getElectricityMonthlyChartIntervalMonth(Map<String, Object> param);

    List<PowerAnalysisElectricityMonthlyRateSeqChartAllF33> getElectricityMonthlyRateSeqChartAll(Map<String, Object> param);

}