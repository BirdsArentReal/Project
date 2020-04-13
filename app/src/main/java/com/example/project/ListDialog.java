package com.example.project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ListDialog extends AppCompatDialogFragment {
    EditText remainingET, nameET;
    Spinner itemSpinner;
    String image_Selected;

    ListDialog.newListItemListener listener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_list_item, null);
        remainingET = view.findViewById(R.id.itemRemaining_et);
        nameET = view.findViewById(R.id.itemName_et);

        itemSpinner = view.findViewById(R.id.itemSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.item_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(adapter);

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: image_Selected = "meat"; break;
                    case 1: image_Selected = "fish"; break;
                    case 2: image_Selected = "egg"; break;
                    case 3: image_Selected = "fruit"; break;
                    case 4: image_Selected = "veggie"; break;
                    default: image_Selected = ""; break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                image_Selected = "";
            }
        });

        builder.setView(view);
        builder.setTitle("Add List Item");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.newListItem(nameET.getText().toString(), remainingET.getText().toString(), image_Selected);
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
            listener = (ListDialog.newListItemListener) context;
        } catch (Exception e) {
            Log.d("message change error", e.getMessage());
        }
    }

    public interface newListItemListener{
        void newListItem(String name, String remaining, String image);
    }
}