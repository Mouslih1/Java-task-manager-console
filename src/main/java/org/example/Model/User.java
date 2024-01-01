package org.example.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private int id;
    private String login;
    private String password;
    private String role;
    private static ArrayList<User> users = new ArrayList<User>();
    private static User userConnect;

    public User() {
    }

    public User(int id,String login, String password, String role)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        users.add(this);
    }

    public User(String login, String password, String role)
    {
        this.login = login;
        this.password = password;
        this.role = role;
        users.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public static User getUserConnect() {
        return userConnect;
    }

    public static void setUserConnect(User userConnect) {
        User.userConnect = userConnect;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password.hashCode() + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
