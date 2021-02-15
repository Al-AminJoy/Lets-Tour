package com.example.letstour.model;

public class EventUser {
private String event_id;
private String agency_id;
private String location;
private String user_id;
private String user_name;
private String user_number;
private String key;

    public EventUser() {
    }

    public EventUser(String event_id, String agency_id, String location, String user_id, String user_name, String user_number) {
        this.event_id = event_id;
        this.agency_id = agency_id;
        this.location = location;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_number = user_number;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getAgency_id() {
        return agency_id;
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

    public String getUser_number() {
        return user_number;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
