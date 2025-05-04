package org.example.services;

import org.example.model.UserDataSet;
import org.example.model.UserDAO;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseService {

    static {
        Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/explorerdb");
        configuration.setProperty("hibernate.connection.username", "user");
        configuration.setProperty("hibernate.connection.password", "123456");
        configuration.setProperty("hibernate.show_sql", true);
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");

        configuration.addAnnotatedClass(UserDataSet.class);

        factory = configuration.buildSessionFactory();
    }

    private static final SessionFactory factory;

    public static boolean IsExists(String username){
        Session session = factory.openSession();
        UserDAO dao = new UserDAO(session);
        return dao.GetUser(username) != null;
    }

    public static boolean IsVerified(String username, String password){
        Session session = factory.openSession();
        UserDAO dao = new UserDAO(session);
        UserDataSet user = dao.GetUser(username);
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }

    public static void CreateNewUser(String username, String email, String password){
        Session session = factory.openSession();
        UserDAO dao = new UserDAO(session);
        UserDataSet dataSet = new UserDataSet(username, email, password);
        dao.CreateUser(dataSet);
    }

}
