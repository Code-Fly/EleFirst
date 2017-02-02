package com.elefirst.poweranalysis.mapper;

import com.elefirst.poweranalysis.po.PowerAnalysisPowerFactorDailyChartF25;
import com.elefirst.poweranalysis.po.PowerAnalysisPowerFactorMonthlyChartF25;

import java.util.List;
import java.util.Map;

public interface PowerAnalysisF25PowerFactorMapper {

    List<PowerAnalysisPowerFactorDailyChartF25> getPowerFactorDailyChart(Map<String, Object> param);

    List<PowerAnalysisPowerFactorMonthlyChartF25> getPowerFactorMonthlyChart(Map<String, Object> param);
}