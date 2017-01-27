package com.elefirst.poweranalysis.dao.impl;

import com.elefirst.base.dao.BaseDAO;
import com.elefirst.poweranalysis.dao.iface.IPowerAnalysisDAO;
import com.elefirst.poweranalysis.mapper.PowerAnalysisF25Mapper;
import com.elefirst.poweranalysis.po.PowerAnalysisComparisonF25;
import com.elefirst.poweranalysis.po.PowerAnalysisF25;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/1/28.
 */
@Repository
public class PowerAnalysisDAO extends BaseDAO implements IPowerAnalysisDAO {
    @Autowired
    private PowerAnalysisF25Mapper powerAnalysisF25Mapper;

    @Override
    public List<PowerAnalysisComparisonF25> getComparisonChart(List<PowerAnalysisF25> list) {
        return powerAnalysisF25Mapper.getComparisonChart(list);
    }
}
