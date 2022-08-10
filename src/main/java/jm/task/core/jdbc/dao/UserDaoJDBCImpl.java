package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private final String CREATE_USER_TABLE = """
            CREATE TABLE IF NOT EXISTS users(id INT  NOT NULL AUTO_INCREMENT,
                                               name VARCHAR(45) NULL,
                                               lastname VARCHAR(45) NULL,
                                               age INT NULL,
                                               PRIMARY KEY (id));
            """;
    private final String DROP_USER_TABLE = """
            DROP TABLE IF EXISTS users;
            """;
    private final String INSERT_USER_TABLE = """
            INSERT INTO users(name, lastname, age)
            VALUES (?, ?, ?);
            """;
    private final String DETETE_USER_TABLE = """
            DELETE FROM users;
            """;

    private final String REMOVE_USER_TABLE = """
            DELETE FROM users 
            WHERE id = ?;
            """;
    private final String GET_ALL_USER_TABLE = """
            SELECT *
            FROM users
            """;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement state = super.getConnection().createStatement()) {
            state.executeUpdate(CREATE_USER_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement state = super.getConnection().prepareStatement(DROP_USER_TABLE)) {
            state.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement state = super.getConnection().prepareStatement(INSERT_USER_TABLE)) {
            state.setString(1, name);
            state.setString(2, lastName);
            state.setLong(3, age);
            state.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement state = super.getConnection().prepareStatement(REMOVE_USER_TABLE)) {
            state.setLong(1, id);
            state.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (PreparedStatement state = super.getConnection().prepareStatement(GET_ALL_USER_TABLE)) {
            ResultSet resSet = state.executeQuery();
            while (resSet.next()) {
                result.add(new User(resSet.getLong("id"),
                        resSet.getString("name"),
                        resSet.getString("lastname"),
                        (byte) resSet.getLong("age")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void cleanUsersTable() {
        try (PreparedStatement state = super.getConnection().prepareStatement(DETETE_USER_TABLE)) {
            state.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
