package com.example.letstour.model;

public class JoinRequest {
    private String agency_id;
    private String event_id;
    private String location;
    private String user_id;
    private String user_name;
    private String user_email;
    private String gender;
    private String user_pri_num;
    private String user_num1;
    private String user_num2;
    private String key;
    private String agency_name;

    public JoinRequest() {
    }

    public JoinRequest(String agency_id, String event_id, String location, String user_id, String user_name, String user_email, String gender, String user_pri_num, String user_num1, String user_num2, String agency_name) {
        this.agency_id = agency_id;
        this.event_id = event_id;
        this.location = location;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.gender = gender;
        this.user_pri_num = user_pri_num;
        this.user_num1 = user_num1;
        this.user_num2 = user_num2;
        this.key = key;
        this.agency_name = agency_name;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getLocation() {
        return location;
    }

    public String getUser_id() {
        return user_id;
    }


    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getGender() {
        return gender;
    }

    public String getUser_pri_num() {
        return user_pri_num;
    }

    public String getUser_num1() {
        return user_num1;
    }

    public String getUser_num2() {
        return user_num2;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }
}
