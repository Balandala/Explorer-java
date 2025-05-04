package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserDataSet {
    @Id
    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @SuppressWarnings("UnusedDeclaration")
    public UserDataSet(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    private UserDataSet(){
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
