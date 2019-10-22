package com.tsbg.ecosys.ecoModel.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EcooperationExample {
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public EcooperationExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andCoidIsNull() {
            addCriterion("coid is null");
            return (Criteria) this;
        }

        public Criteria andCoidIsNotNull() {
            addCriterion("coid is not null");
            return (Criteria) this;
        }

        public Criteria andCoidEqualTo(Integer value) {
            addCriterion("coid =", value, "coid");
            return (Criteria) this;
        }

        public Criteria andCoidNotEqualTo(Integer value) {
            addCriterion("coid <>", value, "coid");
            return (Criteria) this;
        }

        public Criteria andCoidGreaterThan(Integer value) {
            addCriterion("coid >", value, "coid");
            return (Criteria) this;
        }

        public Criteria andCoidGreaterThanOrEqualTo(Integer value) {
            addCriterion("coid >=", value, "coid");
            return (Criteria) this;
        }

        public Criteria andCoidLessThan(Integer value) {
            addCriterion("coid <", value, "coid");
            return (Criteria) this;
        }

        public Criteria andCoidLessThanOrEqualTo(Integer value) {
            addCriterion("coid <=", value, "coid");
            return (Criteria) this;
        }

        public Criteria andCoidIn(List<Integer> values) {
            addCriterion("coid in", values, "coid");
            return (Criteria) this;
        }

        public Criteria andCoidNotIn(List<Integer> values) {
            addCriterion("coid not in", values, "coid");
            return (Criteria) this;
        }

        public Criteria andCoidBetween(Integer value1, Integer value2) {
            addCriterion("coid between", value1, value2, "coid");
            return (Criteria) this;
        }

        public Criteria andCoidNotBetween(Integer value1, Integer value2) {
            addCriterion("coid not between", value1, value2, "coid");
            return (Criteria) this;
        }

        public Criteria andPartnerNoIsNull() {
            addCriterion("partner_no is null");
            return (Criteria) this;
        }

        public Criteria andPartnerNoIsNotNull() {
            addCriterion("partner_no is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerNoEqualTo(Integer value) {
            addCriterion("partner_no =", value, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerNoNotEqualTo(Integer value) {
            addCriterion("partner_no <>", value, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerNoGreaterThan(Integer value) {
            addCriterion("partner_no >", value, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("partner_no >=", value, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerNoLessThan(Integer value) {
            addCriterion("partner_no <", value, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerNoLessThanOrEqualTo(Integer value) {
            addCriterion("partner_no <=", value, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerNoIn(List<Integer> values) {
            addCriterion("partner_no in", values, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerNoNotIn(List<Integer> values) {
            addCriterion("partner_no not in", values, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerNoBetween(Integer value1, Integer value2) {
            addCriterion("partner_no between", value1, value2, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerNoNotBetween(Integer value1, Integer value2) {
            addCriterion("partner_no not between", value1, value2, "partnerNo");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameIsNull() {
            addCriterion("partner_cname is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameIsNotNull() {
            addCriterion("partner_cname is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameEqualTo(String value) {
            addCriterion("partner_cname =", value, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameNotEqualTo(String value) {
            addCriterion("partner_cname <>", value, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameGreaterThan(String value) {
            addCriterion("partner_cname >", value, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameGreaterThanOrEqualTo(String value) {
            addCriterion("partner_cname >=", value, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameLessThan(String value) {
            addCriterion("partner_cname <", value, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameLessThanOrEqualTo(String value) {
            addCriterion("partner_cname <=", value, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameLike(String value) {
            addCriterion("partner_cname like", value, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameNotLike(String value) {
            addCriterion("partner_cname not like", value, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameIn(List<String> values) {
            addCriterion("partner_cname in", values, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameNotIn(List<String> values) {
            addCriterion("partner_cname not in", values, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameBetween(String value1, String value2) {
            addCriterion("partner_cname between", value1, value2, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCnameNotBetween(String value1, String value2) {
            addCriterion("partner_cname not between", value1, value2, "partnerCname");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeIsNull() {
            addCriterion("partner_callintime is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeIsNotNull() {
            addCriterion("partner_callintime is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeEqualTo(Date value) {
            addCriterionForJDBCDate("partner_callintime =", value, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("partner_callintime <>", value, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeGreaterThan(Date value) {
            addCriterionForJDBCDate("partner_callintime >", value, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("partner_callintime >=", value, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeLessThan(Date value) {
            addCriterionForJDBCDate("partner_callintime <", value, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("partner_callintime <=", value, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeIn(List<Date> values) {
            addCriterionForJDBCDate("partner_callintime in", values, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("partner_callintime not in", values, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("partner_callintime between", value1, value2, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerCallintimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("partner_callintime not between", value1, value2, "partnerCallintime");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerIsNull() {
            addCriterion("partner_BD_owner is null");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerIsNotNull() {
            addCriterion("partner_BD_owner is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerEqualTo(String value) {
            addCriterion("partner_BD_owner =", value, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerNotEqualTo(String value) {
            addCriterion("partner_BD_owner <>", value, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerGreaterThan(String value) {
            addCriterion("partner_BD_owner >", value, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("partner_BD_owner >=", value, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerLessThan(String value) {
            addCriterion("partner_BD_owner <", value, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerLessThanOrEqualTo(String value) {
            addCriterion("partner_BD_owner <=", value, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerLike(String value) {
            addCriterion("partner_BD_owner like", value, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerNotLike(String value) {
            addCriterion("partner_BD_owner not like", value, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerIn(List<String> values) {
            addCriterion("partner_BD_owner in", values, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerNotIn(List<String> values) {
            addCriterion("partner_BD_owner not in", values, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerBetween(String value1, String value2) {
            addCriterion("partner_BD_owner between", value1, value2, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerBdOwnerNotBetween(String value1, String value2) {
            addCriterion("partner_BD_owner not between", value1, value2, "partnerBdOwner");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageIsNull() {
            addCriterion("partner_costage is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageIsNotNull() {
            addCriterion("partner_costage is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageEqualTo(String value) {
            addCriterion("partner_costage =", value, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageNotEqualTo(String value) {
            addCriterion("partner_costage <>", value, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageGreaterThan(String value) {
            addCriterion("partner_costage >", value, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageGreaterThanOrEqualTo(String value) {
            addCriterion("partner_costage >=", value, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageLessThan(String value) {
            addCriterion("partner_costage <", value, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageLessThanOrEqualTo(String value) {
            addCriterion("partner_costage <=", value, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageLike(String value) {
            addCriterion("partner_costage like", value, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageNotLike(String value) {
            addCriterion("partner_costage not like", value, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageIn(List<String> values) {
            addCriterion("partner_costage in", values, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageNotIn(List<String> values) {
            addCriterion("partner_costage not in", values, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageBetween(String value1, String value2) {
            addCriterion("partner_costage between", value1, value2, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andPartnerCostageNotBetween(String value1, String value2) {
            addCriterion("partner_costage not between", value1, value2, "partnerCostage");
            return (Criteria) this;
        }

        public Criteria andSignContractIsNull() {
            addCriterion("sign_contract is null");
            return (Criteria) this;
        }

        public Criteria andSignContractIsNotNull() {
            addCriterion("sign_contract is not null");
            return (Criteria) this;
        }

        public Criteria andSignContractEqualTo(Integer value) {
            addCriterion("sign_contract =", value, "signContract");
            return (Criteria) this;
        }

        public Criteria andSignContractNotEqualTo(Integer value) {
            addCriterion("sign_contract <>", value, "signContract");
            return (Criteria) this;
        }

        public Criteria andSignContractGreaterThan(Integer value) {
            addCriterion("sign_contract >", value, "signContract");
            return (Criteria) this;
        }

        public Criteria andSignContractGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_contract >=", value, "signContract");
            return (Criteria) this;
        }

        public Criteria andSignContractLessThan(Integer value) {
            addCriterion("sign_contract <", value, "signContract");
            return (Criteria) this;
        }

        public Criteria andSignContractLessThanOrEqualTo(Integer value) {
            addCriterion("sign_contract <=", value, "signContract");
            return (Criteria) this;
        }

        public Criteria andSignContractIn(List<Integer> values) {
            addCriterion("sign_contract in", values, "signContract");
            return (Criteria) this;
        }

        public Criteria andSignContractNotIn(List<Integer> values) {
            addCriterion("sign_contract not in", values, "signContract");
            return (Criteria) this;
        }

        public Criteria andSignContractBetween(Integer value1, Integer value2) {
            addCriterion("sign_contract between", value1, value2, "signContract");
            return (Criteria) this;
        }

        public Criteria andSignContractNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_contract not between", value1, value2, "signContract");
            return (Criteria) this;
        }

        public Criteria andContractDateIsNull() {
            addCriterion("contract_date is null");
            return (Criteria) this;
        }

        public Criteria andContractDateIsNotNull() {
            addCriterion("contract_date is not null");
            return (Criteria) this;
        }

        public Criteria andContractDateEqualTo(String value) {
            addCriterion("contract_date =", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotEqualTo(String value) {
            addCriterion("contract_date <>", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateGreaterThan(String value) {
            addCriterion("contract_date >", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateGreaterThanOrEqualTo(String value) {
            addCriterion("contract_date >=", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateLessThan(String value) {
            addCriterion("contract_date <", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateLessThanOrEqualTo(String value) {
            addCriterion("contract_date <=", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateLike(String value) {
            addCriterion("contract_date like", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotLike(String value) {
            addCriterion("contract_date not like", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateIn(List<String> values) {
            addCriterion("contract_date in", values, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotIn(List<String> values) {
            addCriterion("contract_date not in", values, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateBetween(String value1, String value2) {
            addCriterion("contract_date between", value1, value2, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotBetween(String value1, String value2) {
            addCriterion("contract_date not between", value1, value2, "contractDate");
            return (Criteria) this;
        }

        public Criteria andEntrustIsNull() {
            addCriterion("entrust is null");
            return (Criteria) this;
        }

        public Criteria andEntrustIsNotNull() {
            addCriterion("entrust is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustEqualTo(Integer value) {
            addCriterion("entrust =", value, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustNotEqualTo(Integer value) {
            addCriterion("entrust <>", value, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustGreaterThan(Integer value) {
            addCriterion("entrust >", value, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustGreaterThanOrEqualTo(Integer value) {
            addCriterion("entrust >=", value, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustLessThan(Integer value) {
            addCriterion("entrust <", value, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustLessThanOrEqualTo(Integer value) {
            addCriterion("entrust <=", value, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustIn(List<Integer> values) {
            addCriterion("entrust in", values, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustNotIn(List<Integer> values) {
            addCriterion("entrust not in", values, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustBetween(Integer value1, Integer value2) {
            addCriterion("entrust between", value1, value2, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustNotBetween(Integer value1, Integer value2) {
            addCriterion("entrust not between", value1, value2, "entrust");
            return (Criteria) this;
        }

        public Criteria andEntrustNameIsNull() {
            addCriterion("entrust_name is null");
            return (Criteria) this;
        }

        public Criteria andEntrustNameIsNotNull() {
            addCriterion("entrust_name is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustNameEqualTo(String value) {
            addCriterion("entrust_name =", value, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameNotEqualTo(String value) {
            addCriterion("entrust_name <>", value, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameGreaterThan(String value) {
            addCriterion("entrust_name >", value, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameGreaterThanOrEqualTo(String value) {
            addCriterion("entrust_name >=", value, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameLessThan(String value) {
            addCriterion("entrust_name <", value, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameLessThanOrEqualTo(String value) {
            addCriterion("entrust_name <=", value, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameLike(String value) {
            addCriterion("entrust_name like", value, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameNotLike(String value) {
            addCriterion("entrust_name not like", value, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameIn(List<String> values) {
            addCriterion("entrust_name in", values, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameNotIn(List<String> values) {
            addCriterion("entrust_name not in", values, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameBetween(String value1, String value2) {
            addCriterion("entrust_name between", value1, value2, "entrustName");
            return (Criteria) this;
        }

        public Criteria andEntrustNameNotBetween(String value1, String value2) {
            addCriterion("entrust_name not between", value1, value2, "entrustName");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingIsNull() {
            addCriterion("partner_awarding is null");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingIsNotNull() {
            addCriterion("partner_awarding is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingEqualTo(Integer value) {
            addCriterion("partner_awarding =", value, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingNotEqualTo(Integer value) {
            addCriterion("partner_awarding <>", value, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingGreaterThan(Integer value) {
            addCriterion("partner_awarding >", value, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingGreaterThanOrEqualTo(Integer value) {
            addCriterion("partner_awarding >=", value, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingLessThan(Integer value) {
            addCriterion("partner_awarding <", value, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingLessThanOrEqualTo(Integer value) {
            addCriterion("partner_awarding <=", value, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingIn(List<Integer> values) {
            addCriterion("partner_awarding in", values, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingNotIn(List<Integer> values) {
            addCriterion("partner_awarding not in", values, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingBetween(Integer value1, Integer value2) {
            addCriterion("partner_awarding between", value1, value2, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andPartnerAwardingNotBetween(Integer value1, Integer value2) {
            addCriterion("partner_awarding not between", value1, value2, "partnerAwarding");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andCoTypeIsNull() {
            addCriterion("co_type is null");
            return (Criteria) this;
        }

        public Criteria andCoTypeIsNotNull() {
            addCriterion("co_type is not null");
            return (Criteria) this;
        }

        public Criteria andCoTypeEqualTo(String value) {
            addCriterion("co_type =", value, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeNotEqualTo(String value) {
            addCriterion("co_type <>", value, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeGreaterThan(String value) {
            addCriterion("co_type >", value, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeGreaterThanOrEqualTo(String value) {
            addCriterion("co_type >=", value, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeLessThan(String value) {
            addCriterion("co_type <", value, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeLessThanOrEqualTo(String value) {
            addCriterion("co_type <=", value, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeLike(String value) {
            addCriterion("co_type like", value, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeNotLike(String value) {
            addCriterion("co_type not like", value, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeIn(List<String> values) {
            addCriterion("co_type in", values, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeNotIn(List<String> values) {
            addCriterion("co_type not in", values, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeBetween(String value1, String value2) {
            addCriterion("co_type between", value1, value2, "coType");
            return (Criteria) this;
        }

        public Criteria andCoTypeNotBetween(String value1, String value2) {
            addCriterion("co_type not between", value1, value2, "coType");
            return (Criteria) this;
        }

        public Criteria andCoProgressIsNull() {
            addCriterion("co_progress is null");
            return (Criteria) this;
        }

        public Criteria andCoProgressIsNotNull() {
            addCriterion("co_progress is not null");
            return (Criteria) this;
        }

        public Criteria andCoProgressEqualTo(String value) {
            addCriterion("co_progress =", value, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressNotEqualTo(String value) {
            addCriterion("co_progress <>", value, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressGreaterThan(String value) {
            addCriterion("co_progress >", value, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressGreaterThanOrEqualTo(String value) {
            addCriterion("co_progress >=", value, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressLessThan(String value) {
            addCriterion("co_progress <", value, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressLessThanOrEqualTo(String value) {
            addCriterion("co_progress <=", value, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressLike(String value) {
            addCriterion("co_progress like", value, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressNotLike(String value) {
            addCriterion("co_progress not like", value, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressIn(List<String> values) {
            addCriterion("co_progress in", values, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressNotIn(List<String> values) {
            addCriterion("co_progress not in", values, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressBetween(String value1, String value2) {
            addCriterion("co_progress between", value1, value2, "coProgress");
            return (Criteria) this;
        }

        public Criteria andCoProgressNotBetween(String value1, String value2) {
            addCriterion("co_progress not between", value1, value2, "coProgress");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentIsNull() {
            addCriterion("fii_codepartment is null");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentIsNotNull() {
            addCriterion("fii_codepartment is not null");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentEqualTo(String value) {
            addCriterion("fii_codepartment =", value, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentNotEqualTo(String value) {
            addCriterion("fii_codepartment <>", value, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentGreaterThan(String value) {
            addCriterion("fii_codepartment >", value, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("fii_codepartment >=", value, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentLessThan(String value) {
            addCriterion("fii_codepartment <", value, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentLessThanOrEqualTo(String value) {
            addCriterion("fii_codepartment <=", value, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentLike(String value) {
            addCriterion("fii_codepartment like", value, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentNotLike(String value) {
            addCriterion("fii_codepartment not like", value, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentIn(List<String> values) {
            addCriterion("fii_codepartment in", values, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentNotIn(List<String> values) {
            addCriterion("fii_codepartment not in", values, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentBetween(String value1, String value2) {
            addCriterion("fii_codepartment between", value1, value2, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andFiiCodepartmentNotBetween(String value1, String value2) {
            addCriterion("fii_codepartment not between", value1, value2, "fiiCodepartment");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
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

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("updater is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("updater is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("updater =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("updater <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("updater >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("updater >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("updater <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("updater <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("updater like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("updater not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("updater in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("updater not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("updater between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("updater not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ecooperation
     *
     * @mbg.generated do_not_delete_during_merge Tue Aug 13 10:40:42 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ecooperation
     *
     * @mbg.generated Tue Aug 13 10:40:42 CST 2019
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