package com.elefirst.poweranalysis.mapper;

import com.elefirst.poweranalysis.po.PowerAnalysisVoltageDailyChartF25;
import com.elefirst.poweranalysis.po.PowerAnalysisVoltageMonthlyChartF25;

import java.util.List;
import java.util.Map;

public interface PowerAnalysisF25VoltageMapper {

    List<PowerAnalysisVoltageDailyChartF25> getVoltageDailyChart(Map<String, Object> param);

    List<PowerAnalysisVoltageMonthlyChartF25> getVoltageMonthlyChart(Map<String, Object> param);

}