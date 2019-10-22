package com.tsbg.ecosys.ecoModel.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EcInfoExample {
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public EcInfoExample() {
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

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(Integer value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(Integer value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(Integer value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(Integer value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(Integer value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<Integer> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<Integer> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(Integer value1, Integer value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(Integer value1, Integer value2) {
            addCriterion("cid not between", value1, value2, "cid");
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

        public Criteria andPartnerCdateIsNull() {
            addCriterion("partner_cdate is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateIsNotNull() {
            addCriterion("partner_cdate is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateEqualTo(Date value) {
            addCriterionForJDBCDate("partner_cdate =", value, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("partner_cdate <>", value, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateGreaterThan(Date value) {
            addCriterionForJDBCDate("partner_cdate >", value, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("partner_cdate >=", value, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateLessThan(Date value) {
            addCriterionForJDBCDate("partner_cdate <", value, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("partner_cdate <=", value, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateIn(List<Date> values) {
            addCriterionForJDBCDate("partner_cdate in", values, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("partner_cdate not in", values, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("partner_cdate between", value1, value2, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("partner_cdate not between", value1, value2, "partnerCdate");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalIsNull() {
            addCriterion("partner_cregistcapital is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalIsNotNull() {
            addCriterion("partner_cregistcapital is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalEqualTo(String value) {
            addCriterion("partner_cregistcapital =", value, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalNotEqualTo(String value) {
            addCriterion("partner_cregistcapital <>", value, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalGreaterThan(String value) {
            addCriterion("partner_cregistcapital >", value, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalGreaterThanOrEqualTo(String value) {
            addCriterion("partner_cregistcapital >=", value, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalLessThan(String value) {
            addCriterion("partner_cregistcapital <", value, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalLessThanOrEqualTo(String value) {
            addCriterion("partner_cregistcapital <=", value, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalLike(String value) {
            addCriterion("partner_cregistcapital like", value, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalNotLike(String value) {
            addCriterion("partner_cregistcapital not like", value, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalIn(List<String> values) {
            addCriterion("partner_cregistcapital in", values, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalNotIn(List<String> values) {
            addCriterion("partner_cregistcapital not in", values, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalBetween(String value1, String value2) {
            addCriterion("partner_cregistcapital between", value1, value2, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerCregistcapitalNotBetween(String value1, String value2) {
            addCriterion("partner_cregistcapital not between", value1, value2, "partnerCregistcapital");
            return (Criteria) this;
        }

        public Criteria andPartnerClistIsNull() {
            addCriterion("partner_clist is null");
            return (Criteria) this;
        }

        public Criteria andPartnerClistIsNotNull() {
            addCriterion("partner_clist is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerClistEqualTo(Integer value) {
            addCriterion("partner_clist =", value, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerClistNotEqualTo(Integer value) {
            addCriterion("partner_clist <>", value, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerClistGreaterThan(Integer value) {
            addCriterion("partner_clist >", value, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerClistGreaterThanOrEqualTo(Integer value) {
            addCriterion("partner_clist >=", value, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerClistLessThan(Integer value) {
            addCriterion("partner_clist <", value, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerClistLessThanOrEqualTo(Integer value) {
            addCriterion("partner_clist <=", value, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerClistIn(List<Integer> values) {
            addCriterion("partner_clist in", values, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerClistNotIn(List<Integer> values) {
            addCriterion("partner_clist not in", values, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerClistBetween(Integer value1, Integer value2) {
            addCriterion("partner_clist between", value1, value2, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerClistNotBetween(Integer value1, Integer value2) {
            addCriterion("partner_clist not between", value1, value2, "partnerClist");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleIsNull() {
            addCriterion("partner_cscale is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleIsNotNull() {
            addCriterion("partner_cscale is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleEqualTo(String value) {
            addCriterion("partner_cscale =", value, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleNotEqualTo(String value) {
            addCriterion("partner_cscale <>", value, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleGreaterThan(String value) {
            addCriterion("partner_cscale >", value, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleGreaterThanOrEqualTo(String value) {
            addCriterion("partner_cscale >=", value, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleLessThan(String value) {
            addCriterion("partner_cscale <", value, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleLessThanOrEqualTo(String value) {
            addCriterion("partner_cscale <=", value, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleLike(String value) {
            addCriterion("partner_cscale like", value, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleNotLike(String value) {
            addCriterion("partner_cscale not like", value, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleIn(List<String> values) {
            addCriterion("partner_cscale in", values, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleNotIn(List<String> values) {
            addCriterion("partner_cscale not in", values, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleBetween(String value1, String value2) {
            addCriterion("partner_cscale between", value1, value2, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCscaleNotBetween(String value1, String value2) {
            addCriterion("partner_cscale not between", value1, value2, "partnerCscale");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrIsNull() {
            addCriterion("partner_caddr is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrIsNotNull() {
            addCriterion("partner_caddr is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrEqualTo(String value) {
            addCriterion("partner_caddr =", value, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrNotEqualTo(String value) {
            addCriterion("partner_caddr <>", value, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrGreaterThan(String value) {
            addCriterion("partner_caddr >", value, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrGreaterThanOrEqualTo(String value) {
            addCriterion("partner_caddr >=", value, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrLessThan(String value) {
            addCriterion("partner_caddr <", value, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrLessThanOrEqualTo(String value) {
            addCriterion("partner_caddr <=", value, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrLike(String value) {
            addCriterion("partner_caddr like", value, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrNotLike(String value) {
            addCriterion("partner_caddr not like", value, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrIn(List<String> values) {
            addCriterion("partner_caddr in", values, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrNotIn(List<String> values) {
            addCriterion("partner_caddr not in", values, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrBetween(String value1, String value2) {
            addCriterion("partner_caddr between", value1, value2, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCaddrNotBetween(String value1, String value2) {
            addCriterion("partner_caddr not between", value1, value2, "partnerCaddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrIsNull() {
            addCriterion("partner_cwebddr is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrIsNotNull() {
            addCriterion("partner_cwebddr is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrEqualTo(String value) {
            addCriterion("partner_cwebddr =", value, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrNotEqualTo(String value) {
            addCriterion("partner_cwebddr <>", value, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrGreaterThan(String value) {
            addCriterion("partner_cwebddr >", value, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrGreaterThanOrEqualTo(String value) {
            addCriterion("partner_cwebddr >=", value, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrLessThan(String value) {
            addCriterion("partner_cwebddr <", value, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrLessThanOrEqualTo(String value) {
            addCriterion("partner_cwebddr <=", value, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrLike(String value) {
            addCriterion("partner_cwebddr like", value, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrNotLike(String value) {
            addCriterion("partner_cwebddr not like", value, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrIn(List<String> values) {
            addCriterion("partner_cwebddr in", values, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrNotIn(List<String> values) {
            addCriterion("partner_cwebddr not in", values, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrBetween(String value1, String value2) {
            addCriterion("partner_cwebddr between", value1, value2, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCwebddrNotBetween(String value1, String value2) {
            addCriterion("partner_cwebddr not between", value1, value2, "partnerCwebddr");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechIsNull() {
            addCriterion("partner_ctech is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechIsNotNull() {
            addCriterion("partner_ctech is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechEqualTo(String value) {
            addCriterion("partner_ctech =", value, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechNotEqualTo(String value) {
            addCriterion("partner_ctech <>", value, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechGreaterThan(String value) {
            addCriterion("partner_ctech >", value, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechGreaterThanOrEqualTo(String value) {
            addCriterion("partner_ctech >=", value, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechLessThan(String value) {
            addCriterion("partner_ctech <", value, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechLessThanOrEqualTo(String value) {
            addCriterion("partner_ctech <=", value, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechLike(String value) {
            addCriterion("partner_ctech like", value, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechNotLike(String value) {
            addCriterion("partner_ctech not like", value, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechIn(List<String> values) {
            addCriterion("partner_ctech in", values, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechNotIn(List<String> values) {
            addCriterion("partner_ctech not in", values, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechBetween(String value1, String value2) {
            addCriterion("partner_ctech between", value1, value2, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCtechNotBetween(String value1, String value2) {
            addCriterion("partner_ctech not between", value1, value2, "partnerCtech");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductIsNull() {
            addCriterion("partner_cproduct is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductIsNotNull() {
            addCriterion("partner_cproduct is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductEqualTo(String value) {
            addCriterion("partner_cproduct =", value, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductNotEqualTo(String value) {
            addCriterion("partner_cproduct <>", value, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductGreaterThan(String value) {
            addCriterion("partner_cproduct >", value, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductGreaterThanOrEqualTo(String value) {
            addCriterion("partner_cproduct >=", value, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductLessThan(String value) {
            addCriterion("partner_cproduct <", value, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductLessThanOrEqualTo(String value) {
            addCriterion("partner_cproduct <=", value, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductLike(String value) {
            addCriterion("partner_cproduct like", value, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductNotLike(String value) {
            addCriterion("partner_cproduct not like", value, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductIn(List<String> values) {
            addCriterion("partner_cproduct in", values, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductNotIn(List<String> values) {
            addCriterion("partner_cproduct not in", values, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductBetween(String value1, String value2) {
            addCriterion("partner_cproduct between", value1, value2, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCproductNotBetween(String value1, String value2) {
            addCriterion("partner_cproduct not between", value1, value2, "partnerCproduct");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelIsNull() {
            addCriterion("partner_cchannel is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelIsNotNull() {
            addCriterion("partner_cchannel is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelEqualTo(String value) {
            addCriterion("partner_cchannel =", value, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelNotEqualTo(String value) {
            addCriterion("partner_cchannel <>", value, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelGreaterThan(String value) {
            addCriterion("partner_cchannel >", value, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelGreaterThanOrEqualTo(String value) {
            addCriterion("partner_cchannel >=", value, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelLessThan(String value) {
            addCriterion("partner_cchannel <", value, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelLessThanOrEqualTo(String value) {
            addCriterion("partner_cchannel <=", value, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelLike(String value) {
            addCriterion("partner_cchannel like", value, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelNotLike(String value) {
            addCriterion("partner_cchannel not like", value, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelIn(List<String> values) {
            addCriterion("partner_cchannel in", values, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelNotIn(List<String> values) {
            addCriterion("partner_cchannel not in", values, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelBetween(String value1, String value2) {
            addCriterion("partner_cchannel between", value1, value2, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCchannelNotBetween(String value1, String value2) {
            addCriterion("partner_cchannel not between", value1, value2, "partnerCchannel");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverIsNull() {
            addCriterion("partner_cturnover is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverIsNotNull() {
            addCriterion("partner_cturnover is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverEqualTo(String value) {
            addCriterion("partner_cturnover =", value, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverNotEqualTo(String value) {
            addCriterion("partner_cturnover <>", value, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverGreaterThan(String value) {
            addCriterion("partner_cturnover >", value, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverGreaterThanOrEqualTo(String value) {
            addCriterion("partner_cturnover >=", value, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverLessThan(String value) {
            addCriterion("partner_cturnover <", value, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverLessThanOrEqualTo(String value) {
            addCriterion("partner_cturnover <=", value, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverLike(String value) {
            addCriterion("partner_cturnover like", value, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverNotLike(String value) {
            addCriterion("partner_cturnover not like", value, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverIn(List<String> values) {
            addCriterion("partner_cturnover in", values, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverNotIn(List<String> values) {
            addCriterion("partner_cturnover not in", values, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverBetween(String value1, String value2) {
            addCriterion("partner_cturnover between", value1, value2, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCturnoverNotBetween(String value1, String value2) {
            addCriterion("partner_cturnover not between", value1, value2, "partnerCturnover");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionIsNull() {
            addCriterion("partner_cregion is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionIsNotNull() {
            addCriterion("partner_cregion is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionEqualTo(String value) {
            addCriterion("partner_cregion =", value, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionNotEqualTo(String value) {
            addCriterion("partner_cregion <>", value, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionGreaterThan(String value) {
            addCriterion("partner_cregion >", value, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionGreaterThanOrEqualTo(String value) {
            addCriterion("partner_cregion >=", value, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionLessThan(String value) {
            addCriterion("partner_cregion <", value, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionLessThanOrEqualTo(String value) {
            addCriterion("partner_cregion <=", value, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionLike(String value) {
            addCriterion("partner_cregion like", value, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionNotLike(String value) {
            addCriterion("partner_cregion not like", value, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionIn(List<String> values) {
            addCriterion("partner_cregion in", values, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionNotIn(List<String> values) {
            addCriterion("partner_cregion not in", values, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionBetween(String value1, String value2) {
            addCriterion("partner_cregion between", value1, value2, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCregionNotBetween(String value1, String value2) {
            addCriterion("partner_cregion not between", value1, value2, "partnerCregion");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryIsNull() {
            addCriterion("Partner_cindustry is null");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryIsNotNull() {
            addCriterion("Partner_cindustry is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryEqualTo(String value) {
            addCriterion("Partner_cindustry =", value, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryNotEqualTo(String value) {
            addCriterion("Partner_cindustry <>", value, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryGreaterThan(String value) {
            addCriterion("Partner_cindustry >", value, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryGreaterThanOrEqualTo(String value) {
            addCriterion("Partner_cindustry >=", value, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryLessThan(String value) {
            addCriterion("Partner_cindustry <", value, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryLessThanOrEqualTo(String value) {
            addCriterion("Partner_cindustry <=", value, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryLike(String value) {
            addCriterion("Partner_cindustry like", value, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryNotLike(String value) {
            addCriterion("Partner_cindustry not like", value, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryIn(List<String> values) {
            addCriterion("Partner_cindustry in", values, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryNotIn(List<String> values) {
            addCriterion("Partner_cindustry not in", values, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryBetween(String value1, String value2) {
            addCriterion("Partner_cindustry between", value1, value2, "partnerCindustry");
            return (Criteria) this;
        }

        public Criteria andPartnerCindustryNotBetween(String value1, String value2) {
            addCriterion("Partner_cindustry not between", value1, value2, "partnerCindustry");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ec_info
     *
     * @mbg.generated do_not_delete_during_merge Tue Aug 13 14:54:10 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ec_info
     *
     * @mbg.generated Tue Aug 13 14:54:10 CST 2019
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