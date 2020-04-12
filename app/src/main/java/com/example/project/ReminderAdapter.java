package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder> {

    ArrayList<String> reminderArrayList;
    Context context;

    public ReminderAdapter(ArrayList<String> reminderArrayList, Context context) {
        this.reminderArrayList = reminderArrayList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return reminderArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        ImageView delete = holder.delete;
        TextView position_tv = holder.position_tv;
        TextView reminder_tv = holder.reminder_tv;
        final CardView cardView = holder.cardView;

        position_tv.setText(position+"");
        reminder_tv.setText(reminderArrayList.get(position));
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setVisibility(View.GONE);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.entity_reminder, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView delete;
        TextView position_tv, reminder_tv;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.reminderDeleteButton);
            position_tv = itemView.findViewById(R.id.reminderPosition);
            reminder_tv = itemView.findViewById(R.id.reminderText);
            cardView = itemView.findViewById(R.id.reminderCard_master);

        }


    }
}
