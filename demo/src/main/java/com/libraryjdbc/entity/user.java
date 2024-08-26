package com.libraryjdbc.entity;

public class user {
    private int userId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String userType;

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_type() {
        return userType;
    }

    public void setUser_type(String user_type) {
        this.userType = user_type;
    }

    public user(String name, String address, String email, String phone, String userType) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }

}
