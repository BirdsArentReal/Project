package com.example.project;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;

public class EmergencyTab extends Fragment{
        Button announce, resolve;
        final DatabaseReference dbRef = Database.fd.getReference("Account");
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.tab_emergency, container, false);
            announce = view.findViewById(R.id.emergencyButton);
            resolve = view.findViewById(R.id.resolveButton);
            resolve.setEnabled(false);

            createNotificationChannel();

            final NotificationCompat.Builder builder1 = new NotificationCompat.Builder(getContext(), "Emergency")
                    .setSmallIcon(R.drawable.ic_warning_black_24dp)
                    .setContentTitle("Emergency")
                    .setContentText("A user of your account has announced an emergency")
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            final NotificationCompat.Builder builder2 = new NotificationCompat.Builder(getContext(), "Emergency")
                    .setSmallIcon(R.drawable.ic_check_black_24dp)
                    .setContentTitle("Resolved")
                    .setContentText("A user of your account has resolved the emergency")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());

            announce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificationManagerCompat.notify(1, builder1.build());
                    resolve.setEnabled(true);
                    announce.setEnabled(false);

                    Database.account.setEmergency(true);
                    dbRef.child(Database.account.id).setValue(
                            new Account(Database.account.getUsername(),
                                    Database.account.getPassword(),
                                    Database.account.isEmergency(),
                                    Database.account.id));
                }
            });

            resolve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    notificationManagerCompat.notify(1, builder2.build());
                    resolve.setEnabled(false);
                    announce.setEnabled(true);

                    Database.account.setEmergency(false);
                    dbRef.child(Database.account.id).setValue(
                            new Account(Database.account.getUsername(),
                                    Database.account.getPassword(),
                                    Database.account.isEmergency(),
                                    Database.account.id));
                }
            });
            return view;
        }

        private void createNotificationChannel(){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                CharSequence name = "Emergency";
                String description = "For announcing emergencies";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel = new NotificationChannel("Emergency", name, importance);
                notificationChannel.setDescription(description);

                NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(notificationChannel);
            }

        }
}
