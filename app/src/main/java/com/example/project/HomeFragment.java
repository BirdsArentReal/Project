package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    CardView calendar, links, food, health;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        calendar = view.findViewById(R.id.calendarCard);
        links = view.findViewById(R.id.linkCard);
        food = view.findViewById(R.id.foodCard);
        health = view.findViewById(R.id.healthCard);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarAction();
            }
        });

        links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.link = "links"; goToLinks();
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.link = "food"; goToLinks();
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Database.link = "health"; goToLinks();
            }
        });

        return view;
    }

    private void goToLinks(){
        Toast toast = Toast.makeText(getContext(), "Going to links", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(getActivity(), LinkActivity.class);
        startActivity(intent);
    }

    private void calendarAction(){
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, "")
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "")
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, "")
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, "");
        try{
            startActivity(intent);
        }
        catch (Exception e){
            Log.d("calendar", e.getMessage());
            Toast toast = Toast.makeText(getContext(), "An Error Occurred", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
