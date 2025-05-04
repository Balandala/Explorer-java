package org.example.model;

import org.hibernate.Session;

public class UserDAO {
    private Session session;

    public UserDAO(Session session){
        this.session = session;
    }

    public UserDataSet GetUser(String username){
        UserDataSet dataSet = session.get(UserDataSet.class, username);
        session.close();
        return dataSet;
    }

    public void  CreateUser(UserDataSet user){
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }
}
