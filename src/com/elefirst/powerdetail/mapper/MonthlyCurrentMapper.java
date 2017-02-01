package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.MonthlyCurrent;
import com.elefirst.powerdetail.po.MonthlyCurrentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonthlyCurrentMapper {
    int countByExample(MonthlyCurrentExample example);

    int deleteByExample(MonthlyCurrentExample example);

    int insert(MonthlyCurrent record);

    int insertSelective(MonthlyCurrent record);

    List<MonthlyCurrent> selectByExample(MonthlyCurrentExample example);

    int updateByExampleSelective(@Param("record") MonthlyCurrent record, @Param("example") MonthlyCurrentExample example);

    int updateByExample(@Param("record") MonthlyCurrent record, @Param("example") MonthlyCurrentExample example);
}