package com.elefirst.system.mapper;

import com.elefirst.system.po.TreeInfo;
import com.elefirst.system.po.TreeInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int countByExample(TreeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int deleteByExample(TreeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int insert(TreeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int insertSelective(TreeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    List<TreeInfo> selectByExampleWithBLOBs(TreeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    List<TreeInfo> selectByExample(TreeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    TreeInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int updateByExampleSelective(@Param("record") TreeInfo record, @Param("example") TreeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") TreeInfo record, @Param("example") TreeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int updateByExample(@Param("record") TreeInfo record, @Param("example") TreeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int updateByPrimaryKeySelective(TreeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(TreeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_203_tree_info
     *
     * @mbggenerated Mon Jan 30 02:04:05 CST 2017
     */
    int updateByPrimaryKey(TreeInfo record);
}