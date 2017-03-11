package com.elefirst.power.mapper;

import com.elefirst.power.po.DataF33FrozenDay;
import com.elefirst.power.po.DataF33FrozenDayExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataF33FrozenDayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    int countByExample(DataF33FrozenDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    int deleteByExample(DataF33FrozenDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    int insert(DataF33FrozenDay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    int insertSelective(DataF33FrozenDay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    List<DataF33FrozenDay> selectByExample(DataF33FrozenDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    DataF33FrozenDay selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    int updateByExampleSelective(@Param("record") DataF33FrozenDay record, @Param("example") DataF33FrozenDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    int updateByExample(@Param("record") DataF33FrozenDay record, @Param("example") DataF33FrozenDayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    int updateByPrimaryKeySelective(DataF33FrozenDay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_004_type_one_data_fn33_frozen_day
     *
     * @mbggenerated Sat Mar 11 19:02:38 CST 2017
     */
    int updateByPrimaryKey(DataF33FrozenDay record);
}