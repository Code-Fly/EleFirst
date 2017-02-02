package com.elefirst.poweranalysis.mapper;

import com.elefirst.poweranalysis.po.PowerAnalysisLoadDailyChartF25;
import com.elefirst.poweranalysis.po.PowerAnalysisLoadDailyTableF25;
import com.elefirst.poweranalysis.po.PowerAnalysisLoadMonthlyChartF25;

import java.util.List;
import java.util.Map;

public interface PowerAnalysisF25LoadMapper {
    List<PowerAnalysisLoadDailyChartF25> getLoadDailyChart(Map<String, Object> param);

    List<PowerAnalysisLoadMonthlyChartF25> getLoadMonthlyChart(Map<String, Object> param);

    List<PowerAnalysisLoadDailyTableF25> getLoadDailyTable(Map<String, Object> param);
}