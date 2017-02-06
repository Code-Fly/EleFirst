package com.elefirst.powerdetail.mapper;

import java.util.List;
import java.util.Map;
import com.elefirst.powerdetail.po.DailyElectricity;


public interface DailyElectricityMapper {
    int countByExample(Map map);

    List<DailyElectricity> selectByExample(Map map);

}