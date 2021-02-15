package com.example.letstour.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.letstour.database.entity.Event;

import java.util.List;
@Dao
public interface eventDao {
    @Insert
    void createEvent(List<Event> users);
    @Query("SELECT * FROM Event")
    List<Event> getAll();
    @Query("DELETE FROM Event")
    void deleteAll();

}
