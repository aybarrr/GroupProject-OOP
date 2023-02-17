package Entities;

import java.sql.Connection;
import java.sql.Statement;

public class User {
    private int id;
    private String name;
    private String surname;
    private String nickname;
    private int level;

    public User(String name, String surname, String nickname){
        setName(name);
        setSurname(surname);
        setNickname(nickname);
    }

//    getter and setters


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLevel(int level) {
        this.level = level;
    }
//    methods


}
