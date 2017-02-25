package com.elefirst.base.utils;

import com.elefirst.connector.entity.DbColumnInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * Created by 123 on 2016/5/6.
 */
public class DBUtils {
    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    private Connection conn = null;

    static Statement st;

    public Connection getConnection(String driver, String jdbcUrl, Properties info) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        logger.debug("==========Connection info============");
        logger.debug("Jdbc driver : " + driver);
        logger.debug("Jdbc URL : " + jdbcUrl);
        logger.debug("User name : " + info.get("user"));
        logger.debug("Password : " + info.get("password"));
        logger.debug("=====================================");

        conn = DriverManager.getConnection(jdbcUrl, info);
        return conn;
    }

    public boolean execute(String sql) throws SQLException {
        logger.debug("执行SQL：" + sql);
        st = conn.createStatement();
        boolean rs = st.execute(sql);
        return rs;
    }

    public int[] executeBatch(List<String> sqlList) throws SQLException {
        st = conn.createStatement();
        for (int i = 0; i < sqlList.size(); i++) {
            st.addBatch(sqlList.get(i));
        }
        int[] rs = st.executeBatch();
        return rs;
    }


    public List<Object> query(String sql) throws SQLException {
        List<Object> list = new ArrayList<Object>();

        logger.debug("执行SQL：" + sql);
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();
        // 获得结果集列数
        int count = rsmd.getColumnCount();

        while (rs.next()) { // 判断是否还有下一个数据
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            for (int i = 1; i <= count; i++) {
                map.put(rsmd.getColumnLabel(i), rs.getObject(i));
            }
            list.add(map);
        }

        return list;
    }

    public int count(String sql) throws SQLException {
        logger.debug("执行SQL：" + sql);
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.next();
        int count = rs.getInt(1);
        return count;
    }

    public List<DbColumnInfo> info(String sql) throws SQLException {
        List<DbColumnInfo> list = new ArrayList<>();

        logger.debug("执行SQL：" + sql);
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        ResultSetMetaData rsmd = rs.getMetaData();


        // 获得结果集列数
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            DbColumnInfo info = new DbColumnInfo();

            info.setColumnName(rsmd.getColumnName(i));
            info.setColumnType(rsmd.getColumnType(i));
            info.setColumnTypeName(rsmd.getColumnTypeName(i));
            info.setColumnClassName(rsmd.getColumnClassName(i));
            info.setColumnDisplaySize(rsmd.getColumnDisplaySize(i));
            info.setColumnLabel(rsmd.getColumnLabel(i));
            info.setPrecision(rsmd.getPrecision(i));
            info.setScale(rsmd.getScale(i));
            info.setAutoInctement(rsmd.isAutoIncrement(i));
            info.setCurrency(rsmd.isCurrency(i));
            info.setReadOnly(rsmd.isReadOnly(i));
            info.setIsNullable(rsmd.isNullable(i));

            list.add(info);

        }
        return list;
    }


    public void insert(String sql) throws SQLException {
        logger.debug("执行SQL：" + sql);
        st = conn.createStatement();
        int count = st.executeUpdate(sql);
    }

    /* 更新符合要求的记录，并返回更新的记录数目*/
    public void update(String sql) throws SQLException {
        logger.debug("执行SQL：" + sql);
        st = conn.createStatement();
        int count = st.executeUpdate(sql);
    }

    public void close() {
        try {
            conn.close();   //关闭数据库连接
        } catch (SQLException e) {
            logger.error("关闭数据失败", e);
        }

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Connection conn;
//        DBUtils connector = new DBUtils();
//        conn = connector.getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.10.226:3306?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true", "root", "root");
//        if (conn == null) {
//            System.out.println("数据库连接失败！");
//        } else {
//            System.out.println("数据库连接成功！");
//        }
//        System.out.println(connector.query("SELECT * FROM `dmpl`.`file_imp_stat` ORDER BY `filename` DESC"));


//        Connection conn;
//        DBUtils connector = new DBUtils();
//        conn = connector.getConnection("org.apache.hive.jdbc.HiveDriver", "jdbc:hive2://192.168.10.181:21050/", "rhino", "1", null);
//        if (conn == null) {
//            System.out.println("数据库连接失败！");
//        } else {
//            System.out.println("数据库连接成功！");
//        }
//        System.out.println(connector.query("show databases"));
    }
}
