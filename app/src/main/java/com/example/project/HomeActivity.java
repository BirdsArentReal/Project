package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch(menuItem.getItemId()){
                        case R.id.homePage:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.chatPage:
                            selectedFragment = new ChatFragment();
                            break;

                        case R.id.listPage:
                            selectedFragment = new ListFragment();
                            break;

                        default: break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    public DatabaseReference reminderRef, chatRef;
    ArrayList<FirebaseStringArraylist> reminderLst, chatLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Action bar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Bottom Navigation View
        BottomNavigationView botNav = findViewById(R.id.bottom_nav_view);
        botNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();


        //Reminders
        reminderRef = Database.fd.getReference("Reminders");
        reminderLst = new ArrayList<>();

        //Chat
        chatRef = Database.fd.getReference("Chat");
        chatLst = new ArrayList<>();

        initialiseReferences();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.profilePic:
                ProfileDialog profileDialog = new ProfileDialog();
                profileDialog.show(getSupportFragmentManager(),"profile dialog");
                return true;

            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialiseReferences(){
        reminderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reminderLst.clear();
                for(DataSnapshot reminderSnapshot: dataSnapshot.getChildren()){
                    FirebaseStringArraylist temp = reminderSnapshot.getValue(FirebaseStringArraylist.class);
                    reminderLst.add(temp);
                }
                Database.reminderLst = reminderLst;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("error", databaseError.getMessage());
            }
        });

        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatLst.clear();
                for(DataSnapshot chatSnapshot: dataSnapshot.getChildren()){
                    FirebaseStringArraylist temp = chatSnapshot.getValue(FirebaseStringArraylist.class);
                    chatLst.add(temp);
                }
                Database.chatLst = chatLst;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("error", databaseError.getMessage());
            }
        });

        //Artificially updating
        String id = reminderRef.push().getKey();
        reminderRef.child(id).setValue(new SavableString("oh god please help"));
        reminderRef.child(id).removeValue();

        id = chatRef.push().getKey();
        chatRef.child(id).setValue(new SavableString("oh god please help"));
        chatRef.child(id).removeValue();

    }

}
