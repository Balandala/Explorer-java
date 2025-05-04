package org.example.services;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.lang.ClassNotFoundException;

public class DatabaseService {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/explorerdb", "user", "123456");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Не удалось найти класс драйвера");
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось подключиться с базе данных");
        }
    }

    private final static Connection connection;

    public static boolean IsExists(String username){
        try {
            PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            prepStatement.setString(1, username);
            ResultSet resultSet = prepStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось проверить наличие пользователя");
        }
    }

    public static boolean IsVerified(String username, String password){
        try {
            PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
            prepStatement.setString(1, username);
            prepStatement.setString(2, password);
            ResultSet resultSet = prepStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в верефикации пользователя");
        }
    }

    public static void CreateNewUser(String username, String email, String password){
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
            insertStatement.setString(1, username);
            insertStatement.setString(2, email);
            insertStatement.setString(3, password);

            insertStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании пользователя");
        }
    }

}
