package com.elefirst.report.service.iface;

import com.elefirst.report.po.ReportEnergyByHour;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IReportEnergyByHourService {
    List<ReportEnergyByHour> getReportEnergyByHourList(ReportEnergyByHour template);

    List<ReportEnergyByHour> getReportEnergyByHourList(List<ReportEnergyByHour> node, String startDate, String endDate);

    //

    long getReportEnergyByHourListCount(ReportEnergyByHour template);

    List<ReportEnergyByHour> getReportEnergyByHourDetail(String id);

    int addReportEnergyByHour(ReportEnergyByHour template);

    int updateReportEnergyByHour(ReportEnergyByHour template);

    int delReportEnergyByHour(String id);

}
