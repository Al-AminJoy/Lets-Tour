package com.example.letstour.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
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

    protected Post(Parcel in) {
        person = in.readInt();
        cost = in.readInt();
        location = in.readString();
        borderingPoint = in.readString();
        description = in.readString();
        date = in.readString();
        agencyName = in.readString();
        agencyKey = in.readString();
        key = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(person);
        dest.writeInt(cost);
        dest.writeString(location);
        dest.writeString(borderingPoint);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(agencyName);
        dest.writeString(agencyKey);
        dest.writeString(key);
    }

    @Override
    public String toString() {
        return "Post{" +
                "person=" + person +
                ", cost=" + cost +
                ", location='" + location + '\'' +
                ", borderingPoint='" + borderingPoint + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", agencyKey='" + agencyKey + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
