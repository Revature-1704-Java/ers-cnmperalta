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

        createAccountFrame.pack();
    }

    public void showFrame() {
        emailTextField.setText("");
        passwordField.setText("");
        createAccountFrame.setVisible(true);
    }

    public void hideFrame() {
        emailTextField.setText("");
        passwordField.setText("");
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
            createAccountPanel.removeAll();
            createAccountPanel.add(passwordLabel);
            createAccountPanel.add(passwordField);
            createAccountPanel.add(createAccountButton);
            createAccountFrame.pack();
            createAccountFrame.repaint();
        } else if(e.getSource().equals(createAccountButton)) {
            // get password and store it in database
            int employeeId = 1; // fetch this from database
            String employeeType = "Manager";

            if(employeeType.equals("Normal")) {
                ERSUserViewFrame.getInstance().setEmployeeId(employeeId);
                this.hideFrame();
                ERSUserViewFrame.getInstance().showFrame();
            } else if(employeeType.equals("Manager")) {
                ERSManagerViewFrame.getInstance().setEmployeeId(employeeId);
                this.hideFrame();
                ERSManagerViewFrame.getInstance().showFrame();
            }
            
        }
    }
}