package com.example.dating_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoSetting {

    @Query("SELECT * FROM settings")
    LiveData<List<Settings>> getSettings();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveSettings(Settings... settings);
}
