package com.revature.ers;

import com.revature.ers.beans.Reimbursement;
import com.revature.ers.dao.EmployeeDAO;
import com.revature.ers.dao.ReimbursementDAO;
import com.revature.ers.ui.ERSInitialFrame;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
        ERSInitialFrame.getInstance(employeeDAO, reimbursementDAO).showFrame();
    }
}