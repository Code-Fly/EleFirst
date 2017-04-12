package com.elefirst.connector.proxy;

import com.elefirst.base.entity.Page2;
import com.elefirst.base.utils.DBUtils;
import com.elefirst.connector.entity.DbColumnInfo;
import com.elefirst.connector.entity.DbTableInfo;
import com.elefirst.connector.example.Example;
import com.elefirst.connector.example.SparkExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2017/2/21.
 */
public class SparkConnector extends DbConnector {
    public SparkConnector(String url, Integer port, boolean anonymous, String userName, String password) {
        super(url, port, anonymous, userName, password);
        driver = "org.apache.hive.jdbc.HiveDriver";
        String address = null == port ? url : url + ":" + port;
        jdbcUrl = "jdbc:hive2://" + address + "/";
        connector = new DBUtils();
    }

    public SparkConnector(String jdbcUrl, String jdbcType, boolean anonymous, String userName, String password) {
        super(jdbcUrl, jdbcType, anonymous, userName, password);
        driver = "org.apache.hive.jdbc.HiveDriver";
        connector = new DBUtils();
    }

    @Override
    public List<String> getDatabaseList() throws SQLException, ClassNotFoundException {
        String sql = "SHOW DATABASES";
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> obj = connector.query(sql);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            if (!((HashMap<?, ?>) obj.get(i)).containsKey("result")) {
                throw new SQLException("连接类型错误");
            }
            list.add((String) ((HashMap<?, ?>) obj.get(i)).get("result"));
        }
        connector.close();
        return list;
    }

    @Override
    public List<String> getTableList(String dbName) throws SQLException, ClassNotFoundException {
        String sql = "SHOW TABLES IN `" + dbName + "`";
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> obj = connector.query(sql);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            String tbName = (String) ((HashMap<?, ?>) obj.get(i)).get("tableName");
            Boolean isTemporary = (Boolean) ((HashMap<?, ?>) obj.get(i)).get("isTemporary");
            if (isTemporary) {
                continue;
            }
            list.add(tbName);
        }
        connector.close();
        return list;
    }

    public DbTableInfo getTableDetail(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        String sql = "SHOW CREATE TABLE `" + dbName + "`.`" + tbName + "`";
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);
        DbTableInfo table = new DbTableInfo();
        List<Object> result = connector.query(sql);

        List<String> propArray = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            propArray.add(((JSONObject) JSONArray.fromObject(result).get(i)).get("result").toString());
        }

        String location = null;
        for (int j = 0; j < propArray.size(); j++) {
            if (propArray.get(j).trim().equals("LOCATION") || propArray.get(j).trim().equals("LOCATION".toLowerCase())) {
                location = propArray.get(j + 1).split("'")[1];
                break;
            }
        }

        table.setName(tbName);
        table.setLocation(location);
        return table;
    }

    @Override
    public List<DbColumnInfo> getColumnInfo(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        String sql = "DESCRIBE `" + dbName + "`.`" + tbName + "`";
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> result = connector.query(sql);
        connector.close();
        List<DbColumnInfo> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            DbColumnInfo item = new DbColumnInfo();
            item.setColumnName((String) ((HashMap<?, ?>) result.get(i)).get("col_name"));
            item.setColumnLabel((String) ((HashMap<?, ?>) result.get(i)).get("col_name"));
            item.setColumnTypeName((String) ((HashMap<?, ?>) result.get(i)).get("data_type"));
            item.setColumnComment((String) ((HashMap<?, ?>) result.get(i)).get("comment"));
            list.add(item);
        }

        return list;
    }

    @Override
    public List<Object> getResult(String sql, Example condition) throws SQLException, ClassNotFoundException {
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);

        String newSql;
        if (condition.getPage() <= 0 || condition.getRows() <= 0) {
            newSql = condition.querySql(sql);
        } else {
            newSql = condition.pageSql(sql);
        }

        List<Object> result = connector.query(newSql);
        connector.close();
        for (int i = 0; i < result.size(); i++) {
            LinkedHashMap row = (LinkedHashMap) result.get(i);
            for (Object key : row.keySet()) {
                if (row.get(key) == null) {
                    row.put(key, "null");
                }
                //
                else if (row.get(key).toString().trim().equals("")) {
                    row.put(key, "　");
                }
            }
        }
        for (int i = 0; i < result.size(); i++) {
            LinkedHashMap row = (LinkedHashMap) result.get(i);
            for (Object key : row.keySet()) {
                if (row.get(key) != null && row.get(key) instanceof java.sql.Timestamp) {
                    row.put(key, row.get(key).toString());
                }
            }
        }

        Page2 page2 = new Page2(result, condition.getRows());

        return page2.getPages(condition.getPage());
    }

    @Override
    public int getResultCount(String sql, Example condition) throws SQLException, ClassNotFoundException {
        String newSql = condition.countSql(sql);
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
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
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<DbColumnInfo> result = connector.info(newSql);
        connector.close();

        return result;
    }

    public List<DbColumnInfo> getColumnInfoPartitioned(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        String sql = "SHOW CREATE TABLE `" + dbName + "`.`" + tbName + "`";
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> result = connector.query(sql);
        connector.close();

        List<String> propArray = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            propArray.add(((JSONObject) JSONArray.fromObject(result).get(i)).get("result").toString());
        }

        List<DbColumnInfo> list = new ArrayList<>();

        int start = -1;
        int end = -1;
        for (int i = 0; i < propArray.size(); i++) {
            if (propArray.get(i).contains("PARTITIONED BY")) {
                start = i;
            }
            if (start != -1 && propArray.get(i).trim().endsWith(")")) {
                end = i + 1;
                break;
            }
        }

        if (start == -1 || end == -1) {
            return list;
        }

        for (int i = start + 1; i < end; i++) {

            String[] itemArray = propArray.get(i).trim().split(" ");
            DbColumnInfo item = new DbColumnInfo();
            item.setColumnName(itemArray[0].replace("`", ""));
            item.setColumnLabel(itemArray[0].replace("`", ""));
            String type = itemArray[1];
            type = type.lastIndexOf(",") == type.length() - 1 ? type.substring(0, type.lastIndexOf(",")).toLowerCase() : type.toLowerCase();
            type = type.lastIndexOf(")") == type.length() - 1 ? type.substring(0, type.lastIndexOf(")")).toLowerCase() : type.toLowerCase();
            item.setColumnTypeName(type);
            list.add(item);
        }

        return list;
    }

    public List<DbColumnInfo> getColumnInfoNonpartitioned(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        String sql = "SHOW CREATE TABLE `" + dbName + "`.`" + tbName + "`";
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> result = connector.query(sql);
        connector.close();

        List<String> propArray = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            propArray.add(((JSONObject) JSONArray.fromObject(result).get(i)).get("result").toString());
        }

        List<DbColumnInfo> list = new ArrayList<>();

        int start = -1;
        int end = -1;
        for (int i = 0; i < propArray.size(); i++) {
            if (propArray.get(i).contains("CREATE TABLE `" + dbName + "." + tbName + "`") || propArray.get(i).contains("CREATE EXTERNAL TABLE `" + dbName + "." + tbName + "`")) {
                start = i;
            }
            if (start != -1 && propArray.get(i).trim().endsWith(")")) {
                end = i;
                break;
            }
        }


        if (start == -1 || end == -1) {
            return list;
        }

        for (int i = start + 1; i < end; i++) {
            String[] itemArray = propArray.get(i).trim().split(" ");
            DbColumnInfo item = new DbColumnInfo();
            item.setColumnName(itemArray[0].replace("`", ""));
            item.setColumnLabel(itemArray[0].replace("`", ""));
            String type = itemArray[1];
            type = type.lastIndexOf(",") == type.length() - 1 ? type.substring(0, type.lastIndexOf(",")).toLowerCase() : type.toLowerCase();
            type = type.lastIndexOf(")") == type.length() - 1 ? type.substring(0, type.lastIndexOf(")")).toLowerCase() : type.toLowerCase();
            item.setColumnTypeName(type);
            list.add(item);
        }

        return list;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SparkConnector connector = new SparkConnector("192.168.10.215", 10000, true, null, null);
//        System.out.println(connector.getDatabaseList());
//        System.out.println(connector.getTableList("test"));
//        System.out.println(connector.getTableDetail("test", "partition_t"));
//        System.out.println(connector.getColumnInfo("test", "partition_t"));
//        System.out.println(connector.getColumnInfoPartitioned("test", "partition_t"));
//        System.out.println(connector.getColumnInfoNonpartitioned("test", "partition_t"));

        SparkExample condition = new SparkExample();
        SparkExample.Criteria criteria = condition.createCriteria();
        criteria.andIsNotNull("col_01");
        criteria.andLike("col_01", "%0%");
        List<Object> list = new ArrayList<>();
        list.add("，");
        list.add("。");
        criteria.andIn("line_separator", list);
        condition.setPage(1);
        condition.setRows(2);
        condition.setOrderByClause("`col_01` ASC");

        System.out.println(connector.getResult("SELECT * FROM `bank`.`bank`", condition));
        System.out.println(connector.getResultCount("SELECT * FROM `bank`.`bank`", condition));
        System.out.println(connector.getResultColumnInfo("SELECT * FROM `bank`.`bank`"));
    }
}
