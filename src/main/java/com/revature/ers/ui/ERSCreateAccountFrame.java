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
        employeeDAO = new EmployeeDAO();
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
            createAccountPanel.add(createAccountButton);
            createAccountFrame.pack();
            createAccountFrame.repaint();
            
            employeeDAO.updateEmployeePassword(employee.getEmployeeId(), passwordField.getText());

            if(employee.getEmployeeType().equalsIgnoreCase("Normal")) {
                ERSUserViewFrame.getInstance().setEmployeeByEmailAddress(employee.getEmailAddress());
                clearLoginForm();
                this.hideFrame();
                ERSUserViewFrame.getInstance().showFrame();
            } else if(employee.getEmployeeType().equalsIgnoreCase("Manager")) {
                ERSManagerViewFrame.getInstance().setEmployeeByEmailAddress(employee.getEmailAddress());
                clearLoginForm();
                this.hideFrame();
                ERSManagerViewFrame.getInstance().showFrame();
            }
        }
    }
}