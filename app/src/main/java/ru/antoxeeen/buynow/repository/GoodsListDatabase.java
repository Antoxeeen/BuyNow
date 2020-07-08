package ru.antoxeeen.buynow.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainList.class, GoodsList.class}, version = 8)
public abstract class GoodsListDatabase extends RoomDatabase {

    private static GoodsListDatabase instance;

    public abstract MainListDao mainListDao();
    public abstract GoodsListDao goodsListDao();

    public static synchronized GoodsListDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    GoodsListDatabase.class, "goods_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
