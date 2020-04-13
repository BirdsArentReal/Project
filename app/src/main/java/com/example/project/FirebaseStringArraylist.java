package com.example.project;

public class FirebaseStringArraylist {
    SavableString savableString;
    String id;
    String firebaseID;

    public String getFirebaseID() {
        return firebaseID;
    }

    public void setFirebaseID(String firebaseID) {
        this.firebaseID = firebaseID;
    }


    public SavableString getSavableString() {
        return savableString;
    }

    public void setSavableString(SavableString savableString) {
        this.savableString = savableString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    FirebaseStringArraylist(){
    }

    public FirebaseStringArraylist(SavableString savableString, String id, String firebaseID) {
        this.savableString = savableString;
        this.id = id;
        this.firebaseID = firebaseID;
    }
}
