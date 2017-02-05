package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.WeeklyDemand;

import java.util.List;
import java.util.Map;

public interface WeeklyDemandMapper {
    int countByExample(Map map);

    List<WeeklyDemand> selectByExample(Map map);

}