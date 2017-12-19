package com.revature.ers.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ERSManagerViewFrame implements ERSFrame {
    private static ERSManagerViewFrame instance;

    private JFrame managerViewFrame;
    private JPanel managerViewPanel;
    private JComboBox<Integer> pendingReimbursementsComboBox;
    private JPanel reimbursementInformationPanel;
    private JLabel employeeNameLabel;
    private JLabel employeeName;
    private JLabel amountLabel;
    private JLabel amount;
    private JLabel justificationLabel;
    private JLabel justification;
    private JButton approveButton;
    private JButton disapproveButton;

    private int employeeId;

    private ERSManagerViewFrame() {
        managerViewFrame = new JFrame("Manager View");
        managerViewPanel = new JPanel();
        pendingReimbursementsComboBox = new JComboBox<Integer>();
        reimbursementInformationPanel = new JPanel();
        employeeNameLabel = new JLabel("Requester:");
        employeeName = new JLabel();
        amountLabel = new JLabel("Amount:");
        amount =  new JLabel();
        justificationLabel = new JLabel("Justification:");
        justification = new JLabel();
        approveButton = new JButton("Approve");
        disapproveButton = new JButton("Disapprove");
        initialize();
    }

    private void initialize() {
        reimbursementInformationPanel.setLayout(new GridLayout(4,2));
        reimbursementInformationPanel.add(employeeNameLabel);
        reimbursementInformationPanel.add(employeeName);
        reimbursementInformationPanel.add(amountLabel);
        reimbursementInformationPanel.add(amount);
        reimbursementInformationPanel.add(justificationLabel);
        reimbursementInformationPanel.add(justification);
        reimbursementInformationPanel.add(approveButton);
        reimbursementInformationPanel.add(disapproveButton);
        managerViewPanel.setLayout(new GridLayout(2, 1));
        managerViewPanel.add(pendingReimbursementsComboBox);
        managerViewPanel.add(reimbursementInformationPanel);
        managerViewFrame.add(managerViewPanel);

        managerViewFrame.pack();
    }

    private void updateManagerViewInformation() {

    }

    public void showFrame() {
        employeeName.setText("");
        amount.setText("");
        justification.setText("");
        managerViewFrame.setVisible(true);
    }

    public void hideFrame() {
        employeeName.setText("");
        amount.setText("");
        justification.setText("");
        managerViewFrame.setVisible(false);
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
        updateManagerViewInformation();
    }

    public static ERSManagerViewFrame getInstance() {
        if(instance == null)
            instance = new ERSManagerViewFrame();
        return instance;
    }
}