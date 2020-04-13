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

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ListItem> listItemArrayList;
    Button add;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        add = view.findViewById(R.id.listButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDialog listDialog = new ListDialog();

                listDialog.show(getActivity().getSupportFragmentManager(), "List dialog");

                prepareListItems();
            }
        });


        recyclerView = view.findViewById(R.id.listRecyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        prepareListItems();

        return view;
    }

    public void prepareListItems(){

        listItemArrayList = new ArrayList<>();

        for (ListItem i: Database.listItemArrayList){
            if (i.getId().equals(Database.account.getId())){
                listItemArrayList.add(i);
            }
        }

        ListAdapter listAdapter = new ListAdapter(listItemArrayList, getActivity());
        recyclerView.setAdapter(listAdapter);
        Log.d("prepareReminder", "no problems");
    }
}
