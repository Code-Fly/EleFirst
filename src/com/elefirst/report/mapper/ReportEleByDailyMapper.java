package com.elefirst.report.mapper;

import com.elefirst.report.po.ReportEleByDaily;
import com.elefirst.report.po.ReportEleByDailyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportEleByDailyMapper {
    int countByExample(ReportEleByDailyExample example);

    int deleteByExample(ReportEleByDailyExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReportEleByDaily record);

    int insertSelective(ReportEleByDaily record);

    List<ReportEleByDaily> selectByExample(ReportEleByDailyExample example);

    ReportEleByDaily selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReportEleByDaily record, @Param("example") ReportEleByDailyExample example);

    int updateByExample(@Param("record") ReportEleByDaily record, @Param("example") ReportEleByDailyExample example);

    int updateByPrimaryKeySelective(ReportEleByDaily record);

    int updateByPrimaryKey(ReportEleByDaily record);
}