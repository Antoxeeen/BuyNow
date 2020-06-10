package ru.antoxeeen.buynow.repository;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = MainList.class, parentColumns = "name",
        childColumns = "listName", onDelete = CASCADE, onUpdate = CASCADE)}, tableName = "table_goodsList")
public class GoodsList {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String goods;

    private String listName;

    public GoodsList(String goods, String listName) {
        this.goods = goods;
        this.listName = listName;
    }

    public String getListName() {
        return listName;
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
