package com.elefirst.connector.example;

import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public abstract class Example {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected static String DELIMITER_BEGINNING = "`";

    protected static String DELIMITER_ENDDING = "`";

    protected List<Criteria> oredCriteria;

    protected String orderByClause;

    protected boolean distinct;

    protected int page;

    protected int rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public Example() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIsNull(String name) {
            addCriterion(formatColumn(name) + " is null");
            return (Criteria) this;
        }

        public Criteria andIsNotNull(String name) {
            addCriterion(formatColumn(name) + " is not null");
            return (Criteria) this;
        }

        public Criteria andEqualTo(String name, Object value) {
            addCriterion(formatColumn(name) + " =", value, name);
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String name, Object value) {
            addCriterion(formatColumn(name) + " <>", value, name);
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String name, Object value) {
            addCriterion(formatColumn(name) + " >", value, name);
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String name, Object value) {
            addCriterion(formatColumn(name) + " >=", value, name);
            return (Criteria) this;
        }

        public Criteria andLessThan(String name, Object value) {
            addCriterion(formatColumn(name) + " <", value, name);
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String name, Object value) {
            addCriterion(formatColumn(name) + " <=", value, name);
            return (Criteria) this;
        }

        public Criteria andLike(String name, String value) {
            addCriterion(formatColumn(name) + " like", value, name);
            return (Criteria) this;
        }

        public Criteria andNotLike(String name, String value) {
            addCriterion(formatColumn(name) + " not like", value, name);
            return (Criteria) this;
        }

        public Criteria andIn(String name, List<Object> values) {
            addCriterion(formatColumn(name) + " in", values, name);
            return (Criteria) this;
        }

        public Criteria andNotIn(String name, List<Object> values) {
            addCriterion(formatColumn(name) + " not in", values, name);
            return (Criteria) this;
        }

        public Criteria andBetween(String name, Object value1, Object value2) {
            addCriterion(formatColumn(name) + " between", value1, value2, name);
            return (Criteria) this;
        }

        public Criteria andNotBetween(String name, Object value1, Object value2) {
            addCriterion(formatColumn(name) + " not between", value1, value2, name);
            return (Criteria) this;
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public String getWhereClause() {
        StringBuilder newSql = new StringBuilder();
        List<Criteria> criterias = getOredCriteria();
        boolean whereClause = false;

        for (int i = 0; i < criterias.size(); i++) {
            if (i > 0) {
                newSql.append("OR ");
            }
            if (criterias.get(i).isValid()) {
                List<Example.Criterion> criterions = criterias.get(i).getAllCriteria();

                for (int j = 0; j < criterions.size(); j++) {
                    if (!whereClause) {
                        newSql.append("WHERE ");
                        whereClause = true;
                    }
                    if (j > 0) {
                        newSql.append("AND ");
                    }
                    newSql.append("( ");
                    if (criterions.get(j).isNoValue()) {
                        newSql.append(criterions.get(j).getCondition() + " ");
                    } else if (criterions.get(j).isSingleValue()) {
                        if (Number.class.isAssignableFrom(criterions.get(j).getValue().getClass())) {
                            newSql.append(criterions.get(j).getCondition() + " " + criterions.get(j).getValue() + " ");
                        } else {
                            newSql.append(criterions.get(j).getCondition() + " " + formatValue(criterions.get(j).getValue()) + " ");
                        }
                    } else if (criterions.get(j).isBetweenValue()) {
                        if (Number.class.isAssignableFrom(criterions.get(j).getValue().getClass()) && Number.class.isAssignableFrom(criterions.get(j).getSecondValue().getClass())) {
                            newSql.append(criterions.get(j).getCondition() + " " + criterions.get(j).getValue() + " AND " + criterions.get(j).getSecondValue() + " ");
                        } else {
                            newSql.append(criterions.get(j).getCondition() + " " + formatValue(criterions.get(j).getValue()) + " AND " + formatValue(criterions.get(j).getSecondValue()) + " ");
                        }
                    } else if (criterions.get(j).isListValue()) {
                        newSql.append(criterions.get(j).getCondition());
                        List<String> valueList = (List<String>) criterions.get(j).getValue();
                        newSql.append(" ( ");
                        for (int k = 0; k < valueList.size(); k++) {
                            if (k > 0) {
                                newSql.append(", ");
                            }
                            if (Number.class.isAssignableFrom(valueList.get(k).getClass())) {
                                newSql.append(valueList.get(k));
                            } else {
                                newSql.append(formatValue(valueList.get(k)));
                            }
                        }
                        newSql.append(") ");
                    }
                    newSql.append(") ");
                }
            }
        }
        return newSql.toString();
    }

    private String build(String sql, boolean orderBy) {
        StringBuilder newSql = new StringBuilder();
        newSql.append("SELECT ");
        if (distinct) {
            newSql.append("DISTINCT ");
        }
        newSql.append("* ");
        newSql.append("FROM (" + sql + ") t ");
        if (null != getWhereClause() && !getWhereClause().isEmpty()) {
            newSql.append(getWhereClause());
        }
        if (orderBy && null != getOrderByClause() && !getOrderByClause().isEmpty()) {
            newSql.append("ORDER BY " + getOrderByClause());
        }
        return newSql.toString();
    }

    public String querySql(String sql) {
        return build(sql, true);
    }

    public String querySql(String sql, boolean orderBy) {
        return build(sql, orderBy);
    }

    public String countSql(String sql) {
        String tmpTbName = "t_" + new Date().getTime();
        String newSql = "SELECT COUNT(*) FROM ( " + build(sql, false) + " ) " + tmpTbName;
        return newSql;
    }

    abstract public String pageSql(String sql) throws SQLException;

    protected static String formatColumn(Object value) {
        return DELIMITER_BEGINNING + value + DELIMITER_ENDDING;
    }

    protected static String formatValue(Object value) {
        return "'" + value + "'";
    }
}
