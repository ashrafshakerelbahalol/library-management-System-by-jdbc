package com.libraryjdbc.entity;

public class user {
    private int user_id;
    private String name;
    private String address;
    private String email;
    private String phone;
  private String user_type;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public user(String name, String address, String email, String phone, String user_type) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.user_type = user_type;
    }

}
