package com.example.letstour.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.letstour.database.dao.eventDao;
import com.example.letstour.database.entity.Event;

@Database(entities = {Event.class},version = 1,exportSchema = false)
public abstract class tourdatabase extends RoomDatabase {
    public abstract eventDao eventDao();
}