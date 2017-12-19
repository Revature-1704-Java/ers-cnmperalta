package com.revature.ers.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.revature.ers.beans.Employee;
import com.revature.ers.beans.Reimbursement;
import com.revature.ers.beans.ReimbursementStatus;
import com.revature.ers.dao.EmployeeDAO;
import com.revature.ers.dao.ReimbursementDAO;

public class ERSManagerViewFrame implements ERSFrame, ActionListener, ItemListener {
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
    private JLabel statusLabel;
    private JLabel status;
    private JButton approveButton;
    private JButton disapproveButton;
    private JButton logoutButton;

    private Employee employee;
    private EmployeeDAO employeeDAO;
    private ReimbursementDAO reimbursementDAO;

    private List<Reimbursement> managerReimbursements;
    private int selectedReimbursement;

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
        statusLabel = new JLabel("Status:");
        status = new JLabel();
        approveButton = new JButton("Approve");
        disapproveButton = new JButton("Disapprove");
        logoutButton = new JButton("Logout");
        initialize();
    }

    private void initialize() {
        approveButton.addActionListener(this);
        disapproveButton.addActionListener(this);
        logoutButton.addActionListener(this);

        pendingReimbursementsComboBox.addItemListener(this);

        reimbursementInformationPanel.setLayout(new GridLayout(4,2));
        reimbursementInformationPanel.add(employeeNameLabel);
        reimbursementInformationPanel.add(employeeName);
        reimbursementInformationPanel.add(amountLabel);
        reimbursementInformationPanel.add(amount);
        reimbursementInformationPanel.add(justificationLabel);
        reimbursementInformationPanel.add(justification);
        reimbursementInformationPanel.add(approveButton);
        reimbursementInformationPanel.add(disapproveButton);
        managerViewPanel.setLayout(new GridLayout(3, 1));
        managerViewPanel.add(pendingReimbursementsComboBox);
        managerViewPanel.add(reimbursementInformationPanel);
        managerViewPanel.add(logoutButton);
        managerViewFrame.add(managerViewPanel);

        managerViewFrame.setPreferredSize(new Dimension(400, 400));
        managerViewFrame.pack();
        managerViewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void updateManagerViewInformation() {
        if(employee != null) {
            managerReimbursements = reimbursementDAO.getAllReimbursementsForManager(employee.getEmployeeId());
            if(pendingReimbursementsComboBox.getItemCount() > 0) pendingReimbursementsComboBox.removeAllItems();
            for(int i = 0; i < managerReimbursements.size(); i++)
                // if(managerReimbursements.get(i).getStatus() == ReimbursementStatus.PENDING)
                pendingReimbursementsComboBox.addItem(i);
            selectedReimbursement = 0;
            updateReimbursementInformation();
        } else
            JOptionPane.showMessageDialog(managerViewFrame, "Employee does not exist.");        
    }

    private void updateReimbursementInformation() {
        if(selectedReimbursement < pendingReimbursementsComboBox.getItemCount()) {
            Employee requester = employeeDAO.getEmployeeById(managerReimbursements.get(selectedReimbursement).getEmployeeId());

            employeeName.setText(requester.getFirstName() + " " + requester.getLastName());
            amount.setText(managerReimbursements.get(selectedReimbursement).getAmount().toPlainString());
            justification.setText(managerReimbursements.get(selectedReimbursement).getJustification());
            if(managerReimbursements.get(selectedReimbursement).getStatus() != ReimbursementStatus.PENDING) {
                if(approveButton.getParent() != null) reimbursementInformationPanel.remove(approveButton);
                if(disapproveButton.getParent() != null) reimbursementInformationPanel.remove(disapproveButton);
                if(statusLabel.getParent() == null) reimbursementInformationPanel.add(statusLabel);
                if(status.getParent() == null) reimbursementInformationPanel.add(status);

                if(managerReimbursements.get(selectedReimbursement).getStatus() == ReimbursementStatus.APPROVED)
                    status.setText("Approved");
                else
                    status.setText("Disapproved");
            } else {
                if(statusLabel.getParent() != null) reimbursementInformationPanel.remove(statusLabel);
                if(status.getParent() != null) reimbursementInformationPanel.remove(status);
                if(approveButton.getParent() == null) reimbursementInformationPanel.add(approveButton);
                if(disapproveButton.getParent() == null) reimbursementInformationPanel.add(disapproveButton);
            }
        }
    }

    private void clearReimbursementInformation() {
        employeeName.setText("");
        amount.setText("");
        justification.setText("");
    }

    public void showFrame() {
        managerViewFrame.setVisible(true);
    }

    public void hideFrame() {
        managerViewFrame.setVisible(false);
    }

    public void setEmployeeByEmailAddress(String emailAddress) {
        employee = employeeDAO.getEmployeeByEmail(emailAddress);
        updateManagerViewInformation();
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

    public static ERSManagerViewFrame getInstance() {
        if(instance == null)
            instance = new ERSManagerViewFrame();
        return instance;
    }

    public static ERSManagerViewFrame getInstance(EmployeeDAO edao, ReimbursementDAO rdao) {
        if(instance == null) {
            instance = new ERSManagerViewFrame();
            instance.setEmployeeDAO(edao);
            instance.setReimbursementDAO(rdao);
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(logoutButton)) {
            employee = null;
            if(managerReimbursements != null) managerReimbursements.clear();
            pendingReimbursementsComboBox.removeAllItems();
            clearReimbursementInformation();
            this.hideFrame();
            ERSInitialFrame.getInstance().showFrame();
        } else if(e.getSource().equals(approveButton)) {
            reimbursementDAO.updateReimbursementStatus(managerReimbursements.get(selectedReimbursement).getReimbursementId(), ReimbursementStatus.APPROVED);
            updateManagerViewInformation();
        } else if(e.getSource().equals(disapproveButton)) {
            reimbursementDAO.updateReimbursementStatus(managerReimbursements.get(selectedReimbursement).getReimbursementId(), ReimbursementStatus.DISAPPROVED);
            updateManagerViewInformation();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        selectedReimbursement = (Integer) e.getItem();
        updateReimbursementInformation();
        managerViewFrame.repaint();
    }
}