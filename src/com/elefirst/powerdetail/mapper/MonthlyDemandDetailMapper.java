package com.elefirst.powerdetail.mapper;

import java.util.List;
import java.util.Map;

import com.elefirst.powerdetail.po.MonthlyDemandDetail;


public interface MonthlyDemandDetailMapper {
    int countByExample(Map map);

    List<MonthlyDemandDetail> selectByExample(Map map);
    
    List<MonthlyDemandDetail> selectByExample2(Map map);
    
    List<MonthlyDemandDetail> selectByExample3(Map map);

}