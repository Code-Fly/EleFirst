package com.elefirst.connector.proxy;

import com.elefirst.base.utils.DBUtils;
import com.elefirst.connector.entity.DbColumnInfo;
import com.elefirst.connector.entity.DbTableInfo;
import com.elefirst.connector.example.Example;
import com.elefirst.connector.example.RhinoExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2017/2/20.
 */
public class RhinoConnector extends DbConnector {


    public RhinoConnector(String url, Integer port, boolean anonymous, String userName, String password) {
        super(url, port, anonymous, userName, password);
        driver = "org.apache.hive.jdbc.HiveDriver";
        String address = null == port ? url : url + ":" + port;
        if (anonymous) {
            jdbcUrl = "jdbc:hive2://" + address + "/;auth=noSasl";
        } else {
            jdbcUrl = "jdbc:hive2://" + address + "/";
        }
        connector = new DBUtils();
    }

    public RhinoConnector(String jdbcUrl, String jdbcType, boolean anonymous, String userName, String password) {
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
            if (!((HashMap<?, ?>) obj.get(i)).containsKey("name")) {
                throw new SQLException("连接类型错误");
            }
            list.add((String) ((HashMap<?, ?>) obj.get(i)).get("name"));
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
            String tbName = (String) ((HashMap<?, ?>) obj.get(i)).get("name");
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
        table.setName(tbName);

        List<Object> prop = connector.query(sql);
        String propStr = ((JSONObject) JSONArray.fromObject(prop).get(0)).get("result").toString();
        if (!propStr.contains("'ELASTIC_SCHEMA'=") && !propStr.contains("'ELASTIC_SCHEMA'=".toLowerCase()) && !propStr.contains("'RHINO_DATABASE_NAME'=") && !propStr.contains("'RHINO_DATABASE_NAME'=".toLowerCase())) {
            String location = null;
            String[] propArray = propStr.split("\\n");
            for (int j = 0; j < propArray.length; j++) {
                if (propArray[j].contains("LOCATION '") || propArray[j].contains("LOCATION '".toLowerCase())) {
                    location = propArray[j].split("'")[1];
                    break;
                }
            }
            table.setLocation(location);
        }
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
            item.setColumnName((String) ((HashMap<?, ?>) result.get(i)).get("name"));
            item.setColumnLabel((String) ((HashMap<?, ?>) result.get(i)).get("name"));
            item.setColumnTypeName((String) ((HashMap<?, ?>) result.get(i)).get("type"));
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
        connector.close();

        return result;
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

        String propStr = ((JSONObject) JSONArray.fromObject(result).get(0)).get("result").toString();
        String[] propArray = propStr.split("\\n");

        List<DbColumnInfo> list = new ArrayList<>();

        int start = -1;
        int end = -1;
        for (int i = 0; i < propArray.length; i++) {
            if (propArray[i].contains("PARTITIONED BY")) {
                start = i;
            }
            if (start != -1 && propArray[i].trim().equals(")")) {
                end = i;
                break;
            }
        }

        if (start == -1 || end == -1) {
            return list;
        }

        for (int i = start + 1; i < end; i++) {
            String[] itemArray = propArray[i].trim().split(" ");
            DbColumnInfo item = new DbColumnInfo();
            item.setColumnName(itemArray[0]);
            item.setColumnLabel(itemArray[0]);
            item.setColumnTypeName(itemArray[1].lastIndexOf(",") == itemArray[1].length() - 1 ? itemArray[1].substring(0, itemArray[1].lastIndexOf(",")).toLowerCase() : itemArray[1].toLowerCase());
            list.add(item);
        }

        return list;
    }

    public List<DbColumnInfo> getColumnInfoNonpartitioned(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        String sql = "SHOW CREATE TABLE `" + dbName + "`.`" + tbName + "`";
        Properties info = new Properties();
        if (null != userName && null != password && !userName.trim().equals("") && !password.trim().equals("")) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(this.driver, this.jdbcUrl, info);
        List<Object> result = connector.query(sql);
        connector.close();

        String propStr = ((JSONObject) JSONArray.fromObject(result).get(0)).get("result").toString();
        String[] propArray = propStr.split("\\n");

        List<DbColumnInfo> list = new ArrayList<>();

        int start = -1;
        int end = -1;
        for (int i = 0; i < propArray.length; i++) {
            if (propArray[i].contains("CREATE TABLE " + dbName + "." + tbName) || propArray[i].contains("CREATE EXTERNAL TABLE " + dbName + "." + tbName)) {
                start = i;
            }
            if (start != -1 && propArray[i].trim().equals(")")) {
                end = i;
                break;
            }
        }

        if (start == -1 || end == -1) {
            return list;
        }

        for (int i = start + 1; i < end; i++) {
            String[] itemArray = propArray[i].trim().split(" ");
            DbColumnInfo item = new DbColumnInfo();
            item.setColumnName(itemArray[0]);
            item.setColumnLabel(itemArray[0]);
            item.setColumnTypeName(itemArray[1].lastIndexOf(",") == itemArray[1].length() - 1 ? itemArray[1].substring(0, itemArray[1].lastIndexOf(",")).toLowerCase() : itemArray[1].toLowerCase());
            list.add(item);
        }

        return list;
    }

    public String getFieldDelimiter(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        String sql = "SHOW CREATE TABLE `" + dbName + "`.`" + tbName + "`";
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> result = connector.query(sql);
        connector.close();

        String propStr = ((JSONObject) JSONArray.fromObject(result).get(0)).get("result").toString();
        String[] propArray = propStr.split("\\n");

        String delimiter = null;
        for (int i = 0; i < propArray.length; i++) {
            if (propArray[i].contains("WITH SERDEPROPERTIES")) {
                String params = propArray[i].substring(propArray[i].indexOf("(") + 1, propArray[i].lastIndexOf(")"));
                String[] paramsArr = params.split(", ");
                for (int j = 0; j < paramsArr.length; j++) {
                    String param = paramsArr[j].trim();
                    String name = param.substring(1, param.indexOf("=") - 1);
                    String value = param.substring(param.indexOf("=") + 2, param.length() - 1);
                    if (name.equals("field.delim")) {
                        delimiter = value;
                    }
                }
            }
        }

        return delimiter;
    }

    public String getStoredAs(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        String sql = "SHOW CREATE TABLE `" + dbName + "`.`" + tbName + "`";
        Properties info = new Properties();
        if (!anonymous) {
            info.put("user", userName);
            info.put("password", password);
        }
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> result = connector.query(sql);
        connector.close();

        String propStr = ((JSONObject) JSONArray.fromObject(result).get(0)).get("result").toString();
        String[] propArray = propStr.split("\\n");

        String storedAs = null;
        for (int i = 0; i < propArray.length; i++) {
            if (propArray[i].contains("STORED AS")) {
                storedAs = propArray[i].substring("STORED AS".length()).trim();
            }
        }

        return storedAs;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        RhinoConnector connector = new RhinoConnector("192.168.10.215", 21050, true, null, null);
//        System.out.println(connector.getDatabaseList());
//        System.out.println(connector.getTableList("default"));
//        System.out.println(connector.getTableDetail("test", "partition_t"));
//        System.out.println(connector.getColumnInfo("test", "partition_t"));
//        System.out.println(connector.getColumnInfoPartitioned("test", "partition_t"));
//        System.out.println(connector.getColumnInfoNonpartitioned("test", "partition_t"));
//        System.out.println(connector.getFieldDelimiter("test", "partition_t"));
//        System.out.println(connector.getStoredAs("test", "partition_t"));

        RhinoExample condition = new RhinoExample();
        RhinoExample.Criteria criteria = condition.createCriteria();
        criteria.andIsNotNull("taskid");
        criteria.andGreaterThan("range", 100);
        criteria.andBetween("range", 1, 300);
        criteria.andEqualTo("bandwidthvalue", 0.1);
        criteria.andEqualTo("modelpath", "hdfs://192.168.10.215:9020/predictModel/test1");
        List<Object> list = new ArrayList<>();
        list.add("0ce1ae637fdb4dbea00f645da87d46cb");
        list.add("96c7c92bc9a940a493c74a5c2261b838");
        criteria.andIn("taskid", list);
        condition.setPage(1);
        condition.setRows(2);
        condition.setOrderByClause("`taskid` ASC");

        System.out.println(connector.getResult("SELECT * FROM `default`.`kerneldensity_model_eval_train`", condition));
        System.out.println(connector.getResultCount("SELECT * FROM `default`.`kerneldensity_model_eval_train`", condition));
        System.out.println(connector.getResultColumnInfo("SELECT * FROM `default`.`kerneldensity_model_eval_train`"));
    }
}
