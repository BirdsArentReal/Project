package com.example.project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ChatDialog extends AppCompatDialogFragment {
    EditText chatET;
    ChatDialog.newMessageListener listener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_chat, null);
        chatET = view.findViewById(R.id.chatEditText);

        builder.setView(view);
        builder.setTitle("Add Chat");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Database.chatArrayList.add(chatET.getText().toString());
                listener.newMessage(chatET.getText().toString());
                Log.d("DatabaseChatArrayList - ChatDialog", Database.chatArrayList.toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ChatDialog.newMessageListener) context;
        } catch (Exception e) {
            Log.d("message change error", e.getMessage());
        }
    }

    public interface newMessageListener{
        void newMessage(String body);
    }
}
