package com.revature.ers.beans;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeTest {
    private Employee emp;
    private Employee mgr;

    @Before
    public void initialize() {
        emp = new Employee(1, "Kei", "Peralta", 21, "cmperalta@sample.com", "password", "Normal");
        mgr = new Employee(21, "Abby", "Peralta", -1, "amperalta@sample.com", "password", "Manager");
    }

    @Test
    public void getEmployeeIdTest() {
        assertEquals(1, emp.getEmployeeId());
    }
    
    @Test
    public void getFirstNameTest() {
        assertEquals("Kei", emp.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        assertEquals("Peralta", emp.getLastName());
    }

    @Test
    public void getManagerIdTest() {
        assertEquals(mgr.getEmployeeId(), emp.getManagerId());
    }

    @Test
    public void getEmailAddressTest() {
        assertEquals("cmperalta@sample.com", emp.getEmailAddress());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("password", emp.getPassword());
    }

    @Test
    public void getEmployeeTypeTest() {
        assertEquals("Normal", emp.getEmployeeType());
        assertEquals("Manager", mgr.getEmployeeType());
    }
}