package com.elefirst.poweranalysis.service.iface;

import com.elefirst.poweranalysis.po.PowerAnalysisComparisonF25;
import com.elefirst.poweranalysis.po.PowerAnalysisF25;

import java.util.List;

/**
 * Created by barrie on 17/1/28.
 */
public interface IPowerAnalysisService {
    List<PowerAnalysisComparisonF25> getComparisonChart(List<PowerAnalysisF25> list);
}
