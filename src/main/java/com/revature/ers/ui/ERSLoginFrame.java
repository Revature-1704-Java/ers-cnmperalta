package com.revature.ers.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ERSLoginFrame implements ERSFrame, ActionListener {
    private static ERSLoginFrame instance;
    private JFrame loginFrame;
    private JPanel loginPanel;
    private JLabel emaiLLabel;
    private JLabel passwordLabel;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private ERSLoginFrame() {
        loginFrame = new JFrame("User Login");
        loginPanel = new JPanel();
        emaiLLabel = new JLabel("Email");
        passwordLabel = new JLabel("Password");
        emailTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
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

        loginFrame.pack();
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
            int employeeId = 1; // fetch this from database
            String employeeType = "Normal"; // fetch this from database
            if(employeeType.equalsIgnoreCase("Normal")) {
                ERSUserViewFrame.getInstance().setEmployeeId(employeeId);
                this.hideFrame();
                ERSUserViewFrame.getInstance().showFrame();
            } else if(employeeType.equalsIgnoreCase("Manager")) {
                ERSManagerViewFrame.getInstance().setEmployeeId(employeeId);
                this.hideFrame();
                ERSManagerViewFrame.getInstance().showFrame();
            }
        }
    }
}