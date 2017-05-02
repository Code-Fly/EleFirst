package com.elefirst.report.dao.impl;

import com.elefirst.base.dao.impl.BaseDAO;
import com.elefirst.report.dao.iface.IReportEnergyByHourDAO;
import com.elefirst.report.mapper.ReportEnergyByHourMapper;
import com.elefirst.report.po.ReportEnergyByHour;
import com.elefirst.report.po.ReportEnergyByHourExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
@Repository
public class ReportEnergyByHourDAO extends BaseDAO implements IReportEnergyByHourDAO {
    @Autowired
    private ReportEnergyByHourMapper reportEnergyByHourMapper;


    @Override
    public List<ReportEnergyByHour> getReportEnergyByHourList(ReportEnergyByHourExample example) {
        return reportEnergyByHourMapper.selectByExample(example);
    }

    @Override
    public long getReportEnergyByHourListCount(ReportEnergyByHourExample example) {
        return reportEnergyByHourMapper.countByExample(example);
    }


    @Override
    public int addReportEnergyByHour(ReportEnergyByHour template) {
        return reportEnergyByHourMapper.insertSelective(template);
    }

    @Override
    public int updateReportEnergyByHour(ReportEnergyByHourExample example, ReportEnergyByHour template) {
        return reportEnergyByHourMapper.updateByExampleSelective(template, example);
    }

    @Override
    public int delReportEnergyByHour(ReportEnergyByHourExample example) {
        return reportEnergyByHourMapper.deleteByExample(example);
    }
}