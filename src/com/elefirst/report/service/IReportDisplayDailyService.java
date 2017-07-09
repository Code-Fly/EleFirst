package com.elefirst.report.service;


import java.util.List;

import com.elefirst.powerdetail.po.Concentrator;
import com.elefirst.report.po.ReportDisplayByDaily;
import com.elefirst.report.po.ReportEnergyByHour;

/**
 * Created by barrie on 17/2/2.
 */
public interface IReportDisplayDailyService {
    List<ReportDisplayByDaily> getReportDisplayByDailyList(ReportDisplayByDaily template);
    List<ReportDisplayByDaily> getReportDisplayByDailyList(List<ReportDisplayByDaily> node, String startDate, String endDate);

    long getReportDisplayByDailyListCount(ReportDisplayByDaily template);

    List<ReportDisplayByDaily> getReportDisplayByDailyDetail(String id);

    int addReportDisplayByDaily(ReportDisplayByDaily template);

    int updateReportDisplayByDaily(ReportDisplayByDaily template);

    int delReportDisplayByDaily(String id);

    public List<ReportDisplayByDaily> fetchAllReportDisplayByDaily(String date, String areaId,
			List<Concentrator> concentrators, int rows, int page,
			boolean isPagination) throws Exception;
    
    public List<ReportDisplayByDaily> fetchAllReportDisplayByDaily2(String date,
			String areaId, List<Concentrator> concentrators, int rows,
			int page, boolean isPagination) throws Exception;
    
    public int fetchAllReportDisplayByDailyCount(String date, String areaId,
			List<Concentrator> concentrators) throws Exception;
}
