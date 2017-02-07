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
    public List<PowerAnalysisLoadDailyChartSumF25> getLoadDailyChartSum(Map<String, Object> param) {
        return powerAnalysisDAO.getLoadDailyChartSum(param);
    }

    @Override
    public List<PowerAnalysisLoadDailyChartIntervalDayF25> getLoadDailyChartIntervalDay(Map<String, Object> param) {
        return powerAnalysisDAO.getLoadDailyChartIntervalDay(param);
    }

    @Override
    public List<PowerAnalysisLoadWeeklyChartF25> getLoadWeeklyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getLoadWeeklyChart(param);
    }

    @Override
    public List<PowerAnalysisLoadMonthlyChartIntervalMonthF25> getLoadMonthlyChartIntervalMonth(Map<String, Object> param) {
        return powerAnalysisDAO.getLoadMonthlyChartIntervalMonth(param);
    }

    @Override
    public List<PowerAnalysisVoltageWeeklyChartF25> getVoltageWeeklyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getVoltageWeeklyChart(param);
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
    public List<PowerAnalysisCurrentWeeklyChartF25> getCurrentWeeklyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getCurrentWeeklyChart(param);
    }

    @Override
    public List<PowerAnalysisCurrentMonthlyChartF25> getCurrentMonthlyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getCurrentMonthlyChart(param);
    }

    @Override
    public List<PowerAnalysisPowerFactorDailyChartF25> getPowerFactorDailyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getPowerFactorDailyChart(param);
    }

    @Override
    public List<PowerAnalysisPowerFactorWeeklyChartF25> getPowerFactorWeeklyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getPowerFactorWeeklyChart(param);
    }

    @Override
    public List<PowerAnalysisPowerFactorMonthlyChartF25> getPowerFactorMonthlyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getPowerFactorMonthlyChart(param);
    }

    @Override
    public List<PowerAnalysisElectricityDailyChartF33> getElectricityDailyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getElectricityDailyChart(param);
    }

    @Override
    public List<PowerAnalysisElectricityWeeklyChartF33> getElectricityWeeklyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getElectricityWeeklyChart(param);
    }

    @Override
    public List<PowerAnalysisElectricityMonthlyChartF33> getElectricityMonthlyChart(Map<String, Object> param) {
        return powerAnalysisDAO.getElectricityMonthlyChart(param);
    }


}
