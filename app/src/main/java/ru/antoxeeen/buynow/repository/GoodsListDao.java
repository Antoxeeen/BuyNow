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

    @Query("DELETE FROM table_goodsList")
    void deleteAllGoods();

    @Query("SELECT * FROM table_goodsList")
    LiveData<List<GoodsList>> getAllGoods();

    @Query("SELECT * FROM table_goodsList WHERE listId = :listId ORDER BY id")
    LiveData<List<GoodsList>> getGoodsListFromListId(int listId);
}
