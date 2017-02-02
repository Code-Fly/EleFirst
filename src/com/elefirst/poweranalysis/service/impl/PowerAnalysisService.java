package com.elefirst.poweranalysis.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.poweranalysis.dao.iface.IPowerAnalysisDAO;
import com.elefirst.poweranalysis.po.*;
import com.elefirst.poweranalysis.service.iface.IPowerAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/1/28.
 */
@Service
public class PowerAnalysisService extends BaseService implements IPowerAnalysisService {
    @Autowired
    private IPowerAnalysisDAO powerAnalysisDAO;

    @Override
    public List<PowerAnalysisLoadDailyChartF25> getLoadDailyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getLoadDailyChart(param);
    }

    @Override
    public List<PowerAnalysisLoadMonthlyChartF25> getLoadMonthlyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getLoadMonthlyChart(param);
    }

    @Override
    public List<PowerAnalysisLoadDailyTableF25> getLoadDailyTable(Map<String, Object> param) {
        return powerAnalysisDAO.getLoadDailyTable(param);
    }

    @Override
    public List<PowerAnalysisVoltageDailyChartF25> getVoltageDailyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getVoltageDailyChart(param);
    }

    @Override
    public List<PowerAnalysisVoltageMonthlyChartF25> getVoltageMonthlyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getVoltageMonthlyChart(param);
    }

    @Override
    public List<PowerAnalysisCurrentDailyChartF25> getCurrentDailyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getCurrentDailyChart(param);
    }

    @Override
    public List<PowerAnalysisPowerFactorDailyChartF25> getPowerFactorDailyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getPowerFactorDailyChart(param);
    }
}
