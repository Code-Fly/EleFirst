package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.WeeklyCurrent;
import com.elefirst.powerdetail.po.WeeklyCurrentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeeklyCurrentMapper {
    int countByExample(WeeklyCurrentExample example);

    int deleteByExample(WeeklyCurrentExample example);

    int insert(WeeklyCurrent record);

    int insertSelective(WeeklyCurrent record);

    List<WeeklyCurrent> selectByExample(WeeklyCurrentExample example);

    int updateByExampleSelective(@Param("record") WeeklyCurrent record, @Param("example") WeeklyCurrentExample example);

    int updateByExample(@Param("record") WeeklyCurrent record, @Param("example") WeeklyCurrentExample example);
}