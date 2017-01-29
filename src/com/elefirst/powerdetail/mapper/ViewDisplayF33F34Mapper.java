package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.ViewDisplayF33F34;
import com.elefirst.powerdetail.po.ViewDisplayF33F34Example;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ViewDisplayF33F34Mapper {
    int countByExample(ViewDisplayF33F34Example example);

    int deleteByExample(ViewDisplayF33F34Example example);

    int insert(ViewDisplayF33F34 record);

    int insertSelective(ViewDisplayF33F34 record);

    List<ViewDisplayF33F34> selectByExample(ViewDisplayF33F34Example example);

    int updateByExampleSelective(@Param("record") ViewDisplayF33F34 record, @Param("example") ViewDisplayF33F34Example example);

    int updateByExample(@Param("record") ViewDisplayF33F34 record, @Param("example") ViewDisplayF33F34Example example);
    
    //自定义获取每个区域下相关集线器对应的监测点最新一条电力记录数(根据发送时间)
    List<ViewDisplayF33F34> myselectByExample(Map map);
    
    //获取每个区域下相关集线器对应的监测点最新示数总数
    int myselectByExampleCount(Map map); 
    
    //根据集中器id,区域id,以及检测点id查询对应的所有记录
    int displayDetailCount(Map map);
}