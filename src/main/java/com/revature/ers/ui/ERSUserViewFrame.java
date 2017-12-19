package com.revature.ers.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ERSUserViewFrame implements ERSFrame {
    private static final String MAKE_NEW_REIMBURSEMENT = "New Reimbursement Request";
    private static final String VIEW_PENDING_REIMBURSEMENT_REQUESTS = "Pending Reimbursement Requests";

    private static ERSUserViewFrame instance;
    private JFrame userViewFrame;
    private JPanel userViewPanel;
    private JTabbedPane userViewTabbedPane;
    private JPanel makeNewReimbursementRequestPanel;
    private JPanel viewReimbursementRequestsPanel;
    private int employeeId;

    private ERSUserViewFrame() {
        userViewFrame = new JFrame("User View");
        userViewPanel = new JPanel();
        userViewTabbedPane = new JTabbedPane();
        makeNewReimbursementRequestPanel = new JPanel();
        viewReimbursementRequestsPanel = new JPanel();
        initialize();
    }

    private void initialize() {
        userViewTabbedPane.addTab(MAKE_NEW_REIMBURSEMENT, makeNewReimbursementRequestPanel);
        userViewTabbedPane.addTab(VIEW_PENDING_REIMBURSEMENT_REQUESTS, viewReimbursementRequestsPanel);
        userViewPanel.add(userViewTabbedPane);
        userViewFrame.add(userViewPanel);
        userViewFrame.pack();
    }

    private void updateUserViewInformation() {
        // get employee information from DAO and put relevant info into labels
    }

    public void showFrame() {
        userViewFrame.setVisible(true);
    }

    public void hideFrame() {
        userViewFrame.setVisible(false);
    }

    public static ERSUserViewFrame getInstance() {
        if(instance == null)
            instance = new ERSUserViewFrame();
        return instance;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
        updateUserViewInformation();
    }
}