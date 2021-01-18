package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static String testName = "Ivan";
    private static String testLastName = "Ivanov";
    private static byte testAge = 5;
    private static String testName1 = "Peter";
    private static String testLastName1 = "Petrov";
    private static byte testAge1 = 23;
    private static String testName2 = "Jina";
    private static String testLastName2 = "Yoon";
    private static byte testAge2 = 17;
    private static String testName3 = "Mary";
    private static String testLastName3 = "Kim";
    private static byte testAge3 = 42;

    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        // реализуйте алгоритм здесь
        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);
        userService.saveUser(testName1, testLastName1, testAge1);
        userService.saveUser(testName2, testLastName2, testAge2);
        userService.saveUser(testName3, testLastName3, testAge3);

//        userService.removeUserById(2L);

        List<User> allUsers = userService.getAllUsers();

        for (User user : allUsers) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
