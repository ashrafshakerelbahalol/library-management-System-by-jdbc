package com.libraryjdbc.entity;

public class User {
    private int userId;
    private String name;
    private String address;
    private String email;
    private String phone;
   

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

  

    public User(String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
      
    }
    public User(){}

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", address=" + address + ", email=" + email + ", phone="
                + phone + "]";
    }

}
