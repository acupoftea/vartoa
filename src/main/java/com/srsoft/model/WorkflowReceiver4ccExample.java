package com.srsoft.model;

import java.util.ArrayList;
import java.util.List;

public class WorkflowReceiver4ccExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    public WorkflowReceiver4ccExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
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
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andWorkflowidIsNull() {
            addCriterion("workflowId is null");
            return (Criteria) this;
        }

        public Criteria andWorkflowidIsNotNull() {
            addCriterion("workflowId is not null");
            return (Criteria) this;
        }

        public Criteria andWorkflowidEqualTo(Integer value) {
            addCriterion("workflowId =", value, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflowidNotEqualTo(Integer value) {
            addCriterion("workflowId <>", value, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflowidGreaterThan(Integer value) {
            addCriterion("workflowId >", value, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflowidGreaterThanOrEqualTo(Integer value) {
            addCriterion("workflowId >=", value, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflowidLessThan(Integer value) {
            addCriterion("workflowId <", value, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflowidLessThanOrEqualTo(Integer value) {
            addCriterion("workflowId <=", value, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflowidIn(List<Integer> values) {
            addCriterion("workflowId in", values, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflowidNotIn(List<Integer> values) {
            addCriterion("workflowId not in", values, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflowidBetween(Integer value1, Integer value2) {
            addCriterion("workflowId between", value1, value2, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflowidNotBetween(Integer value1, Integer value2) {
            addCriterion("workflowId not between", value1, value2, "workflowid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidIsNull() {
            addCriterion("workflowNodeId is null");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidIsNotNull() {
            addCriterion("workflowNodeId is not null");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidEqualTo(Integer value) {
            addCriterion("workflowNodeId =", value, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidNotEqualTo(Integer value) {
            addCriterion("workflowNodeId <>", value, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidGreaterThan(Integer value) {
            addCriterion("workflowNodeId >", value, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("workflowNodeId >=", value, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidLessThan(Integer value) {
            addCriterion("workflowNodeId <", value, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidLessThanOrEqualTo(Integer value) {
            addCriterion("workflowNodeId <=", value, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidIn(List<Integer> values) {
            addCriterion("workflowNodeId in", values, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidNotIn(List<Integer> values) {
            addCriterion("workflowNodeId not in", values, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidBetween(Integer value1, Integer value2) {
            addCriterion("workflowNodeId between", value1, value2, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andWorkflownodeidNotBetween(Integer value1, Integer value2) {
            addCriterion("workflowNodeId not between", value1, value2, "workflownodeid");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(Integer value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(Integer value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(Integer value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(Integer value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(Integer value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(Integer value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<Integer> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<Integer> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(Integer value1, Integer value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(Integer value1, Integer value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated do_not_delete_during_merge Sat Jul 09 15:07:04 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table workflowReceiver4cc
     *
     * @mbggenerated Sat Jul 09 15:07:04 CST 2016
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