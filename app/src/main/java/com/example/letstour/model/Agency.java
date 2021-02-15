package com.example.letstour.model;

public class Agency {
    private String name;
    private String email;
    private String address;
    private String image;
    private String pri_num;
    private String num1;
    private String num2;
    private String key;

    public Agency() {
    }

    public Agency(String name, String email, String address, String image, String pri_num, String num1, String num2) {
        this.name = name;
        this.email = email;
        this.address = address;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
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
