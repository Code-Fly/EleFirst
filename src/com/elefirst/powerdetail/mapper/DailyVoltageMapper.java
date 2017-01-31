package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.DailyVoltage;
import com.elefirst.powerdetail.po.DailyVoltageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyVoltageMapper {
    int countByExample(DailyVoltageExample example);

    int deleteByExample(DailyVoltageExample example);

    int insert(DailyVoltage record);

    int insertSelective(DailyVoltage record);

    List<DailyVoltage> selectByExample(DailyVoltageExample example);

    int updateByExampleSelective(@Param("record") DailyVoltage record, @Param("example") DailyVoltageExample example);

    int updateByExample(@Param("record") DailyVoltage record, @Param("example") DailyVoltageExample example);
}