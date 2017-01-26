package com.elefirst.powerdetail.po;

import java.util.ArrayList;
import java.util.List;

public class PowerDetailF25Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public PowerDetailF25Example() {
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

        public Criteria andTotalactivepowerIsNull() {
            addCriterion("totalActivePower is null");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerIsNotNull() {
            addCriterion("totalActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerEqualTo(String value) {
            addCriterion("totalActivePower =", value, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerNotEqualTo(String value) {
            addCriterion("totalActivePower <>", value, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerGreaterThan(String value) {
            addCriterion("totalActivePower >", value, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("totalActivePower >=", value, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerLessThan(String value) {
            addCriterion("totalActivePower <", value, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerLessThanOrEqualTo(String value) {
            addCriterion("totalActivePower <=", value, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerLike(String value) {
            addCriterion("totalActivePower like", value, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerNotLike(String value) {
            addCriterion("totalActivePower not like", value, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerIn(List<String> values) {
            addCriterion("totalActivePower in", values, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerNotIn(List<String> values) {
            addCriterion("totalActivePower not in", values, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerBetween(String value1, String value2) {
            addCriterion("totalActivePower between", value1, value2, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalactivepowerNotBetween(String value1, String value2) {
            addCriterion("totalActivePower not between", value1, value2, "totalactivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerIsNull() {
            addCriterion("A_ActivePower is null");
            return (Criteria) this;
        }

        public Criteria andAActivepowerIsNotNull() {
            addCriterion("A_ActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andAActivepowerEqualTo(String value) {
            addCriterion("A_ActivePower =", value, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerNotEqualTo(String value) {
            addCriterion("A_ActivePower <>", value, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerGreaterThan(String value) {
            addCriterion("A_ActivePower >", value, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("A_ActivePower >=", value, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerLessThan(String value) {
            addCriterion("A_ActivePower <", value, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerLessThanOrEqualTo(String value) {
            addCriterion("A_ActivePower <=", value, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerLike(String value) {
            addCriterion("A_ActivePower like", value, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerNotLike(String value) {
            addCriterion("A_ActivePower not like", value, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerIn(List<String> values) {
            addCriterion("A_ActivePower in", values, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerNotIn(List<String> values) {
            addCriterion("A_ActivePower not in", values, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerBetween(String value1, String value2) {
            addCriterion("A_ActivePower between", value1, value2, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andAActivepowerNotBetween(String value1, String value2) {
            addCriterion("A_ActivePower not between", value1, value2, "aActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerIsNull() {
            addCriterion("B_ActivePower is null");
            return (Criteria) this;
        }

        public Criteria andBActivepowerIsNotNull() {
            addCriterion("B_ActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andBActivepowerEqualTo(String value) {
            addCriterion("B_ActivePower =", value, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerNotEqualTo(String value) {
            addCriterion("B_ActivePower <>", value, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerGreaterThan(String value) {
            addCriterion("B_ActivePower >", value, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("B_ActivePower >=", value, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerLessThan(String value) {
            addCriterion("B_ActivePower <", value, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerLessThanOrEqualTo(String value) {
            addCriterion("B_ActivePower <=", value, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerLike(String value) {
            addCriterion("B_ActivePower like", value, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerNotLike(String value) {
            addCriterion("B_ActivePower not like", value, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerIn(List<String> values) {
            addCriterion("B_ActivePower in", values, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerNotIn(List<String> values) {
            addCriterion("B_ActivePower not in", values, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerBetween(String value1, String value2) {
            addCriterion("B_ActivePower between", value1, value2, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andBActivepowerNotBetween(String value1, String value2) {
            addCriterion("B_ActivePower not between", value1, value2, "bActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerIsNull() {
            addCriterion("C_ActivePower is null");
            return (Criteria) this;
        }

        public Criteria andCActivepowerIsNotNull() {
            addCriterion("C_ActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andCActivepowerEqualTo(String value) {
            addCriterion("C_ActivePower =", value, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerNotEqualTo(String value) {
            addCriterion("C_ActivePower <>", value, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerGreaterThan(String value) {
            addCriterion("C_ActivePower >", value, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("C_ActivePower >=", value, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerLessThan(String value) {
            addCriterion("C_ActivePower <", value, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerLessThanOrEqualTo(String value) {
            addCriterion("C_ActivePower <=", value, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerLike(String value) {
            addCriterion("C_ActivePower like", value, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerNotLike(String value) {
            addCriterion("C_ActivePower not like", value, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerIn(List<String> values) {
            addCriterion("C_ActivePower in", values, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerNotIn(List<String> values) {
            addCriterion("C_ActivePower not in", values, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerBetween(String value1, String value2) {
            addCriterion("C_ActivePower between", value1, value2, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andCActivepowerNotBetween(String value1, String value2) {
            addCriterion("C_ActivePower not between", value1, value2, "cActivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerIsNull() {
            addCriterion("totalReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerIsNotNull() {
            addCriterion("totalReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerEqualTo(String value) {
            addCriterion("totalReactivePower =", value, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerNotEqualTo(String value) {
            addCriterion("totalReactivePower <>", value, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerGreaterThan(String value) {
            addCriterion("totalReactivePower >", value, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("totalReactivePower >=", value, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerLessThan(String value) {
            addCriterion("totalReactivePower <", value, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerLessThanOrEqualTo(String value) {
            addCriterion("totalReactivePower <=", value, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerLike(String value) {
            addCriterion("totalReactivePower like", value, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerNotLike(String value) {
            addCriterion("totalReactivePower not like", value, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerIn(List<String> values) {
            addCriterion("totalReactivePower in", values, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerNotIn(List<String> values) {
            addCriterion("totalReactivePower not in", values, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerBetween(String value1, String value2) {
            addCriterion("totalReactivePower between", value1, value2, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreactivepowerNotBetween(String value1, String value2) {
            addCriterion("totalReactivePower not between", value1, value2, "totalreactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerIsNull() {
            addCriterion("A_ReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerIsNotNull() {
            addCriterion("A_ReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerEqualTo(String value) {
            addCriterion("A_ReactivePower =", value, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerNotEqualTo(String value) {
            addCriterion("A_ReactivePower <>", value, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerGreaterThan(String value) {
            addCriterion("A_ReactivePower >", value, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("A_ReactivePower >=", value, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerLessThan(String value) {
            addCriterion("A_ReactivePower <", value, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerLessThanOrEqualTo(String value) {
            addCriterion("A_ReactivePower <=", value, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerLike(String value) {
            addCriterion("A_ReactivePower like", value, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerNotLike(String value) {
            addCriterion("A_ReactivePower not like", value, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerIn(List<String> values) {
            addCriterion("A_ReactivePower in", values, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerNotIn(List<String> values) {
            addCriterion("A_ReactivePower not in", values, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerBetween(String value1, String value2) {
            addCriterion("A_ReactivePower between", value1, value2, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andAReactivepowerNotBetween(String value1, String value2) {
            addCriterion("A_ReactivePower not between", value1, value2, "aReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerIsNull() {
            addCriterion("B_ReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerIsNotNull() {
            addCriterion("B_ReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerEqualTo(String value) {
            addCriterion("B_ReactivePower =", value, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerNotEqualTo(String value) {
            addCriterion("B_ReactivePower <>", value, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerGreaterThan(String value) {
            addCriterion("B_ReactivePower >", value, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("B_ReactivePower >=", value, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerLessThan(String value) {
            addCriterion("B_ReactivePower <", value, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerLessThanOrEqualTo(String value) {
            addCriterion("B_ReactivePower <=", value, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerLike(String value) {
            addCriterion("B_ReactivePower like", value, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerNotLike(String value) {
            addCriterion("B_ReactivePower not like", value, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerIn(List<String> values) {
            addCriterion("B_ReactivePower in", values, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerNotIn(List<String> values) {
            addCriterion("B_ReactivePower not in", values, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerBetween(String value1, String value2) {
            addCriterion("B_ReactivePower between", value1, value2, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andBReactivepowerNotBetween(String value1, String value2) {
            addCriterion("B_ReactivePower not between", value1, value2, "bReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerIsNull() {
            addCriterion("C_ReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerIsNotNull() {
            addCriterion("C_ReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerEqualTo(String value) {
            addCriterion("C_ReactivePower =", value, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerNotEqualTo(String value) {
            addCriterion("C_ReactivePower <>", value, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerGreaterThan(String value) {
            addCriterion("C_ReactivePower >", value, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("C_ReactivePower >=", value, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerLessThan(String value) {
            addCriterion("C_ReactivePower <", value, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerLessThanOrEqualTo(String value) {
            addCriterion("C_ReactivePower <=", value, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerLike(String value) {
            addCriterion("C_ReactivePower like", value, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerNotLike(String value) {
            addCriterion("C_ReactivePower not like", value, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerIn(List<String> values) {
            addCriterion("C_ReactivePower in", values, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerNotIn(List<String> values) {
            addCriterion("C_ReactivePower not in", values, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerBetween(String value1, String value2) {
            addCriterion("C_ReactivePower between", value1, value2, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andCReactivepowerNotBetween(String value1, String value2) {
            addCriterion("C_ReactivePower not between", value1, value2, "cReactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorIsNull() {
            addCriterion("totalPowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorIsNotNull() {
            addCriterion("totalPowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorEqualTo(String value) {
            addCriterion("totalPowerFactor =", value, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorNotEqualTo(String value) {
            addCriterion("totalPowerFactor <>", value, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorGreaterThan(String value) {
            addCriterion("totalPowerFactor >", value, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorGreaterThanOrEqualTo(String value) {
            addCriterion("totalPowerFactor >=", value, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorLessThan(String value) {
            addCriterion("totalPowerFactor <", value, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorLessThanOrEqualTo(String value) {
            addCriterion("totalPowerFactor <=", value, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorLike(String value) {
            addCriterion("totalPowerFactor like", value, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorNotLike(String value) {
            addCriterion("totalPowerFactor not like", value, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorIn(List<String> values) {
            addCriterion("totalPowerFactor in", values, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorNotIn(List<String> values) {
            addCriterion("totalPowerFactor not in", values, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorBetween(String value1, String value2) {
            addCriterion("totalPowerFactor between", value1, value2, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andTotalpowerfactorNotBetween(String value1, String value2) {
            addCriterion("totalPowerFactor not between", value1, value2, "totalpowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorIsNull() {
            addCriterion("A_PowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorIsNotNull() {
            addCriterion("A_PowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorEqualTo(String value) {
            addCriterion("A_PowerFactor =", value, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorNotEqualTo(String value) {
            addCriterion("A_PowerFactor <>", value, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorGreaterThan(String value) {
            addCriterion("A_PowerFactor >", value, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorGreaterThanOrEqualTo(String value) {
            addCriterion("A_PowerFactor >=", value, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorLessThan(String value) {
            addCriterion("A_PowerFactor <", value, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorLessThanOrEqualTo(String value) {
            addCriterion("A_PowerFactor <=", value, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorLike(String value) {
            addCriterion("A_PowerFactor like", value, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorNotLike(String value) {
            addCriterion("A_PowerFactor not like", value, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorIn(List<String> values) {
            addCriterion("A_PowerFactor in", values, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorNotIn(List<String> values) {
            addCriterion("A_PowerFactor not in", values, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorBetween(String value1, String value2) {
            addCriterion("A_PowerFactor between", value1, value2, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAPowerfactorNotBetween(String value1, String value2) {
            addCriterion("A_PowerFactor not between", value1, value2, "aPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorIsNull() {
            addCriterion("B_PowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorIsNotNull() {
            addCriterion("B_PowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorEqualTo(String value) {
            addCriterion("B_PowerFactor =", value, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorNotEqualTo(String value) {
            addCriterion("B_PowerFactor <>", value, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorGreaterThan(String value) {
            addCriterion("B_PowerFactor >", value, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorGreaterThanOrEqualTo(String value) {
            addCriterion("B_PowerFactor >=", value, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorLessThan(String value) {
            addCriterion("B_PowerFactor <", value, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorLessThanOrEqualTo(String value) {
            addCriterion("B_PowerFactor <=", value, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorLike(String value) {
            addCriterion("B_PowerFactor like", value, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorNotLike(String value) {
            addCriterion("B_PowerFactor not like", value, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorIn(List<String> values) {
            addCriterion("B_PowerFactor in", values, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorNotIn(List<String> values) {
            addCriterion("B_PowerFactor not in", values, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorBetween(String value1, String value2) {
            addCriterion("B_PowerFactor between", value1, value2, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andBPowerfactorNotBetween(String value1, String value2) {
            addCriterion("B_PowerFactor not between", value1, value2, "bPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorIsNull() {
            addCriterion("C_PowerFactor is null");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorIsNotNull() {
            addCriterion("C_PowerFactor is not null");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorEqualTo(String value) {
            addCriterion("C_PowerFactor =", value, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorNotEqualTo(String value) {
            addCriterion("C_PowerFactor <>", value, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorGreaterThan(String value) {
            addCriterion("C_PowerFactor >", value, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorGreaterThanOrEqualTo(String value) {
            addCriterion("C_PowerFactor >=", value, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorLessThan(String value) {
            addCriterion("C_PowerFactor <", value, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorLessThanOrEqualTo(String value) {
            addCriterion("C_PowerFactor <=", value, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorLike(String value) {
            addCriterion("C_PowerFactor like", value, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorNotLike(String value) {
            addCriterion("C_PowerFactor not like", value, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorIn(List<String> values) {
            addCriterion("C_PowerFactor in", values, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorNotIn(List<String> values) {
            addCriterion("C_PowerFactor not in", values, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorBetween(String value1, String value2) {
            addCriterion("C_PowerFactor between", value1, value2, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andCPowerfactorNotBetween(String value1, String value2) {
            addCriterion("C_PowerFactor not between", value1, value2, "cPowerfactor");
            return (Criteria) this;
        }

        public Criteria andAVoltageIsNull() {
            addCriterion("A_Voltage is null");
            return (Criteria) this;
        }

        public Criteria andAVoltageIsNotNull() {
            addCriterion("A_Voltage is not null");
            return (Criteria) this;
        }

        public Criteria andAVoltageEqualTo(String value) {
            addCriterion("A_Voltage =", value, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageNotEqualTo(String value) {
            addCriterion("A_Voltage <>", value, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageGreaterThan(String value) {
            addCriterion("A_Voltage >", value, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageGreaterThanOrEqualTo(String value) {
            addCriterion("A_Voltage >=", value, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageLessThan(String value) {
            addCriterion("A_Voltage <", value, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageLessThanOrEqualTo(String value) {
            addCriterion("A_Voltage <=", value, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageLike(String value) {
            addCriterion("A_Voltage like", value, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageNotLike(String value) {
            addCriterion("A_Voltage not like", value, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageIn(List<String> values) {
            addCriterion("A_Voltage in", values, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageNotIn(List<String> values) {
            addCriterion("A_Voltage not in", values, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageBetween(String value1, String value2) {
            addCriterion("A_Voltage between", value1, value2, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andAVoltageNotBetween(String value1, String value2) {
            addCriterion("A_Voltage not between", value1, value2, "aVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageIsNull() {
            addCriterion("B_Voltage is null");
            return (Criteria) this;
        }

        public Criteria andBVoltageIsNotNull() {
            addCriterion("B_Voltage is not null");
            return (Criteria) this;
        }

        public Criteria andBVoltageEqualTo(String value) {
            addCriterion("B_Voltage =", value, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageNotEqualTo(String value) {
            addCriterion("B_Voltage <>", value, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageGreaterThan(String value) {
            addCriterion("B_Voltage >", value, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageGreaterThanOrEqualTo(String value) {
            addCriterion("B_Voltage >=", value, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageLessThan(String value) {
            addCriterion("B_Voltage <", value, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageLessThanOrEqualTo(String value) {
            addCriterion("B_Voltage <=", value, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageLike(String value) {
            addCriterion("B_Voltage like", value, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageNotLike(String value) {
            addCriterion("B_Voltage not like", value, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageIn(List<String> values) {
            addCriterion("B_Voltage in", values, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageNotIn(List<String> values) {
            addCriterion("B_Voltage not in", values, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageBetween(String value1, String value2) {
            addCriterion("B_Voltage between", value1, value2, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andBVoltageNotBetween(String value1, String value2) {
            addCriterion("B_Voltage not between", value1, value2, "bVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageIsNull() {
            addCriterion("C_Voltage is null");
            return (Criteria) this;
        }

        public Criteria andCVoltageIsNotNull() {
            addCriterion("C_Voltage is not null");
            return (Criteria) this;
        }

        public Criteria andCVoltageEqualTo(String value) {
            addCriterion("C_Voltage =", value, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageNotEqualTo(String value) {
            addCriterion("C_Voltage <>", value, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageGreaterThan(String value) {
            addCriterion("C_Voltage >", value, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageGreaterThanOrEqualTo(String value) {
            addCriterion("C_Voltage >=", value, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageLessThan(String value) {
            addCriterion("C_Voltage <", value, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageLessThanOrEqualTo(String value) {
            addCriterion("C_Voltage <=", value, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageLike(String value) {
            addCriterion("C_Voltage like", value, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageNotLike(String value) {
            addCriterion("C_Voltage not like", value, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageIn(List<String> values) {
            addCriterion("C_Voltage in", values, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageNotIn(List<String> values) {
            addCriterion("C_Voltage not in", values, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageBetween(String value1, String value2) {
            addCriterion("C_Voltage between", value1, value2, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andCVoltageNotBetween(String value1, String value2) {
            addCriterion("C_Voltage not between", value1, value2, "cVoltage");
            return (Criteria) this;
        }

        public Criteria andACurrentIsNull() {
            addCriterion("A_Current is null");
            return (Criteria) this;
        }

        public Criteria andACurrentIsNotNull() {
            addCriterion("A_Current is not null");
            return (Criteria) this;
        }

        public Criteria andACurrentEqualTo(String value) {
            addCriterion("A_Current =", value, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentNotEqualTo(String value) {
            addCriterion("A_Current <>", value, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentGreaterThan(String value) {
            addCriterion("A_Current >", value, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentGreaterThanOrEqualTo(String value) {
            addCriterion("A_Current >=", value, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentLessThan(String value) {
            addCriterion("A_Current <", value, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentLessThanOrEqualTo(String value) {
            addCriterion("A_Current <=", value, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentLike(String value) {
            addCriterion("A_Current like", value, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentNotLike(String value) {
            addCriterion("A_Current not like", value, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentIn(List<String> values) {
            addCriterion("A_Current in", values, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentNotIn(List<String> values) {
            addCriterion("A_Current not in", values, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentBetween(String value1, String value2) {
            addCriterion("A_Current between", value1, value2, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andACurrentNotBetween(String value1, String value2) {
            addCriterion("A_Current not between", value1, value2, "aCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentIsNull() {
            addCriterion("B_Current is null");
            return (Criteria) this;
        }

        public Criteria andBCurrentIsNotNull() {
            addCriterion("B_Current is not null");
            return (Criteria) this;
        }

        public Criteria andBCurrentEqualTo(String value) {
            addCriterion("B_Current =", value, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentNotEqualTo(String value) {
            addCriterion("B_Current <>", value, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentGreaterThan(String value) {
            addCriterion("B_Current >", value, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentGreaterThanOrEqualTo(String value) {
            addCriterion("B_Current >=", value, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentLessThan(String value) {
            addCriterion("B_Current <", value, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentLessThanOrEqualTo(String value) {
            addCriterion("B_Current <=", value, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentLike(String value) {
            addCriterion("B_Current like", value, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentNotLike(String value) {
            addCriterion("B_Current not like", value, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentIn(List<String> values) {
            addCriterion("B_Current in", values, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentNotIn(List<String> values) {
            addCriterion("B_Current not in", values, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentBetween(String value1, String value2) {
            addCriterion("B_Current between", value1, value2, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andBCurrentNotBetween(String value1, String value2) {
            addCriterion("B_Current not between", value1, value2, "bCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentIsNull() {
            addCriterion("C_Current is null");
            return (Criteria) this;
        }

        public Criteria andCCurrentIsNotNull() {
            addCriterion("C_Current is not null");
            return (Criteria) this;
        }

        public Criteria andCCurrentEqualTo(String value) {
            addCriterion("C_Current =", value, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentNotEqualTo(String value) {
            addCriterion("C_Current <>", value, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentGreaterThan(String value) {
            addCriterion("C_Current >", value, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentGreaterThanOrEqualTo(String value) {
            addCriterion("C_Current >=", value, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentLessThan(String value) {
            addCriterion("C_Current <", value, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentLessThanOrEqualTo(String value) {
            addCriterion("C_Current <=", value, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentLike(String value) {
            addCriterion("C_Current like", value, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentNotLike(String value) {
            addCriterion("C_Current not like", value, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentIn(List<String> values) {
            addCriterion("C_Current in", values, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentNotIn(List<String> values) {
            addCriterion("C_Current not in", values, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentBetween(String value1, String value2) {
            addCriterion("C_Current between", value1, value2, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andCCurrentNotBetween(String value1, String value2) {
            addCriterion("C_Current not between", value1, value2, "cCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentIsNull() {
            addCriterion("Zero_Sequence_Current is null");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentIsNotNull() {
            addCriterion("Zero_Sequence_Current is not null");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentEqualTo(String value) {
            addCriterion("Zero_Sequence_Current =", value, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentNotEqualTo(String value) {
            addCriterion("Zero_Sequence_Current <>", value, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentGreaterThan(String value) {
            addCriterion("Zero_Sequence_Current >", value, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentGreaterThanOrEqualTo(String value) {
            addCriterion("Zero_Sequence_Current >=", value, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentLessThan(String value) {
            addCriterion("Zero_Sequence_Current <", value, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentLessThanOrEqualTo(String value) {
            addCriterion("Zero_Sequence_Current <=", value, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentLike(String value) {
            addCriterion("Zero_Sequence_Current like", value, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentNotLike(String value) {
            addCriterion("Zero_Sequence_Current not like", value, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentIn(List<String> values) {
            addCriterion("Zero_Sequence_Current in", values, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentNotIn(List<String> values) {
            addCriterion("Zero_Sequence_Current not in", values, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentBetween(String value1, String value2) {
            addCriterion("Zero_Sequence_Current between", value1, value2, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andZeroSequenceCurrentNotBetween(String value1, String value2) {
            addCriterion("Zero_Sequence_Current not between", value1, value2, "zeroSequenceCurrent");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerIsNull() {
            addCriterion("totalApparentPower is null");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerIsNotNull() {
            addCriterion("totalApparentPower is not null");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerEqualTo(String value) {
            addCriterion("totalApparentPower =", value, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerNotEqualTo(String value) {
            addCriterion("totalApparentPower <>", value, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerGreaterThan(String value) {
            addCriterion("totalApparentPower >", value, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerGreaterThanOrEqualTo(String value) {
            addCriterion("totalApparentPower >=", value, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerLessThan(String value) {
            addCriterion("totalApparentPower <", value, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerLessThanOrEqualTo(String value) {
            addCriterion("totalApparentPower <=", value, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerLike(String value) {
            addCriterion("totalApparentPower like", value, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerNotLike(String value) {
            addCriterion("totalApparentPower not like", value, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerIn(List<String> values) {
            addCriterion("totalApparentPower in", values, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerNotIn(List<String> values) {
            addCriterion("totalApparentPower not in", values, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerBetween(String value1, String value2) {
            addCriterion("totalApparentPower between", value1, value2, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andTotalapparentpowerNotBetween(String value1, String value2) {
            addCriterion("totalApparentPower not between", value1, value2, "totalapparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerIsNull() {
            addCriterion("A_ApparentPower is null");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerIsNotNull() {
            addCriterion("A_ApparentPower is not null");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerEqualTo(String value) {
            addCriterion("A_ApparentPower =", value, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerNotEqualTo(String value) {
            addCriterion("A_ApparentPower <>", value, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerGreaterThan(String value) {
            addCriterion("A_ApparentPower >", value, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerGreaterThanOrEqualTo(String value) {
            addCriterion("A_ApparentPower >=", value, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerLessThan(String value) {
            addCriterion("A_ApparentPower <", value, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerLessThanOrEqualTo(String value) {
            addCriterion("A_ApparentPower <=", value, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerLike(String value) {
            addCriterion("A_ApparentPower like", value, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerNotLike(String value) {
            addCriterion("A_ApparentPower not like", value, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerIn(List<String> values) {
            addCriterion("A_ApparentPower in", values, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerNotIn(List<String> values) {
            addCriterion("A_ApparentPower not in", values, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerBetween(String value1, String value2) {
            addCriterion("A_ApparentPower between", value1, value2, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andAApparentpowerNotBetween(String value1, String value2) {
            addCriterion("A_ApparentPower not between", value1, value2, "aApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerIsNull() {
            addCriterion("B_ApparentPower is null");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerIsNotNull() {
            addCriterion("B_ApparentPower is not null");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerEqualTo(String value) {
            addCriterion("B_ApparentPower =", value, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerNotEqualTo(String value) {
            addCriterion("B_ApparentPower <>", value, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerGreaterThan(String value) {
            addCriterion("B_ApparentPower >", value, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerGreaterThanOrEqualTo(String value) {
            addCriterion("B_ApparentPower >=", value, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerLessThan(String value) {
            addCriterion("B_ApparentPower <", value, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerLessThanOrEqualTo(String value) {
            addCriterion("B_ApparentPower <=", value, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerLike(String value) {
            addCriterion("B_ApparentPower like", value, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerNotLike(String value) {
            addCriterion("B_ApparentPower not like", value, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerIn(List<String> values) {
            addCriterion("B_ApparentPower in", values, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerNotIn(List<String> values) {
            addCriterion("B_ApparentPower not in", values, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerBetween(String value1, String value2) {
            addCriterion("B_ApparentPower between", value1, value2, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andBApparentpowerNotBetween(String value1, String value2) {
            addCriterion("B_ApparentPower not between", value1, value2, "bApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerIsNull() {
            addCriterion("C_ApparentPower is null");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerIsNotNull() {
            addCriterion("C_ApparentPower is not null");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerEqualTo(String value) {
            addCriterion("C_ApparentPower =", value, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerNotEqualTo(String value) {
            addCriterion("C_ApparentPower <>", value, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerGreaterThan(String value) {
            addCriterion("C_ApparentPower >", value, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerGreaterThanOrEqualTo(String value) {
            addCriterion("C_ApparentPower >=", value, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerLessThan(String value) {
            addCriterion("C_ApparentPower <", value, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerLessThanOrEqualTo(String value) {
            addCriterion("C_ApparentPower <=", value, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerLike(String value) {
            addCriterion("C_ApparentPower like", value, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerNotLike(String value) {
            addCriterion("C_ApparentPower not like", value, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerIn(List<String> values) {
            addCriterion("C_ApparentPower in", values, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerNotIn(List<String> values) {
            addCriterion("C_ApparentPower not in", values, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerBetween(String value1, String value2) {
            addCriterion("C_ApparentPower between", value1, value2, "cApparentpower");
            return (Criteria) this;
        }

        public Criteria andCApparentpowerNotBetween(String value1, String value2) {
            addCriterion("C_ApparentPower not between", value1, value2, "cApparentpower");
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