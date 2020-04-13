package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    DatabaseReference db;
    Database database;
    EditText usernameTxt, passwordTxt;
    int checked;
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameTxt = findViewById(R.id.loginUsername);
        passwordTxt = findViewById(R.id.loginPassword);
        /*Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);*/
        database = new Database();
        db = Database.fd.getReference("Account");

        sharedPref = getApplication().getSharedPreferences("Login", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();


        //editor.clear();
//        editor.commit();

        checked = sharedPref.getInt("logged_in", 0);
        username = sharedPref.getString("username", "");
        password = sharedPref.getString("password", "");
        //accPos = sharedPref.getInt("acc pos", -1);

        Log.d("checked", checked+" ");
        Log.d("username", username+"");
        Log.d("password", password+"");

    }

    protected void onStart() {
        super.onStart();
        // Read from the database

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d("DB Update", "this happened");
                Database.accLst.clear();
                for(DataSnapshot accSnapshot: dataSnapshot.getChildren()){
                    Account acc = accSnapshot.getValue(Account.class);
                    Database.accLst.add(acc);
                }
                Log.d("DB Update accLst", Database.accLst+"");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("error: ", error.getMessage());
            }
        });

        //artificially making an update to initialise accLst
        String id = db.push().getKey();
        Account test = new Account("temp", "temp", false, "temp");
        db.child(id).setValue(test);
        db.child(id).removeValue();

        Log.d("checked",checked+"");
        Log.d("username", username);
        Log.d("password", password);


        if(checked == 1 && !username.equals("")){
            Log.d("accLst", Database.accLst.toString());
/*            for (int i = 0; i < Database.accLst.size(); i++){
                if (username.equals(Database.accLst.get(i).getUsername()) && password.equals(Database.accLst.get(i).getPassword())){
                    Log.d("set stuff", "yay");
                    usernameTxt.setText(username);
                    passwordTxt.setText(password);
                    break;
                }

              }*/
            usernameTxt.setText(username);
            passwordTxt.setText(password);

            /*Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);*/
        }
    }

    public void onSignUpClicked(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "going to sign up screen", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);

    }

    public void onLoginClicked(View view){

        String username_entered = usernameTxt.getText().toString(),
                password_entered = passwordTxt.getText().toString();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("logged_in", 0);
        editor.commit();

        for (Account acc: Database.accLst){
            if (username_entered.equals(acc.getUsername()) && password_entered.equals(acc.getPassword())){

                editor.putInt("logged_in", 1);
                editor.putString("username", username_entered);
                editor.putString("password", password_entered);
                editor.commit();
                Database.account = acc;

                Toast toast = Toast.makeText(getApplicationContext(), "highly suspect", Toast.LENGTH_LONG);
                toast.show();

                break;
            }
        }

        checked = sharedPref.getInt("logged_in", 0);

        if(checked == 0){
            Toast toast = Toast.makeText(getApplicationContext(), "username and password do not match, or does not exist", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        //Toast toast = Toast.makeText(getApplicationContext(), "going back to home screen", Toast.LENGTH_LONG);
        //toast.show();

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);

    }
}
