package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.MonthlyVoltage;
import com.elefirst.powerdetail.po.MonthlyVoltageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonthlyVoltageMapper {
    int countByExample(MonthlyVoltageExample example);

    int deleteByExample(MonthlyVoltageExample example);

    int insert(MonthlyVoltage record);

    int insertSelective(MonthlyVoltage record);

    List<MonthlyVoltage> selectByExample(MonthlyVoltageExample example);

    int updateByExampleSelective(@Param("record") MonthlyVoltage record, @Param("example") MonthlyVoltageExample example);

    int updateByExample(@Param("record") MonthlyVoltage record, @Param("example") MonthlyVoltageExample example);
}