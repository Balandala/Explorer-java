package org.example.model;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private static final ArrayList<User> users = new ArrayList<User>();

    private String email;
    private String login;
    private String password;

    public User(String login, String password, String email){
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
}
