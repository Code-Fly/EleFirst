package com.elefirst.poweranalysis.service.iface;

import com.elefirst.poweranalysis.po.*;

import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/1/28.
 */
public interface IPowerAnalysisService {
    //
    List<PowerAnalysisLoadDailyChartF25> getLoadDailyChart(Map<String, Object> param);

    List<PowerAnalysisLoadDailyChartSumF25> getLoadDailyChartSum(Map<String, Object> param);

    List<PowerAnalysisLoadDailyChartIntervalDayF25> getLoadDailyChartIntervalDay(Map<String, Object> param);

    List<PowerAnalysisLoadWeeklyChartF25> getLoadWeeklyChart(Map<String, Object> param);

    List<PowerAnalysisLoadMonthlyChartIntervalMonthF25> getLoadMonthlyChartIntervalMonth(Map<String, Object> param);

    List<PowerAnalysisLoadMonthlyChartF25> getLoadMonthlyChart(Map<String, Object> param);

    List<PowerAnalysisLoadDailyTableF25> getLoadDailyTable(Map<String, Object> param);

    List<PowerAnalysisLoadMaxF25> getLoadMax(Map<String, Object> param);

    //

    List<PowerAnalysisVoltageDailyChartF25> getVoltageDailyChart(Map<String, Object> param);

    List<PowerAnalysisVoltageWeeklyChartF25> getVoltageWeeklyChart(Map<String, Object> param);

    List<PowerAnalysisVoltageMonthlyChartF25> getVoltageMonthlyChart(Map<String, Object> param);

    //

    List<PowerAnalysisCurrentDailyChartF25> getCurrentDailyChart(Map<String, Object> param);

    List<PowerAnalysisCurrentWeeklyChartF25> getCurrentWeeklyChart(Map<String, Object> param);

    List<PowerAnalysisCurrentMonthlyChartF25> getCurrentMonthlyChart(Map<String, Object> param);

    //

    List<PowerAnalysisPowerFactorDailyChartF25> getPowerFactorDailyChart(Map<String, Object> param);

    List<PowerAnalysisPowerFactorWeeklyChartF25> getPowerFactorWeeklyChart(Map<String, Object> param);

    List<PowerAnalysisPowerFactorMonthlyChartF25> getPowerFactorMonthlyChart(Map<String, Object> param);

    //

    List<PowerAnalysisElectricityDailyChartF33> getElectricityDailyChart(Map<String, Object> param);

    List<PowerAnalysisElectricityDailyChartIntervalDayF33> getElectricityDailyChartIntervalDay(Map<String, Object> param);

    List<PowerAnalysisElectricityWeeklyChartF33> getElectricityWeeklyChart(Map<String, Object> param);

    List<PowerAnalysisElectricityMonthlyChartF33> getElectricityMonthlyChart(Map<String, Object> param);

    List<PowerAnalysisElectricityMonthlyChartIntervalMonthF33> getElectricityMonthlyChartIntervalMonth(Map<String, Object> param);



}
