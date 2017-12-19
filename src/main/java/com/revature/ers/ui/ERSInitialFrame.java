package com.revature.ers.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ERSInitialFrame implements ERSFrame, ActionListener {
    private static ERSInitialFrame instance;
    private JFrame initialFrame;
    private JPanel initialPanel;
    private JButton createAccountButton;
    private JButton loginButton;

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
        initialFrame.pack();
    }

    public void showFrame() {
        initialFrame.setVisible(true);
    }

    public void hideFrame() {
        initialFrame.setVisible(false);
    }

    public static ERSInitialFrame getInstance() {
        if(instance == null)
            instance = new ERSInitialFrame();
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)) {
            System.out.println("Showing login frame.");
            this.hideFrame();
            ERSLoginFrame.getInstance().showFrame();
        } else if(e.getSource().equals(createAccountButton)) {
            System.out.println("Showing create account frame.");
            this.hideFrame();
            ERSCreateAccountFrame.getInstance().showFrame();
        }
    }
}