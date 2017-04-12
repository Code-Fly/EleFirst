package com.elefirst.connector.proxy;

import com.elefirst.base.utils.DBUtils;
import com.elefirst.connector.entity.DbColumnInfo;
import com.elefirst.connector.entity.DbTableInfo;
import com.elefirst.connector.example.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/20.
 */
public abstract class DbConnector {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static String JDBC_TYPE_RHINO_SQL = "rhinosql";
    public static String JDBC_TYPE_SPARK_SQL = "sparksql";
    public static String JDBC_TYPE_SPARK_SQL_2 = "sparksql2";

    protected String jdbcUrl;
    protected String jdbcType;
    protected String driver;
    protected String url;
    protected Integer port;
    protected String userName;
    protected String password;
    protected boolean anonymous;
    protected DBUtils connector;


    public DbConnector(String url, Integer port, boolean anonymous, String userName, String password) {
        this.url = url;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.anonymous = anonymous;
    }

    public DbConnector(String jdbcUrl, String jdbcType, boolean anonymous, String userName, String password) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcType = jdbcType;
        this.userName = userName;
        this.password = password;
        this.anonymous = anonymous;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public DBUtils getConnector() {
        return connector;
    }

    public void setConnector(DBUtils connector) {
        this.connector = connector;
    }

    abstract public List<String> getDatabaseList() throws SQLException, ClassNotFoundException;

    abstract public List<String> getTableList(String dbName) throws SQLException, ClassNotFoundException;

    abstract public List<DbColumnInfo> getColumnInfo(String dbName, String tbName) throws SQLException, ClassNotFoundException;

    abstract public List<Object> getResult(String sql, Example condition) throws SQLException, ClassNotFoundException;

    abstract public int getResultCount(String sql, Example condition) throws SQLException, ClassNotFoundException;

    abstract public List<DbColumnInfo> getResultColumnInfo(String sql) throws SQLException, ClassNotFoundException;

}
