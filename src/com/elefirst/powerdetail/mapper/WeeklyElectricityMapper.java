package com.elefirst.powerdetail.mapper;

import java.util.List;
import java.util.Map;

import com.elefirst.powerdetail.po.WeeklyElectricity;


public interface WeeklyElectricityMapper {
    int countByExample(Map map);

    List<WeeklyElectricity> selectByExample(Map map);

}