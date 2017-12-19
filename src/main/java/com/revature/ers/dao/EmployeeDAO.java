package com.revature.ers.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.revature.ers.beans.Employee;
import com.revature.ers.util.ConnectionUtility;

public class EmployeeDAO {
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new LinkedList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection connection = ConnectionUtility.getConnection()) {
            String sql = "select * from employee";
            
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("EmployeeID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                int managerId = rs.getInt("ManagerID");
                String emailAddress = rs.getString("EmailAddress");
                String password = rs.getString("Password");
                String employeeType = rs.getString("EmployeeType");

                employees.add(new Employee(id, firstName, lastName, managerId, emailAddress, password, employeeType));
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

        return employees;
    }

    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try(Connection connection = ConnectionUtility.getConnection()) {
            String sql = "select * from employee where EmployeeID=?";
            
            ps = connection.prepareStatement(sql);
            ps.setInt(1, employeeId);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                int id = rs.getInt("EmployeeID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                int managerId = rs.getInt("ManagerID");
                String emailAddress = rs.getString("EmailAddress");
                String password = rs.getString("Password");
                String employeeType = rs.getString("EmployeeType");

                employee = new Employee(id, firstName, lastName, managerId, emailAddress, password, employeeType);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(IOException e) {
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

        return employee;
    }

    public Employee getEmployeeByEmail(String emailAddress) {
        Employee employee = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try(Connection connection = ConnectionUtility.getConnection()) {
            String sql = "select * from employee where EmailAddress=?";
            
            ps = connection.prepareStatement(sql);
            ps.setString(1, emailAddress);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                int id = rs.getInt("EmployeeID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                int managerId = rs.getInt("ManagerID");
                String password = rs.getString("Password");
                String employeeType = rs.getString("EmployeeType");

                emailAddress = rs.getString("EmailAddress");
                employee = new Employee(id, firstName, lastName, managerId, emailAddress, password, employeeType);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } catch(IOException e) {
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

        return employee;
    }

    public void updateEmployeePassword(int employeeId, String password) {
        CallableStatement cs = null;

        try(Connection connection = ConnectionUtility.getConnection()) {
            String sql = "{call SP_Update_Employee_Password(?, ?)}";
            cs = connection.prepareCall(sql);
            cs.setInt(1, employeeId);
            cs.setString(2, password);

            cs.execute();
            cs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(IOException e) {
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