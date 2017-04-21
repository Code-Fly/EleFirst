package com.elefirst.powerdetail.po;

import java.util.ArrayList;
import java.util.List;

public class TwoRealtimeDisplayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public TwoRealtimeDisplayExample() {
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

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andPtIsNull() {
            addCriterion("pt is null");
            return (Criteria) this;
        }

        public Criteria andPtIsNotNull() {
            addCriterion("pt is not null");
            return (Criteria) this;
        }

        public Criteria andPtEqualTo(Double value) {
            addCriterion("pt =", value, "pt");
            return (Criteria) this;
        }

        public Criteria andPtNotEqualTo(Double value) {
            addCriterion("pt <>", value, "pt");
            return (Criteria) this;
        }

        public Criteria andPtGreaterThan(Double value) {
            addCriterion("pt >", value, "pt");
            return (Criteria) this;
        }

        public Criteria andPtGreaterThanOrEqualTo(Double value) {
            addCriterion("pt >=", value, "pt");
            return (Criteria) this;
        }

        public Criteria andPtLessThan(Double value) {
            addCriterion("pt <", value, "pt");
            return (Criteria) this;
        }

        public Criteria andPtLessThanOrEqualTo(Double value) {
            addCriterion("pt <=", value, "pt");
            return (Criteria) this;
        }

        public Criteria andPtIn(List<Double> values) {
            addCriterion("pt in", values, "pt");
            return (Criteria) this;
        }

        public Criteria andPtNotIn(List<Double> values) {
            addCriterion("pt not in", values, "pt");
            return (Criteria) this;
        }

        public Criteria andPtBetween(Double value1, Double value2) {
            addCriterion("pt between", value1, value2, "pt");
            return (Criteria) this;
        }

        public Criteria andPtNotBetween(Double value1, Double value2) {
            addCriterion("pt not between", value1, value2, "pt");
            return (Criteria) this;
        }

        public Criteria andCtIsNull() {
            addCriterion("ct is null");
            return (Criteria) this;
        }

        public Criteria andCtIsNotNull() {
            addCriterion("ct is not null");
            return (Criteria) this;
        }

        public Criteria andCtEqualTo(Double value) {
            addCriterion("ct =", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotEqualTo(Double value) {
            addCriterion("ct <>", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThan(Double value) {
            addCriterion("ct >", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThanOrEqualTo(Double value) {
            addCriterion("ct >=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThan(Double value) {
            addCriterion("ct <", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThanOrEqualTo(Double value) {
            addCriterion("ct <=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtIn(List<Double> values) {
            addCriterion("ct in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotIn(List<Double> values) {
            addCriterion("ct not in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtBetween(Double value1, Double value2) {
            addCriterion("ct between", value1, value2, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotBetween(Double value1, Double value2) {
            addCriterion("ct not between", value1, value2, "ct");
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

        public Criteria andTotalpositivereactivepowerIsNull() {
            addCriterion("totalPositiveReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerIsNotNull() {
            addCriterion("totalPositiveReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerEqualTo(String value) {
            addCriterion("totalPositiveReactivePower =", value, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerNotEqualTo(String value) {
            addCriterion("totalPositiveReactivePower <>", value, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerGreaterThan(String value) {
            addCriterion("totalPositiveReactivePower >", value, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("totalPositiveReactivePower >=", value, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerLessThan(String value) {
            addCriterion("totalPositiveReactivePower <", value, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerLessThanOrEqualTo(String value) {
            addCriterion("totalPositiveReactivePower <=", value, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerLike(String value) {
            addCriterion("totalPositiveReactivePower like", value, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerNotLike(String value) {
            addCriterion("totalPositiveReactivePower not like", value, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerIn(List<String> values) {
            addCriterion("totalPositiveReactivePower in", values, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerNotIn(List<String> values) {
            addCriterion("totalPositiveReactivePower not in", values, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerBetween(String value1, String value2) {
            addCriterion("totalPositiveReactivePower between", value1, value2, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalpositivereactivepowerNotBetween(String value1, String value2) {
            addCriterion("totalPositiveReactivePower not between", value1, value2, "totalpositivereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerIsNull() {
            addCriterion("totalReverseActivePower is null");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerIsNotNull() {
            addCriterion("totalReverseActivePower is not null");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerEqualTo(String value) {
            addCriterion("totalReverseActivePower =", value, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerNotEqualTo(String value) {
            addCriterion("totalReverseActivePower <>", value, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerGreaterThan(String value) {
            addCriterion("totalReverseActivePower >", value, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("totalReverseActivePower >=", value, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerLessThan(String value) {
            addCriterion("totalReverseActivePower <", value, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerLessThanOrEqualTo(String value) {
            addCriterion("totalReverseActivePower <=", value, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerLike(String value) {
            addCriterion("totalReverseActivePower like", value, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerNotLike(String value) {
            addCriterion("totalReverseActivePower not like", value, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerIn(List<String> values) {
            addCriterion("totalReverseActivePower in", values, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerNotIn(List<String> values) {
            addCriterion("totalReverseActivePower not in", values, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerBetween(String value1, String value2) {
            addCriterion("totalReverseActivePower between", value1, value2, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreverseactivepowerNotBetween(String value1, String value2) {
            addCriterion("totalReverseActivePower not between", value1, value2, "totalreverseactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerIsNull() {
            addCriterion("totalReverseReactivePower is null");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerIsNotNull() {
            addCriterion("totalReverseReactivePower is not null");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerEqualTo(String value) {
            addCriterion("totalReverseReactivePower =", value, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerNotEqualTo(String value) {
            addCriterion("totalReverseReactivePower <>", value, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerGreaterThan(String value) {
            addCriterion("totalReverseReactivePower >", value, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerGreaterThanOrEqualTo(String value) {
            addCriterion("totalReverseReactivePower >=", value, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerLessThan(String value) {
            addCriterion("totalReverseReactivePower <", value, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerLessThanOrEqualTo(String value) {
            addCriterion("totalReverseReactivePower <=", value, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerLike(String value) {
            addCriterion("totalReverseReactivePower like", value, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerNotLike(String value) {
            addCriterion("totalReverseReactivePower not like", value, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerIn(List<String> values) {
            addCriterion("totalReverseReactivePower in", values, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerNotIn(List<String> values) {
            addCriterion("totalReverseReactivePower not in", values, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerBetween(String value1, String value2) {
            addCriterion("totalReverseReactivePower between", value1, value2, "totalreversereactivepower");
            return (Criteria) this;
        }

        public Criteria andTotalreversereactivepowerNotBetween(String value1, String value2) {
            addCriterion("totalReverseReactivePower not between", value1, value2, "totalreversereactivepower");
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