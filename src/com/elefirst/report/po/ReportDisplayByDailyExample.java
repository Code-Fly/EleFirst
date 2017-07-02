package com.elefirst.report.po;

import java.util.ArrayList;
import java.util.List;

public class ReportDisplayByDailyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ReportDisplayByDailyExample() {
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

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andP00IsNull() {
            addCriterion("p_00 is null");
            return (Criteria) this;
        }

        public Criteria andP00IsNotNull() {
            addCriterion("p_00 is not null");
            return (Criteria) this;
        }

        public Criteria andP00EqualTo(String value) {
            addCriterion("p_00 =", value, "p00");
            return (Criteria) this;
        }

        public Criteria andP00NotEqualTo(String value) {
            addCriterion("p_00 <>", value, "p00");
            return (Criteria) this;
        }

        public Criteria andP00GreaterThan(String value) {
            addCriterion("p_00 >", value, "p00");
            return (Criteria) this;
        }

        public Criteria andP00GreaterThanOrEqualTo(String value) {
            addCriterion("p_00 >=", value, "p00");
            return (Criteria) this;
        }

        public Criteria andP00LessThan(String value) {
            addCriterion("p_00 <", value, "p00");
            return (Criteria) this;
        }

        public Criteria andP00LessThanOrEqualTo(String value) {
            addCriterion("p_00 <=", value, "p00");
            return (Criteria) this;
        }

        public Criteria andP00Like(String value) {
            addCriterion("p_00 like", value, "p00");
            return (Criteria) this;
        }

        public Criteria andP00NotLike(String value) {
            addCriterion("p_00 not like", value, "p00");
            return (Criteria) this;
        }

        public Criteria andP00In(List<String> values) {
            addCriterion("p_00 in", values, "p00");
            return (Criteria) this;
        }

        public Criteria andP00NotIn(List<String> values) {
            addCriterion("p_00 not in", values, "p00");
            return (Criteria) this;
        }

        public Criteria andP00Between(String value1, String value2) {
            addCriterion("p_00 between", value1, value2, "p00");
            return (Criteria) this;
        }

        public Criteria andP00NotBetween(String value1, String value2) {
            addCriterion("p_00 not between", value1, value2, "p00");
            return (Criteria) this;
        }

        public Criteria andP01IsNull() {
            addCriterion("p_01 is null");
            return (Criteria) this;
        }

        public Criteria andP01IsNotNull() {
            addCriterion("p_01 is not null");
            return (Criteria) this;
        }

        public Criteria andP01EqualTo(String value) {
            addCriterion("p_01 =", value, "p01");
            return (Criteria) this;
        }

        public Criteria andP01NotEqualTo(String value) {
            addCriterion("p_01 <>", value, "p01");
            return (Criteria) this;
        }

        public Criteria andP01GreaterThan(String value) {
            addCriterion("p_01 >", value, "p01");
            return (Criteria) this;
        }

        public Criteria andP01GreaterThanOrEqualTo(String value) {
            addCriterion("p_01 >=", value, "p01");
            return (Criteria) this;
        }

        public Criteria andP01LessThan(String value) {
            addCriterion("p_01 <", value, "p01");
            return (Criteria) this;
        }

        public Criteria andP01LessThanOrEqualTo(String value) {
            addCriterion("p_01 <=", value, "p01");
            return (Criteria) this;
        }

        public Criteria andP01Like(String value) {
            addCriterion("p_01 like", value, "p01");
            return (Criteria) this;
        }

        public Criteria andP01NotLike(String value) {
            addCriterion("p_01 not like", value, "p01");
            return (Criteria) this;
        }

        public Criteria andP01In(List<String> values) {
            addCriterion("p_01 in", values, "p01");
            return (Criteria) this;
        }

        public Criteria andP01NotIn(List<String> values) {
            addCriterion("p_01 not in", values, "p01");
            return (Criteria) this;
        }

        public Criteria andP01Between(String value1, String value2) {
            addCriterion("p_01 between", value1, value2, "p01");
            return (Criteria) this;
        }

        public Criteria andP01NotBetween(String value1, String value2) {
            addCriterion("p_01 not between", value1, value2, "p01");
            return (Criteria) this;
        }

        public Criteria andP02IsNull() {
            addCriterion("p_02 is null");
            return (Criteria) this;
        }

        public Criteria andP02IsNotNull() {
            addCriterion("p_02 is not null");
            return (Criteria) this;
        }

        public Criteria andP02EqualTo(String value) {
            addCriterion("p_02 =", value, "p02");
            return (Criteria) this;
        }

        public Criteria andP02NotEqualTo(String value) {
            addCriterion("p_02 <>", value, "p02");
            return (Criteria) this;
        }

        public Criteria andP02GreaterThan(String value) {
            addCriterion("p_02 >", value, "p02");
            return (Criteria) this;
        }

        public Criteria andP02GreaterThanOrEqualTo(String value) {
            addCriterion("p_02 >=", value, "p02");
            return (Criteria) this;
        }

        public Criteria andP02LessThan(String value) {
            addCriterion("p_02 <", value, "p02");
            return (Criteria) this;
        }

        public Criteria andP02LessThanOrEqualTo(String value) {
            addCriterion("p_02 <=", value, "p02");
            return (Criteria) this;
        }

        public Criteria andP02Like(String value) {
            addCriterion("p_02 like", value, "p02");
            return (Criteria) this;
        }

        public Criteria andP02NotLike(String value) {
            addCriterion("p_02 not like", value, "p02");
            return (Criteria) this;
        }

        public Criteria andP02In(List<String> values) {
            addCriterion("p_02 in", values, "p02");
            return (Criteria) this;
        }

        public Criteria andP02NotIn(List<String> values) {
            addCriterion("p_02 not in", values, "p02");
            return (Criteria) this;
        }

        public Criteria andP02Between(String value1, String value2) {
            addCriterion("p_02 between", value1, value2, "p02");
            return (Criteria) this;
        }

        public Criteria andP02NotBetween(String value1, String value2) {
            addCriterion("p_02 not between", value1, value2, "p02");
            return (Criteria) this;
        }

        public Criteria andP03IsNull() {
            addCriterion("p_03 is null");
            return (Criteria) this;
        }

        public Criteria andP03IsNotNull() {
            addCriterion("p_03 is not null");
            return (Criteria) this;
        }

        public Criteria andP03EqualTo(String value) {
            addCriterion("p_03 =", value, "p03");
            return (Criteria) this;
        }

        public Criteria andP03NotEqualTo(String value) {
            addCriterion("p_03 <>", value, "p03");
            return (Criteria) this;
        }

        public Criteria andP03GreaterThan(String value) {
            addCriterion("p_03 >", value, "p03");
            return (Criteria) this;
        }

        public Criteria andP03GreaterThanOrEqualTo(String value) {
            addCriterion("p_03 >=", value, "p03");
            return (Criteria) this;
        }

        public Criteria andP03LessThan(String value) {
            addCriterion("p_03 <", value, "p03");
            return (Criteria) this;
        }

        public Criteria andP03LessThanOrEqualTo(String value) {
            addCriterion("p_03 <=", value, "p03");
            return (Criteria) this;
        }

        public Criteria andP03Like(String value) {
            addCriterion("p_03 like", value, "p03");
            return (Criteria) this;
        }

        public Criteria andP03NotLike(String value) {
            addCriterion("p_03 not like", value, "p03");
            return (Criteria) this;
        }

        public Criteria andP03In(List<String> values) {
            addCriterion("p_03 in", values, "p03");
            return (Criteria) this;
        }

        public Criteria andP03NotIn(List<String> values) {
            addCriterion("p_03 not in", values, "p03");
            return (Criteria) this;
        }

        public Criteria andP03Between(String value1, String value2) {
            addCriterion("p_03 between", value1, value2, "p03");
            return (Criteria) this;
        }

        public Criteria andP03NotBetween(String value1, String value2) {
            addCriterion("p_03 not between", value1, value2, "p03");
            return (Criteria) this;
        }

        public Criteria andP04IsNull() {
            addCriterion("p_04 is null");
            return (Criteria) this;
        }

        public Criteria andP04IsNotNull() {
            addCriterion("p_04 is not null");
            return (Criteria) this;
        }

        public Criteria andP04EqualTo(String value) {
            addCriterion("p_04 =", value, "p04");
            return (Criteria) this;
        }

        public Criteria andP04NotEqualTo(String value) {
            addCriterion("p_04 <>", value, "p04");
            return (Criteria) this;
        }

        public Criteria andP04GreaterThan(String value) {
            addCriterion("p_04 >", value, "p04");
            return (Criteria) this;
        }

        public Criteria andP04GreaterThanOrEqualTo(String value) {
            addCriterion("p_04 >=", value, "p04");
            return (Criteria) this;
        }

        public Criteria andP04LessThan(String value) {
            addCriterion("p_04 <", value, "p04");
            return (Criteria) this;
        }

        public Criteria andP04LessThanOrEqualTo(String value) {
            addCriterion("p_04 <=", value, "p04");
            return (Criteria) this;
        }

        public Criteria andP04Like(String value) {
            addCriterion("p_04 like", value, "p04");
            return (Criteria) this;
        }

        public Criteria andP04NotLike(String value) {
            addCriterion("p_04 not like", value, "p04");
            return (Criteria) this;
        }

        public Criteria andP04In(List<String> values) {
            addCriterion("p_04 in", values, "p04");
            return (Criteria) this;
        }

        public Criteria andP04NotIn(List<String> values) {
            addCriterion("p_04 not in", values, "p04");
            return (Criteria) this;
        }

        public Criteria andP04Between(String value1, String value2) {
            addCriterion("p_04 between", value1, value2, "p04");
            return (Criteria) this;
        }

        public Criteria andP04NotBetween(String value1, String value2) {
            addCriterion("p_04 not between", value1, value2, "p04");
            return (Criteria) this;
        }

        public Criteria andP05IsNull() {
            addCriterion("p_05 is null");
            return (Criteria) this;
        }

        public Criteria andP05IsNotNull() {
            addCriterion("p_05 is not null");
            return (Criteria) this;
        }

        public Criteria andP05EqualTo(String value) {
            addCriterion("p_05 =", value, "p05");
            return (Criteria) this;
        }

        public Criteria andP05NotEqualTo(String value) {
            addCriterion("p_05 <>", value, "p05");
            return (Criteria) this;
        }

        public Criteria andP05GreaterThan(String value) {
            addCriterion("p_05 >", value, "p05");
            return (Criteria) this;
        }

        public Criteria andP05GreaterThanOrEqualTo(String value) {
            addCriterion("p_05 >=", value, "p05");
            return (Criteria) this;
        }

        public Criteria andP05LessThan(String value) {
            addCriterion("p_05 <", value, "p05");
            return (Criteria) this;
        }

        public Criteria andP05LessThanOrEqualTo(String value) {
            addCriterion("p_05 <=", value, "p05");
            return (Criteria) this;
        }

        public Criteria andP05Like(String value) {
            addCriterion("p_05 like", value, "p05");
            return (Criteria) this;
        }

        public Criteria andP05NotLike(String value) {
            addCriterion("p_05 not like", value, "p05");
            return (Criteria) this;
        }

        public Criteria andP05In(List<String> values) {
            addCriterion("p_05 in", values, "p05");
            return (Criteria) this;
        }

        public Criteria andP05NotIn(List<String> values) {
            addCriterion("p_05 not in", values, "p05");
            return (Criteria) this;
        }

        public Criteria andP05Between(String value1, String value2) {
            addCriterion("p_05 between", value1, value2, "p05");
            return (Criteria) this;
        }

        public Criteria andP05NotBetween(String value1, String value2) {
            addCriterion("p_05 not between", value1, value2, "p05");
            return (Criteria) this;
        }

        public Criteria andP06IsNull() {
            addCriterion("p_06 is null");
            return (Criteria) this;
        }

        public Criteria andP06IsNotNull() {
            addCriterion("p_06 is not null");
            return (Criteria) this;
        }

        public Criteria andP06EqualTo(String value) {
            addCriterion("p_06 =", value, "p06");
            return (Criteria) this;
        }

        public Criteria andP06NotEqualTo(String value) {
            addCriterion("p_06 <>", value, "p06");
            return (Criteria) this;
        }

        public Criteria andP06GreaterThan(String value) {
            addCriterion("p_06 >", value, "p06");
            return (Criteria) this;
        }

        public Criteria andP06GreaterThanOrEqualTo(String value) {
            addCriterion("p_06 >=", value, "p06");
            return (Criteria) this;
        }

        public Criteria andP06LessThan(String value) {
            addCriterion("p_06 <", value, "p06");
            return (Criteria) this;
        }

        public Criteria andP06LessThanOrEqualTo(String value) {
            addCriterion("p_06 <=", value, "p06");
            return (Criteria) this;
        }

        public Criteria andP06Like(String value) {
            addCriterion("p_06 like", value, "p06");
            return (Criteria) this;
        }

        public Criteria andP06NotLike(String value) {
            addCriterion("p_06 not like", value, "p06");
            return (Criteria) this;
        }

        public Criteria andP06In(List<String> values) {
            addCriterion("p_06 in", values, "p06");
            return (Criteria) this;
        }

        public Criteria andP06NotIn(List<String> values) {
            addCriterion("p_06 not in", values, "p06");
            return (Criteria) this;
        }

        public Criteria andP06Between(String value1, String value2) {
            addCriterion("p_06 between", value1, value2, "p06");
            return (Criteria) this;
        }

        public Criteria andP06NotBetween(String value1, String value2) {
            addCriterion("p_06 not between", value1, value2, "p06");
            return (Criteria) this;
        }

        public Criteria andP07IsNull() {
            addCriterion("p_07 is null");
            return (Criteria) this;
        }

        public Criteria andP07IsNotNull() {
            addCriterion("p_07 is not null");
            return (Criteria) this;
        }

        public Criteria andP07EqualTo(String value) {
            addCriterion("p_07 =", value, "p07");
            return (Criteria) this;
        }

        public Criteria andP07NotEqualTo(String value) {
            addCriterion("p_07 <>", value, "p07");
            return (Criteria) this;
        }

        public Criteria andP07GreaterThan(String value) {
            addCriterion("p_07 >", value, "p07");
            return (Criteria) this;
        }

        public Criteria andP07GreaterThanOrEqualTo(String value) {
            addCriterion("p_07 >=", value, "p07");
            return (Criteria) this;
        }

        public Criteria andP07LessThan(String value) {
            addCriterion("p_07 <", value, "p07");
            return (Criteria) this;
        }

        public Criteria andP07LessThanOrEqualTo(String value) {
            addCriterion("p_07 <=", value, "p07");
            return (Criteria) this;
        }

        public Criteria andP07Like(String value) {
            addCriterion("p_07 like", value, "p07");
            return (Criteria) this;
        }

        public Criteria andP07NotLike(String value) {
            addCriterion("p_07 not like", value, "p07");
            return (Criteria) this;
        }

        public Criteria andP07In(List<String> values) {
            addCriterion("p_07 in", values, "p07");
            return (Criteria) this;
        }

        public Criteria andP07NotIn(List<String> values) {
            addCriterion("p_07 not in", values, "p07");
            return (Criteria) this;
        }

        public Criteria andP07Between(String value1, String value2) {
            addCriterion("p_07 between", value1, value2, "p07");
            return (Criteria) this;
        }

        public Criteria andP07NotBetween(String value1, String value2) {
            addCriterion("p_07 not between", value1, value2, "p07");
            return (Criteria) this;
        }

        public Criteria andP08IsNull() {
            addCriterion("p_08 is null");
            return (Criteria) this;
        }

        public Criteria andP08IsNotNull() {
            addCriterion("p_08 is not null");
            return (Criteria) this;
        }

        public Criteria andP08EqualTo(String value) {
            addCriterion("p_08 =", value, "p08");
            return (Criteria) this;
        }

        public Criteria andP08NotEqualTo(String value) {
            addCriterion("p_08 <>", value, "p08");
            return (Criteria) this;
        }

        public Criteria andP08GreaterThan(String value) {
            addCriterion("p_08 >", value, "p08");
            return (Criteria) this;
        }

        public Criteria andP08GreaterThanOrEqualTo(String value) {
            addCriterion("p_08 >=", value, "p08");
            return (Criteria) this;
        }

        public Criteria andP08LessThan(String value) {
            addCriterion("p_08 <", value, "p08");
            return (Criteria) this;
        }

        public Criteria andP08LessThanOrEqualTo(String value) {
            addCriterion("p_08 <=", value, "p08");
            return (Criteria) this;
        }

        public Criteria andP08Like(String value) {
            addCriterion("p_08 like", value, "p08");
            return (Criteria) this;
        }

        public Criteria andP08NotLike(String value) {
            addCriterion("p_08 not like", value, "p08");
            return (Criteria) this;
        }

        public Criteria andP08In(List<String> values) {
            addCriterion("p_08 in", values, "p08");
            return (Criteria) this;
        }

        public Criteria andP08NotIn(List<String> values) {
            addCriterion("p_08 not in", values, "p08");
            return (Criteria) this;
        }

        public Criteria andP08Between(String value1, String value2) {
            addCriterion("p_08 between", value1, value2, "p08");
            return (Criteria) this;
        }

        public Criteria andP08NotBetween(String value1, String value2) {
            addCriterion("p_08 not between", value1, value2, "p08");
            return (Criteria) this;
        }

        public Criteria andP09IsNull() {
            addCriterion("p_09 is null");
            return (Criteria) this;
        }

        public Criteria andP09IsNotNull() {
            addCriterion("p_09 is not null");
            return (Criteria) this;
        }

        public Criteria andP09EqualTo(String value) {
            addCriterion("p_09 =", value, "p09");
            return (Criteria) this;
        }

        public Criteria andP09NotEqualTo(String value) {
            addCriterion("p_09 <>", value, "p09");
            return (Criteria) this;
        }

        public Criteria andP09GreaterThan(String value) {
            addCriterion("p_09 >", value, "p09");
            return (Criteria) this;
        }

        public Criteria andP09GreaterThanOrEqualTo(String value) {
            addCriterion("p_09 >=", value, "p09");
            return (Criteria) this;
        }

        public Criteria andP09LessThan(String value) {
            addCriterion("p_09 <", value, "p09");
            return (Criteria) this;
        }

        public Criteria andP09LessThanOrEqualTo(String value) {
            addCriterion("p_09 <=", value, "p09");
            return (Criteria) this;
        }

        public Criteria andP09Like(String value) {
            addCriterion("p_09 like", value, "p09");
            return (Criteria) this;
        }

        public Criteria andP09NotLike(String value) {
            addCriterion("p_09 not like", value, "p09");
            return (Criteria) this;
        }

        public Criteria andP09In(List<String> values) {
            addCriterion("p_09 in", values, "p09");
            return (Criteria) this;
        }

        public Criteria andP09NotIn(List<String> values) {
            addCriterion("p_09 not in", values, "p09");
            return (Criteria) this;
        }

        public Criteria andP09Between(String value1, String value2) {
            addCriterion("p_09 between", value1, value2, "p09");
            return (Criteria) this;
        }

        public Criteria andP09NotBetween(String value1, String value2) {
            addCriterion("p_09 not between", value1, value2, "p09");
            return (Criteria) this;
        }

        public Criteria andP10IsNull() {
            addCriterion("p_10 is null");
            return (Criteria) this;
        }

        public Criteria andP10IsNotNull() {
            addCriterion("p_10 is not null");
            return (Criteria) this;
        }

        public Criteria andP10EqualTo(String value) {
            addCriterion("p_10 =", value, "p10");
            return (Criteria) this;
        }

        public Criteria andP10NotEqualTo(String value) {
            addCriterion("p_10 <>", value, "p10");
            return (Criteria) this;
        }

        public Criteria andP10GreaterThan(String value) {
            addCriterion("p_10 >", value, "p10");
            return (Criteria) this;
        }

        public Criteria andP10GreaterThanOrEqualTo(String value) {
            addCriterion("p_10 >=", value, "p10");
            return (Criteria) this;
        }

        public Criteria andP10LessThan(String value) {
            addCriterion("p_10 <", value, "p10");
            return (Criteria) this;
        }

        public Criteria andP10LessThanOrEqualTo(String value) {
            addCriterion("p_10 <=", value, "p10");
            return (Criteria) this;
        }

        public Criteria andP10Like(String value) {
            addCriterion("p_10 like", value, "p10");
            return (Criteria) this;
        }

        public Criteria andP10NotLike(String value) {
            addCriterion("p_10 not like", value, "p10");
            return (Criteria) this;
        }

        public Criteria andP10In(List<String> values) {
            addCriterion("p_10 in", values, "p10");
            return (Criteria) this;
        }

        public Criteria andP10NotIn(List<String> values) {
            addCriterion("p_10 not in", values, "p10");
            return (Criteria) this;
        }

        public Criteria andP10Between(String value1, String value2) {
            addCriterion("p_10 between", value1, value2, "p10");
            return (Criteria) this;
        }

        public Criteria andP10NotBetween(String value1, String value2) {
            addCriterion("p_10 not between", value1, value2, "p10");
            return (Criteria) this;
        }

        public Criteria andP11IsNull() {
            addCriterion("p_11 is null");
            return (Criteria) this;
        }

        public Criteria andP11IsNotNull() {
            addCriterion("p_11 is not null");
            return (Criteria) this;
        }

        public Criteria andP11EqualTo(String value) {
            addCriterion("p_11 =", value, "p11");
            return (Criteria) this;
        }

        public Criteria andP11NotEqualTo(String value) {
            addCriterion("p_11 <>", value, "p11");
            return (Criteria) this;
        }

        public Criteria andP11GreaterThan(String value) {
            addCriterion("p_11 >", value, "p11");
            return (Criteria) this;
        }

        public Criteria andP11GreaterThanOrEqualTo(String value) {
            addCriterion("p_11 >=", value, "p11");
            return (Criteria) this;
        }

        public Criteria andP11LessThan(String value) {
            addCriterion("p_11 <", value, "p11");
            return (Criteria) this;
        }

        public Criteria andP11LessThanOrEqualTo(String value) {
            addCriterion("p_11 <=", value, "p11");
            return (Criteria) this;
        }

        public Criteria andP11Like(String value) {
            addCriterion("p_11 like", value, "p11");
            return (Criteria) this;
        }

        public Criteria andP11NotLike(String value) {
            addCriterion("p_11 not like", value, "p11");
            return (Criteria) this;
        }

        public Criteria andP11In(List<String> values) {
            addCriterion("p_11 in", values, "p11");
            return (Criteria) this;
        }

        public Criteria andP11NotIn(List<String> values) {
            addCriterion("p_11 not in", values, "p11");
            return (Criteria) this;
        }

        public Criteria andP11Between(String value1, String value2) {
            addCriterion("p_11 between", value1, value2, "p11");
            return (Criteria) this;
        }

        public Criteria andP11NotBetween(String value1, String value2) {
            addCriterion("p_11 not between", value1, value2, "p11");
            return (Criteria) this;
        }

        public Criteria andP12IsNull() {
            addCriterion("p_12 is null");
            return (Criteria) this;
        }

        public Criteria andP12IsNotNull() {
            addCriterion("p_12 is not null");
            return (Criteria) this;
        }

        public Criteria andP12EqualTo(String value) {
            addCriterion("p_12 =", value, "p12");
            return (Criteria) this;
        }

        public Criteria andP12NotEqualTo(String value) {
            addCriterion("p_12 <>", value, "p12");
            return (Criteria) this;
        }

        public Criteria andP12GreaterThan(String value) {
            addCriterion("p_12 >", value, "p12");
            return (Criteria) this;
        }

        public Criteria andP12GreaterThanOrEqualTo(String value) {
            addCriterion("p_12 >=", value, "p12");
            return (Criteria) this;
        }

        public Criteria andP12LessThan(String value) {
            addCriterion("p_12 <", value, "p12");
            return (Criteria) this;
        }

        public Criteria andP12LessThanOrEqualTo(String value) {
            addCriterion("p_12 <=", value, "p12");
            return (Criteria) this;
        }

        public Criteria andP12Like(String value) {
            addCriterion("p_12 like", value, "p12");
            return (Criteria) this;
        }

        public Criteria andP12NotLike(String value) {
            addCriterion("p_12 not like", value, "p12");
            return (Criteria) this;
        }

        public Criteria andP12In(List<String> values) {
            addCriterion("p_12 in", values, "p12");
            return (Criteria) this;
        }

        public Criteria andP12NotIn(List<String> values) {
            addCriterion("p_12 not in", values, "p12");
            return (Criteria) this;
        }

        public Criteria andP12Between(String value1, String value2) {
            addCriterion("p_12 between", value1, value2, "p12");
            return (Criteria) this;
        }

        public Criteria andP12NotBetween(String value1, String value2) {
            addCriterion("p_12 not between", value1, value2, "p12");
            return (Criteria) this;
        }

        public Criteria andP13IsNull() {
            addCriterion("p_13 is null");
            return (Criteria) this;
        }

        public Criteria andP13IsNotNull() {
            addCriterion("p_13 is not null");
            return (Criteria) this;
        }

        public Criteria andP13EqualTo(String value) {
            addCriterion("p_13 =", value, "p13");
            return (Criteria) this;
        }

        public Criteria andP13NotEqualTo(String value) {
            addCriterion("p_13 <>", value, "p13");
            return (Criteria) this;
        }

        public Criteria andP13GreaterThan(String value) {
            addCriterion("p_13 >", value, "p13");
            return (Criteria) this;
        }

        public Criteria andP13GreaterThanOrEqualTo(String value) {
            addCriterion("p_13 >=", value, "p13");
            return (Criteria) this;
        }

        public Criteria andP13LessThan(String value) {
            addCriterion("p_13 <", value, "p13");
            return (Criteria) this;
        }

        public Criteria andP13LessThanOrEqualTo(String value) {
            addCriterion("p_13 <=", value, "p13");
            return (Criteria) this;
        }

        public Criteria andP13Like(String value) {
            addCriterion("p_13 like", value, "p13");
            return (Criteria) this;
        }

        public Criteria andP13NotLike(String value) {
            addCriterion("p_13 not like", value, "p13");
            return (Criteria) this;
        }

        public Criteria andP13In(List<String> values) {
            addCriterion("p_13 in", values, "p13");
            return (Criteria) this;
        }

        public Criteria andP13NotIn(List<String> values) {
            addCriterion("p_13 not in", values, "p13");
            return (Criteria) this;
        }

        public Criteria andP13Between(String value1, String value2) {
            addCriterion("p_13 between", value1, value2, "p13");
            return (Criteria) this;
        }

        public Criteria andP13NotBetween(String value1, String value2) {
            addCriterion("p_13 not between", value1, value2, "p13");
            return (Criteria) this;
        }

        public Criteria andP14IsNull() {
            addCriterion("p_14 is null");
            return (Criteria) this;
        }

        public Criteria andP14IsNotNull() {
            addCriterion("p_14 is not null");
            return (Criteria) this;
        }

        public Criteria andP14EqualTo(String value) {
            addCriterion("p_14 =", value, "p14");
            return (Criteria) this;
        }

        public Criteria andP14NotEqualTo(String value) {
            addCriterion("p_14 <>", value, "p14");
            return (Criteria) this;
        }

        public Criteria andP14GreaterThan(String value) {
            addCriterion("p_14 >", value, "p14");
            return (Criteria) this;
        }

        public Criteria andP14GreaterThanOrEqualTo(String value) {
            addCriterion("p_14 >=", value, "p14");
            return (Criteria) this;
        }

        public Criteria andP14LessThan(String value) {
            addCriterion("p_14 <", value, "p14");
            return (Criteria) this;
        }

        public Criteria andP14LessThanOrEqualTo(String value) {
            addCriterion("p_14 <=", value, "p14");
            return (Criteria) this;
        }

        public Criteria andP14Like(String value) {
            addCriterion("p_14 like", value, "p14");
            return (Criteria) this;
        }

        public Criteria andP14NotLike(String value) {
            addCriterion("p_14 not like", value, "p14");
            return (Criteria) this;
        }

        public Criteria andP14In(List<String> values) {
            addCriterion("p_14 in", values, "p14");
            return (Criteria) this;
        }

        public Criteria andP14NotIn(List<String> values) {
            addCriterion("p_14 not in", values, "p14");
            return (Criteria) this;
        }

        public Criteria andP14Between(String value1, String value2) {
            addCriterion("p_14 between", value1, value2, "p14");
            return (Criteria) this;
        }

        public Criteria andP14NotBetween(String value1, String value2) {
            addCriterion("p_14 not between", value1, value2, "p14");
            return (Criteria) this;
        }

        public Criteria andP15IsNull() {
            addCriterion("p_15 is null");
            return (Criteria) this;
        }

        public Criteria andP15IsNotNull() {
            addCriterion("p_15 is not null");
            return (Criteria) this;
        }

        public Criteria andP15EqualTo(String value) {
            addCriterion("p_15 =", value, "p15");
            return (Criteria) this;
        }

        public Criteria andP15NotEqualTo(String value) {
            addCriterion("p_15 <>", value, "p15");
            return (Criteria) this;
        }

        public Criteria andP15GreaterThan(String value) {
            addCriterion("p_15 >", value, "p15");
            return (Criteria) this;
        }

        public Criteria andP15GreaterThanOrEqualTo(String value) {
            addCriterion("p_15 >=", value, "p15");
            return (Criteria) this;
        }

        public Criteria andP15LessThan(String value) {
            addCriterion("p_15 <", value, "p15");
            return (Criteria) this;
        }

        public Criteria andP15LessThanOrEqualTo(String value) {
            addCriterion("p_15 <=", value, "p15");
            return (Criteria) this;
        }

        public Criteria andP15Like(String value) {
            addCriterion("p_15 like", value, "p15");
            return (Criteria) this;
        }

        public Criteria andP15NotLike(String value) {
            addCriterion("p_15 not like", value, "p15");
            return (Criteria) this;
        }

        public Criteria andP15In(List<String> values) {
            addCriterion("p_15 in", values, "p15");
            return (Criteria) this;
        }

        public Criteria andP15NotIn(List<String> values) {
            addCriterion("p_15 not in", values, "p15");
            return (Criteria) this;
        }

        public Criteria andP15Between(String value1, String value2) {
            addCriterion("p_15 between", value1, value2, "p15");
            return (Criteria) this;
        }

        public Criteria andP15NotBetween(String value1, String value2) {
            addCriterion("p_15 not between", value1, value2, "p15");
            return (Criteria) this;
        }

        public Criteria andP16IsNull() {
            addCriterion("p_16 is null");
            return (Criteria) this;
        }

        public Criteria andP16IsNotNull() {
            addCriterion("p_16 is not null");
            return (Criteria) this;
        }

        public Criteria andP16EqualTo(String value) {
            addCriterion("p_16 =", value, "p16");
            return (Criteria) this;
        }

        public Criteria andP16NotEqualTo(String value) {
            addCriterion("p_16 <>", value, "p16");
            return (Criteria) this;
        }

        public Criteria andP16GreaterThan(String value) {
            addCriterion("p_16 >", value, "p16");
            return (Criteria) this;
        }

        public Criteria andP16GreaterThanOrEqualTo(String value) {
            addCriterion("p_16 >=", value, "p16");
            return (Criteria) this;
        }

        public Criteria andP16LessThan(String value) {
            addCriterion("p_16 <", value, "p16");
            return (Criteria) this;
        }

        public Criteria andP16LessThanOrEqualTo(String value) {
            addCriterion("p_16 <=", value, "p16");
            return (Criteria) this;
        }

        public Criteria andP16Like(String value) {
            addCriterion("p_16 like", value, "p16");
            return (Criteria) this;
        }

        public Criteria andP16NotLike(String value) {
            addCriterion("p_16 not like", value, "p16");
            return (Criteria) this;
        }

        public Criteria andP16In(List<String> values) {
            addCriterion("p_16 in", values, "p16");
            return (Criteria) this;
        }

        public Criteria andP16NotIn(List<String> values) {
            addCriterion("p_16 not in", values, "p16");
            return (Criteria) this;
        }

        public Criteria andP16Between(String value1, String value2) {
            addCriterion("p_16 between", value1, value2, "p16");
            return (Criteria) this;
        }

        public Criteria andP16NotBetween(String value1, String value2) {
            addCriterion("p_16 not between", value1, value2, "p16");
            return (Criteria) this;
        }

        public Criteria andP17IsNull() {
            addCriterion("p_17 is null");
            return (Criteria) this;
        }

        public Criteria andP17IsNotNull() {
            addCriterion("p_17 is not null");
            return (Criteria) this;
        }

        public Criteria andP17EqualTo(String value) {
            addCriterion("p_17 =", value, "p17");
            return (Criteria) this;
        }

        public Criteria andP17NotEqualTo(String value) {
            addCriterion("p_17 <>", value, "p17");
            return (Criteria) this;
        }

        public Criteria andP17GreaterThan(String value) {
            addCriterion("p_17 >", value, "p17");
            return (Criteria) this;
        }

        public Criteria andP17GreaterThanOrEqualTo(String value) {
            addCriterion("p_17 >=", value, "p17");
            return (Criteria) this;
        }

        public Criteria andP17LessThan(String value) {
            addCriterion("p_17 <", value, "p17");
            return (Criteria) this;
        }

        public Criteria andP17LessThanOrEqualTo(String value) {
            addCriterion("p_17 <=", value, "p17");
            return (Criteria) this;
        }

        public Criteria andP17Like(String value) {
            addCriterion("p_17 like", value, "p17");
            return (Criteria) this;
        }

        public Criteria andP17NotLike(String value) {
            addCriterion("p_17 not like", value, "p17");
            return (Criteria) this;
        }

        public Criteria andP17In(List<String> values) {
            addCriterion("p_17 in", values, "p17");
            return (Criteria) this;
        }

        public Criteria andP17NotIn(List<String> values) {
            addCriterion("p_17 not in", values, "p17");
            return (Criteria) this;
        }

        public Criteria andP17Between(String value1, String value2) {
            addCriterion("p_17 between", value1, value2, "p17");
            return (Criteria) this;
        }

        public Criteria andP17NotBetween(String value1, String value2) {
            addCriterion("p_17 not between", value1, value2, "p17");
            return (Criteria) this;
        }

        public Criteria andP18IsNull() {
            addCriterion("p_18 is null");
            return (Criteria) this;
        }

        public Criteria andP18IsNotNull() {
            addCriterion("p_18 is not null");
            return (Criteria) this;
        }

        public Criteria andP18EqualTo(String value) {
            addCriterion("p_18 =", value, "p18");
            return (Criteria) this;
        }

        public Criteria andP18NotEqualTo(String value) {
            addCriterion("p_18 <>", value, "p18");
            return (Criteria) this;
        }

        public Criteria andP18GreaterThan(String value) {
            addCriterion("p_18 >", value, "p18");
            return (Criteria) this;
        }

        public Criteria andP18GreaterThanOrEqualTo(String value) {
            addCriterion("p_18 >=", value, "p18");
            return (Criteria) this;
        }

        public Criteria andP18LessThan(String value) {
            addCriterion("p_18 <", value, "p18");
            return (Criteria) this;
        }

        public Criteria andP18LessThanOrEqualTo(String value) {
            addCriterion("p_18 <=", value, "p18");
            return (Criteria) this;
        }

        public Criteria andP18Like(String value) {
            addCriterion("p_18 like", value, "p18");
            return (Criteria) this;
        }

        public Criteria andP18NotLike(String value) {
            addCriterion("p_18 not like", value, "p18");
            return (Criteria) this;
        }

        public Criteria andP18In(List<String> values) {
            addCriterion("p_18 in", values, "p18");
            return (Criteria) this;
        }

        public Criteria andP18NotIn(List<String> values) {
            addCriterion("p_18 not in", values, "p18");
            return (Criteria) this;
        }

        public Criteria andP18Between(String value1, String value2) {
            addCriterion("p_18 between", value1, value2, "p18");
            return (Criteria) this;
        }

        public Criteria andP18NotBetween(String value1, String value2) {
            addCriterion("p_18 not between", value1, value2, "p18");
            return (Criteria) this;
        }

        public Criteria andP19IsNull() {
            addCriterion("p_19 is null");
            return (Criteria) this;
        }

        public Criteria andP19IsNotNull() {
            addCriterion("p_19 is not null");
            return (Criteria) this;
        }

        public Criteria andP19EqualTo(String value) {
            addCriterion("p_19 =", value, "p19");
            return (Criteria) this;
        }

        public Criteria andP19NotEqualTo(String value) {
            addCriterion("p_19 <>", value, "p19");
            return (Criteria) this;
        }

        public Criteria andP19GreaterThan(String value) {
            addCriterion("p_19 >", value, "p19");
            return (Criteria) this;
        }

        public Criteria andP19GreaterThanOrEqualTo(String value) {
            addCriterion("p_19 >=", value, "p19");
            return (Criteria) this;
        }

        public Criteria andP19LessThan(String value) {
            addCriterion("p_19 <", value, "p19");
            return (Criteria) this;
        }

        public Criteria andP19LessThanOrEqualTo(String value) {
            addCriterion("p_19 <=", value, "p19");
            return (Criteria) this;
        }

        public Criteria andP19Like(String value) {
            addCriterion("p_19 like", value, "p19");
            return (Criteria) this;
        }

        public Criteria andP19NotLike(String value) {
            addCriterion("p_19 not like", value, "p19");
            return (Criteria) this;
        }

        public Criteria andP19In(List<String> values) {
            addCriterion("p_19 in", values, "p19");
            return (Criteria) this;
        }

        public Criteria andP19NotIn(List<String> values) {
            addCriterion("p_19 not in", values, "p19");
            return (Criteria) this;
        }

        public Criteria andP19Between(String value1, String value2) {
            addCriterion("p_19 between", value1, value2, "p19");
            return (Criteria) this;
        }

        public Criteria andP19NotBetween(String value1, String value2) {
            addCriterion("p_19 not between", value1, value2, "p19");
            return (Criteria) this;
        }

        public Criteria andP20IsNull() {
            addCriterion("p_20 is null");
            return (Criteria) this;
        }

        public Criteria andP20IsNotNull() {
            addCriterion("p_20 is not null");
            return (Criteria) this;
        }

        public Criteria andP20EqualTo(String value) {
            addCriterion("p_20 =", value, "p20");
            return (Criteria) this;
        }

        public Criteria andP20NotEqualTo(String value) {
            addCriterion("p_20 <>", value, "p20");
            return (Criteria) this;
        }

        public Criteria andP20GreaterThan(String value) {
            addCriterion("p_20 >", value, "p20");
            return (Criteria) this;
        }

        public Criteria andP20GreaterThanOrEqualTo(String value) {
            addCriterion("p_20 >=", value, "p20");
            return (Criteria) this;
        }

        public Criteria andP20LessThan(String value) {
            addCriterion("p_20 <", value, "p20");
            return (Criteria) this;
        }

        public Criteria andP20LessThanOrEqualTo(String value) {
            addCriterion("p_20 <=", value, "p20");
            return (Criteria) this;
        }

        public Criteria andP20Like(String value) {
            addCriterion("p_20 like", value, "p20");
            return (Criteria) this;
        }

        public Criteria andP20NotLike(String value) {
            addCriterion("p_20 not like", value, "p20");
            return (Criteria) this;
        }

        public Criteria andP20In(List<String> values) {
            addCriterion("p_20 in", values, "p20");
            return (Criteria) this;
        }

        public Criteria andP20NotIn(List<String> values) {
            addCriterion("p_20 not in", values, "p20");
            return (Criteria) this;
        }

        public Criteria andP20Between(String value1, String value2) {
            addCriterion("p_20 between", value1, value2, "p20");
            return (Criteria) this;
        }

        public Criteria andP20NotBetween(String value1, String value2) {
            addCriterion("p_20 not between", value1, value2, "p20");
            return (Criteria) this;
        }

        public Criteria andP21IsNull() {
            addCriterion("p_21 is null");
            return (Criteria) this;
        }

        public Criteria andP21IsNotNull() {
            addCriterion("p_21 is not null");
            return (Criteria) this;
        }

        public Criteria andP21EqualTo(String value) {
            addCriterion("p_21 =", value, "p21");
            return (Criteria) this;
        }

        public Criteria andP21NotEqualTo(String value) {
            addCriterion("p_21 <>", value, "p21");
            return (Criteria) this;
        }

        public Criteria andP21GreaterThan(String value) {
            addCriterion("p_21 >", value, "p21");
            return (Criteria) this;
        }

        public Criteria andP21GreaterThanOrEqualTo(String value) {
            addCriterion("p_21 >=", value, "p21");
            return (Criteria) this;
        }

        public Criteria andP21LessThan(String value) {
            addCriterion("p_21 <", value, "p21");
            return (Criteria) this;
        }

        public Criteria andP21LessThanOrEqualTo(String value) {
            addCriterion("p_21 <=", value, "p21");
            return (Criteria) this;
        }

        public Criteria andP21Like(String value) {
            addCriterion("p_21 like", value, "p21");
            return (Criteria) this;
        }

        public Criteria andP21NotLike(String value) {
            addCriterion("p_21 not like", value, "p21");
            return (Criteria) this;
        }

        public Criteria andP21In(List<String> values) {
            addCriterion("p_21 in", values, "p21");
            return (Criteria) this;
        }

        public Criteria andP21NotIn(List<String> values) {
            addCriterion("p_21 not in", values, "p21");
            return (Criteria) this;
        }

        public Criteria andP21Between(String value1, String value2) {
            addCriterion("p_21 between", value1, value2, "p21");
            return (Criteria) this;
        }

        public Criteria andP21NotBetween(String value1, String value2) {
            addCriterion("p_21 not between", value1, value2, "p21");
            return (Criteria) this;
        }

        public Criteria andP22IsNull() {
            addCriterion("p_22 is null");
            return (Criteria) this;
        }

        public Criteria andP22IsNotNull() {
            addCriterion("p_22 is not null");
            return (Criteria) this;
        }

        public Criteria andP22EqualTo(String value) {
            addCriterion("p_22 =", value, "p22");
            return (Criteria) this;
        }

        public Criteria andP22NotEqualTo(String value) {
            addCriterion("p_22 <>", value, "p22");
            return (Criteria) this;
        }

        public Criteria andP22GreaterThan(String value) {
            addCriterion("p_22 >", value, "p22");
            return (Criteria) this;
        }

        public Criteria andP22GreaterThanOrEqualTo(String value) {
            addCriterion("p_22 >=", value, "p22");
            return (Criteria) this;
        }

        public Criteria andP22LessThan(String value) {
            addCriterion("p_22 <", value, "p22");
            return (Criteria) this;
        }

        public Criteria andP22LessThanOrEqualTo(String value) {
            addCriterion("p_22 <=", value, "p22");
            return (Criteria) this;
        }

        public Criteria andP22Like(String value) {
            addCriterion("p_22 like", value, "p22");
            return (Criteria) this;
        }

        public Criteria andP22NotLike(String value) {
            addCriterion("p_22 not like", value, "p22");
            return (Criteria) this;
        }

        public Criteria andP22In(List<String> values) {
            addCriterion("p_22 in", values, "p22");
            return (Criteria) this;
        }

        public Criteria andP22NotIn(List<String> values) {
            addCriterion("p_22 not in", values, "p22");
            return (Criteria) this;
        }

        public Criteria andP22Between(String value1, String value2) {
            addCriterion("p_22 between", value1, value2, "p22");
            return (Criteria) this;
        }

        public Criteria andP22NotBetween(String value1, String value2) {
            addCriterion("p_22 not between", value1, value2, "p22");
            return (Criteria) this;
        }

        public Criteria andP23IsNull() {
            addCriterion("p_23 is null");
            return (Criteria) this;
        }

        public Criteria andP23IsNotNull() {
            addCriterion("p_23 is not null");
            return (Criteria) this;
        }

        public Criteria andP23EqualTo(String value) {
            addCriterion("p_23 =", value, "p23");
            return (Criteria) this;
        }

        public Criteria andP23NotEqualTo(String value) {
            addCriterion("p_23 <>", value, "p23");
            return (Criteria) this;
        }

        public Criteria andP23GreaterThan(String value) {
            addCriterion("p_23 >", value, "p23");
            return (Criteria) this;
        }

        public Criteria andP23GreaterThanOrEqualTo(String value) {
            addCriterion("p_23 >=", value, "p23");
            return (Criteria) this;
        }

        public Criteria andP23LessThan(String value) {
            addCriterion("p_23 <", value, "p23");
            return (Criteria) this;
        }

        public Criteria andP23LessThanOrEqualTo(String value) {
            addCriterion("p_23 <=", value, "p23");
            return (Criteria) this;
        }

        public Criteria andP23Like(String value) {
            addCriterion("p_23 like", value, "p23");
            return (Criteria) this;
        }

        public Criteria andP23NotLike(String value) {
            addCriterion("p_23 not like", value, "p23");
            return (Criteria) this;
        }

        public Criteria andP23In(List<String> values) {
            addCriterion("p_23 in", values, "p23");
            return (Criteria) this;
        }

        public Criteria andP23NotIn(List<String> values) {
            addCriterion("p_23 not in", values, "p23");
            return (Criteria) this;
        }

        public Criteria andP23Between(String value1, String value2) {
            addCriterion("p_23 between", value1, value2, "p23");
            return (Criteria) this;
        }

        public Criteria andP23NotBetween(String value1, String value2) {
            addCriterion("p_23 not between", value1, value2, "p23");
            return (Criteria) this;
        }

        public Criteria andP24IsNull() {
            addCriterion("p_24 is null");
            return (Criteria) this;
        }

        public Criteria andP24IsNotNull() {
            addCriterion("p_24 is not null");
            return (Criteria) this;
        }

        public Criteria andP24EqualTo(String value) {
            addCriterion("p_24 =", value, "p24");
            return (Criteria) this;
        }

        public Criteria andP24NotEqualTo(String value) {
            addCriterion("p_24 <>", value, "p24");
            return (Criteria) this;
        }

        public Criteria andP24GreaterThan(String value) {
            addCriterion("p_24 >", value, "p24");
            return (Criteria) this;
        }

        public Criteria andP24GreaterThanOrEqualTo(String value) {
            addCriterion("p_24 >=", value, "p24");
            return (Criteria) this;
        }

        public Criteria andP24LessThan(String value) {
            addCriterion("p_24 <", value, "p24");
            return (Criteria) this;
        }

        public Criteria andP24LessThanOrEqualTo(String value) {
            addCriterion("p_24 <=", value, "p24");
            return (Criteria) this;
        }

        public Criteria andP24Like(String value) {
            addCriterion("p_24 like", value, "p24");
            return (Criteria) this;
        }

        public Criteria andP24NotLike(String value) {
            addCriterion("p_24 not like", value, "p24");
            return (Criteria) this;
        }

        public Criteria andP24In(List<String> values) {
            addCriterion("p_24 in", values, "p24");
            return (Criteria) this;
        }

        public Criteria andP24NotIn(List<String> values) {
            addCriterion("p_24 not in", values, "p24");
            return (Criteria) this;
        }

        public Criteria andP24Between(String value1, String value2) {
            addCriterion("p_24 between", value1, value2, "p24");
            return (Criteria) this;
        }

        public Criteria andP24NotBetween(String value1, String value2) {
            addCriterion("p_24 not between", value1, value2, "p24");
            return (Criteria) this;
        }

        public Criteria andP25IsNull() {
            addCriterion("p_25 is null");
            return (Criteria) this;
        }

        public Criteria andP25IsNotNull() {
            addCriterion("p_25 is not null");
            return (Criteria) this;
        }

        public Criteria andP25EqualTo(String value) {
            addCriterion("p_25 =", value, "p25");
            return (Criteria) this;
        }

        public Criteria andP25NotEqualTo(String value) {
            addCriterion("p_25 <>", value, "p25");
            return (Criteria) this;
        }

        public Criteria andP25GreaterThan(String value) {
            addCriterion("p_25 >", value, "p25");
            return (Criteria) this;
        }

        public Criteria andP25GreaterThanOrEqualTo(String value) {
            addCriterion("p_25 >=", value, "p25");
            return (Criteria) this;
        }

        public Criteria andP25LessThan(String value) {
            addCriterion("p_25 <", value, "p25");
            return (Criteria) this;
        }

        public Criteria andP25LessThanOrEqualTo(String value) {
            addCriterion("p_25 <=", value, "p25");
            return (Criteria) this;
        }

        public Criteria andP25Like(String value) {
            addCriterion("p_25 like", value, "p25");
            return (Criteria) this;
        }

        public Criteria andP25NotLike(String value) {
            addCriterion("p_25 not like", value, "p25");
            return (Criteria) this;
        }

        public Criteria andP25In(List<String> values) {
            addCriterion("p_25 in", values, "p25");
            return (Criteria) this;
        }

        public Criteria andP25NotIn(List<String> values) {
            addCriterion("p_25 not in", values, "p25");
            return (Criteria) this;
        }

        public Criteria andP25Between(String value1, String value2) {
            addCriterion("p_25 between", value1, value2, "p25");
            return (Criteria) this;
        }

        public Criteria andP25NotBetween(String value1, String value2) {
            addCriterion("p_25 not between", value1, value2, "p25");
            return (Criteria) this;
        }

        public Criteria andP26IsNull() {
            addCriterion("p_26 is null");
            return (Criteria) this;
        }

        public Criteria andP26IsNotNull() {
            addCriterion("p_26 is not null");
            return (Criteria) this;
        }

        public Criteria andP26EqualTo(String value) {
            addCriterion("p_26 =", value, "p26");
            return (Criteria) this;
        }

        public Criteria andP26NotEqualTo(String value) {
            addCriterion("p_26 <>", value, "p26");
            return (Criteria) this;
        }

        public Criteria andP26GreaterThan(String value) {
            addCriterion("p_26 >", value, "p26");
            return (Criteria) this;
        }

        public Criteria andP26GreaterThanOrEqualTo(String value) {
            addCriterion("p_26 >=", value, "p26");
            return (Criteria) this;
        }

        public Criteria andP26LessThan(String value) {
            addCriterion("p_26 <", value, "p26");
            return (Criteria) this;
        }

        public Criteria andP26LessThanOrEqualTo(String value) {
            addCriterion("p_26 <=", value, "p26");
            return (Criteria) this;
        }

        public Criteria andP26Like(String value) {
            addCriterion("p_26 like", value, "p26");
            return (Criteria) this;
        }

        public Criteria andP26NotLike(String value) {
            addCriterion("p_26 not like", value, "p26");
            return (Criteria) this;
        }

        public Criteria andP26In(List<String> values) {
            addCriterion("p_26 in", values, "p26");
            return (Criteria) this;
        }

        public Criteria andP26NotIn(List<String> values) {
            addCriterion("p_26 not in", values, "p26");
            return (Criteria) this;
        }

        public Criteria andP26Between(String value1, String value2) {
            addCriterion("p_26 between", value1, value2, "p26");
            return (Criteria) this;
        }

        public Criteria andP26NotBetween(String value1, String value2) {
            addCriterion("p_26 not between", value1, value2, "p26");
            return (Criteria) this;
        }

        public Criteria andP27IsNull() {
            addCriterion("p_27 is null");
            return (Criteria) this;
        }

        public Criteria andP27IsNotNull() {
            addCriterion("p_27 is not null");
            return (Criteria) this;
        }

        public Criteria andP27EqualTo(String value) {
            addCriterion("p_27 =", value, "p27");
            return (Criteria) this;
        }

        public Criteria andP27NotEqualTo(String value) {
            addCriterion("p_27 <>", value, "p27");
            return (Criteria) this;
        }

        public Criteria andP27GreaterThan(String value) {
            addCriterion("p_27 >", value, "p27");
            return (Criteria) this;
        }

        public Criteria andP27GreaterThanOrEqualTo(String value) {
            addCriterion("p_27 >=", value, "p27");
            return (Criteria) this;
        }

        public Criteria andP27LessThan(String value) {
            addCriterion("p_27 <", value, "p27");
            return (Criteria) this;
        }

        public Criteria andP27LessThanOrEqualTo(String value) {
            addCriterion("p_27 <=", value, "p27");
            return (Criteria) this;
        }

        public Criteria andP27Like(String value) {
            addCriterion("p_27 like", value, "p27");
            return (Criteria) this;
        }

        public Criteria andP27NotLike(String value) {
            addCriterion("p_27 not like", value, "p27");
            return (Criteria) this;
        }

        public Criteria andP27In(List<String> values) {
            addCriterion("p_27 in", values, "p27");
            return (Criteria) this;
        }

        public Criteria andP27NotIn(List<String> values) {
            addCriterion("p_27 not in", values, "p27");
            return (Criteria) this;
        }

        public Criteria andP27Between(String value1, String value2) {
            addCriterion("p_27 between", value1, value2, "p27");
            return (Criteria) this;
        }

        public Criteria andP27NotBetween(String value1, String value2) {
            addCriterion("p_27 not between", value1, value2, "p27");
            return (Criteria) this;
        }

        public Criteria andP28IsNull() {
            addCriterion("p_28 is null");
            return (Criteria) this;
        }

        public Criteria andP28IsNotNull() {
            addCriterion("p_28 is not null");
            return (Criteria) this;
        }

        public Criteria andP28EqualTo(String value) {
            addCriterion("p_28 =", value, "p28");
            return (Criteria) this;
        }

        public Criteria andP28NotEqualTo(String value) {
            addCriterion("p_28 <>", value, "p28");
            return (Criteria) this;
        }

        public Criteria andP28GreaterThan(String value) {
            addCriterion("p_28 >", value, "p28");
            return (Criteria) this;
        }

        public Criteria andP28GreaterThanOrEqualTo(String value) {
            addCriterion("p_28 >=", value, "p28");
            return (Criteria) this;
        }

        public Criteria andP28LessThan(String value) {
            addCriterion("p_28 <", value, "p28");
            return (Criteria) this;
        }

        public Criteria andP28LessThanOrEqualTo(String value) {
            addCriterion("p_28 <=", value, "p28");
            return (Criteria) this;
        }

        public Criteria andP28Like(String value) {
            addCriterion("p_28 like", value, "p28");
            return (Criteria) this;
        }

        public Criteria andP28NotLike(String value) {
            addCriterion("p_28 not like", value, "p28");
            return (Criteria) this;
        }

        public Criteria andP28In(List<String> values) {
            addCriterion("p_28 in", values, "p28");
            return (Criteria) this;
        }

        public Criteria andP28NotIn(List<String> values) {
            addCriterion("p_28 not in", values, "p28");
            return (Criteria) this;
        }

        public Criteria andP28Between(String value1, String value2) {
            addCriterion("p_28 between", value1, value2, "p28");
            return (Criteria) this;
        }

        public Criteria andP28NotBetween(String value1, String value2) {
            addCriterion("p_28 not between", value1, value2, "p28");
            return (Criteria) this;
        }

        public Criteria andP29IsNull() {
            addCriterion("p_29 is null");
            return (Criteria) this;
        }

        public Criteria andP29IsNotNull() {
            addCriterion("p_29 is not null");
            return (Criteria) this;
        }

        public Criteria andP29EqualTo(String value) {
            addCriterion("p_29 =", value, "p29");
            return (Criteria) this;
        }

        public Criteria andP29NotEqualTo(String value) {
            addCriterion("p_29 <>", value, "p29");
            return (Criteria) this;
        }

        public Criteria andP29GreaterThan(String value) {
            addCriterion("p_29 >", value, "p29");
            return (Criteria) this;
        }

        public Criteria andP29GreaterThanOrEqualTo(String value) {
            addCriterion("p_29 >=", value, "p29");
            return (Criteria) this;
        }

        public Criteria andP29LessThan(String value) {
            addCriterion("p_29 <", value, "p29");
            return (Criteria) this;
        }

        public Criteria andP29LessThanOrEqualTo(String value) {
            addCriterion("p_29 <=", value, "p29");
            return (Criteria) this;
        }

        public Criteria andP29Like(String value) {
            addCriterion("p_29 like", value, "p29");
            return (Criteria) this;
        }

        public Criteria andP29NotLike(String value) {
            addCriterion("p_29 not like", value, "p29");
            return (Criteria) this;
        }

        public Criteria andP29In(List<String> values) {
            addCriterion("p_29 in", values, "p29");
            return (Criteria) this;
        }

        public Criteria andP29NotIn(List<String> values) {
            addCriterion("p_29 not in", values, "p29");
            return (Criteria) this;
        }

        public Criteria andP29Between(String value1, String value2) {
            addCriterion("p_29 between", value1, value2, "p29");
            return (Criteria) this;
        }

        public Criteria andP29NotBetween(String value1, String value2) {
            addCriterion("p_29 not between", value1, value2, "p29");
            return (Criteria) this;
        }

        public Criteria andP30IsNull() {
            addCriterion("p_30 is null");
            return (Criteria) this;
        }

        public Criteria andP30IsNotNull() {
            addCriterion("p_30 is not null");
            return (Criteria) this;
        }

        public Criteria andP30EqualTo(String value) {
            addCriterion("p_30 =", value, "p30");
            return (Criteria) this;
        }

        public Criteria andP30NotEqualTo(String value) {
            addCriterion("p_30 <>", value, "p30");
            return (Criteria) this;
        }

        public Criteria andP30GreaterThan(String value) {
            addCriterion("p_30 >", value, "p30");
            return (Criteria) this;
        }

        public Criteria andP30GreaterThanOrEqualTo(String value) {
            addCriterion("p_30 >=", value, "p30");
            return (Criteria) this;
        }

        public Criteria andP30LessThan(String value) {
            addCriterion("p_30 <", value, "p30");
            return (Criteria) this;
        }

        public Criteria andP30LessThanOrEqualTo(String value) {
            addCriterion("p_30 <=", value, "p30");
            return (Criteria) this;
        }

        public Criteria andP30Like(String value) {
            addCriterion("p_30 like", value, "p30");
            return (Criteria) this;
        }

        public Criteria andP30NotLike(String value) {
            addCriterion("p_30 not like", value, "p30");
            return (Criteria) this;
        }

        public Criteria andP30In(List<String> values) {
            addCriterion("p_30 in", values, "p30");
            return (Criteria) this;
        }

        public Criteria andP30NotIn(List<String> values) {
            addCriterion("p_30 not in", values, "p30");
            return (Criteria) this;
        }

        public Criteria andP30Between(String value1, String value2) {
            addCriterion("p_30 between", value1, value2, "p30");
            return (Criteria) this;
        }

        public Criteria andP30NotBetween(String value1, String value2) {
            addCriterion("p_30 not between", value1, value2, "p30");
            return (Criteria) this;
        }

        public Criteria andP31IsNull() {
            addCriterion("p_31 is null");
            return (Criteria) this;
        }

        public Criteria andP31IsNotNull() {
            addCriterion("p_31 is not null");
            return (Criteria) this;
        }

        public Criteria andP31EqualTo(String value) {
            addCriterion("p_31 =", value, "p31");
            return (Criteria) this;
        }

        public Criteria andP31NotEqualTo(String value) {
            addCriterion("p_31 <>", value, "p31");
            return (Criteria) this;
        }

        public Criteria andP31GreaterThan(String value) {
            addCriterion("p_31 >", value, "p31");
            return (Criteria) this;
        }

        public Criteria andP31GreaterThanOrEqualTo(String value) {
            addCriterion("p_31 >=", value, "p31");
            return (Criteria) this;
        }

        public Criteria andP31LessThan(String value) {
            addCriterion("p_31 <", value, "p31");
            return (Criteria) this;
        }

        public Criteria andP31LessThanOrEqualTo(String value) {
            addCriterion("p_31 <=", value, "p31");
            return (Criteria) this;
        }

        public Criteria andP31Like(String value) {
            addCriterion("p_31 like", value, "p31");
            return (Criteria) this;
        }

        public Criteria andP31NotLike(String value) {
            addCriterion("p_31 not like", value, "p31");
            return (Criteria) this;
        }

        public Criteria andP31In(List<String> values) {
            addCriterion("p_31 in", values, "p31");
            return (Criteria) this;
        }

        public Criteria andP31NotIn(List<String> values) {
            addCriterion("p_31 not in", values, "p31");
            return (Criteria) this;
        }

        public Criteria andP31Between(String value1, String value2) {
            addCriterion("p_31 between", value1, value2, "p31");
            return (Criteria) this;
        }

        public Criteria andP31NotBetween(String value1, String value2) {
            addCriterion("p_31 not between", value1, value2, "p31");
            return (Criteria) this;
        }

        public Criteria andOperationTimeIsNull() {
            addCriterion("operation_time is null");
            return (Criteria) this;
        }

        public Criteria andOperationTimeIsNotNull() {
            addCriterion("operation_time is not null");
            return (Criteria) this;
        }

        public Criteria andOperationTimeEqualTo(String value) {
            addCriterion("operation_time =", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotEqualTo(String value) {
            addCriterion("operation_time <>", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeGreaterThan(String value) {
            addCriterion("operation_time >", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeGreaterThanOrEqualTo(String value) {
            addCriterion("operation_time >=", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeLessThan(String value) {
            addCriterion("operation_time <", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeLessThanOrEqualTo(String value) {
            addCriterion("operation_time <=", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeLike(String value) {
            addCriterion("operation_time like", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotLike(String value) {
            addCriterion("operation_time not like", value, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeIn(List<String> values) {
            addCriterion("operation_time in", values, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotIn(List<String> values) {
            addCriterion("operation_time not in", values, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeBetween(String value1, String value2) {
            addCriterion("operation_time between", value1, value2, "operationTime");
            return (Criteria) this;
        }

        public Criteria andOperationTimeNotBetween(String value1, String value2) {
            addCriterion("operation_time not between", value1, value2, "operationTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("update_time like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("update_time not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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