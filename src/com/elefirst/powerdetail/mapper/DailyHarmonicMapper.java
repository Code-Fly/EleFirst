package com.elefirst.powerdetail.mapper;

import java.util.List;
import java.util.Map;

import com.elefirst.powerdetail.po.DailyElectricity;
import com.elefirst.powerdetail.po.DailyHarmonic;


public interface DailyHarmonicMapper {
    int countByExample(Map map);

    List<DailyHarmonic> selectByExample(Map map);
    
    int countByExample2(Map map);

    List<DailyHarmonic> selectByExample2(Map map);

}