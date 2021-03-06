package com.elefirst.connector.example;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/22.
 */
public class MysqlExample extends Example {
    protected static final String DELIMITER_BEGINNING = "`";

    protected static final String DELIMITER_ENDDING = "`";

    public MysqlExample() {
        super();
        super.DELIMITER_BEGINNING = this.DELIMITER_BEGINNING;
        super.DELIMITER_ENDDING = this.DELIMITER_ENDDING;
    }

    @Override
    public String pageSql(String sql) throws SQLException {
        if (page <= 0 || rows <= 0) {
            throw new SQLException("invalid page or rows");
        }

        String tmpTbName = "t_" + new Date().getTime();
        String newSql = "SELECT * FROM ( " + querySql(sql) + " ) " + tmpTbName + " LIMIT " + ((page - 1) * rows) + "," + rows;
        System.out.println(newSql);

        return newSql;
    }
}
