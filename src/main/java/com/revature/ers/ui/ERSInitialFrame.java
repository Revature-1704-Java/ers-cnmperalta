package com.revature.ers.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.revature.ers.dao.EmployeeDAO;
import com.revature.ers.dao.ReimbursementDAO;

public class ERSInitialFrame implements ERSFrame, ActionListener {
    private static ERSInitialFrame instance;
    private JFrame initialFrame;
    private JPanel initialPanel;
    private JButton createAccountButton;
    private JButton loginButton;
    private EmployeeDAO employeeDAO;
    private ReimbursementDAO reimbursementDAO;

    private ERSInitialFrame() {
        initialFrame = new JFrame("Expense Reimbursement System");
        initialPanel = new JPanel();
        createAccountButton = new JButton("Create Account");
        loginButton = new JButton("Existing User? Log in!");
        initialize();
    }

    private void initialize() {
        loginButton.addActionListener(this);
        createAccountButton.addActionListener(this);
        initialPanel.setLayout(new GridLayout(2, 1));
        initialPanel.add(createAccountButton);
        initialPanel.add(loginButton);
        initialFrame.add(initialPanel);
        initialFrame.setPreferredSize(new Dimension(600, 200));
        initialFrame.pack();
        initialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showFrame() {
        initialFrame.setVisible(true);
    }

    public void hideFrame() {
        initialFrame.setVisible(false);
    }

    /**
     * @param employeeDAO the employeeDAO to set
     */
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    /**
     * @param reimbursementDAO the reimbursementDAO to set
     */
    public void setReimbursementDAO(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public static ERSInitialFrame getInstance() {
        if(instance == null)
            instance = new ERSInitialFrame();

        return instance;
    }

    public static ERSInitialFrame getInstance(EmployeeDAO edao, ReimbursementDAO rdao) {
        if(instance == null) {
            instance = new ERSInitialFrame();
            instance.setEmployeeDAO(edao);
            instance.setReimbursementDAO(rdao);       
        }

        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)) {
            this.hideFrame();
            ERSLoginFrame.getInstance(employeeDAO, reimbursementDAO).showFrame();
        } else if(e.getSource().equals(createAccountButton)) {
            this.hideFrame();
            ERSCreateAccountFrame.getInstance(employeeDAO, reimbursementDAO).showFrame();
        }
    }
}