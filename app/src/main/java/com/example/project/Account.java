package com.example.project;

import androidx.annotation.NonNull;

public class Account {
    String username, password,id;
    boolean emergency;

    public Account(){}

    @NonNull
    @Override
    public String toString() {
        return username + " " + password + " " + id + " " + emergency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account(String username, String password, boolean emergency, String id) {
        this.username = username;
        this.password = password;
        this.emergency = emergency;
        this.id = id;
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

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }
}
