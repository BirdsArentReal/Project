package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    ArrayList<ListItem> listItemArrayList;
    Context context;

    public ListAdapter(ArrayList<ListItem> listItemArrayList, Context context) {
        this.listItemArrayList = listItemArrayList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return listItemArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.remaining.setText(listItemArrayList.get(position).getRemaining());
        holder.name.setText(listItemArrayList.get(position).getName());

        switch (listItemArrayList.get(position).getImageURL()){
            case "meat":

                Picasso.get()
                        .load(R.drawable.pork)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(holder.item);
                break;
            case "fish":

                Picasso.get()
                        .load(R.drawable.fish)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(holder.item);
                break;

            case "egg":

                Picasso.get()
                        .load(R.drawable.egg)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(holder.item);
                break;

            case "fruit":

                Picasso.get()
                        .load(R.drawable.fruits)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(holder.item);
                break;

            case "veggie":

                Picasso.get()
                        .load(R.drawable.vegetables)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(holder.item);
                break;

            default:

                Picasso.get()
                        .load(R.mipmap.ic_launcher)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .centerCrop()
                        .into(holder.item);
                break;
        }

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.entity_list_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView edit,item;
        TextView name;
        EditText remaining;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.img_item);
            edit = itemView.findViewById(R.id.img_edit);
            name = itemView.findViewById(R.id.tv_name);
            remaining = itemView.findViewById(R.id.et_remaining);

        }


    }
}
