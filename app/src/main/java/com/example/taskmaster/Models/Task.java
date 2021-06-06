package com.example.taskmaster.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @ColumnInfo(name = "title")
    public  String title;

    @ColumnInfo(name = "body")
    public  String body;

    @ColumnInfo(name = "state")
    public State state;

    @ColumnInfo(name = "key")
    public String key;

    @ColumnInfo(name = "isImg")
    public boolean isImg;

    @PrimaryKey(autoGenerate = true)
    public int uid;

    public Task(String title, String body, State state,String key,boolean isImg) {
        this.title = title;
        this.body = body;
        this.state = state;
        this.key = key;
        this.isImg = isImg;
    }
    public Task() {
    }
}
