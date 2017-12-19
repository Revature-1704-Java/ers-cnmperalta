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

public class ERSLoginFrame implements ERSFrame, ActionListener {
    private static ERSLoginFrame instance;
    private JFrame loginFrame;
    private JPanel loginPanel;
    private JLabel emaiLLabel;
    private JLabel passwordLabel;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private EmployeeDAO employeeDAO;

    private ERSLoginFrame() {
        loginFrame = new JFrame("User Login");
        loginPanel = new JPanel();
        emaiLLabel = new JLabel("Email");
        passwordLabel = new JLabel("Password");
        emailTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        employeeDAO = new EmployeeDAO();
        initialize();
    }

    private void initialize() {
        loginButton.addActionListener(this);
        loginPanel.setLayout(new GridLayout(3, 2));
        loginPanel.add(emaiLLabel);
        loginPanel.add(emailTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginFrame.add(loginPanel);

        loginFrame.setPreferredSize(new Dimension(600, 200));
        loginFrame.pack();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showFrame() {
        emailTextField.setText("");
        passwordField.setText("");
        loginFrame.setVisible(true);
    }

    public void hideFrame() {
        emailTextField.setText("");
        passwordField.setText("");
        loginFrame.setVisible(false);
    }


    public static ERSLoginFrame getInstance() {
        if (instance == null)
            instance = new ERSLoginFrame();
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)) {
            // check username and password if correct
            Employee employee = employeeDAO.getEmployeeByEmail(emailTextField.getText());

            if(employee == null) {
                JOptionPane.showMessageDialog(loginFrame, "Invalid email address.");
                return;
            } else if(employee.getPassword() == null) {
                JOptionPane.showMessageDialog(loginFrame, "Create an account first.");
                return;
            } else if (employee.getPassword() != null && employee.getPassword().equals(passwordField.getText())) {
                if(employee.getEmployeeType().equalsIgnoreCase("Normal")) {                
                    ERSUserViewFrame.getInstance().setEmployeeByEmailAddress(employee.getEmailAddress());
                    this.hideFrame();
                    ERSUserViewFrame.getInstance().showFrame();
                } else if(employee.getEmployeeType().equalsIgnoreCase("Manager")) {
                    ERSManagerViewFrame.getInstance().setEmployeeByEmailAddress(employee.getEmailAddress());
                    this.hideFrame();
                    ERSManagerViewFrame.getInstance().showFrame();
                }    
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid password.");
                return;
            } 
        }
    }
}