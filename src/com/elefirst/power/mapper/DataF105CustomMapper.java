package com.elefirst.power.mapper;

import com.elefirst.power.po.DataF105;
import com.elefirst.power.po.DataF105Example;

import java.util.List;

public interface DataF105CustomMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_037_type_one_data_fn105
     *
     * @mbg.generated Thu Apr 20 09:41:10 CST 2017
     */
    List<DataF105> selectSumByExample(DataF105Example example);

    List<DataF105> selectGroupByHourByExample(DataF105Example example);
}