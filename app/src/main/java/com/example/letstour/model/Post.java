package com.example.letstour.model;

public class Post {
    private int person;
    private int cost;
    private String location;
    private String borderingPoint;
    private String description;
    private String date;
    private String agencyName;
    private String agencyKey;
    private String key;

    public Post() {
    }

    public Post(int person, int cost, String location, String borderingPoint, String description, String date, String agencyName, String agencyKey) {
        this.person = person;
        this.cost = cost;
        this.location = location;
        this.borderingPoint = borderingPoint;
        this.description = description;
        this.date = date;
        this.agencyName = agencyName;
        this.agencyKey = agencyKey;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public int getPerson() {
        return person;
    }

    public int getCost() {
        return cost;
    }

    public String getLocation() {
        return location;
    }

    public String getBorderingPoint() {
        return borderingPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String getAgencyKey() {
        return agencyKey;
    }


}
