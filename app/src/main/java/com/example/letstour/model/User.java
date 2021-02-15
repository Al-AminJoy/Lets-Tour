package com.example.letstour.model;

public class User {
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String gender;
    private String image;
    private String pri_num;
    private String num1;
    private String num2;
    private String key;

    public User() {
    }

    public User(String first_name, String last_name, String address, String email, String gender, String image, String pri_num, String num1, String num2) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.image = image;
        this.pri_num = pri_num;
        this.num1 = num1;
        this.num2 = num2;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

    public String getPri_num() {
        return pri_num;
    }

    public String getNum1() {
        return num1;
    }

    public String getNum2() {
        return num2;
    }
}
