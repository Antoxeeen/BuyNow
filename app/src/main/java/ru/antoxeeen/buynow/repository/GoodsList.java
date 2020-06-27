package ru.antoxeeen.buynow.repository;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = MainList.class, parentColumns = "id",
        childColumns = "listId", onDelete = CASCADE, onUpdate = CASCADE)}, tableName = "table_goodsList")
public class GoodsList {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String goods;

    private int listId;

    public GoodsList(String goods, int listId) {
        this.goods = goods;
        this.listId = listId;
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

    public void setId(int id) {
        this.id = id;
    }
}
