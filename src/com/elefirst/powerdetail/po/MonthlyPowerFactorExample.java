package com.elefirst.powerdetail.po;

import java.util.ArrayList;
import java.util.List;

public class MonthlyPowerFactorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public MonthlyPowerFactorExample() {
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

        public Criteria andPowerFactorStandardIsNull() {
            addCriterion("power_factor_standard is null");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardIsNotNull() {
            addCriterion("power_factor_standard is not null");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardEqualTo(Double value) {
            addCriterion("power_factor_standard =", value, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardNotEqualTo(Double value) {
            addCriterion("power_factor_standard <>", value, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardGreaterThan(Double value) {
            addCriterion("power_factor_standard >", value, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardGreaterThanOrEqualTo(Double value) {
            addCriterion("power_factor_standard >=", value, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardLessThan(Double value) {
            addCriterion("power_factor_standard <", value, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardLessThanOrEqualTo(Double value) {
            addCriterion("power_factor_standard <=", value, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardIn(List<Double> values) {
            addCriterion("power_factor_standard in", values, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardNotIn(List<Double> values) {
            addCriterion("power_factor_standard not in", values, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardBetween(Double value1, Double value2) {
            addCriterion("power_factor_standard between", value1, value2, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andPowerFactorStandardNotBetween(Double value1, Double value2) {
            addCriterion("power_factor_standard not between", value1, value2, "powerFactorStandard");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorIsNull() {
            addCriterion("avgTotalPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorIsNotNull() {
            addCriterion("avgTotalPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorEqualTo(Double value) {
            addCriterion("avgTotalPowerFactor =", value, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorNotEqualTo(Double value) {
            addCriterion("avgTotalPowerFactor <>", value, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorGreaterThan(Double value) {
            addCriterion("avgTotalPowerFactor >", value, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("avgTotalPowerFactor >=", value, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorLessThan(Double value) {
            addCriterion("avgTotalPowerFactor <", value, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("avgTotalPowerFactor <=", value, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorIn(List<Double> values) {
            addCriterion("avgTotalPowerFactor in", values, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorNotIn(List<Double> values) {
            addCriterion("avgTotalPowerFactor not in", values, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorBetween(Double value1, Double value2) {
            addCriterion("avgTotalPowerFactor between", value1, value2, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAvgtotalpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("avgTotalPowerFactor not between", value1, value2, "avgtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorIsNull() {
            addCriterion("aMaxPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorIsNotNull() {
            addCriterion("aMaxPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorEqualTo(Double value) {
            addCriterion("aMaxPowerFactor =", value, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorNotEqualTo(Double value) {
            addCriterion("aMaxPowerFactor <>", value, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorGreaterThan(Double value) {
            addCriterion("aMaxPowerFactor >", value, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("aMaxPowerFactor >=", value, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorLessThan(Double value) {
            addCriterion("aMaxPowerFactor <", value, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("aMaxPowerFactor <=", value, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorIn(List<Double> values) {
            addCriterion("aMaxPowerFactor in", values, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorNotIn(List<Double> values) {
            addCriterion("aMaxPowerFactor not in", values, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorBetween(Double value1, Double value2) {
            addCriterion("aMaxPowerFactor between", value1, value2, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAmaxpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("aMaxPowerFactor not between", value1, value2, "amaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorIsNull() {
            addCriterion("bMaxPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorIsNotNull() {
            addCriterion("bMaxPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorEqualTo(Double value) {
            addCriterion("bMaxPowerFactor =", value, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorNotEqualTo(Double value) {
            addCriterion("bMaxPowerFactor <>", value, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorGreaterThan(Double value) {
            addCriterion("bMaxPowerFactor >", value, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("bMaxPowerFactor >=", value, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorLessThan(Double value) {
            addCriterion("bMaxPowerFactor <", value, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("bMaxPowerFactor <=", value, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorIn(List<Double> values) {
            addCriterion("bMaxPowerFactor in", values, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorNotIn(List<Double> values) {
            addCriterion("bMaxPowerFactor not in", values, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorBetween(Double value1, Double value2) {
            addCriterion("bMaxPowerFactor between", value1, value2, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBmaxpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("bMaxPowerFactor not between", value1, value2, "bmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorIsNull() {
            addCriterion("cMaxPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorIsNotNull() {
            addCriterion("cMaxPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorEqualTo(Double value) {
            addCriterion("cMaxPowerFactor =", value, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorNotEqualTo(Double value) {
            addCriterion("cMaxPowerFactor <>", value, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorGreaterThan(Double value) {
            addCriterion("cMaxPowerFactor >", value, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("cMaxPowerFactor >=", value, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorLessThan(Double value) {
            addCriterion("cMaxPowerFactor <", value, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("cMaxPowerFactor <=", value, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorIn(List<Double> values) {
            addCriterion("cMaxPowerFactor in", values, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorNotIn(List<Double> values) {
            addCriterion("cMaxPowerFactor not in", values, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorBetween(Double value1, Double value2) {
            addCriterion("cMaxPowerFactor between", value1, value2, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCmaxpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("cMaxPowerFactor not between", value1, value2, "cmaxpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorIsNull() {
            addCriterion("maxTotalPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorIsNotNull() {
            addCriterion("maxTotalPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorEqualTo(Double value) {
            addCriterion("maxTotalPowerFactor =", value, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorNotEqualTo(Double value) {
            addCriterion("maxTotalPowerFactor <>", value, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorGreaterThan(Double value) {
            addCriterion("maxTotalPowerFactor >", value, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("maxTotalPowerFactor >=", value, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorLessThan(Double value) {
            addCriterion("maxTotalPowerFactor <", value, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("maxTotalPowerFactor <=", value, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorIn(List<Double> values) {
            addCriterion("maxTotalPowerFactor in", values, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorNotIn(List<Double> values) {
            addCriterion("maxTotalPowerFactor not in", values, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorBetween(Double value1, Double value2) {
            addCriterion("maxTotalPowerFactor between", value1, value2, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMaxtotalpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("maxTotalPowerFactor not between", value1, value2, "maxtotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorIsNull() {
            addCriterion("aMinPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorIsNotNull() {
            addCriterion("aMinPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorEqualTo(Double value) {
            addCriterion("aMinPowerFactor =", value, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorNotEqualTo(Double value) {
            addCriterion("aMinPowerFactor <>", value, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorGreaterThan(Double value) {
            addCriterion("aMinPowerFactor >", value, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("aMinPowerFactor >=", value, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorLessThan(Double value) {
            addCriterion("aMinPowerFactor <", value, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("aMinPowerFactor <=", value, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorIn(List<Double> values) {
            addCriterion("aMinPowerFactor in", values, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorNotIn(List<Double> values) {
            addCriterion("aMinPowerFactor not in", values, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorBetween(Double value1, Double value2) {
            addCriterion("aMinPowerFactor between", value1, value2, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAminpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("aMinPowerFactor not between", value1, value2, "aminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorIsNull() {
            addCriterion("bMinPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorIsNotNull() {
            addCriterion("bMinPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorEqualTo(Double value) {
            addCriterion("bMinPowerFactor =", value, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorNotEqualTo(Double value) {
            addCriterion("bMinPowerFactor <>", value, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorGreaterThan(Double value) {
            addCriterion("bMinPowerFactor >", value, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("bMinPowerFactor >=", value, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorLessThan(Double value) {
            addCriterion("bMinPowerFactor <", value, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("bMinPowerFactor <=", value, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorIn(List<Double> values) {
            addCriterion("bMinPowerFactor in", values, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorNotIn(List<Double> values) {
            addCriterion("bMinPowerFactor not in", values, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorBetween(Double value1, Double value2) {
            addCriterion("bMinPowerFactor between", value1, value2, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andBminpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("bMinPowerFactor not between", value1, value2, "bminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorIsNull() {
            addCriterion("cMinPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorIsNotNull() {
            addCriterion("cMinPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorEqualTo(Double value) {
            addCriterion("cMinPowerFactor =", value, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorNotEqualTo(Double value) {
            addCriterion("cMinPowerFactor <>", value, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorGreaterThan(Double value) {
            addCriterion("cMinPowerFactor >", value, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("cMinPowerFactor >=", value, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorLessThan(Double value) {
            addCriterion("cMinPowerFactor <", value, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("cMinPowerFactor <=", value, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorIn(List<Double> values) {
            addCriterion("cMinPowerFactor in", values, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorNotIn(List<Double> values) {
            addCriterion("cMinPowerFactor not in", values, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorBetween(Double value1, Double value2) {
            addCriterion("cMinPowerFactor between", value1, value2, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andCminpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("cMinPowerFactor not between", value1, value2, "cminpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorIsNull() {
            addCriterion("minTotalPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorIsNotNull() {
            addCriterion("minTotalPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorEqualTo(Double value) {
            addCriterion("minTotalPowerFactor =", value, "mintotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorNotEqualTo(Double value) {
            addCriterion("minTotalPowerFactor <>", value, "mintotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorGreaterThan(Double value) {
            addCriterion("minTotalPowerFactor >", value, "mintotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorGreaterThanOrEqualTo(Double value) {
            addCriterion("minTotalPowerFactor >=", value, "mintotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorLessThan(Double value) {
            addCriterion("minTotalPowerFactor <", value, "mintotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorLessThanOrEqualTo(Double value) {
            addCriterion("minTotalPowerFactor <=", value, "mintotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorIn(List<Double> values) {
            addCriterion("minTotalPowerFactor in", values, "mintotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorNotIn(List<Double> values) {
            addCriterion("minTotalPowerFactor not in", values, "mintotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorBetween(Double value1, Double value2) {
            addCriterion("minTotalPowerFactor between", value1, value2, "mintotalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andMintotalpowerfactorNotBetween(Double value1, Double value2) {
            addCriterion("minTotalPowerFactor not between", value1, value2, "mintotalpowerfactor");
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