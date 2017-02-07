package com.elefirst.poweranalysis.mapper;

import com.elefirst.poweranalysis.po.PowerAnalysisElectricityDailyChartF33;

import java.util.List;
import java.util.Map;

public interface PowerAnalysisF33ElectricityMapper {
    List<PowerAnalysisElectricityDailyChartF33> getElectricityDailyChart(Map<String, Object> param);
}