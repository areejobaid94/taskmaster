package com.example.taskmaster.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.taskmaster.Models.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Query("SELECT * FROM Task WHERE uid IN (:userIds)")
    List<Task> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Task WHERE title LIKE :title LIMIT 1")
    Task findTitle(String title);

    @Insert
    void insertAll(Task... tasks);

    @Delete
    void delete(Task task);
}