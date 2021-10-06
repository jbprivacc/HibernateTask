package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//here too
public class UserServiceImpl implements UserService {
    UserDao userDao =  new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        userDao.createUsersTable();

    }

    public void dropUsersTable() throws SQLException {
    userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");

    }

    public void removeUserById(long id) throws SQLException {
        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for(User user: users){
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        userDao.cleanUsersTable();

    }
}