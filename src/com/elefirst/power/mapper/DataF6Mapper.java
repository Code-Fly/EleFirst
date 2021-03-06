package com.elefirst.power.mapper;

import com.elefirst.power.po.DataF6;
import com.elefirst.power.po.DataF6Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataF6Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    long countByExample(DataF6Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    int deleteByExample(DataF6Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    int insert(DataF6 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    int insertSelective(DataF6 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    List<DataF6> selectByExample(DataF6Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    DataF6 selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    int updateByExampleSelective(@Param("record") DataF6 record, @Param("example") DataF6Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    int updateByExample(@Param("record") DataF6 record, @Param("example") DataF6Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    int updateByPrimaryKeySelective(DataF6 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_032_type_two_data_fn6
     *
     * @mbg.generated Wed Apr 19 11:42:11 CST 2017
     */
    int updateByPrimaryKey(DataF6 record);
}