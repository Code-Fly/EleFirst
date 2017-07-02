package com.elefirst.report.mapper;

import com.elefirst.report.po.ReportDisplayByDaily;
import com.elefirst.report.po.ReportDisplayByDailyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportDisplayByDailyMapper {
    int countByExample(ReportDisplayByDailyExample example);

    int deleteByExample(ReportDisplayByDailyExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportDisplayByDaily record);

    int insertSelective(ReportDisplayByDaily record);

    List<ReportDisplayByDaily> selectByExample(ReportDisplayByDailyExample example);

    ReportDisplayByDaily selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportDisplayByDaily record, @Param("example") ReportDisplayByDailyExample example);

    int updateByExample(@Param("record") ReportDisplayByDaily record, @Param("example") ReportDisplayByDailyExample example);

    int updateByPrimaryKeySelective(ReportDisplayByDaily record);

    int updateByPrimaryKey(ReportDisplayByDaily record);
}