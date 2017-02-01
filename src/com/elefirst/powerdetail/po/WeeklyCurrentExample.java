package com.elefirst.powerdetail.po;

import java.util.ArrayList;
import java.util.List;

public class WeeklyCurrentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public WeeklyCurrentExample() {
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

        public Criteria andWeekstartIsNull() {
            addCriterion("weekstart is null");
            return (Criteria) this;
        }

        public Criteria andWeekstartIsNotNull() {
            addCriterion("weekstart is not null");
            return (Criteria) this;
        }

        public Criteria andWeekstartEqualTo(String value) {
            addCriterion("weekstart =", value, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartNotEqualTo(String value) {
            addCriterion("weekstart <>", value, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartGreaterThan(String value) {
            addCriterion("weekstart >", value, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartGreaterThanOrEqualTo(String value) {
            addCriterion("weekstart >=", value, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartLessThan(String value) {
            addCriterion("weekstart <", value, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartLessThanOrEqualTo(String value) {
            addCriterion("weekstart <=", value, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartLike(String value) {
            addCriterion("weekstart like", value, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartNotLike(String value) {
            addCriterion("weekstart not like", value, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartIn(List<String> values) {
            addCriterion("weekstart in", values, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartNotIn(List<String> values) {
            addCriterion("weekstart not in", values, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartBetween(String value1, String value2) {
            addCriterion("weekstart between", value1, value2, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekstartNotBetween(String value1, String value2) {
            addCriterion("weekstart not between", value1, value2, "weekstart");
            return (Criteria) this;
        }

        public Criteria andWeekendIsNull() {
            addCriterion("weekend is null");
            return (Criteria) this;
        }

        public Criteria andWeekendIsNotNull() {
            addCriterion("weekend is not null");
            return (Criteria) this;
        }

        public Criteria andWeekendEqualTo(String value) {
            addCriterion("weekend =", value, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendNotEqualTo(String value) {
            addCriterion("weekend <>", value, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendGreaterThan(String value) {
            addCriterion("weekend >", value, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendGreaterThanOrEqualTo(String value) {
            addCriterion("weekend >=", value, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendLessThan(String value) {
            addCriterion("weekend <", value, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendLessThanOrEqualTo(String value) {
            addCriterion("weekend <=", value, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendLike(String value) {
            addCriterion("weekend like", value, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendNotLike(String value) {
            addCriterion("weekend not like", value, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendIn(List<String> values) {
            addCriterion("weekend in", values, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendNotIn(List<String> values) {
            addCriterion("weekend not in", values, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendBetween(String value1, String value2) {
            addCriterion("weekend between", value1, value2, "weekend");
            return (Criteria) this;
        }

        public Criteria andWeekendNotBetween(String value1, String value2) {
            addCriterion("weekend not between", value1, value2, "weekend");
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

        public Criteria andMaxacurrentIsNull() {
            addCriterion("maxACurrent is null");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentIsNotNull() {
            addCriterion("maxACurrent is not null");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentEqualTo(Double value) {
            addCriterion("maxACurrent =", value, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentNotEqualTo(Double value) {
            addCriterion("maxACurrent <>", value, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentGreaterThan(Double value) {
            addCriterion("maxACurrent >", value, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("maxACurrent >=", value, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentLessThan(Double value) {
            addCriterion("maxACurrent <", value, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentLessThanOrEqualTo(Double value) {
            addCriterion("maxACurrent <=", value, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentIn(List<Double> values) {
            addCriterion("maxACurrent in", values, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentNotIn(List<Double> values) {
            addCriterion("maxACurrent not in", values, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentBetween(Double value1, Double value2) {
            addCriterion("maxACurrent between", value1, value2, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxacurrentNotBetween(Double value1, Double value2) {
            addCriterion("maxACurrent not between", value1, value2, "maxacurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentIsNull() {
            addCriterion("maxBCurrent is null");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentIsNotNull() {
            addCriterion("maxBCurrent is not null");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentEqualTo(Double value) {
            addCriterion("maxBCurrent =", value, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentNotEqualTo(Double value) {
            addCriterion("maxBCurrent <>", value, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentGreaterThan(Double value) {
            addCriterion("maxBCurrent >", value, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("maxBCurrent >=", value, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentLessThan(Double value) {
            addCriterion("maxBCurrent <", value, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentLessThanOrEqualTo(Double value) {
            addCriterion("maxBCurrent <=", value, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentIn(List<Double> values) {
            addCriterion("maxBCurrent in", values, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentNotIn(List<Double> values) {
            addCriterion("maxBCurrent not in", values, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentBetween(Double value1, Double value2) {
            addCriterion("maxBCurrent between", value1, value2, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxbcurrentNotBetween(Double value1, Double value2) {
            addCriterion("maxBCurrent not between", value1, value2, "maxbcurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentIsNull() {
            addCriterion("maxCCurrent is null");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentIsNotNull() {
            addCriterion("maxCCurrent is not null");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentEqualTo(Double value) {
            addCriterion("maxCCurrent =", value, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentNotEqualTo(Double value) {
            addCriterion("maxCCurrent <>", value, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentGreaterThan(Double value) {
            addCriterion("maxCCurrent >", value, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("maxCCurrent >=", value, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentLessThan(Double value) {
            addCriterion("maxCCurrent <", value, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentLessThanOrEqualTo(Double value) {
            addCriterion("maxCCurrent <=", value, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentIn(List<Double> values) {
            addCriterion("maxCCurrent in", values, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentNotIn(List<Double> values) {
            addCriterion("maxCCurrent not in", values, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentBetween(Double value1, Double value2) {
            addCriterion("maxCCurrent between", value1, value2, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMaxccurrentNotBetween(Double value1, Double value2) {
            addCriterion("maxCCurrent not between", value1, value2, "maxccurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentIsNull() {
            addCriterion("minACurrent is null");
            return (Criteria) this;
        }

        public Criteria andMinacurrentIsNotNull() {
            addCriterion("minACurrent is not null");
            return (Criteria) this;
        }

        public Criteria andMinacurrentEqualTo(Double value) {
            addCriterion("minACurrent =", value, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentNotEqualTo(Double value) {
            addCriterion("minACurrent <>", value, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentGreaterThan(Double value) {
            addCriterion("minACurrent >", value, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("minACurrent >=", value, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentLessThan(Double value) {
            addCriterion("minACurrent <", value, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentLessThanOrEqualTo(Double value) {
            addCriterion("minACurrent <=", value, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentIn(List<Double> values) {
            addCriterion("minACurrent in", values, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentNotIn(List<Double> values) {
            addCriterion("minACurrent not in", values, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentBetween(Double value1, Double value2) {
            addCriterion("minACurrent between", value1, value2, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinacurrentNotBetween(Double value1, Double value2) {
            addCriterion("minACurrent not between", value1, value2, "minacurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentIsNull() {
            addCriterion("minBCurrent is null");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentIsNotNull() {
            addCriterion("minBCurrent is not null");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentEqualTo(Double value) {
            addCriterion("minBCurrent =", value, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentNotEqualTo(Double value) {
            addCriterion("minBCurrent <>", value, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentGreaterThan(Double value) {
            addCriterion("minBCurrent >", value, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("minBCurrent >=", value, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentLessThan(Double value) {
            addCriterion("minBCurrent <", value, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentLessThanOrEqualTo(Double value) {
            addCriterion("minBCurrent <=", value, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentIn(List<Double> values) {
            addCriterion("minBCurrent in", values, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentNotIn(List<Double> values) {
            addCriterion("minBCurrent not in", values, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentBetween(Double value1, Double value2) {
            addCriterion("minBCurrent between", value1, value2, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinbcurrentNotBetween(Double value1, Double value2) {
            addCriterion("minBCurrent not between", value1, value2, "minbcurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentIsNull() {
            addCriterion("minCCurrent is null");
            return (Criteria) this;
        }

        public Criteria andMinccurrentIsNotNull() {
            addCriterion("minCCurrent is not null");
            return (Criteria) this;
        }

        public Criteria andMinccurrentEqualTo(Double value) {
            addCriterion("minCCurrent =", value, "minccurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentNotEqualTo(Double value) {
            addCriterion("minCCurrent <>", value, "minccurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentGreaterThan(Double value) {
            addCriterion("minCCurrent >", value, "minccurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentGreaterThanOrEqualTo(Double value) {
            addCriterion("minCCurrent >=", value, "minccurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentLessThan(Double value) {
            addCriterion("minCCurrent <", value, "minccurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentLessThanOrEqualTo(Double value) {
            addCriterion("minCCurrent <=", value, "minccurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentIn(List<Double> values) {
            addCriterion("minCCurrent in", values, "minccurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentNotIn(List<Double> values) {
            addCriterion("minCCurrent not in", values, "minccurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentBetween(Double value1, Double value2) {
            addCriterion("minCCurrent between", value1, value2, "minccurrent");
            return (Criteria) this;
        }

        public Criteria andMinccurrentNotBetween(Double value1, Double value2) {
            addCriterion("minCCurrent not between", value1, value2, "minccurrent");
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