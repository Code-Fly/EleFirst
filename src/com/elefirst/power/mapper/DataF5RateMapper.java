package com.elefirst.power.mapper;

import com.elefirst.power.po.DataF5Rate;
import com.elefirst.power.po.DataF5RateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataF5RateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_030_type_two_data_fn5_rate
     *
     * @mbg.generated Wed Apr 19 11:27:02 CST 2017
     */
    long countByExample(DataF5RateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_030_type_two_data_fn5_rate
     *
     * @mbg.generated Wed Apr 19 11:27:02 CST 2017
     */
    int deleteByExample(DataF5RateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_030_type_two_data_fn5_rate
     *
     * @mbg.generated Wed Apr 19 11:27:02 CST 2017
     */
    int insert(DataF5Rate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_030_type_two_data_fn5_rate
     *
     * @mbg.generated Wed Apr 19 11:27:02 CST 2017
     */
    int insertSelective(DataF5Rate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_030_type_two_data_fn5_rate
     *
     * @mbg.generated Wed Apr 19 11:27:02 CST 2017
     */
    List<DataF5Rate> selectByExample(DataF5RateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_030_type_two_data_fn5_rate
     *
     * @mbg.generated Wed Apr 19 11:27:02 CST 2017
     */
    int updateByExampleSelective(@Param("record") DataF5Rate record, @Param("example") DataF5RateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_030_type_two_data_fn5_rate
     *
     * @mbg.generated Wed Apr 19 11:27:02 CST 2017
     */
    int updateByExample(@Param("record") DataF5Rate record, @Param("example") DataF5RateExample example);
}