package com.example.project;

import androidx.annotation.NonNull;

public class ListItem {
    String imageURL, name, remaining, id, firebaseID;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirebaseID() {
        return firebaseID;
    }

    public void setFirebaseID(String firebaseID) {
        this.firebaseID = firebaseID;
    }

    ListItem(){}

    public ListItem(String imageURL, String name, String remaining, String id, String firebaseID) {
        this.imageURL = imageURL;
        this.name = name;
        this.remaining = remaining;
        this.id = id;
        this.firebaseID = firebaseID;

        if (remaining.trim().equals("")){
            this.remaining = "";
        }
    }

    @NonNull
    @Override
    public String toString() {
        return name + " " + remaining + " " + imageURL;
    }
}
