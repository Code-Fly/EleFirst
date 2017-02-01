package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.WeeklyLoad;
import com.elefirst.powerdetail.po.WeeklyLoadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeeklyLoadMapper {
    int countByExample(WeeklyLoadExample example);

    int deleteByExample(WeeklyLoadExample example);

    int insert(WeeklyLoad record);

    int insertSelective(WeeklyLoad record);

    List<WeeklyLoad> selectByExample(WeeklyLoadExample example);

    int updateByExampleSelective(@Param("record") WeeklyLoad record, @Param("example") WeeklyLoadExample example);

    int updateByExample(@Param("record") WeeklyLoad record, @Param("example") WeeklyLoadExample example);
}