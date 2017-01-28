package com.elefirst.poweranalysis.dao.iface;

import com.elefirst.poweranalysis.po.*;

import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/1/28.
 */
public interface IPowerAnalysisDAO {
    List<PowerAnalysisLoadDailyChartF25> getLoadDailyChart(Map<String, Object> param);

    List<PowerAnalysisLoadDailyTableF25> getLoadDailyTable(Map<String, Object> param);

    List<PowerAnalysisVoltageDailyChartF25> getVoltageDailyChart(Map<String, Object> param);

    List<PowerAnalysisCurrentDailyChartF25> getCurrentDailyChart(Map<String, Object> param);

    List<PowerAnalysisPowerFactorDailyChartF25> getPowerFactorDailyChart(Map<String, Object> param);
}
