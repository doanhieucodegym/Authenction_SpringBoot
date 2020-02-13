package com.grokonez.jwtauthentication.model;



import java.time.LocalDateTime;

/** Table hiro_user_info **/
public class User {
    private String id;
    private String userName;
    private String pwHash;
    private String type;
    private String email;
    private String role;
    private long lastLoginTime;
    private long changePasswordTime;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public User(String userName, String pwHash, String type, String email, String role, long lastLoginTime, long changePasswordTime) {
        this.userName = userName;
        this.pwHash = pwHash;
        this.type = type;
        this.email = email;
        this.role = role;
        this.lastLoginTime = lastLoginTime;
        this.changePasswordTime = changePasswordTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public long getChangePasswordTime() {
        return changePasswordTime;
    }

    public void setChangePasswordTime(long changePasswordTime) {
        this.changePasswordTime = changePasswordTime;
    }
}
