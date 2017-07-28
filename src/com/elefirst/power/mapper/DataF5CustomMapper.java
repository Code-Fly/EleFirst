package com.elefirst.power.mapper;

import com.elefirst.power.po.DataF5;
import com.elefirst.power.po.DataF5Example;
import com.elefirst.power.po.DataF5WithRate;

import java.util.List;

public interface DataF5CustomMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_030_type_two_data_fn5
     *
     * @mbg.generated Wed Apr 19 10:34:08 CST 2017
     */
    List<DataF5> selectSumByExample(DataF5Example example);

    List<DataF5WithRate> selectWithRateByExample(DataF5Example example);

    long countWithRateByExample(DataF5Example example);
}