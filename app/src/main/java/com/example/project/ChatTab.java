package com.example.project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatTab extends Fragment{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> chatArrayList;
    Button add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_chat_group, container, false);

        add = view.findViewById(R.id.chatButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChatDialog chatDialog = new ChatDialog();

                chatDialog.show(getActivity().getSupportFragmentManager(), "chat dialog");

                updateFirebase();

                preparechat();
            }
        });


        recyclerView = view.findViewById(R.id.chatRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        preparechat();

        return view;
    }

    public void preparechat(){

        chatArrayList = new ArrayList<>();

        for (FirebaseStringArraylist i: Database.chatLst){
            Log.d("prepare news", "we found one");
            if (i.getId().equals(Database.account.getId())){

                Log.d("prepare news", "found something");
                Database.chatID = i.getFirebaseID();
                chatArrayList = SavableString.convertToArrayList(i.getSavableString());
                Log.d("chatArrayList", chatArrayList+"");
                Log.d("DatabaseChatArrayList", Database.chatArrayList+"");
                if (Database.chatArrayList.size() < chatArrayList.size() && Database.chatRemoved == false){
                    Database.chatArrayList = chatArrayList;
                }
                else {
                    chatArrayList = Database.chatArrayList;
                    updateFirebase();
                }
            }
        }

        ChatAdapter chatAdapter = new ChatAdapter(chatArrayList, getActivity());
        recyclerView.setAdapter(chatAdapter);
        Log.d("preparechat", "no problems");
        Log.d("Firebase id", Database.chatID+"");
    }

    public void updateFirebase(){

        chatArrayList = Database.chatArrayList;
        DatabaseReference chatRef = Database.fd.getReference("Chat");

        chatRef.child(Database.chatID).setValue(new FirebaseStringArraylist(
                SavableString.convertToSavableString(chatArrayList), Database.account.getId(), Database.chatID));
        Log.d("updateFirebase", "no problems");
    }
}
