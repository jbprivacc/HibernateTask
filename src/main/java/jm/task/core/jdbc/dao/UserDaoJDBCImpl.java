package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;;

public class UserDaoJDBCImpl implements UserDao  {
    private static final Connection connection = Util.getInstance().getConnection();
    public UserDaoJDBCImpl() {

    }

    //создание таблицы
    public void createUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS userstable " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age INT)");

        } catch (SQLException exp) {
            exp.printStackTrace();
        }

    }


    // Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS userstable");

        } catch (SQLException exp) {
            exp.printStackTrace();
        }

    }


    //Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO userstable.people" +
                " (name, lastName, age) VALUES (?, ?, ?)")) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Удаление User из таблицы ( по id )
    public void removeUserById(long id) {
        try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM userstable.people WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        try(ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM userstable.people")) {
            while (resultSet.next()){
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;

    }

    // Очистка содержания таблицы
    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("TRUNCATE TABLE userstable.people");

        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
}