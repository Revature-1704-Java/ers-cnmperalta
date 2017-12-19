package com.revature.ers.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.revature.ers.beans.Reimbursement;
import com.revature.ers.beans.ReimbursementStatus;
import com.revature.ers.util.ConnectionUtility;

import oracle.sql.NUMBER;

public class ReimbursementDAO {
    public List<Reimbursement> getAllReimbursementsForManager(int managerId) {
        List<Reimbursement> reimbursements = new LinkedList<Reimbursement>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try(Connection connection = ConnectionUtility.getConnection()) {
            String sql = "select * from Reimbursement where ManagerID=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, managerId);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                int rid = rs.getInt("ReimbursementID");
                BigDecimal amount = rs.getObject("Amount", BigDecimal.class);
                int eid = rs.getInt("EmployeeID");
                int mid = rs.getInt("ManagerID");
                String justification = rs.getString("Justification");
                int status = rs.getInt("Approved");
                ReimbursementStatus rstatus = ReimbursementStatus.PENDING;

                switch (status) {
                    case 1:
                        rstatus = ReimbursementStatus.APPROVED;
                        break;
                    case 2:
                        rstatus = ReimbursementStatus.DISAPPROVED;
                        break;
                }

                reimbursements.add(new Reimbursement(rid, amount, eid, mid, justification, rstatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return reimbursements;
    }

    public List<Reimbursement> getAllReimbursementsForEmployee(int employeeId) {
        List<Reimbursement> reimbursements = new LinkedList<Reimbursement>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try(Connection connection = ConnectionUtility.getConnection()) {
            String sql = "select * from Reimbursement where EmployeeID=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                int rid = rs.getInt("ReimbursementID");
                BigDecimal amount = rs.getObject("Amount", NUMBER.class).bigDecimalValue();
                int eid = rs.getInt("EmployeeID");
                int mid = rs.getInt("ManagerID");
                String justification = rs.getString("Justification");
                int status = rs.getInt("Approved");
                ReimbursementStatus rstatus = ReimbursementStatus.PENDING;

                switch (status) {
                    case 1:
                        rstatus = ReimbursementStatus.APPROVED;
                        break;
                    case 2:
                        rstatus = ReimbursementStatus.DISAPPROVED;
                        break;
                }

                reimbursements.add(new Reimbursement(rid, amount, eid, mid, justification, rstatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return reimbursements;
    }

    public void updateReimbursementStatus(int reimbursementId, ReimbursementStatus status) {
        CallableStatement cs = null;

        try (Connection connection = ConnectionUtility.getConnection()) {
            String sql = "{call SP_";

            switch(status) {
                case APPROVED:
                    sql = sql.concat("Approve_Reimbursement");
                    break;
                case DISAPPROVED:
                    sql = sql.concat("Disapprove_Reimbursement");
            }

            sql = sql.concat("(?)}");

            cs = connection.prepareCall(sql);
            cs.setInt(1, reimbursementId);
            
            cs.execute();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void insertNewReimbursementRequest(Reimbursement reimbursement) {
        CallableStatement cs = null;

        try (Connection connection = ConnectionUtility.getConnection()){
            String sql = "{call SP_Insert_Reimbursement_Record(?, ?, ?, ?, ?)}";
            cs = connection.prepareCall(sql);
            // System.out.println(reimbursement.getAmount().toPlainString());
            cs.setBigDecimal("amt", reimbursement.getAmount());
            cs.setInt("eid", reimbursement.getEmployeeId());
            cs.setInt("mid", reimbursement.getManagerId());
            cs.setString("jst", reimbursement.getJustification());
            
            switch(reimbursement.getStatus()) {
                case PENDING:
                    cs.setInt("app", 0);
                    break;
                case APPROVED:
                    cs.setInt("app", 1);
                    break;
                case DISAPPROVED:
                    cs.setInt("app", 2);
                    break;
            }

            cs.execute();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}