package com.elefirst.poweranalysis.mapper;

import com.elefirst.poweranalysis.po.*;

import java.util.List;
import java.util.Map;

public interface PowerAnalysisF25LoadMapper {
    List<PowerAnalysisLoadDailyChartF25> getLoadDailyChart(Map<String, Object> param);

    List<PowerAnalysisLoadDailyChartSumF25> getLoadDailyChartSum(Map<String, Object> param);

    List<PowerAnalysisLoadDailyChartIntervalDayF25> getLoadDailyChartIntervalDay(Map<String, Object> param);

    List<PowerAnalysisLoadWeeklyChartF25> getLoadWeeklyChart(Map<String, Object> param);

    List<PowerAnalysisLoadMonthlyChartF25> getLoadMonthlyChart(Map<String, Object> param);

    List<PowerAnalysisLoadMonthlyChartIntervalMonthF25> getLoadMonthlyChartIntervalMonth(Map<String, Object> param);

    List<PowerAnalysisLoadDailyTableF25> getLoadDailyTable(Map<String, Object> param);
}