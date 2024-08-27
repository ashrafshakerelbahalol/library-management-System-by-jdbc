package com.libraryjdbc.entity;

public class User {
    private int userId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String userType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public User(String name, String address, String email, String phone, String userType) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }
    public User(){}

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", address=" + address + ", email=" + email + ", phone="
                + phone + ", userType=" + userType + "]";
    }

}
