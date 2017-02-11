package com.elefirst.poweranalysis.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.poweranalysis.dao.iface.IPowerAnalysisDAO;
import com.elefirst.poweranalysis.mapper.*;
import com.elefirst.poweranalysis.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by barrie on 17/1/28.
 */
@Repository
public class PowerAnalysisDAO extends BaseDAO implements IPowerAnalysisDAO {
    @Autowired
    private PowerAnalysisF25VoltageMapper powerAnalysisF25VoltageMapper;

    @Autowired
    private PowerAnalysisF25CurrentMapper powerAnalysisF25CurrentMapper;

    @Autowired
    private PowerAnalysisF25LoadMapper powerAnalysisF25LoadMapper;

    @Autowired
    private PowerAnalysisF25PowerFactorMapper powerAnalysisF25PowerFactorMapper;

    @Autowired
    private PowerAnalysisF33ElectricityMapper powerAnalysisF33ElectricityMapper;

    @Override
    public List<PowerAnalysisLoadDailyChartF25> getLoadDailyChart(Map<String, Object> param) {
        return powerAnalysisF25LoadMapper.getLoadDailyChart(param);
    }

    @Override
    public List<PowerAnalysisLoadDailyChartSumF25> getLoadDailyChartSum(Map<String, Object> param) {
        return powerAnalysisF25LoadMapper.getLoadDailyChartSum(param);
    }

    @Override
    public List<PowerAnalysisLoadDailyChartIntervalDayF25> getLoadDailyChartIntervalDay(Map<String, Object> param) {
        return powerAnalysisF25LoadMapper.getLoadDailyChartIntervalDay(param);
    }

    @Override
    public List<PowerAnalysisLoadWeeklyChartF25> getLoadWeeklyChart(Map<String, Object> param) {
        return powerAnalysisF25LoadMapper.getLoadWeeklyChart(param);
    }

    @Override
    public List<PowerAnalysisLoadMonthlyChartIntervalMonthF25> getLoadMonthlyChartIntervalMonth(Map<String, Object> param) {
        return powerAnalysisF25LoadMapper.getLoadMonthlyChartIntervalMonth(param);
    }

    @Override
    public List<PowerAnalysisLoadMonthlyChartF25> getLoadMonthlyChart(Map<String, Object> param) {
        return powerAnalysisF25LoadMapper.getLoadMonthlyChart(param);
    }

    @Override
    public List<PowerAnalysisLoadDailyTableF25> getLoadDailyTable(Map<String, Object> param) {
        return powerAnalysisF25LoadMapper.getLoadDailyTable(param);
    }

    @Override
    public List<PowerAnalysisVoltageDailyChartF25> getVoltageDailyChart(Map<String, Object> param) {
        return powerAnalysisF25VoltageMapper.getVoltageDailyChart(param);
    }

    @Override
    public List<PowerAnalysisVoltageWeeklyChartF25> getVoltageWeeklyChart(Map<String, Object> param) {
        return powerAnalysisF25VoltageMapper.getVoltageWeeklyChart(param);
    }

    @Override
    public List<PowerAnalysisVoltageMonthlyChartF25> getVoltageMonthlyChart(Map<String, Object> param) {
        return powerAnalysisF25VoltageMapper.getVoltageMonthlyChart(param);
    }

    @Override
    public List<PowerAnalysisCurrentDailyChartF25> getCurrentDailyChart(Map<String, Object> param) {
        return powerAnalysisF25CurrentMapper.getCurrentDailyChart(param);
    }

    @Override
    public List<PowerAnalysisCurrentWeeklyChartF25> getCurrentWeeklyChart(Map<String, Object> param) {
        return powerAnalysisF25CurrentMapper.getCurrentWeeklyChart(param);
    }

    @Override
    public List<PowerAnalysisCurrentMonthlyChartF25> getCurrentMonthlyChart(Map<String, Object> param) {
        return powerAnalysisF25CurrentMapper.getCurrentMonthlyChart(param);
    }

    @Override
    public List<PowerAnalysisPowerFactorDailyChartF25> getPowerFactorDailyChart(Map<String, Object> param) {
        return powerAnalysisF25PowerFactorMapper.getPowerFactorDailyChart(param);
    }

    @Override
    public List<PowerAnalysisPowerFactorWeeklyChartF25> getPowerFactorWeeklyChart(Map<String, Object> param) {
        return powerAnalysisF25PowerFactorMapper.getPowerFactorWeeklyChart(param);
    }

    @Override
    public List<PowerAnalysisPowerFactorMonthlyChartF25> getPowerFactorMonthlyChart(Map<String, Object> param) {
        return powerAnalysisF25PowerFactorMapper.getPowerFactorMonthlyChart(param);
    }

    @Override
    public List<PowerAnalysisElectricityDailyChartF33> getElectricityDailyChart(Map<String, Object> param) {
        return powerAnalysisF33ElectricityMapper.getElectricityDailyChart(param);
    }

    @Override
    public List<PowerAnalysisElectricityDailyChartIntervalDayF33> getElectricityDailyChartIntervalDay(Map<String, Object> param) {
        return powerAnalysisF33ElectricityMapper.getElectricityDailyChartIntervalDay(param);
    }

    @Override
    public List<PowerAnalysisElectricityWeeklyChartF33> getElectricityWeeklyChart(Map<String, Object> param) {
        return powerAnalysisF33ElectricityMapper.getElectricityWeeklyChart(param);
    }

    @Override
    public List<PowerAnalysisElectricityMonthlyChartF33> getElectricityMonthlyChart(Map<String, Object> param) {
        return powerAnalysisF33ElectricityMapper.getElectricityMonthlyChart(param);
    }

    @Override
    public List<PowerAnalysisElectricityMonthlyChartIntervalMonthF33> getElectricityMonthlyChartIntervalMonth(Map<String, Object> param) {
        return powerAnalysisF33ElectricityMapper.getElectricityMonthlyChartIntervalMonth(param);
    }
}
