package com.elefirst.poweranalysis.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.poweranalysis.dao.iface.IPowerAnalysisDAO;
import com.elefirst.poweranalysis.mapper.PowerAnalysisF25Mapper;
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
    private PowerAnalysisF25Mapper powerAnalysisF25Mapper;

    @Override
    public List<PowerAnalysisLoadDailyChartF25> getLoadDailyChart(Map<String, Object> param) {
        return powerAnalysisF25Mapper.getLoadDailyChart(param);
    }

    @Override
    public List<PowerAnalysisLoadDailyTableF25> getLoadDailyTable(Map<String, Object> param) {
        return powerAnalysisF25Mapper.getLoadDailyTable(param);
    }

    @Override
    public List<PowerAnalysisVoltageDailyChartF25> getVoltageDailyChart(Map<String, Object> param) {
        return powerAnalysisF25Mapper.getVoltageDailyChart(param);
    }

    @Override
    public List<PowerAnalysisCurrentDailyChartF25> getCurrentDailyChart(Map<String, Object> param) {
        return powerAnalysisF25Mapper.getCurrentDailyChart(param);
    }

    @Override
    public List<PowerAnalysisPowerFactorDailyChartF25> getPowerFactorDailyChart(Map<String, Object> param) {
        return powerAnalysisF25Mapper.getPowerFactorDailyChart(param);
    }
}
