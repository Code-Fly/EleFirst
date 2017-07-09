package com.elefirst.report.service;

import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.powerdetail.po.DailyVoltage;
import com.elefirst.report.po.ReportDisplayByDaily;
import com.elefirst.report.po.ReportEleByDaily;
import com.elefirst.report.po.ReportEnergyByHour;
import com.elefirst.system.po.PnInfo;

import java.util.List;

/**
 * Created by barrie on 17/2/2.
 */
public interface IReportEleDailyService {
	
    List<ReportEleByDaily> getReportEleByDailyList(ReportEleByDaily template);

    List<ReportEleByDaily> getReportEleByDailyList(List<ReportEleByDaily> node, String startDate, String endDate);

    long getReportEleByDailyListCount(ReportEleByDaily template);

    List<ReportEleByDaily> getReportEleByDailyDetail(String id);

    int addReportEleByDaily(ReportEleByDaily template);

    int updateReportEleByDaily(ReportEleByDaily template);

    int delReportEleByDaily(String id);

    public List<ReportEleByDaily> fetchAllReportEleByDaily(String date, String areaId,
			List<Concentrator> concentrators, int rows, int page,
			boolean isPagination) throws Exception;
    
    public List<ReportEleByDaily> fetchAllReportEleByDaily2(String date,
			String areaId, List<Concentrator> concentrators, int rows,
			int page, boolean isPagination) throws Exception;
    
    public int fetchAllReportEleByDailyCount(String date, String areaId,
			List<Concentrator> concentrators) throws Exception;
}
