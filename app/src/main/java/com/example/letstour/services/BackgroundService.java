package com.example.letstour.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.letstour.adapter.PostAdapter;
import com.example.letstour.database.entity.Event;
import com.example.letstour.database.repository.EventRepository;
import com.example.letstour.model.Post;
import com.example.letstour.utils.CommonTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BackgroundService extends Service {
    EventRepository eventRepository;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    List<Post> posts=new ArrayList<>();
    List<Event> events=new ArrayList<>();
    public BackgroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        insertIntoSQL(intent);
       return START_STICKY;

    }

    private void insertIntoSQL(Intent intent) {
   // String test=intent.getStringExtra("show");
     //   Toast.makeText(getApplicationContext(),test,Toast.LENGTH_SHORT).show();
        load();
    }

    private void load() {
        posts.clear();
        db.collection("post").whereEqualTo("agencyKey", CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_KEY))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Post data = document.toObject(Post.class);
                              //  data.setKey(document.getId());
                                Event event=new Event();
                                event.setPerson(data.getPerson());
                                event.setCost(data.getCost());
                                event.setLocation(data.getLocation());
                                event.setBorderingPoint(data.getBorderingPoint());
                                event.setDescription(data.getDescription());
                                event.setDate(data.getDate());
                                event.setAgencyName(data.getAgencyName());
                                event.setAgencyKey(data.getAgencyKey());
                                posts.add(data);
                                events.add(event);
                            }
                            Collections.reverse(posts);
                            eventRepository = new EventRepository(getApplicationContext());
                            eventRepository.addEvent(events);
                        } else {
                        }
                    }
                });
    }
}