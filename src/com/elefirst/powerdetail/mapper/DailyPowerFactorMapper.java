package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.DailyPowerFactor;
import com.elefirst.powerdetail.po.DailyPowerFactorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyPowerFactorMapper {
    int countByExample(DailyPowerFactorExample example);

    int deleteByExample(DailyPowerFactorExample example);

    int insert(DailyPowerFactor record);

    int insertSelective(DailyPowerFactor record);

    List<DailyPowerFactor> selectByExample(DailyPowerFactorExample example);

    int updateByExampleSelective(@Param("record") DailyPowerFactor record, @Param("example") DailyPowerFactorExample example);

    int updateByExample(@Param("record") DailyPowerFactor record, @Param("example") DailyPowerFactorExample example);
}