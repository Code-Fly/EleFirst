package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.WeeklyVoltage;
import com.elefirst.powerdetail.po.WeeklyVoltageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeeklyVoltageMapper {
    int countByExample(WeeklyVoltageExample example);

    int deleteByExample(WeeklyVoltageExample example);

    int insert(WeeklyVoltage record);

    int insertSelective(WeeklyVoltage record);

    List<WeeklyVoltage> selectByExample(WeeklyVoltageExample example);

    int updateByExampleSelective(@Param("record") WeeklyVoltage record, @Param("example") WeeklyVoltageExample example);

    int updateByExample(@Param("record") WeeklyVoltage record, @Param("example") WeeklyVoltageExample example);
}