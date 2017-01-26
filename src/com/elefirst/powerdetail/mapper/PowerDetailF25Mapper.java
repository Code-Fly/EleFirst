package com.elefirst.powerdetail.mapper;

import com.elefirst.powerdetail.po.PowerDetailF25;
import com.elefirst.powerdetail.po.PowerDetailF25Example;
import java.util.List;
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
}