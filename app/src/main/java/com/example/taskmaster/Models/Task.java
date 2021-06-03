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

    @PrimaryKey(autoGenerate = true)
    public int uid;

    public Task(String title, String body, State state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
}
