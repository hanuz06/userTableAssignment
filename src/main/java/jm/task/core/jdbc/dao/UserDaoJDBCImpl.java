package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String sql;
    Util worker = new Util();
    Connection connection = worker.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                "  id BIGINT(20) NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(64) NOT NULL,\n" +
                "  last_name VARCHAR(64) NOT NULL,\n" +
                "  age TINYINT NOT NULL,\n" +
                "  PRIMARY KEY (id),\n" +
                "  UNIQUE INDEX id_UNIQUE (id ASC));";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        sql = "DROP TABLE IF EXISTS users;";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String last_name, byte age) {
        sql = "INSERT INTO users(name, last_name, age ) values (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        sql = "DELETE FROM users where id=?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        sql = "SELECT * from users";

        List<User> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String last_name = rs.getString(3);
                Byte age = rs.getByte("age");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(last_name);
                user.setAge(age);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        sql = "DELETE FROM users;";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
