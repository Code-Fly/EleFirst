package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.CurrentDetail;
import com.elefirst.powerdetail.po.PowerDetailF25;
import com.elefirst.powerdetail.po.PowerDetailF25Example;
import com.elefirst.powerdetail.po.PowerFactorDetail;
import com.elefirst.powerdetail.po.TotalActivePowerDetail;
import com.elefirst.powerdetail.po.VoltageDetail;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PowerDetailF25Mapper {
    int countByExample(PowerDetailF25Example example);

    int deleteByExample(PowerDetailF25Example example);

    int deleteByPrimaryKey(String id);

    int insert(PowerDetailF25 record);

    int insertSelective(PowerDetailF25 record);

    List<PowerDetailF25> selectByExample(PowerDetailF25Example example);

    PowerDetailF25 selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PowerDetailF25 record, @Param("example") PowerDetailF25Example example);

    int updateByExample(@Param("record") PowerDetailF25 record, @Param("example") PowerDetailF25Example example);

    int updateByPrimaryKeySelective(PowerDetailF25 record);

    int updateByPrimaryKey(PowerDetailF25 record);
    
    //自定义获取每个区域下相关集线器对应的监测点最新一条电力记录数(根据发送时间)
    List<PowerDetailF25> myselectByExample(Map map);
    
    //获取每个区域下相关集线器对应的监测点总数
    int myselectByExampleCount(Map map); 
    
    //获取每个监测点下最大和最小电压
    VoltageDetail queryVoltageDetail(Map map);
    
    //获取每个监测点下最大和最小电流
    CurrentDetail queryCurrentDetail(Map map);
    
    //获取每个监测点下最大和最小功率因素
    PowerFactorDetail queryPowerFactorDetail(Map map);
    
    //获取每个监测点下最大,最小以及平均负荷
    TotalActivePowerDetail queryTotalActivePowerDetail(Map map);
}