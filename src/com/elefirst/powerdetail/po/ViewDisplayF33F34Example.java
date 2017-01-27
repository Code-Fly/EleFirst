package com.elefirst.powerdetail.po;

import java.util.ArrayList;
import java.util.List;

public class ViewDisplayF33F34Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ViewDisplayF33F34Example() {
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

        public Criteria andId33IsNull() {
            addCriterion("id33 is null");
            return (Criteria) this;
        }

        public Criteria andId33IsNotNull() {
            addCriterion("id33 is not null");
            return (Criteria) this;
        }

        public Criteria andId33EqualTo(String value) {
            addCriterion("id33 =", value, "id33");
            return (Criteria) this;
        }

        public Criteria andId33NotEqualTo(String value) {
            addCriterion("id33 <>", value, "id33");
            return (Criteria) this;
        }

        public Criteria andId33GreaterThan(String value) {
            addCriterion("id33 >", value, "id33");
            return (Criteria) this;
        }

        public Criteria andId33GreaterThanOrEqualTo(String value) {
            addCriterion("id33 >=", value, "id33");
            return (Criteria) this;
        }

        public Criteria andId33LessThan(String value) {
            addCriterion("id33 <", value, "id33");
            return (Criteria) this;
        }

        public Criteria andId33LessThanOrEqualTo(String value) {
            addCriterion("id33 <=", value, "id33");
            return (Criteria) this;
        }

        public Criteria andId33Like(String value) {
            addCriterion("id33 like", value, "id33");
            return (Criteria) this;
        }

        public Criteria andId33NotLike(String value) {
            addCriterion("id33 not like", value, "id33");
            return (Criteria) this;
        }

        public Criteria andId33In(List<String> values) {
            addCriterion("id33 in", values, "id33");
            return (Criteria) this;
        }

        public Criteria andId33NotIn(List<String> values) {
            addCriterion("id33 not in", values, "id33");
            return (Criteria) this;
        }

        public Criteria andId33Between(String value1, String value2) {
            addCriterion("id33 between", value1, value2, "id33");
            return (Criteria) this;
        }

        public Criteria andId33NotBetween(String value1, String value2) {
            addCriterion("id33 not between", value1, value2, "id33");
            return (Criteria) this;
        }

        public Criteria andAreaId33IsNull() {
            addCriterion("area_id33 is null");
            return (Criteria) this;
        }

        public Criteria andAreaId33IsNotNull() {
            addCriterion("area_id33 is not null");
            return (Criteria) this;
        }

        public Criteria andAreaId33EqualTo(String value) {
            addCriterion("area_id33 =", value, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33NotEqualTo(String value) {
            addCriterion("area_id33 <>", value, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33GreaterThan(String value) {
            addCriterion("area_id33 >", value, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33GreaterThanOrEqualTo(String value) {
            addCriterion("area_id33 >=", value, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33LessThan(String value) {
            addCriterion("area_id33 <", value, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33LessThanOrEqualTo(String value) {
            addCriterion("area_id33 <=", value, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33Like(String value) {
            addCriterion("area_id33 like", value, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33NotLike(String value) {
            addCriterion("area_id33 not like", value, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33In(List<String> values) {
            addCriterion("area_id33 in", values, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33NotIn(List<String> values) {
            addCriterion("area_id33 not in", values, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33Between(String value1, String value2) {
            addCriterion("area_id33 between", value1, value2, "areaId33");
            return (Criteria) this;
        }

        public Criteria andAreaId33NotBetween(String value1, String value2) {
            addCriterion("area_id33 not between", value1, value2, "areaId33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33IsNull() {
            addCriterion("clientOperationTime33 is null");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33IsNotNull() {
            addCriterion("clientOperationTime33 is not null");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33EqualTo(String value) {
            addCriterion("clientOperationTime33 =", value, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33NotEqualTo(String value) {
            addCriterion("clientOperationTime33 <>", value, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33GreaterThan(String value) {
            addCriterion("clientOperationTime33 >", value, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33GreaterThanOrEqualTo(String value) {
            addCriterion("clientOperationTime33 >=", value, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33LessThan(String value) {
            addCriterion("clientOperationTime33 <", value, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33LessThanOrEqualTo(String value) {
            addCriterion("clientOperationTime33 <=", value, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33Like(String value) {
            addCriterion("clientOperationTime33 like", value, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33NotLike(String value) {
            addCriterion("clientOperationTime33 not like", value, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33In(List<String> values) {
            addCriterion("clientOperationTime33 in", values, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33NotIn(List<String> values) {
            addCriterion("clientOperationTime33 not in", values, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33Between(String value1, String value2) {
            addCriterion("clientOperationTime33 between", value1, value2, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime33NotBetween(String value1, String value2) {
            addCriterion("clientOperationTime33 not between", value1, value2, "clientoperationtime33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33IsNull() {
            addCriterion("concentrator_id33 is null");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33IsNotNull() {
            addCriterion("concentrator_id33 is not null");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33EqualTo(String value) {
            addCriterion("concentrator_id33 =", value, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33NotEqualTo(String value) {
            addCriterion("concentrator_id33 <>", value, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33GreaterThan(String value) {
            addCriterion("concentrator_id33 >", value, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33GreaterThanOrEqualTo(String value) {
            addCriterion("concentrator_id33 >=", value, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33LessThan(String value) {
            addCriterion("concentrator_id33 <", value, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33LessThanOrEqualTo(String value) {
            addCriterion("concentrator_id33 <=", value, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33Like(String value) {
            addCriterion("concentrator_id33 like", value, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33NotLike(String value) {
            addCriterion("concentrator_id33 not like", value, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33In(List<String> values) {
            addCriterion("concentrator_id33 in", values, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33NotIn(List<String> values) {
            addCriterion("concentrator_id33 not in", values, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33Between(String value1, String value2) {
            addCriterion("concentrator_id33 between", value1, value2, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andConcentratorId33NotBetween(String value1, String value2) {
            addCriterion("concentrator_id33 not between", value1, value2, "concentratorId33");
            return (Criteria) this;
        }

        public Criteria andPn33IsNull() {
            addCriterion("pn33 is null");
            return (Criteria) this;
        }

        public Criteria andPn33IsNotNull() {
            addCriterion("pn33 is not null");
            return (Criteria) this;
        }

        public Criteria andPn33EqualTo(String value) {
            addCriterion("pn33 =", value, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33NotEqualTo(String value) {
            addCriterion("pn33 <>", value, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33GreaterThan(String value) {
            addCriterion("pn33 >", value, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33GreaterThanOrEqualTo(String value) {
            addCriterion("pn33 >=", value, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33LessThan(String value) {
            addCriterion("pn33 <", value, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33LessThanOrEqualTo(String value) {
            addCriterion("pn33 <=", value, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33Like(String value) {
            addCriterion("pn33 like", value, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33NotLike(String value) {
            addCriterion("pn33 not like", value, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33In(List<String> values) {
            addCriterion("pn33 in", values, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33NotIn(List<String> values) {
            addCriterion("pn33 not in", values, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33Between(String value1, String value2) {
            addCriterion("pn33 between", value1, value2, "pn33");
            return (Criteria) this;
        }

        public Criteria andPn33NotBetween(String value1, String value2) {
            addCriterion("pn33 not between", value1, value2, "pn33");
            return (Criteria) this;
        }

        public Criteria andRate33IsNull() {
            addCriterion("rate33 is null");
            return (Criteria) this;
        }

        public Criteria andRate33IsNotNull() {
            addCriterion("rate33 is not null");
            return (Criteria) this;
        }

        public Criteria andRate33EqualTo(String value) {
            addCriterion("rate33 =", value, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33NotEqualTo(String value) {
            addCriterion("rate33 <>", value, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33GreaterThan(String value) {
            addCriterion("rate33 >", value, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33GreaterThanOrEqualTo(String value) {
            addCriterion("rate33 >=", value, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33LessThan(String value) {
            addCriterion("rate33 <", value, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33LessThanOrEqualTo(String value) {
            addCriterion("rate33 <=", value, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33Like(String value) {
            addCriterion("rate33 like", value, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33NotLike(String value) {
            addCriterion("rate33 not like", value, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33In(List<String> values) {
            addCriterion("rate33 in", values, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33NotIn(List<String> values) {
            addCriterion("rate33 not in", values, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33Between(String value1, String value2) {
            addCriterion("rate33 between", value1, value2, "rate33");
            return (Criteria) this;
        }

        public Criteria andRate33NotBetween(String value1, String value2) {
            addCriterion("rate33 not between", value1, value2, "rate33");
            return (Criteria) this;
        }

        public Criteria andSendtime33IsNull() {
            addCriterion("sendTime33 is null");
            return (Criteria) this;
        }

        public Criteria andSendtime33IsNotNull() {
            addCriterion("sendTime33 is not null");
            return (Criteria) this;
        }

        public Criteria andSendtime33EqualTo(String value) {
            addCriterion("sendTime33 =", value, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33NotEqualTo(String value) {
            addCriterion("sendTime33 <>", value, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33GreaterThan(String value) {
            addCriterion("sendTime33 >", value, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33GreaterThanOrEqualTo(String value) {
            addCriterion("sendTime33 >=", value, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33LessThan(String value) {
            addCriterion("sendTime33 <", value, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33LessThanOrEqualTo(String value) {
            addCriterion("sendTime33 <=", value, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33Like(String value) {
            addCriterion("sendTime33 like", value, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33NotLike(String value) {
            addCriterion("sendTime33 not like", value, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33In(List<String> values) {
            addCriterion("sendTime33 in", values, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33NotIn(List<String> values) {
            addCriterion("sendTime33 not in", values, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33Between(String value1, String value2) {
            addCriterion("sendTime33 between", value1, value2, "sendtime33");
            return (Criteria) this;
        }

        public Criteria andSendtime33NotBetween(String value1, String value2) {
            addCriterion("sendTime33 not between", value1, value2, "sendtime33");
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

        public Criteria andId34IsNull() {
            addCriterion("id34 is null");
            return (Criteria) this;
        }

        public Criteria andId34IsNotNull() {
            addCriterion("id34 is not null");
            return (Criteria) this;
        }

        public Criteria andId34EqualTo(String value) {
            addCriterion("id34 =", value, "id34");
            return (Criteria) this;
        }

        public Criteria andId34NotEqualTo(String value) {
            addCriterion("id34 <>", value, "id34");
            return (Criteria) this;
        }

        public Criteria andId34GreaterThan(String value) {
            addCriterion("id34 >", value, "id34");
            return (Criteria) this;
        }

        public Criteria andId34GreaterThanOrEqualTo(String value) {
            addCriterion("id34 >=", value, "id34");
            return (Criteria) this;
        }

        public Criteria andId34LessThan(String value) {
            addCriterion("id34 <", value, "id34");
            return (Criteria) this;
        }

        public Criteria andId34LessThanOrEqualTo(String value) {
            addCriterion("id34 <=", value, "id34");
            return (Criteria) this;
        }

        public Criteria andId34Like(String value) {
            addCriterion("id34 like", value, "id34");
            return (Criteria) this;
        }

        public Criteria andId34NotLike(String value) {
            addCriterion("id34 not like", value, "id34");
            return (Criteria) this;
        }

        public Criteria andId34In(List<String> values) {
            addCriterion("id34 in", values, "id34");
            return (Criteria) this;
        }

        public Criteria andId34NotIn(List<String> values) {
            addCriterion("id34 not in", values, "id34");
            return (Criteria) this;
        }

        public Criteria andId34Between(String value1, String value2) {
            addCriterion("id34 between", value1, value2, "id34");
            return (Criteria) this;
        }

        public Criteria andId34NotBetween(String value1, String value2) {
            addCriterion("id34 not between", value1, value2, "id34");
            return (Criteria) this;
        }

        public Criteria andAreaId34IsNull() {
            addCriterion("area_id34 is null");
            return (Criteria) this;
        }

        public Criteria andAreaId34IsNotNull() {
            addCriterion("area_id34 is not null");
            return (Criteria) this;
        }

        public Criteria andAreaId34EqualTo(String value) {
            addCriterion("area_id34 =", value, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34NotEqualTo(String value) {
            addCriterion("area_id34 <>", value, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34GreaterThan(String value) {
            addCriterion("area_id34 >", value, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34GreaterThanOrEqualTo(String value) {
            addCriterion("area_id34 >=", value, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34LessThan(String value) {
            addCriterion("area_id34 <", value, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34LessThanOrEqualTo(String value) {
            addCriterion("area_id34 <=", value, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34Like(String value) {
            addCriterion("area_id34 like", value, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34NotLike(String value) {
            addCriterion("area_id34 not like", value, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34In(List<String> values) {
            addCriterion("area_id34 in", values, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34NotIn(List<String> values) {
            addCriterion("area_id34 not in", values, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34Between(String value1, String value2) {
            addCriterion("area_id34 between", value1, value2, "areaId34");
            return (Criteria) this;
        }

        public Criteria andAreaId34NotBetween(String value1, String value2) {
            addCriterion("area_id34 not between", value1, value2, "areaId34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34IsNull() {
            addCriterion("clientOperationTime34 is null");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34IsNotNull() {
            addCriterion("clientOperationTime34 is not null");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34EqualTo(String value) {
            addCriterion("clientOperationTime34 =", value, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34NotEqualTo(String value) {
            addCriterion("clientOperationTime34 <>", value, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34GreaterThan(String value) {
            addCriterion("clientOperationTime34 >", value, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34GreaterThanOrEqualTo(String value) {
            addCriterion("clientOperationTime34 >=", value, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34LessThan(String value) {
            addCriterion("clientOperationTime34 <", value, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34LessThanOrEqualTo(String value) {
            addCriterion("clientOperationTime34 <=", value, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34Like(String value) {
            addCriterion("clientOperationTime34 like", value, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34NotLike(String value) {
            addCriterion("clientOperationTime34 not like", value, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34In(List<String> values) {
            addCriterion("clientOperationTime34 in", values, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34NotIn(List<String> values) {
            addCriterion("clientOperationTime34 not in", values, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34Between(String value1, String value2) {
            addCriterion("clientOperationTime34 between", value1, value2, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andClientoperationtime34NotBetween(String value1, String value2) {
            addCriterion("clientOperationTime34 not between", value1, value2, "clientoperationtime34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34IsNull() {
            addCriterion("concentrator_id34 is null");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34IsNotNull() {
            addCriterion("concentrator_id34 is not null");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34EqualTo(String value) {
            addCriterion("concentrator_id34 =", value, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34NotEqualTo(String value) {
            addCriterion("concentrator_id34 <>", value, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34GreaterThan(String value) {
            addCriterion("concentrator_id34 >", value, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34GreaterThanOrEqualTo(String value) {
            addCriterion("concentrator_id34 >=", value, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34LessThan(String value) {
            addCriterion("concentrator_id34 <", value, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34LessThanOrEqualTo(String value) {
            addCriterion("concentrator_id34 <=", value, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34Like(String value) {
            addCriterion("concentrator_id34 like", value, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34NotLike(String value) {
            addCriterion("concentrator_id34 not like", value, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34In(List<String> values) {
            addCriterion("concentrator_id34 in", values, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34NotIn(List<String> values) {
            addCriterion("concentrator_id34 not in", values, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34Between(String value1, String value2) {
            addCriterion("concentrator_id34 between", value1, value2, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andConcentratorId34NotBetween(String value1, String value2) {
            addCriterion("concentrator_id34 not between", value1, value2, "concentratorId34");
            return (Criteria) this;
        }

        public Criteria andPn34IsNull() {
            addCriterion("pn34 is null");
            return (Criteria) this;
        }

        public Criteria andPn34IsNotNull() {
            addCriterion("pn34 is not null");
            return (Criteria) this;
        }

        public Criteria andPn34EqualTo(String value) {
            addCriterion("pn34 =", value, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34NotEqualTo(String value) {
            addCriterion("pn34 <>", value, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34GreaterThan(String value) {
            addCriterion("pn34 >", value, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34GreaterThanOrEqualTo(String value) {
            addCriterion("pn34 >=", value, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34LessThan(String value) {
            addCriterion("pn34 <", value, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34LessThanOrEqualTo(String value) {
            addCriterion("pn34 <=", value, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34Like(String value) {
            addCriterion("pn34 like", value, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34NotLike(String value) {
            addCriterion("pn34 not like", value, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34In(List<String> values) {
            addCriterion("pn34 in", values, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34NotIn(List<String> values) {
            addCriterion("pn34 not in", values, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34Between(String value1, String value2) {
            addCriterion("pn34 between", value1, value2, "pn34");
            return (Criteria) this;
        }

        public Criteria andPn34NotBetween(String value1, String value2) {
            addCriterion("pn34 not between", value1, value2, "pn34");
            return (Criteria) this;
        }

        public Criteria andRate34IsNull() {
            addCriterion("rate34 is null");
            return (Criteria) this;
        }

        public Criteria andRate34IsNotNull() {
            addCriterion("rate34 is not null");
            return (Criteria) this;
        }

        public Criteria andRate34EqualTo(String value) {
            addCriterion("rate34 =", value, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34NotEqualTo(String value) {
            addCriterion("rate34 <>", value, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34GreaterThan(String value) {
            addCriterion("rate34 >", value, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34GreaterThanOrEqualTo(String value) {
            addCriterion("rate34 >=", value, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34LessThan(String value) {
            addCriterion("rate34 <", value, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34LessThanOrEqualTo(String value) {
            addCriterion("rate34 <=", value, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34Like(String value) {
            addCriterion("rate34 like", value, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34NotLike(String value) {
            addCriterion("rate34 not like", value, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34In(List<String> values) {
            addCriterion("rate34 in", values, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34NotIn(List<String> values) {
            addCriterion("rate34 not in", values, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34Between(String value1, String value2) {
            addCriterion("rate34 between", value1, value2, "rate34");
            return (Criteria) this;
        }

        public Criteria andRate34NotBetween(String value1, String value2) {
            addCriterion("rate34 not between", value1, value2, "rate34");
            return (Criteria) this;
        }

        public Criteria andSendtime34IsNull() {
            addCriterion("sendTime34 is null");
            return (Criteria) this;
        }

        public Criteria andSendtime34IsNotNull() {
            addCriterion("sendTime34 is not null");
            return (Criteria) this;
        }

        public Criteria andSendtime34EqualTo(String value) {
            addCriterion("sendTime34 =", value, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34NotEqualTo(String value) {
            addCriterion("sendTime34 <>", value, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34GreaterThan(String value) {
            addCriterion("sendTime34 >", value, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34GreaterThanOrEqualTo(String value) {
            addCriterion("sendTime34 >=", value, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34LessThan(String value) {
            addCriterion("sendTime34 <", value, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34LessThanOrEqualTo(String value) {
            addCriterion("sendTime34 <=", value, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34Like(String value) {
            addCriterion("sendTime34 like", value, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34NotLike(String value) {
            addCriterion("sendTime34 not like", value, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34In(List<String> values) {
            addCriterion("sendTime34 in", values, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34NotIn(List<String> values) {
            addCriterion("sendTime34 not in", values, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34Between(String value1, String value2) {
            addCriterion("sendTime34 between", value1, value2, "sendtime34");
            return (Criteria) this;
        }

        public Criteria andSendtime34NotBetween(String value1, String value2) {
            addCriterion("sendTime34 not between", value1, value2, "sendtime34");
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