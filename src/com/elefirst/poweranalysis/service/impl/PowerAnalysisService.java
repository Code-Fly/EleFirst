package com.elefirst.poweranalysis.service.impl;

import com.elefirst.base.service.BaseService;
import com.elefirst.poweranalysis.dao.iface.IPowerAnalysisDAO;
import com.elefirst.poweranalysis.po.PowerAnalysisComparisonF25;
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
    public List<PowerAnalysisComparisonF25> getComparisonChart(Map<String, Object> param) {
        return powerAnalysisDAO.getComparisonChart(param);
    }
}