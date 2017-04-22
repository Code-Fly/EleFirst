package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.YearlyLoad;
import com.elefirst.powerdetail.po.YearlyLoadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YearlyLoadMapper {
    int countByExample(YearlyLoadExample example);

    int deleteByExample(YearlyLoadExample example);

    int insert(YearlyLoad record);

    int insertSelective(YearlyLoad record);

    List<YearlyLoad> selectByExample(YearlyLoadExample example);

    int updateByExampleSelective(@Param("record") YearlyLoad record, @Param("example") YearlyLoadExample example);

    int updateByExample(@Param("record") YearlyLoad record, @Param("example") YearlyLoadExample example);
}