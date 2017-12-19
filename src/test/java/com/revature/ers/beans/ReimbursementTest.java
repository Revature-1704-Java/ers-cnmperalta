package com.revature.ers.beans;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

public class ReimbursementTest {
    private Reimbursement r;
    @Before
    public void initialize() {
        r = new Reimbursement(1, new BigDecimal("54.45"), 1, 21, "Gas", ReimbursementStatus.APPROVED);
    }

    @Test
    public void getReimbursementIdTest() {
        assertEquals(1, r.getReimbursementId());
    }

    @Test
    public void getAmountTest() {
        assertEquals(new BigDecimal("54.45"), r.getAmount());
    }

    @Test
    public void getEmployeeIdTest() {
        assertEquals(1, r.getEmployeeId());
    }

    @Test
    public void getManagerIdTest() {
        assertEquals(21, r.getManagerId());
    }

    @Test
    public void getJustificationTest() {
        assertEquals("Gas", r.getJustification());
    }

    @Test
    public void getStatusTest() {
        assertEquals(ReimbursementStatus.APPROVED, r.getStatus());
    }
}