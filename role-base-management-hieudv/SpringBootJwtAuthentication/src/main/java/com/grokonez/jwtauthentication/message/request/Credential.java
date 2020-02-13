package com.grokonez.jwtauthentication.message.request;

import org.springframework.stereotype.Service;

@Service
public class Credential {
    private String userName;
    private String userPassword;

    public Credential() {
    }

    public Credential(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
