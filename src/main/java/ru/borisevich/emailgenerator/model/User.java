package ru.borisevich.emailgenerator.model;

/**
 * Created by Leonid on 05.09.2016.
 */
public class User {
    private int user_id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int user_id, String username, String password){
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
