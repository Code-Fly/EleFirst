package com.elefirst.poweranalysis.service.iface;

import com.elefirst.poweranalysis.po.*;

import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/1/28.
 */
public interface IPowerAnalysisService {
    List<PowerAnalysisLoadDailyChartF25> getLoadDailyChart(Map<String, Object> param);

    List<PowerAnalysisLoadMonthlyChartF25> getLoadMonthlyChart(Map<String, Object> param);

    List<PowerAnalysisLoadDailyTableF25> getLoadDailyTable(Map<String, Object> param);

    List<PowerAnalysisVoltageDailyChartF25> getVoltageDailyChart(Map<String, Object> param);

    List<PowerAnalysisVoltageMonthlyChartF25> getVoltageMonthlyChart(Map<String, Object> param);

    List<PowerAnalysisCurrentDailyChartF25> getCurrentDailyChart(Map<String, Object> param);

    List<PowerAnalysisCurrentMonthlyChartF25> getCurrentMonthlyChart(Map<String, Object> param);

    List<PowerAnalysisPowerFactorDailyChartF25> getPowerFactorDailyChart(Map<String, Object> param);
}
