package Entities;

import java.sql.Connection;
import java.sql.Statement;

public class User {
    private int id;
    private String name;
    private String nickname;
    private int level;

    public User(String name, String nickname){
        setName(name);
        setNickname(nickname);
    }

//    getter and setters


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
