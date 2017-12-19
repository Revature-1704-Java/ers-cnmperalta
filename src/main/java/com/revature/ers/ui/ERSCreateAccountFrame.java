package com.revature.ers.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.revature.ers.beans.Employee;
import com.revature.ers.dao.EmployeeDAO;
import com.revature.ers.dao.ReimbursementDAO;

public class ERSCreateAccountFrame implements ERSFrame, ActionListener {
    private static ERSCreateAccountFrame instance;
    private JFrame createAccountFrame;
    private JPanel createAccountPanel;
    private JLabel emaiLLabel;
    private JLabel passwordLabel;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton nextButton;
    private JButton createAccountButton;
    private EmployeeDAO employeeDAO;
    private ReimbursementDAO reimbursementDAO;
    private Employee employee;

    private ERSCreateAccountFrame() {
        createAccountFrame = new JFrame("Create Account");
        createAccountPanel = new JPanel();
        emaiLLabel = new JLabel("Email");
        passwordLabel = new JLabel("Password");
        emailTextField = new JTextField();
        passwordField = new JPasswordField();
        nextButton = new JButton("Next");
        createAccountButton = new JButton("Create Account");
        initialize();
    }

    private void initialize() {
        nextButton.addActionListener(this);
        createAccountButton.addActionListener(this);
        createAccountPanel.setLayout(new GridLayout(2, 2));
        createAccountPanel.add(emaiLLabel);
        createAccountPanel.add(emailTextField);
        createAccountPanel.add(nextButton);
        createAccountFrame.add(createAccountPanel);

        createAccountFrame.setPreferredSize(new Dimension(600, 200));
        createAccountFrame.pack();
        createAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void clearLoginForm() {
        emailTextField.setText("");
        passwordField.setText("");
    }

    public void showFrame() {
        createAccountFrame.setVisible(true);
    }

    public void hideFrame() {
        createAccountFrame.setVisible(false);
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

    public static ERSCreateAccountFrame getInstance(EmployeeDAO edao, ReimbursementDAO rdao) {
        if (instance == null) {
            instance = new ERSCreateAccountFrame();
            instance.setEmployeeDAO(edao);
            instance.setReimbursementDAO(rdao);
        }
        return instance;
    }

    public static ERSCreateAccountFrame getInstance() {
        if (instance == null)
            instance = new ERSCreateAccountFrame();
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(nextButton)) {
            employee = employeeDAO.getEmployeeByEmail(emailTextField.getText());
            if(employee != null) {
                createAccountPanel.removeAll();
                createAccountPanel.add(passwordLabel);
                createAccountPanel.add(passwordField);
                createAccountPanel.add(createAccountButton);
                createAccountFrame.pack();
                createAccountFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(createAccountFrame, "Invalid email address.");
                return;
            }
            
        } else if(e.getSource().equals(createAccountButton)) {
            // get password and store it in database
            createAccountPanel.removeAll();
            createAccountPanel.add(emaiLLabel);
            createAccountPanel.add(emailTextField);
            createAccountPanel.add(nextButton);
            createAccountFrame.pack();
            createAccountFrame.repaint();
            
            employeeDAO.updateEmployeePassword(employee.getEmployeeId(), passwordField.getText());

            if(employee.getEmployeeType().equalsIgnoreCase("Normal")) {
                ERSUserViewFrame userView = ERSUserViewFrame.getInstance(employeeDAO, reimbursementDAO);
                userView.setEmployeeByEmailAddress(employee.getEmailAddress());
                clearLoginForm();
                this.hideFrame();
                userView.showFrame();
            } else if(employee.getEmployeeType().equalsIgnoreCase("Manager")) {
                ERSManagerViewFrame managerView = ERSManagerViewFrame.getInstance(employeeDAO, reimbursementDAO);
                managerView.setEmployeeByEmailAddress(employee.getEmailAddress());
                clearLoginForm();
                this.hideFrame();
                managerView.showFrame();
            }
        }
    }
}