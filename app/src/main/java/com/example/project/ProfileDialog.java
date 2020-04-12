package com.example.project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ProfileDialog extends AppCompatDialogFragment {
    TextView tvPassword, tvUsername;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        sharedPref = getActivity().getApplication().getSharedPreferences("Login", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        View view = inflater.inflate(R.layout.dialog_profile, null);

        tvPassword = view.findViewById(R.id.passwordText);
        tvUsername = view.findViewById(R.id.nameText);

        tvUsername.setText(Database.account.getUsername());
        tvPassword.setText(Database.account.getPassword());

        builder.setView(view);
        builder.setTitle("Profile");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.clear();
                editor.commit();

                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Logging out", Toast.LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return builder.create();
    }
}
