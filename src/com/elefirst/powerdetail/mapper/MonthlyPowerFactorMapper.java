package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.MonthlyPowerFactor;
import com.elefirst.powerdetail.po.MonthlyPowerFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonthlyPowerFactorMapper {
    int countByExample(MonthlyPowerFactorExample example);

    int deleteByExample(MonthlyPowerFactorExample example);

    int insert(MonthlyPowerFactor record);

    int insertSelective(MonthlyPowerFactor record);

    List<MonthlyPowerFactor> selectByExample(MonthlyPowerFactorExample example);

    int updateByExampleSelective(@Param("record") MonthlyPowerFactor record, @Param("example") MonthlyPowerFactorExample example);

    int updateByExample(@Param("record") MonthlyPowerFactor record, @Param("example") MonthlyPowerFactorExample example);
}