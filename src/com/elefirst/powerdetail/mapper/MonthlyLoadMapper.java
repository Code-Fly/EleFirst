package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.MonthlyLoad;
import com.elefirst.powerdetail.po.MonthlyLoadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonthlyLoadMapper {
    int countByExample(MonthlyLoadExample example);

    int deleteByExample(MonthlyLoadExample example);

    int insert(MonthlyLoad record);

    int insertSelective(MonthlyLoad record);

    List<MonthlyLoad> selectByExample(MonthlyLoadExample example);

    int updateByExampleSelective(@Param("record") MonthlyLoad record, @Param("example") MonthlyLoadExample example);

    int updateByExample(@Param("record") MonthlyLoad record, @Param("example") MonthlyLoadExample example);
}