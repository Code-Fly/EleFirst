package com.elefirst.connector.entity;


import com.elefirst.base.entity.BaseEntity;

/**
 * Created by 123 on 2016/4/26.
 */
public class DbColumnInfo extends BaseEntity {
    // 获得指定列的列名
    private String columnName;
    // 获得指定列的数据类型
    private Number columnType;
    // 获得指定列的数据类型名
    private String columnTypeName;

    private String columnComment;
    // 所在的Catalog名字
    private String catalogName;
    // 对应数据类型的类
    private String columnClassName;
    // 在数据库中类型的最大字符个数
    private Number columnDisplaySize;
    // 默认的列的标题
    private String columnLabel;
    // 获得列的模式
    private String schemaName;
    // 某列类型的精确度(类型的长度)
    private Number precision;
    // 小数点后的位数
    private Number scale;
    // 获取某列对应的表名
    private String tableName;
    // 是否自动递增
    private Boolean isAutoInctement;
    // 在数据库中是否为货币型
    private Boolean isCurrency;
    // 是否为空
    private Number isNullable;
    // 是否为只读
    private Boolean isReadOnly;
    // 能否出现在where中
    private Boolean isSearchable;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Number getColumnType() {
        return columnType;
    }

    public void setColumnType(Number columnType) {
        this.columnType = columnType;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getColumnClassName() {
        return columnClassName;
    }

    public void setColumnClassName(String columnClassName) {
        this.columnClassName = columnClassName;
    }

    public Number getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(Number columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public Number getPrecision() {
        return precision;
    }

    public void setPrecision(Number precision) {
        this.precision = precision;
    }

    public Number getScale() {
        return scale;
    }

    public void setScale(Number scale) {
        this.scale = scale;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Boolean getAutoInctement() {
        return isAutoInctement;
    }

    public void setAutoInctement(Boolean autoInctement) {
        isAutoInctement = autoInctement;
    }

    public Boolean getCurrency() {
        return isCurrency;
    }

    public void setCurrency(Boolean currency) {
        isCurrency = currency;
    }

    public Number getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(Number isNullable) {
        this.isNullable = isNullable;
    }

    public Boolean getReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        isReadOnly = readOnly;
    }

    public Boolean getSearchable() {
        return isSearchable;
    }

    public void setSearchable(Boolean searchable) {
        isSearchable = searchable;
    }
}
