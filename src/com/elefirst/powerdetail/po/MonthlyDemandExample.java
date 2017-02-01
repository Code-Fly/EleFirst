package com.elefirst.powerdetail.po;

import java.util.ArrayList;
import java.util.List;

public class MonthlyDemandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public MonthlyDemandExample() {
        oredCriteria = new ArrayList<Criteria>();
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

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
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

        public Criteria andDaysIsNull() {
            addCriterion("days is null");
            return (Criteria) this;
        }

        public Criteria andDaysIsNotNull() {
            addCriterion("days is not null");
            return (Criteria) this;
        }

        public Criteria andDaysEqualTo(String value) {
            addCriterion("days =", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotEqualTo(String value) {
            addCriterion("days <>", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThan(String value) {
            addCriterion("days >", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysGreaterThanOrEqualTo(String value) {
            addCriterion("days >=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThan(String value) {
            addCriterion("days <", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLessThanOrEqualTo(String value) {
            addCriterion("days <=", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysLike(String value) {
            addCriterion("days like", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotLike(String value) {
            addCriterion("days not like", value, "days");
            return (Criteria) this;
        }

        public Criteria andDaysIn(List<String> values) {
            addCriterion("days in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotIn(List<String> values) {
            addCriterion("days not in", values, "days");
            return (Criteria) this;
        }

        public Criteria andDaysBetween(String value1, String value2) {
            addCriterion("days between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andDaysNotBetween(String value1, String value2) {
            addCriterion("days not between", value1, value2, "days");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(String value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(String value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(String value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(String value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(String value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLike(String value) {
            addCriterion("area_id like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotLike(String value) {
            addCriterion("area_id not like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<String> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<String> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(String value1, String value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(String value1, String value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdIsNull() {
            addCriterion("concentrator_id is null");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdIsNotNull() {
            addCriterion("concentrator_id is not null");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdEqualTo(String value) {
            addCriterion("concentrator_id =", value, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdNotEqualTo(String value) {
            addCriterion("concentrator_id <>", value, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdGreaterThan(String value) {
            addCriterion("concentrator_id >", value, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdGreaterThanOrEqualTo(String value) {
            addCriterion("concentrator_id >=", value, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdLessThan(String value) {
            addCriterion("concentrator_id <", value, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdLessThanOrEqualTo(String value) {
            addCriterion("concentrator_id <=", value, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdLike(String value) {
            addCriterion("concentrator_id like", value, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdNotLike(String value) {
            addCriterion("concentrator_id not like", value, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdIn(List<String> values) {
            addCriterion("concentrator_id in", values, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdNotIn(List<String> values) {
            addCriterion("concentrator_id not in", values, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdBetween(String value1, String value2) {
            addCriterion("concentrator_id between", value1, value2, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andConcentratorIdNotBetween(String value1, String value2) {
            addCriterion("concentrator_id not between", value1, value2, "concentratorId");
            return (Criteria) this;
        }

        public Criteria andPnIsNull() {
            addCriterion("pn is null");
            return (Criteria) this;
        }

        public Criteria andPnIsNotNull() {
            addCriterion("pn is not null");
            return (Criteria) this;
        }

        public Criteria andPnEqualTo(String value) {
            addCriterion("pn =", value, "pn");
            return (Criteria) this;
        }

        public Criteria andPnNotEqualTo(String value) {
            addCriterion("pn <>", value, "pn");
            return (Criteria) this;
        }

        public Criteria andPnGreaterThan(String value) {
            addCriterion("pn >", value, "pn");
            return (Criteria) this;
        }

        public Criteria andPnGreaterThanOrEqualTo(String value) {
            addCriterion("pn >=", value, "pn");
            return (Criteria) this;
        }

        public Criteria andPnLessThan(String value) {
            addCriterion("pn <", value, "pn");
            return (Criteria) this;
        }

        public Criteria andPnLessThanOrEqualTo(String value) {
            addCriterion("pn <=", value, "pn");
            return (Criteria) this;
        }

        public Criteria andPnLike(String value) {
            addCriterion("pn like", value, "pn");
            return (Criteria) this;
        }

        public Criteria andPnNotLike(String value) {
            addCriterion("pn not like", value, "pn");
            return (Criteria) this;
        }

        public Criteria andPnIn(List<String> values) {
            addCriterion("pn in", values, "pn");
            return (Criteria) this;
        }

        public Criteria andPnNotIn(List<String> values) {
            addCriterion("pn not in", values, "pn");
            return (Criteria) this;
        }

        public Criteria andPnBetween(String value1, String value2) {
            addCriterion("pn between", value1, value2, "pn");
            return (Criteria) this;
        }

        public Criteria andPnNotBetween(String value1, String value2) {
            addCriterion("pn not between", value1, value2, "pn");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerIsNull() {
            addCriterion("maxTotalPositiveMaxActivePower is null");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerIsNotNull() {
            addCriterion("maxTotalPositiveMaxActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerEqualTo(Double value) {
            addCriterion("maxTotalPositiveMaxActivePower =", value, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerNotEqualTo(Double value) {
            addCriterion("maxTotalPositiveMaxActivePower <>", value, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerGreaterThan(Double value) {
            addCriterion("maxTotalPositiveMaxActivePower >", value, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerGreaterThanOrEqualTo(Double value) {
            addCriterion("maxTotalPositiveMaxActivePower >=", value, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerLessThan(Double value) {
            addCriterion("maxTotalPositiveMaxActivePower <", value, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerLessThanOrEqualTo(Double value) {
            addCriterion("maxTotalPositiveMaxActivePower <=", value, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerIn(List<Double> values) {
            addCriterion("maxTotalPositiveMaxActivePower in", values, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerNotIn(List<Double> values) {
            addCriterion("maxTotalPositiveMaxActivePower not in", values, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerBetween(Double value1, Double value2) {
            addCriterion("maxTotalPositiveMaxActivePower between", value1, value2, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpositivemaxactivepowerNotBetween(Double value1, Double value2) {
            addCriterion("maxTotalPositiveMaxActivePower not between", value1, value2, "maxtotalpositivemaxactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeIsNull() {
            addCriterion("totalPositiveMaxActivePowerTime is null");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeIsNotNull() {
            addCriterion("totalPositiveMaxActivePowerTime is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeEqualTo(String value) {
            addCriterion("totalPositiveMaxActivePowerTime =", value, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeNotEqualTo(String value) {
            addCriterion("totalPositiveMaxActivePowerTime <>", value, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeGreaterThan(String value) {
            addCriterion("totalPositiveMaxActivePowerTime >", value, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeGreaterThanOrEqualTo(String value) {
            addCriterion("totalPositiveMaxActivePowerTime >=", value, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeLessThan(String value) {
            addCriterion("totalPositiveMaxActivePowerTime <", value, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeLessThanOrEqualTo(String value) {
            addCriterion("totalPositiveMaxActivePowerTime <=", value, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeLike(String value) {
            addCriterion("totalPositiveMaxActivePowerTime like", value, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeNotLike(String value) {
            addCriterion("totalPositiveMaxActivePowerTime not like", value, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeIn(List<String> values) {
            addCriterion("totalPositiveMaxActivePowerTime in", values, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeNotIn(List<String> values) {
            addCriterion("totalPositiveMaxActivePowerTime not in", values, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeBetween(String value1, String value2) {
            addCriterion("totalPositiveMaxActivePowerTime between", value1, value2, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andTotalpositivemaxactivepowertimeNotBetween(String value1, String value2) {
            addCriterion("totalPositiveMaxActivePowerTime not between", value1, value2, "totalpositivemaxactivepowertime");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
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
}