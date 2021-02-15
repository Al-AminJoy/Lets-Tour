package com.example.letstour.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int event_id;
    private int person;
    private int cost;
    private String location;
    private String borderingPoint;
    private String description;
    private String date;
    private String agencyName;
    private String agencyKey;

    public Event() {
    }

    public Event(int event_id, int person, int cost, String location, String borderingPoint, String description, String date, String agencyName, String agencyKey) {
        this.event_id = event_id;
        this.person = person;
        this.cost = cost;
        this.location = location;
        this.borderingPoint = borderingPoint;
        this.description = description;
        this.date = date;
        this.agencyName = agencyName;
        this.agencyKey = agencyKey;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBorderingPoint() {
        return borderingPoint;
    }

    public void setBorderingPoint(String borderingPoint) {
        this.borderingPoint = borderingPoint;
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

    public void setDate(String date) {
        this.date = date;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyKey() {
        return agencyKey;
    }

    public void setAgencyKey(String agencyKey) {
        this.agencyKey = agencyKey;
    }
}
