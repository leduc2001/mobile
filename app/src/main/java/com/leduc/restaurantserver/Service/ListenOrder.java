package com.leduc.restaurantserver.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leduc.restaurantserver.Model.Request;
import com.leduc.restaurantserver.OrderStatus;
import com.leduc.restaurantserver.R;

import java.util.Random;

public class ListenOrder extends Service implements ChildEventListener {

    FirebaseDatabase db;
    DatabaseReference orders;

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseDatabase.getInstance();
        orders = db.getReference("Requests");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        orders.addChildEventListener(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        Request request = snapshot.getValue(Request.class);
        if(request.getStatus().equals("0")) {
            showNotification(snapshot.getKey(),request);
        }
    }

    private void showNotification(String key, Request request) {
        Intent intent = new Intent(getBaseContext(), OrderStatus.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(),0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setTicker("Server")
                .setContentInfo("New Order")
                .setContentText("You have new order #" + key)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        int random_int = new Random().nextInt(9999-1)+1;
        manager.notify(random_int,builder.build());
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
