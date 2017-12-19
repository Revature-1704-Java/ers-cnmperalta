package com.revature.ers.beans;

import java.math.BigDecimal;

public class Reimbursement {
    private int reimbursementId;
    private BigDecimal amount;
    private int employeeId;
    private int managerId;
    private String justification;
    private ReimbursementStatus status;

    public Reimbursement() {}

    public Reimbursement(BigDecimal amount, int employeeId, int managerId, String justification, ReimbursementStatus status) {
        this.amount = amount;
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.justification = justification;
        this.status = status;
    }

    public Reimbursement (int reimbursementId, BigDecimal amount, int employeeId, int managerId, String justification, ReimbursementStatus status) {
        this.reimbursementId = reimbursementId;
        this.amount = amount;
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.justification = justification;
        this.status = status;
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
     * @return the justification
     */
    public String getJustification() {
        return justification;
    }

    /**
     * @return the status
     */
    public ReimbursementStatus getStatus() {
        return status;
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
     * @param status the status to set
     */
    public void setStatus(ReimbursementStatus status) {
        this.status = status;
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
     * @param justification the justification to set
     */
    public void setJustification(String justification) {
        this.justification = justification;
    }

    /**
     * @param reimbursementId the reimbursementId to set
     */
    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }
}