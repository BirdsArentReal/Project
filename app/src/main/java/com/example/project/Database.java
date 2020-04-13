package com.example.project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Database {
    public static ArrayList<Account> accLst = new ArrayList<>();
    public static ArrayList<FirebaseStringArraylist> reminderLst = new ArrayList<>();
    public static ArrayList<FirebaseStringArraylist> chatLst = new ArrayList<>();
    public static String reminderID, chatID;
    public static Account account;

    public static FirebaseDatabase fd;
    public static StorageReference sr;

    public static ArrayList<String> reminderArrayList = new ArrayList<>(), chatArrayList = new ArrayList<>();
    public static boolean reminderRemoved = false, chatRemoved = false;

    Database(){
        fd = FirebaseDatabase.getInstance();
    }


}
