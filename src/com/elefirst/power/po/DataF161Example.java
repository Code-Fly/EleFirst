package com.elefirst.power.po;

import java.util.ArrayList;
import java.util.List;

public class DataF161Example {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    protected int limitStart = -1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    protected int limitEnd = -1;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public DataF161Example() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public void setLimitStart(int limitStart) {
        this.limitStart = limitStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public int getLimitStart() {
        return limitStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public void setLimitEnd(int limitEnd) {
        this.limitEnd = limitEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
    public int getLimitEnd() {
        return limitEnd;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(String value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(String value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(String value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(String value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(String value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(String value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLike(String value) {
            addCriterion("rate like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotLike(String value) {
            addCriterion("rate not like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<String> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<String> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(String value1, String value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(String value1, String value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerIsNull() {
            addCriterion("totalPositiveActivePower is null");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerIsNotNull() {
            addCriterion("totalPositiveActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerEqualTo(String value) {
            addCriterion("totalPositiveActivePower =", value, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerNotEqualTo(String value) {
            addCriterion("totalPositiveActivePower <>", value, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerGreaterThan(String value) {
            addCriterion("totalPositiveActivePower >", value, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("totalPositiveActivePower >=", value, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerLessThan(String value) {
            addCriterion("totalPositiveActivePower <", value, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerLessThanOrEqualTo(String value) {
            addCriterion("totalPositiveActivePower <=", value, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerLike(String value) {
            addCriterion("totalPositiveActivePower like", value, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerNotLike(String value) {
            addCriterion("totalPositiveActivePower not like", value, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerIn(List<String> values) {
            addCriterion("totalPositiveActivePower in", values, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerNotIn(List<String> values) {
            addCriterion("totalPositiveActivePower not in", values, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerBetween(String value1, String value2) {
            addCriterion("totalPositiveActivePower between", value1, value2, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositiveactivepowerNotBetween(String value1, String value2) {
            addCriterion("totalPositiveActivePower not between", value1, value2, "totalpositiveactivepower");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNull() {
            addCriterion("sendTime is null");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNotNull() {
            addCriterion("sendTime is not null");
            return (Criteria) this;
        }

        public Criteria andSendtimeEqualTo(String value) {
            addCriterion("sendTime =", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotEqualTo(String value) {
            addCriterion("sendTime <>", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThan(String value) {
            addCriterion("sendTime >", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThanOrEqualTo(String value) {
            addCriterion("sendTime >=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThan(String value) {
            addCriterion("sendTime <", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThanOrEqualTo(String value) {
            addCriterion("sendTime <=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLike(String value) {
            addCriterion("sendTime like", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotLike(String value) {
            addCriterion("sendTime not like", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIn(List<String> values) {
            addCriterion("sendTime in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotIn(List<String> values) {
            addCriterion("sendTime not in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeBetween(String value1, String value2) {
            addCriterion("sendTime between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotBetween(String value1, String value2) {
            addCriterion("sendTime not between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andFrozenDayIsNull() {
            addCriterion("frozen_day is null");
            return (Criteria) this;
        }

        public Criteria andFrozenDayIsNotNull() {
            addCriterion("frozen_day is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenDayEqualTo(String value) {
            addCriterion("frozen_day =", value, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayNotEqualTo(String value) {
            addCriterion("frozen_day <>", value, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayGreaterThan(String value) {
            addCriterion("frozen_day >", value, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayGreaterThanOrEqualTo(String value) {
            addCriterion("frozen_day >=", value, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayLessThan(String value) {
            addCriterion("frozen_day <", value, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayLessThanOrEqualTo(String value) {
            addCriterion("frozen_day <=", value, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayLike(String value) {
            addCriterion("frozen_day like", value, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayNotLike(String value) {
            addCriterion("frozen_day not like", value, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayIn(List<String> values) {
            addCriterion("frozen_day in", values, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayNotIn(List<String> values) {
            addCriterion("frozen_day not in", values, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayBetween(String value1, String value2) {
            addCriterion("frozen_day between", value1, value2, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andFrozenDayNotBetween(String value1, String value2) {
            addCriterion("frozen_day not between", value1, value2, "frozenDay");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeIsNull() {
            addCriterion("clientOperationTime is null");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeIsNotNull() {
            addCriterion("clientOperationTime is not null");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeEqualTo(String value) {
            addCriterion("clientOperationTime =", value, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeNotEqualTo(String value) {
            addCriterion("clientOperationTime <>", value, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeGreaterThan(String value) {
            addCriterion("clientOperationTime >", value, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeGreaterThanOrEqualTo(String value) {
            addCriterion("clientOperationTime >=", value, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeLessThan(String value) {
            addCriterion("clientOperationTime <", value, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeLessThanOrEqualTo(String value) {
            addCriterion("clientOperationTime <=", value, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeLike(String value) {
            addCriterion("clientOperationTime like", value, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeNotLike(String value) {
            addCriterion("clientOperationTime not like", value, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeIn(List<String> values) {
            addCriterion("clientOperationTime in", values, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeNotIn(List<String> values) {
            addCriterion("clientOperationTime not in", values, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeBetween(String value1, String value2) {
            addCriterion("clientOperationTime between", value1, value2, "clientoperationtime");
            return (Criteria) this;
        }

        public Criteria andClientoperationtimeNotBetween(String value1, String value2) {
            addCriterion("clientOperationTime not between", value1, value2, "clientoperationtime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated do_not_delete_during_merge Sat Aug 05 23:36:44 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_033_type_two_data_fn161
     *
     * @mbggenerated Sat Aug 05 23:36:44 CST 2017
     */
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