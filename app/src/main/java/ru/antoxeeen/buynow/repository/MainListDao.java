package ru.antoxeeen.buynow.repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface MainListDao {

    @Insert
    void insert(MainList mainList);

    @Delete
    void delete(MainList mainList);

    @Update
    void update(MainList mainList);

    @Query("DELETE FROM table_lists")
    void deleteAllList();

    @Query("SELECT * FROM table_lists")
    LiveData<List<MainList>> getAllMainList();
}
