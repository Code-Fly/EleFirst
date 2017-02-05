package com.elefirst.powerdetail.mapper;

import java.util.List;
import java.util.Map;
import com.elefirst.powerdetail.po.WeeklyDemandDetail;


public interface WeeklyDemandDetailMapper {
    int countByExample(Map map);

    List<WeeklyDemandDetail> selectByExample(Map map);

}