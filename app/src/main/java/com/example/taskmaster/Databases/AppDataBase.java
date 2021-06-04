package com.example.taskmaster.Databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.taskmaster.DAOs.TaskDao;
import com.example.taskmaster.Models.Task;

@Database(entities = {Task.class},version=1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
    private static  volatile  AppDataBase appDataBase;

    public static AppDataBase getAppDataBase;

    public static AppDataBase getAppDataBase(Context context) {
        if(appDataBase == null){
            synchronized (AppDataBase.class){
                if(appDataBase == null) {
                    appDataBase = Room.databaseBuilder(context,AppDataBase.class,"task").allowMainThreadQueries().build();
                }
            }
        }
        return appDataBase;
    }
}