package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ReminderTab extends Fragment{
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

                    reminderDialog.show(getActivity().getSupportFragmentManager(),"Reminder dialog");
                }
            });
            return view;
        }
}
