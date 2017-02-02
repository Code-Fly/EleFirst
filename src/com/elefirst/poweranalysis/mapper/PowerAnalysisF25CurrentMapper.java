package com.elefirst.poweranalysis.mapper;

import com.elefirst.poweranalysis.po.PowerAnalysisCurrentDailyChartF25;
import com.elefirst.poweranalysis.po.PowerAnalysisCurrentMonthlyChartF25;

import java.util.List;
import java.util.Map;

public interface PowerAnalysisF25CurrentMapper {
    List<PowerAnalysisCurrentDailyChartF25> getCurrentDailyChart(Map<String, Object> param);

    List<PowerAnalysisCurrentMonthlyChartF25> getCurrentMonthlyChart(Map<String, Object> param);
}