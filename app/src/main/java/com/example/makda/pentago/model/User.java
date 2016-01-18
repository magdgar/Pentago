package com.example.makda.pentago.model;

/**
 * Created by Makda on 2016-01-18.
 */
public class User {
    private final String name;
    private final String password;

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }
}