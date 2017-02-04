package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.MonthlyDemand;
import java.util.List;
import java.util.Map;

public interface MonthlyDemandMapper {
    int countByExample(Map map);

    List<MonthlyDemand> selectByExample(Map map);

}