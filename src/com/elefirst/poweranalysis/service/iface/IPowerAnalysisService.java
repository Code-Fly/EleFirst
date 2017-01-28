package com.elefirst.poweranalysis.service.iface;

import com.elefirst.poweranalysis.po.PowerAnalysisComparisonChartF25;
import com.elefirst.poweranalysis.po.PowerAnalysisComparisonTableF25;

import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/1/28.
 */
public interface IPowerAnalysisService {
    List<PowerAnalysisComparisonChartF25> getComparisonChart(Map<String, Object> param);

    List<PowerAnalysisComparisonTableF25> getComparisonTable(Map<String, Object> param);
}
