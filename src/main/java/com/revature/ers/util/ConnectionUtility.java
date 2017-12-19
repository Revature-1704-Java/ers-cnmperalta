package com.revature.ers.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {
    private static Connection connection;

    public static Connection getConnection() throws SQLException, IOException {
        if(connection == null || connection.isClosed()) {
            Properties properties = new Properties();
            InputStream in = new FileInputStream("connection.properties");
            String url, user, password;

            properties.load(in);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
        }

        return connection;
    }
}