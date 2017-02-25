package com.elefirst.connector.proxy;

import com.elefirst.base.utils.DBUtils;
import com.elefirst.connector.entity.DbColumnInfo;
import com.elefirst.connector.example.Example;
import com.elefirst.connector.example.MysqlExample;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2017/2/21.
 */
public class MysqlConnector extends DbConnector {
    public MysqlConnector(String url, Integer port, boolean anonymous, String userName, String password) {
        super(url, port, anonymous, userName, password);
        this.driver = "com.mysql.jdbc.Driver";
        this.jdbcUrl = "jdbc:mysql://" + url + ":" + port + "?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true";
        this.connector = new DBUtils();
    }

    public MysqlConnector(String jdbcUrl, String jdbcType, boolean anonymous, String userName, String password) {
        super(jdbcUrl, jdbcType, anonymous, userName, password);
        this.driver = "com.mysql.jdbc.Driver";
        this.connector = new DBUtils();
    }

    @Override
    public List<String> getDatabaseList() throws SQLException, ClassNotFoundException {
        String sql = "SHOW DATABASES";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> obj = connector.query(sql);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            list.add((String) ((HashMap<?, ?>) obj.get(i)).get("Database"));
        }
        connector.close();
        return list;
    }

    @Override
    public List<String> getTableList(String dbName) throws SQLException, ClassNotFoundException {
        String sql = "SHOW TABLES IN `" + dbName + "`";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> obj = connector.query(sql);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            list.add((String) ((HashMap<?, ?>) obj.get(i)).get("Tables_in_" + dbName));
        }
        connector.close();
        return list;
    }

    @Override
    public List<DbColumnInfo> getColumnInfo(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT " +
                "* " +
                "FROM " +
                "( " +
                "SELECT " +
                "* " +
                "FROM " +
                "INFORMATION_SCHEMA. COLUMNS " +
                "WHERE " +
                "table_schema = '" + dbName + "' " +
                "AND table_name = '" + tbName + "' " +
                ") AS a ";
//                "ORDER BY " +
//                "a.column_name ASC";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> result = connector.query(sql);
        connector.close();
        List<DbColumnInfo> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            DbColumnInfo item = new DbColumnInfo();
            item.setColumnName((String) ((HashMap<?, ?>) result.get(i)).get("COLUMN_NAME"));
            item.setColumnLabel((String) ((HashMap<?, ?>) result.get(i)).get("COLUMN_NAME"));
            item.setColumnTypeName((String) ((HashMap<?, ?>) result.get(i)).get("DATA_TYPE"));
            item.setColumnDisplaySize((Number) ((HashMap<?, ?>) result.get(i)).get("CHARACTER_MAXIMUM_LENGTH"));
            item.setColumnComment((String) ((HashMap<?, ?>) result.get(i)).get("COLUMN_COMMENT"));
            list.add(item);
        }

        return list;
    }


    @Override
    public List<Object> getResult(String sql, Example condition) throws SQLException, ClassNotFoundException {
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
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
                if (row.get(key) != null && row.get(key) instanceof java.sql.Timestamp) {
                    row.put(key, row.get(key).toString());
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
        String newSql = "SELECT * FROM ( " + sql + " ) " + tmpTbName + " LIMIT 1";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        List<DbColumnInfo> result = connector.info(newSql);
        connector.close();

        return result;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MysqlConnector connector = new MysqlConnector("116.62.102.138", 3306, false, "root", "c45d36576b");
//        System.out.println(connector.getDatabaseList());
//        System.out.println(connector.getTableList("power"));
//        System.out.println(connector.getColumnInfo("power", "t_012_type_one_data_fn57"));

        MysqlExample condition = new MysqlExample();
        MysqlExample.Criteria criteria = condition.createCriteria();
        criteria.andIsNotNull("id");
        criteria.andGreaterThan("pn", "1");
        criteria.andBetween("sendTime", "20170217000000", "20170225000000");
        criteria.andEqualTo("area_id", "1");
        List<Object> list = new ArrayList<>();
        list.add("417");
        list.add("3658");
        criteria.andIn("concentrator_id", list);
        condition.setPage(1);
        condition.setRows(2);
        condition.setOrderByClause("`sendTime` ASC");

        System.out.println(connector.getResult("SELECT a.*,b.A_harmonicCurrent,b.A_harmonicVoltage,b.B_harmonicCurrent,b.B_harmonicVoltage,b.C_harmonicCurrent,b.C_harmonicVoltage as b_C_harmonicVoltage FROM power.t_012_type_one_data_fn57 a JOIN power.t_013_type_one_data_fn57_harmonic b ON a.id = b.id", condition));
        System.out.println(connector.getResultCount("SELECT a.*,b.A_harmonicCurrent,b.A_harmonicVoltage,b.B_harmonicCurrent,b.B_harmonicVoltage,b.C_harmonicCurrent,b.C_harmonicVoltage as b_C_harmonicVoltage FROM power.t_012_type_one_data_fn57 a JOIN power.t_013_type_one_data_fn57_harmonic b ON a.id = b.id", condition));
        System.out.println(connector.getResultColumnInfo("SELECT a.*,b.A_harmonicCurrent,b.A_harmonicVoltage,b.B_harmonicCurrent,b.B_harmonicVoltage,b.C_harmonicCurrent,b.C_harmonicVoltage as b_C_harmonicVoltage FROM power.t_012_type_one_data_fn57 a JOIN power.t_013_type_one_data_fn57_harmonic b ON a.id = b.id"));
    }
}
