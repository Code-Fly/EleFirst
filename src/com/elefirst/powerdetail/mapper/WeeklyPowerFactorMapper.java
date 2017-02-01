package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.WeeklyPowerFactor;
import com.elefirst.powerdetail.po.WeeklyPowerFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeeklyPowerFactorMapper {
    int countByExample(WeeklyPowerFactorExample example);

    int deleteByExample(WeeklyPowerFactorExample example);

    int insert(WeeklyPowerFactor record);

    int insertSelective(WeeklyPowerFactor record);

    List<WeeklyPowerFactor> selectByExample(WeeklyPowerFactorExample example);

    int updateByExampleSelective(@Param("record") WeeklyPowerFactor record, @Param("example") WeeklyPowerFactorExample example);

    int updateByExample(@Param("record") WeeklyPowerFactor record, @Param("example") WeeklyPowerFactorExample example);
}