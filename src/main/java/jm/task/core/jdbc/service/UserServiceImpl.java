package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoConnection = new UserDaoJDBCImpl();
//    UserDao userDaoConnection = new UserDaoHibernateImpl();

    public void createUsersTable() throws SQLException {
        userDaoConnection.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoConnection.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoConnection.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoConnection.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoConnection.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoConnection.cleanUsersTable();
    }
}
