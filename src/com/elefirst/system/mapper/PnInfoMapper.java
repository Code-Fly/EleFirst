package com.elefirst.system.mapper;

import com.elefirst.system.po.PnInfo;
import com.elefirst.system.po.PnInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PnInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    int countByExample(PnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    int deleteByExample(PnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    int insert(PnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    int insertSelective(PnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    List<PnInfo> selectByExample(PnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    PnInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    int updateByExampleSelective(@Param("record") PnInfo record, @Param("example") PnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    int updateByExample(@Param("record") PnInfo record, @Param("example") PnInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    int updateByPrimaryKeySelective(PnInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_202_pn_info
     *
     * @mbggenerated Mon Jan 30 01:55:38 CST 2017
     */
    int updateByPrimaryKey(PnInfo record);
}