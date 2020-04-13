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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SignUpActivity extends AppCompatActivity {
    EditText username_et, password_et;
    DatabaseReference db;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = Database.fd.getReference("Account");
        username_et = findViewById(R.id.signUpUsername);
        password_et = findViewById(R.id.signUpPassword);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Read from the database
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Database.accLst.clear();
                for(DataSnapshot accSnapshot: dataSnapshot.getChildren()){
                    Account acc = accSnapshot.getValue(Account.class);
                    Database.accLst.add(acc);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("error: ", error.getMessage());
            }
        });
        String id = db.push().getKey();
        Account temp = new Account("temp", "temp", false, id+"");
        db.child(id).setValue(temp);
        db.child(id).removeValue();
    }

    public void onSignInClicked(View view){
        String username = username_et.getText().toString();
        String password = password_et.getText().toString();

        for (Account acc: Database.accLst){
            if (username.equals(acc.getUsername())){
                Toast toast = Toast.makeText(getApplicationContext(), "That username is taken :(", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
        }

        //creating a new account object for the account in firebase
        String id = db.push().getKey();
        Account acc = new Account(username, password, false, id);
        db.child(id).setValue(acc);

        //creating a new reminders object for the account in firebase
        DatabaseReference reminderRef = Database.fd.getReference("Reminders");
        String reminderID = reminderRef.push().getKey();
        FirebaseStringArraylist temp = new FirebaseStringArraylist(new SavableString(), id, reminderID);
        reminderRef.child(reminderID).setValue(temp);

        //creating a new chat object for the account in firebase
        DatabaseReference chatRef = Database.fd.getReference("Chat");
        String chatID = reminderRef.push().getKey();
        FirebaseStringArraylist temp2 = new FirebaseStringArraylist(new SavableString(), id, chatID);
        chatRef.child(chatID).setValue(temp2);

        //initialising app
        Database.account = acc;

        //SharedPreferences so logging in twice is not needed
        sharedPref = getApplication().getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("logged_in", 1);
        editor.putString("username", Database.account.getUsername());
        editor.putString("password", Database.account.getPassword());
        editor.commit();

        Intent intent = new Intent(this, HomeActivity.class);
        Toast toast = Toast.makeText(getApplicationContext(), "Going to home page", Toast.LENGTH_LONG);

        toast.show();
        startActivity(intent);
    }
}
