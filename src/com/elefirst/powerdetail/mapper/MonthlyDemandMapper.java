package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.MonthlyDemand;
import com.elefirst.powerdetail.po.MonthlyDemandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonthlyDemandMapper {
    int countByExample(MonthlyDemandExample example);

    int deleteByExample(MonthlyDemandExample example);

    int insert(MonthlyDemand record);

    int insertSelective(MonthlyDemand record);

    List<MonthlyDemand> selectByExample(MonthlyDemandExample example);

    int updateByExampleSelective(@Param("record") MonthlyDemand record, @Param("example") MonthlyDemandExample example);

    int updateByExample(@Param("record") MonthlyDemand record, @Param("example") MonthlyDemandExample example);
}