package jm.task.core.jdbc;


import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        Util worker = new Util();

        try (Statement statement = worker.getConnection().createStatement()) {
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
