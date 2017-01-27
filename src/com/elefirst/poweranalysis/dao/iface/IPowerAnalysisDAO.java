package com.elefirst.poweranalysis.dao.iface;

import com.elefirst.poweranalysis.po.PowerAnalysisComparisonF25;

import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/1/28.
 */
public interface IPowerAnalysisDAO {
    List<PowerAnalysisComparisonF25> getComparisonChart(Map<String, Object> param);
}
