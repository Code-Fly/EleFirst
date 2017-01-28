package com.elefirst.poweranalysis.service.iface;

import com.elefirst.poweranalysis.po.PowerAnalysisLoadDailyChartF25;
import com.elefirst.poweranalysis.po.PowerAnalysisLoadDailyTableF25;
import com.elefirst.poweranalysis.po.PowerAnalysisVoltageDailyChartF25;

import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/1/28.
 */
public interface IPowerAnalysisService {
    List<PowerAnalysisLoadDailyChartF25> getLoadDailyChart(Map<String, Object> param);

    List<PowerAnalysisLoadDailyTableF25> getLoadDailyTable(Map<String, Object> param);

    List<PowerAnalysisVoltageDailyChartF25> getVoltageDailyChart(Map<String, Object> param);
}
