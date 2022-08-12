package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Алексей", "Малинин", (byte) 25);
        us.saveUser("Георгий", "Брынза", (byte) 34);
        us.saveUser("Василий", "Пупкин", (byte) 47);
        us.saveUser("Николай", "Николаев", (byte) 22);
        List<User> allUsers = us.getAllUsers();
        allUsers.forEach(System.out::println);
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
