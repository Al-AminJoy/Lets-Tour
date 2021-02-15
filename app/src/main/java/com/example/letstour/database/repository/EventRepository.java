package com.example.letstour.database.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.letstour.database.entity.Event;
import com.example.letstour.database.tourdatabase;
import com.example.letstour.utils.CommonConstant;

import java.util.List;

public class EventRepository {
    private final tourdatabase database;
    Context context;

    public EventRepository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context,tourdatabase.class, CommonConstant.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public void addEvent(List<Event> events){
        database.eventDao().createEvent(events);
    }

    public List<Event> getAll(){
        return database.eventDao().getAll();
    }
    public void deleteAll(){
        database.eventDao().deleteAll();
    };


}
