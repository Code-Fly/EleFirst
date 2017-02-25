package com.elefirst.connector.example;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/22.
 */
public class MssqlExample extends Example {
    protected static final String DELIMITER_BEGINNING = "[";

    protected static final String DELIMITER_ENDDING = "]";

    public MssqlExample() {
        super();
        super.DELIMITER_BEGINNING = this.DELIMITER_BEGINNING;
        super.DELIMITER_ENDDING = this.DELIMITER_ENDDING;
    }

    @Override
    public String pageSql(String sql) throws SQLException {
        if (page <= 0 || rows <= 0) {
            throw new SQLException("invalid page or rows");
        }
        if (null == getOrderByClause() || getOrderByClause().isEmpty()) {
            throw new SQLException("order by clause is empty or null");
        }

        String tmpTbName = "t_" + new Date().getTime();
        String newSql = "SELECT * FROM ( SELECT *, ROW_NUMBER() OVER(ORDER BY " + getOrderByClause() + " ) AS RN FROM (" + querySql(sql,false) + ") " + tmpTbName + ") " + tmpTbName + " WHERE RN BETWEEN " + ((page - 1) * rows + 1) + " AND " + (page * rows);

        System.out.println(newSql);
        return newSql;
    }
}
