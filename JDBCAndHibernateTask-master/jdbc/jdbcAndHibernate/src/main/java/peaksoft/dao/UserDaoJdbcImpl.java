package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            //creating table
            statement.executeQuery("create table users ( " +
                    " id serial primary key , " +
                    " name varchar not null , " +
                    " last_name varchar not null ," +
                    " age int not null );");
            System.out.println("CREATED");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.connect();
        Statement statement = connection.createStatement() ) {
            statement.executeQuery("drop table users;");
            System.out.println("DROPPED");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlZapros = "insert into users ( name, last_name, age) values (?, ?, ?);";
        try (Connection connection = Util.connect();
        PreparedStatement statement = connection.prepareStatement(sqlZapros)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("INSERTED");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.connect();
             PreparedStatement statement = connection.prepareStatement(
                     "delete from users where id = ? ;")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("DELETED");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.connect();
        Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from users;")) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age"));
                users.add(user);
            }
            System.out.println(users.size() + " users founded!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.connect();
        Statement statement = connection.createStatement()) {
            statement.executeQuery("delete from users;");
            System.out.println("DELETED ALL USERS");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
}