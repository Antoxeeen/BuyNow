package ru.antoxeeen.buynow.repository;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_lists", indices = {@Index(value = {"name"}, unique = true)})
public class MainList {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public MainList(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
