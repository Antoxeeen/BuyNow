package ru.antoxeeen.buynow.repository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface GoodsListDao {

    @Insert
    void insert(GoodsList goodsList);

    @Update
    void update(GoodsList goodsList);

    @Delete
    void delete(GoodsList goodsList);

    @Query("SELECT goods FROM table_goodsList WHERE listName = :listName")
    LiveData<List<String>> getAllGoods(String listName);


}
