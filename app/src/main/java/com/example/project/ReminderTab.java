package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReminderTab extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> reminderArrayList;
    Button add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_reminder, container, false);
        add = view.findViewById(R.id.reminderButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReminderDialog reminderDialog = new ReminderDialog();

                reminderDialog.show(getActivity().getSupportFragmentManager(), "Reminder dialog");

                updateFirebase();

                prepareReminder();
            }
        });


        recyclerView = view.findViewById(R.id.reminderRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        prepareReminder();

        return view;
    }

    public void prepareReminder(){

        reminderArrayList = new ArrayList<>();

        for (FirebaseStringArraylist i: Database.reminderLst){
            Log.d("prepare news", "we found one");
            if (i.getId().equals(Database.account.getId())){

                Log.d("prepare news", "found something");
                Database.reminderID = i.getFirebaseID();
                reminderArrayList = SavableString.convertToArrayList(i.getSavableString());
                Log.d("reminderArrayList", reminderArrayList+"");
                Log.d("DatabaseReminderArrayList", Database.reminderArrayList+"");
                if (Database.reminderArrayList.size() < reminderArrayList.size() && Database.reminderRemoved == false){
                    Database.reminderArrayList = reminderArrayList;
                }
                else {
                    reminderArrayList = Database.reminderArrayList;
                    updateFirebase();
                }
            }
        }

        ReminderAdapter reminderAdapter = new ReminderAdapter(reminderArrayList, getActivity());
        recyclerView.setAdapter(reminderAdapter);
        Log.d("prepareReminder", "no problems");
        Log.d("Firebase id", Database.reminderID+"");
    }

    public void updateFirebase(){

        reminderArrayList = Database.reminderArrayList;
        DatabaseReference reminderRef = Database.fd.getReference("Reminders");

        reminderRef.child(Database.reminderID).setValue(new FirebaseStringArraylist(
                SavableString.convertToSavableString(reminderArrayList), Database.account.getId(), Database.reminderID));
        Log.d("updateFirebase", "no problems");
    }
}
