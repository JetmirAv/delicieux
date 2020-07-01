package edu.fiek.delicieux.models;

public class User {

    String email;
    String  mobile;
    String  username;
    String  avatar;

    public User(String email, String mobile, String username, String avatar) {
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.avatar = avatar;
    }
    public User (){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
