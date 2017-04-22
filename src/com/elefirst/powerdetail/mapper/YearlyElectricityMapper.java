package com.elefirst.powerdetail.mapper;

import java.util.List;
import java.util.Map;

import com.elefirst.powerdetail.po.YearlyElectricity;


public interface YearlyElectricityMapper {
    int countByExample(Map map);

    List<YearlyElectricity> selectByExample(Map map);

}