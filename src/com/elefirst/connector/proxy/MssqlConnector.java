package com.elefirst.connector.proxy;

import com.elefirst.base.utils.DBUtils;
import com.elefirst.connector.entity.DbColumnInfo;
import com.elefirst.connector.example.Example;
import com.elefirst.connector.example.MssqlExample;
import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2017/2/21.
 */
public class MssqlConnector extends DbConnector {
    public MssqlConnector(String url, Integer port, boolean anonymous, String userName, String password) {
        super(url, port, anonymous, userName, password);
        this.driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
        this.jdbcUrl = "jdbc:microsoft:sqlserver://" + url + ":" + port;
        this.connector = new DBUtils();
    }

    public MssqlConnector(String jdbcUrl, String jdbcType, boolean anonymous, String userName, String password) {
        super(jdbcUrl, jdbcType, anonymous, userName, password);
        this.driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
        this.connector = new DBUtils();
    }

    @Override
    public List<String> getDatabaseList() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Name FROM Master..SysDatabases ORDER BY Name";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> obj = connector.query(sql);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            list.add((String) ((HashMap<?, ?>) obj.get(i)).get("Name"));
        }
        connector.close();
        return list;
    }

    @Override
    public List<String> getTableList(String dbName) throws SQLException, ClassNotFoundException {
        String sql = "USE [" + dbName + "];SELECT Name FROM [" + dbName + "]..SysObjects Where XType='U' ORDER BY Name";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> obj = connector.query(sql);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            list.add((String) ((HashMap<?, ?>) obj.get(i)).get("Name"));
        }
        connector.close();
        return list;
    }

    @Override
    public List<DbColumnInfo> getColumnInfo(String dbName, String tbName) throws SQLException, ClassNotFoundException {
        // SQL 2000
//        String sql = "USE [" + dbName + "]; " +
//                "SELECT " +
//                "[column_name] = a.name, " +
//                "[data_type] = b.name, " +
//                "[data_length] = a.length, " +
//                "[column_description] = g.[value] " +
//                "FROM " +
//                "syscolumns a " +
//                "LEFT JOIN systypes b ON a.xusertype = b.xusertype " +
//                "INNER JOIN sysobjects d ON a.id = d.id " +
//                "AND d.xtype = 'U' " +
//                "AND d.name <> 'dtproperties' " +
//                "LEFT JOIN syscomments e ON a.cdefault = e.id " +
//                "LEFT JOIN sysproperties g ON a.id = g.id " +
//                "AND a.colid = g.smallid " +
//                "LEFT JOIN sysproperties f ON d.id = f.id " +
//                "AND f.smallid = 0 " +
//                "WHERE " +
//                "d.name = '" + tbName + "' " +
//                "ORDER BY " +
//                "a.name ASC";

        // SQL 2005+
        String sql = "USE [" + dbName + "]; " +
                "SELECT " +
                " [table_name] = CASE " +
                "WHEN a.colorder = 1 THEN " +
                " d.name " +
                "ELSE " +
                " '' " +
                "END, " +
                " [table_description] = CASE " +
                "WHEN a.colorder = 1 THEN " +
                " isnull(f. VALUE, '') " +
                "ELSE " +
                " '' " +
                "END, " +
                " [column_index] = a.colorder, " +
                " [column_name] = a.name, " +
                " [column_default] = isnull(e. TEXT, ''), " +
                " [column_description] = isnull(g.[value], ''), " +
                " [data_type] = b.name, " +
                " [data_length] = a.length, " +
                " [data_precision] = COLUMNPROPERTY(a.id, a.name, 'PRECISION') " +
                "FROM " +
                " syscolumns a " +
                "LEFT JOIN systypes b ON a.xusertype = b.xusertype " +
                "INNER JOIN sysobjects d ON a.id = d.id " +
                "AND d.xtype = 'U' " +
                "AND d.name <> 'dtproperties' " +
                "LEFT JOIN syscomments e ON a.cdefault = e.id " +
                "LEFT JOIN sys.extended_properties g ON a.id = g.major_id " +
                "AND a.colid = g.minor_id " +
                "LEFT JOIN sys.extended_properties f ON d.id = f.major_id " +
                "AND f.minor_id = 0 " +
                "WHERE " +
                " d.name = '" + tbName + "' ";
//                "ORDER BY " +
//                " a.name ASC";
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        List<Object> result = connector.query(sql);
        connector.close();
        List<DbColumnInfo> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            DbColumnInfo item = new DbColumnInfo();
            item.setColumnName((String) ((HashMap<?, ?>) result.get(i)).get("column_name"));
            item.setColumnLabel((String) ((HashMap<?, ?>) result.get(i)).get("column_name"));
            item.setColumnTypeName((String) ((HashMap<?, ?>) result.get(i)).get("data_type"));
            item.setColumnDisplaySize((Number) ((HashMap<?, ?>) result.get(i)).get("data_length"));
            item.setColumnComment((String) ((HashMap<?, ?>) result.get(i)).get("column_description"));
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
        String newSql = "SELECT TOP 1 * FROM ( " + sql + " ) " + tmpTbName;
        Properties info = new Properties();
        info.put("user", userName);
        info.put("password", password);
        connector.getConnection(driver, jdbcUrl, info);
        List<DbColumnInfo> result = connector.info(newSql);
        connector.close();

        return result;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PropertyConfigurator.configure("/Users/barrie/Documents/Workspaces/MyEclipse/EleFirst/WebRoot/WEB-INF/conf/log4j.properties");

        MssqlConnector connector = new MssqlConnector("192.168.10.43", 1433, false, "sa", "1");
//        System.out.println(connector.getDatabaseList());
//        System.out.println(connector.getTableList("中文数据库"));
//        System.out.println(connector.getColumnInfo("中文数据库", "t_012_type_one_data_fn57"));

        MssqlExample condition = new MssqlExample();
        MssqlExample.Criteria criteria = condition.createCriteria();
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
        condition.setOrderByClause("[sendTime] ASC");

        System.out.println(connector.getResult("SELECT * FROM [中文数据库].dbo.[t_012_type_one_data_fn57]", condition));
        System.out.println(connector.getResultCount("SELECT * FROM [中文数据库].dbo.[t_012_type_one_data_fn57]", condition));
        System.out.println(connector.getResultColumnInfo("SELECT * FROM [中文数据库].dbo.[t_012_type_one_data_fn57]"));
    }
}
