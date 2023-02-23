package Entities;

import java.sql.Connection;
import java.sql.Statement;

public class User {
    private int id;
    private String name;
    private String nickname;
    private int level;

    private String password;

    public User (String name, String username){
        setName(name);
        setNickname(nickname);
    }
    public User(String name, String nickname, String password){
        setName(name);
        setNickname(nickname);
        setPassword(password);
    }



//    getter and setters


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLevel(int level) {
        this.level = level;
    }
//    methods
}
