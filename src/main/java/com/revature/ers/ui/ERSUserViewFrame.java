package com.revature.ers.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.revature.ers.beans.Employee;
import com.revature.ers.beans.Reimbursement;
import com.revature.ers.beans.ReimbursementStatus;
import com.revature.ers.dao.EmployeeDAO;
import com.revature.ers.dao.ReimbursementDAO;

import oracle.net.aso.b;

public class ERSUserViewFrame implements ERSFrame, ActionListener, ItemListener {
    private static final String MAKE_NEW_REIMBURSEMENT = "New Reimbursement Request";
    private static final String VIEW_PENDING_REIMBURSEMENT_REQUESTS = "Pending Reimbursement Requests";

    private static ERSUserViewFrame instance;
    private JFrame userViewFrame;
    private JPanel userViewPanel;
    private JTabbedPane userViewTabbedPane;
    private JPanel makeNewReimbursementRequestPanel;
    private JPanel viewReimbursementRequestsPanel;
    private JPanel reimbursementInformationPanel;

    private JLabel newAmountLabel;
    private JLabel newJustificationLabel;
    private JTextField amountTextField;
    private JTextPane justificationTextPane;
    private JButton clearButton;
    private JButton submitButton;
    
    private JComboBox<Integer> reimbursementComboBox;
    private JLabel amountLabel;
    private JLabel amount;
    private JLabel justificationLabel;
    private JLabel justification;
    private JLabel statusLabel;
    private JLabel status;

    private JButton logoutButton;

    private Employee employee;
    private EmployeeDAO employeeDAO;
    private ReimbursementDAO reimbursementDAO;

    private List<Reimbursement> employeeReimbursements;

    private int selectedReimbursement;

    private ERSUserViewFrame() {
        userViewFrame = new JFrame("User View");
        userViewPanel = new JPanel();
        userViewTabbedPane = new JTabbedPane();
        makeNewReimbursementRequestPanel = new JPanel();
        viewReimbursementRequestsPanel = new JPanel();
        reimbursementInformationPanel =  new JPanel();
        newAmountLabel = new JLabel("Amount:");
        newJustificationLabel = new JLabel("Justification:");
        amountTextField = new JTextField();
        justificationTextPane = new JTextPane();
        clearButton = new JButton("Clear");
        submitButton = new JButton("Submit");
        reimbursementComboBox = new JComboBox<Integer>();
        amountLabel = new JLabel("Amount:");
        justificationLabel = new JLabel("Justification:");
        statusLabel = new JLabel("Status");
        amount = new JLabel();
        justification = new JLabel();
        status = new JLabel();
        logoutButton = new JButton("Logout");
        employeeDAO = new EmployeeDAO();
        reimbursementDAO = new ReimbursementDAO();
        initialize();
    }

    private void initialize() {
        clearButton.addActionListener(this);
        submitButton.addActionListener(this);
        logoutButton.addActionListener(this);

        reimbursementComboBox.addItemListener(this);

        makeNewReimbursementRequestPanel.setLayout(new GridLayout(3, 2));
        makeNewReimbursementRequestPanel.add(newAmountLabel);
        makeNewReimbursementRequestPanel.add(amountTextField);
        makeNewReimbursementRequestPanel.add(newJustificationLabel);
        makeNewReimbursementRequestPanel.add(justificationTextPane);
        makeNewReimbursementRequestPanel.add(clearButton);
        makeNewReimbursementRequestPanel.add(submitButton);
        userViewTabbedPane.addTab(MAKE_NEW_REIMBURSEMENT, makeNewReimbursementRequestPanel);

        viewReimbursementRequestsPanel.setLayout(new GridLayout(2, 1));
        viewReimbursementRequestsPanel.add(reimbursementComboBox);
        reimbursementInformationPanel.setLayout(new GridLayout(3, 2));
        reimbursementInformationPanel.add(amountLabel);
        reimbursementInformationPanel.add(amount);
        reimbursementInformationPanel.add(justificationLabel);
        reimbursementInformationPanel.add(justification);
        reimbursementInformationPanel.add(statusLabel);
        reimbursementInformationPanel.add(status);
        viewReimbursementRequestsPanel.add(reimbursementInformationPanel);
        userViewTabbedPane.addTab(VIEW_PENDING_REIMBURSEMENT_REQUESTS, viewReimbursementRequestsPanel);
        userViewPanel.setLayout(new GridLayout(2,1));
        userViewPanel.add(userViewTabbedPane);
        userViewPanel.add(logoutButton);
        userViewFrame.add(userViewPanel);
        
        userViewFrame.pack();
        userViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void updateUserViewInformation() {
        // get employee information from DAO and put relevant info into labels
        if(employee != null) {
            employeeReimbursements = reimbursementDAO.getAllReimbursementsForEmployee(employee.getEmployeeId());
            if(reimbursementComboBox.getItemCount() > 0) reimbursementComboBox.removeAllItems();
            for(int i = 0; i < employeeReimbursements.size(); i++)
                reimbursementComboBox.addItem(i);
            selectedReimbursement = 0;
            updateReimbursementInformation();
        } else
            JOptionPane.showMessageDialog(userViewFrame, "Employee does not exist.");
    }

    private void updateReimbursementInformation() {
        if(selectedReimbursement < employeeReimbursements.size()) {
            amount.setText(employeeReimbursements.get(selectedReimbursement).getAmount().toPlainString());
            justification.setText(employeeReimbursements.get(selectedReimbursement).getJustification());
            switch(employeeReimbursements.get(selectedReimbursement).getStatus()) {
                case APPROVED:
                    status.setText("Approved");
                    break;
                case DISAPPROVED:
                    status.setText("Disapproved");
                    break;
                case PENDING:
                    status.setText("Pending");
                    break;
            }
        }
    }

    private void clearReimbursementForm() {
        amountTextField.setText("");
        justificationTextPane.setText("");
        status.setText("");
    }

    private void clearReimbursementInformation() {
        amount.setText("");
        justification.setText("");

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

    public void setEmployeeByEmailAddress(String emailAddress) {
        employee = employeeDAO.getEmployeeByEmail(emailAddress);
        updateUserViewInformation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(clearButton)) {
            clearReimbursementForm();
        } else if(e.getSource().equals(submitButton)) {
            Reimbursement reimbursement = new Reimbursement(new BigDecimal(amountTextField.getText()), employee.getEmployeeId(), employee.getManagerId(), justificationTextPane.getText(), ReimbursementStatus.PENDING);
            reimbursementDAO.insertNewReimbursementRequest(reimbursement);
            clearReimbursementForm();
            updateUserViewInformation();
        } else if(e.getSource().equals(logoutButton)) {
            employee = null;
            if(employeeReimbursements != null) employeeReimbursements.clear();
            reimbursementComboBox.removeAllItems();
            clearReimbursementForm();
            clearReimbursementInformation();
            this.hideFrame();
            ERSInitialFrame.getInstance().showFrame();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        selectedReimbursement = (Integer) e.getItem();
        updateReimbursementInformation();
    }
}