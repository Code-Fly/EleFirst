package com.elefirst.powerdetail.mapper;

import java.util.List;
import java.util.Map;

import com.elefirst.powerdetail.po.MonthlyElectricity;


public interface MonthlyElectricityMapper {
    int countByExample(Map map);

    List<MonthlyElectricity> selectByExample(Map map);

}