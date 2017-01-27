package com.elefirst.poweranalysis.mapper;

import com.elefirst.poweranalysis.po.PowerAnalysisF25;
import com.elefirst.poweranalysis.po.PowerAnalysisF25Example;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PowerAnalysisF25Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    int countByExample(PowerAnalysisF25Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    int deleteByExample(PowerAnalysisF25Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    int insert(PowerAnalysisF25 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    int insertSelective(PowerAnalysisF25 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    List<PowerAnalysisF25> selectByExample(PowerAnalysisF25Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    PowerAnalysisF25 selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    int updateByExampleSelective(@Param("record") PowerAnalysisF25 record, @Param("example") PowerAnalysisF25Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    int updateByExample(@Param("record") PowerAnalysisF25 record, @Param("example") PowerAnalysisF25Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    int updateByPrimaryKeySelective(PowerAnalysisF25 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_003_type_one_data_fn25
     *
     * @mbggenerated Sat Jan 28 01:45:28 CST 2017
     */
    int updateByPrimaryKey(PowerAnalysisF25 record);
}