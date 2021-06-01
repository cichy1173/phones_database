package com.example.lab2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ElementDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Phone phone);

    @Query("DELETE FROM smartphone")
    void deleteAll();

    @Query("SELECT * FROM smartphone ORDER BY OEM ASC")
    LiveData<List<Phone>> getAlphabetizedElements();

    @Delete
    void deletePhone(Phone phone);
}
