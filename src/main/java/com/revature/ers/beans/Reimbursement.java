package com.revature.ers.beans;

import java.math.BigDecimal;

public class Reimbursement {
    private int reimbursementId;
    private BigDecimal amount;
    private int employeeId;
    private int managerId;
    private boolean approved;
    private String reasons;

    public Reimbursement () {}

    public Reimbursement (int reimbursementId, BigDecimal amount, int employeeId, int managerId, boolean approved, String reasons) {
        this.reimbursementId = reimbursementId;
        this.amount = amount;
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.approved = approved;
        this.reasons = reasons;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @return the managerId
     */
    public int getManagerId() {
        return managerId;
    }

    /**
     * @return the reasons
     */
    public String getReasons() {
        return reasons;
    }

    /**
     * @return the reimbursementId
     */
    public int getReimbursementId() {
        return reimbursementId;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @param managerId the managerId to set
     */
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    /**
     * @param reasons the reasons to set
     */
    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    /**
     * @param reimbursementId the reimbursementId to set
     */
    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }
}