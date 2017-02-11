package com.elefirst.poweranalysis.mapper;

import com.elefirst.poweranalysis.po.PowerAnalysisElectricityDailyChartF33;
import com.elefirst.poweranalysis.po.PowerAnalysisElectricityDailyChartIntervalDayF33;
import com.elefirst.poweranalysis.po.PowerAnalysisElectricityMonthlyChartF33;
import com.elefirst.poweranalysis.po.PowerAnalysisElectricityWeeklyChartF33;

import java.util.List;
import java.util.Map;

public interface PowerAnalysisF33ElectricityMapper {
    List<PowerAnalysisElectricityDailyChartF33> getElectricityDailyChart(Map<String, Object> param);

    List<PowerAnalysisElectricityDailyChartIntervalDayF33> getElectricityDailyChartIntervalDay(Map<String, Object> param);

    List<PowerAnalysisElectricityWeeklyChartF33> getElectricityWeeklyChart(Map<String, Object> param);

    List<PowerAnalysisElectricityMonthlyChartF33> getElectricityMonthlyChart(Map<String, Object> param);
}