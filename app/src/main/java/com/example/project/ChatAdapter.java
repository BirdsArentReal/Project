package com.example.project;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    ArrayList<String> chatArrayList;
    Context context;

    public ChatAdapter(ArrayList<String> chatArrayList, Context context) {
        this.chatArrayList = chatArrayList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return chatArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        ImageView delete = holder.delete;
        TextView position_tv = holder.position_tv;
        TextView chat_tv = holder.chat_tv;
        //final CardView cardView = holder.cardView;

        position_tv.setText(position + "");
        chat_tv.setText(chatArrayList.get(position));
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideitem(position);
            }
        });
    }

    public void hideitem(final int position) {
        Database.chatArrayList.remove(position);
        Database.chatRemoved = true;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.entity_chat, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView delete;
        TextView position_tv, chat_tv;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.chatDeleteButton);
            position_tv = itemView.findViewById(R.id.chatPosition);
            chat_tv = itemView.findViewById(R.id.chatText);
            cardView = itemView.findViewById(R.id.chatCard_master);

        }


    }
}

