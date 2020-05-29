package org.park.zoo.repositories.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static final String url = "jdbc:h2:mem:";
    private static Connection INSTANCE;

    static {
        try {
            INSTANCE = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private ConnectionSingleton() {
    }

    public static Connection getConnection() {
        return INSTANCE;
    }
}
