package com.mischaboldy.mischa.rehapp;

/**
 * Created by mischa on 11/07/16.
 */
public class Users {
    private String userName;
    private String medals;

    public Users(String name, String medalsAchieved){
        userName = name;
        medals = medalsAchieved;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMedals() {
        return medals;
    }

    public void setMedals(String medals) {
        this.medals = medals;
    }
}
