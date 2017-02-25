package com.elefirst.connector.proxy;

import com.elefirst.base.utils.DBUtils;
import com.elefirst.connector.entity.DbColumnInfo;
import com.elefirst.connector.example.Example;
import com.elefirst.connector.example.OracleExample;
import net.sf.json.JSONArray;
import oracle.sql.TIMESTAMP;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2017/2/21.
 */
public class OracleConnector extends DbConnector {
    private String instance;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public OracleConnector(String url, Integer port, boolean anonymous, String userName, String password, String instance) {
        super(url, port, anonymous, userName, password);
        this.instance = instance;
        this.driver = "oracle.jdbc.driver.OracleDriver";
        this.jdbcUrl = "jdbc:oracle:thin:@" + url + ":" + port + ":" + instance;
        this.connector = new DBUtils();
    }

    public OracleConnector(String jdbcUrl, String jdbcType, boolean anonymous, String userName, String password, String instance) {
        super(jdbcUrl, jdbcType, anonymous, userName, password);
        this.instance = instance;
        this.driver = "oracle.jdbc.driver.OracleDriver";
        this.connector = new DBUtils();
    }

    @Override
    public List<String> getDatabaseList() throws SQLException, ClassNotFoundException {
        String sql = "SELECT username FROM all_users";

        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        if (userName.toUpperCase().equals("SYSTEM") || userName.toUpperCase().equals("SYS")) {
            info.put("internal_logon", "sysdba");
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> obj = connector.query(sql);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            list.add((String) ((HashMap<?, ?>) obj.get(i)).get("username".toUpperCase()));
        }
        connector.close();
        return list;
    }

    @Override
    public List<String> getTableList(String dbName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT table_name FROM all_tables T WHERE T . OWNER = '" + dbName + "'";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        if (userName.toUpperCase().equals("SYSTEM") || userName.toUpperCase().equals("SYS")) {
            info.put("internal_logon", "sysdba");
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> obj = connector.query(sql);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            list.add((String) ((HashMap<?, ?>) obj.get(i)).get("table_name".toUpperCase()));
        }
        connector.close();
        return list;
    }

    @Override
    public List<DbColumnInfo> getColumnInfo(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT " +
                "* " +
                "FROM " +
                "all_tab_columns t1 " +
                "LEFT JOIN all_col_comments t2 ON t1. OWNER = t2. OWNER " +
                "AND T1.table_name = T2.table_name " +
                "AND T1.column_name = T2.column_name " +
                "WHERE " +
                "T1. OWNER = '" + dbName + "' " +
                "AND T1.table_name = '" + tbName + "'";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        if (userName.toUpperCase().equals("SYSTEM") || userName.toUpperCase().equals("SYS")) {
            info.put("internal_logon", "sysdba");
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> result = connector.query(sql);
        connector.close();
        List<DbColumnInfo> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            DbColumnInfo item = new DbColumnInfo();
            item.setColumnName((String) ((HashMap<?, ?>) result.get(i)).get("column_name".toUpperCase()));
            item.setColumnLabel((String) ((HashMap<?, ?>) result.get(i)).get("column_name".toUpperCase()));
            item.setColumnTypeName(((String) ((HashMap<?, ?>) result.get(i)).get("data_type".toUpperCase())).split("\\(")[0]);
            item.setColumnDisplaySize((Number) ((HashMap<?, ?>) result.get(i)).get("data_length".toUpperCase()));
            item.setColumnComment((String) ((HashMap<?, ?>) result.get(i)).get("comments".toUpperCase()));
            list.add(item);
        }

        return list;
    }

    @Override
    public List<Object> getResult(String sql, Example condition) throws SQLException, ClassNotFoundException {
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        if (userName.toUpperCase().equals("SYSTEM") || userName.toUpperCase().equals("SYS")) {
            info.put("internal_logon", "sysdba");
        }
        connector.getConnection(driver, jdbcUrl, info);
        String newSql;
        if (condition.getPage() <= 0 || condition.getRows() <= 0) {
            newSql = condition.querySql(sql);
        } else {
            newSql = condition.pageSql(sql);
        }

        List<Object> result = connector.query(newSql);
        for (int i = 0; i < result.size(); i++) {
            LinkedHashMap row = (LinkedHashMap) result.get(i);
            for (Object key : row.keySet()) {
                if (row.get(key) != null) {
                    if (row.get(key) instanceof TIMESTAMP) {
                        row.put(key, ((TIMESTAMP) row.get(key)).toJdbc().toString());
                    }
                    //
                    else if (row.get(key) != null && row.get(key) instanceof java.sql.Timestamp) {
                        row.put(key, row.get(key).toString());
                    }
                }
            }
        }
        connector.close();
        return result;
    }

    @Override
    public int getResultCount(String sql, Example condition) throws SQLException, ClassNotFoundException {
        String newSql = condition.countSql(sql);
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        int result = connector.count(newSql);
        connector.close();

        return result;
    }

    @Override
    public List<DbColumnInfo> getResultColumnInfo(String sql) throws SQLException, ClassNotFoundException {
        String tmpTbName = "t_" + new Date().getTime();
        String newSql = "SELECT * FROM ( " + sql + " ) " + tmpTbName + " WHERE ROWNUM <= 1";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        List<DbColumnInfo> result = connector.info(newSql);
        connector.close();

        return result;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        OracleConnector connector = new OracleConnector("192.168.10.43", 1521, false, "system", "1", "XE");
//        System.out.println(connector.getDatabaseList());
//        System.out.println(connector.getTableList("SYSTEM"));
//        System.out.println(connector.getColumnInfo("SYSTEM", "t_012_type_one_data_fn57"));

        OracleExample condition = new OracleExample();
        OracleExample.Criteria criteria = condition.createCriteria();
        criteria.andIsNotNull("id");
        criteria.andGreaterThan("harmonicTimes", "1");
        criteria.andBetween("sendTime", "20170218000000", "20170222000000");
        criteria.andEqualTo("area_id", "1");
        List<Object> list = new ArrayList<>();
        list.add("417");
        list.add("3658");
        criteria.andIn("concentrator_id", list);
        condition.setPage(1);
        condition.setRows(2);

        System.out.println(connector.getResult("SELECT * FROM \"SYSTEM\".\"t_012_type_one_data_fn57\"", condition));
        System.out.println(connector.getResultCount("SELECT * FROM \"SYSTEM\".\"t_012_type_one_data_fn57\"", condition));
        System.out.println(connector.getResultColumnInfo("SELECT * FROM \"SYSTEM\".\"t_012_type_one_data_fn57\""));
    }
}
