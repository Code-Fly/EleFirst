package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.TwoRealtimeDisplay;
import com.elefirst.powerdetail.po.TwoRealtimeDisplayExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TwoRealtimeDisplayMapper {
    int countByExample(TwoRealtimeDisplayExample example);

    int deleteByExample(TwoRealtimeDisplayExample example);

    int insert(TwoRealtimeDisplay record);

    int insertSelective(TwoRealtimeDisplay record);

    List<TwoRealtimeDisplay> selectByExample(TwoRealtimeDisplayExample example);

    int updateByExampleSelective(@Param("record") TwoRealtimeDisplay record, @Param("example") TwoRealtimeDisplayExample example);

    int updateByExample(@Param("record") TwoRealtimeDisplay record, @Param("example") TwoRealtimeDisplayExample example);
    
    //自定义获取每个区域下相关集线器对应的监测点最新一条电力记录数(根据发送时间)
    List<TwoRealtimeDisplay> myselectByExample(Map map);
    
    //获取每个区域下相关集线器对应的监测点最新示数总数
    int myselectByExampleCount(Map map); 
    
    //根据集中器id,区域id,以及检测点id查询对应的所有记录
    int displayDetailCount(Map map);
}