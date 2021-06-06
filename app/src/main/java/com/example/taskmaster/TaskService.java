package com.example.taskmaster;

import android.app.Service;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class TaskService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("Message", "From: " + remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {
            Log.d("Notification", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }

}
