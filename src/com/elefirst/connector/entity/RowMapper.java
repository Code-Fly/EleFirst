package com.elefirst.connector.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/2/24.
 */
public interface RowMapper {
    Object mapRow(ResultSet rs, int index) throws SQLException;
}
