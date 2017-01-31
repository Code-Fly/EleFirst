package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.DailyLoad;
import com.elefirst.powerdetail.po.DailyLoadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyLoadMapper {
    int countByExample(DailyLoadExample example);

    int deleteByExample(DailyLoadExample example);

    int insert(DailyLoad record);

    int insertSelective(DailyLoad record);

    List<DailyLoad> selectByExample(DailyLoadExample example);

    int updateByExampleSelective(@Param("record") DailyLoad record, @Param("example") DailyLoadExample example);

    int updateByExample(@Param("record") DailyLoad record, @Param("example") DailyLoadExample example);
}