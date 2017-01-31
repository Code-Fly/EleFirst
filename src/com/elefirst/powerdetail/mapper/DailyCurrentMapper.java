package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.DailyCurrent;
import com.elefirst.powerdetail.po.DailyCurrentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyCurrentMapper {
    int countByExample(DailyCurrentExample example);

    int deleteByExample(DailyCurrentExample example);

    int insert(DailyCurrent record);

    int insertSelective(DailyCurrent record);

    List<DailyCurrent> selectByExample(DailyCurrentExample example);

    int updateByExampleSelective(@Param("record") DailyCurrent record, @Param("example") DailyCurrentExample example);

    int updateByExample(@Param("record") DailyCurrent record, @Param("example") DailyCurrentExample example);
}