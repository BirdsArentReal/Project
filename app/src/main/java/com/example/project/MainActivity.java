package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

        /*SharedPreferences sharedPref = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        int checked = sharedPref.getInt("logged in", 0);
        if (checked == 1) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        else {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("logged in", 1);
            editor.commit();
        }*/
    }
}
