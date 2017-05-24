package com.elefirst.system.mapper;

import com.elefirst.system.po.RoleAreaMap;
import com.elefirst.system.po.RoleAreaMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleAreaMapMapper {
    int countByExample(RoleAreaMapExample example);

    int deleteByExample(RoleAreaMapExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoleAreaMap record);

    int insertSelective(RoleAreaMap record);

    List<RoleAreaMap> selectByExample(RoleAreaMapExample example);

    RoleAreaMap selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoleAreaMap record, @Param("example") RoleAreaMapExample example);

    int updateByExample(@Param("record") RoleAreaMap record, @Param("example") RoleAreaMapExample example);

    int updateByPrimaryKeySelective(RoleAreaMap record);

    int updateByPrimaryKey(RoleAreaMap record);
}