package ru.antoxeeen.buynow.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = MainList.class, parentColumns = "id",
        childColumns = "listId", onDelete = CASCADE, onUpdate = CASCADE)}, tableName = "table_goodsList")
public class GoodsList {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String goods;

    @ColumnInfo(name = "listId")
    private int listId;

    public GoodsList(String goods) {
        this.goods = goods;
    }

    public int getListId() {
        return listId;
    }

    public int getId() {
        return id;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
