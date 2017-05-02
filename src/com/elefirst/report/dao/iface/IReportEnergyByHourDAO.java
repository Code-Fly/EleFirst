package com.elefirst.report.dao.iface;


import com.elefirst.report.po.ReportEnergyByHour;
import com.elefirst.report.po.ReportEnergyByHourExample;

import java.util.List;

/**
 * Created by VM on 4/19/2017.
 */
public interface IReportEnergyByHourDAO {
    List<ReportEnergyByHour> getReportEnergyByHourList(ReportEnergyByHourExample example);

    long getReportEnergyByHourListCount(ReportEnergyByHourExample example);

    int addReportEnergyByHour(ReportEnergyByHour template);

    int updateReportEnergyByHour(ReportEnergyByHourExample example, ReportEnergyByHour template);

    int delReportEnergyByHour(ReportEnergyByHourExample example);
}
