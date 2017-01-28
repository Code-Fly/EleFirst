package com.elefirst.poweranalysis.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.poweranalysis.dao.iface.IPowerAnalysisDAO;
import com.elefirst.poweranalysis.mapper.PowerAnalysisF25Mapper;
import com.elefirst.poweranalysis.po.PowerAnalysisComparisonChartF25;
import com.elefirst.poweranalysis.po.PowerAnalysisComparisonTableF25;
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
    public List<PowerAnalysisComparisonChartF25> getComparisonChart(Map<String, Object> param) {
        return powerAnalysisF25Mapper.getComparisonChart(param);
    }

    @Override
    public List<PowerAnalysisComparisonTableF25> getComparisonTable(Map<String, Object> param) {
        return powerAnalysisF25Mapper.getComparisonTable(param);
    }
}
