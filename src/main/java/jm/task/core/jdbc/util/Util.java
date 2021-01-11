package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public Util() {
//        try {
//            Driver driver = new FabricMySQLDriver();
//            DriverManager.registerDriver(driver);//
//        } catch (SQLException e) {
//            System.out.println("Failed to load driver class");
//            e.printStackTrace();
//        }

//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             Statement statement = connection.createStatement()) {
//            System.out.println(statement.isClosed());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
