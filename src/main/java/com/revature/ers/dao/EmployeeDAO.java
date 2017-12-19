package com.revature.ers.dao;

import java.io.IOException;
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

        try (Connection connection = ConnectionUtility.getConnection()) {
            String sql = "select * from employee";
            ResultSet rs;
            
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("Employee_ID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                int managerId = rs.getInt("Manager_ID");
                String emailAddress = rs.getString("Email_Address");
                String password = rs.getString("Password");

                employees.add(new Employee(id, firstName, lastName, managerId, emailAddress, password));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }
}