package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.taskmaster.Databases.AppDataBase;
import com.example.taskmaster.Models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdpater taskAdpater;
    @Override
    protected void onStart()
    {
        super.onStart();
        List<Task> tasks = AppDataBase.getAppDataBase(getApplicationContext()).taskDao().getAll();
        taskAdpater = new TaskAdpater(this,tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(taskAdpater);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onStart();
        setContentView(R.layout.activity_main);
//        setupRecyclerView(R.layout.task);
        android.text.format.DateFormat.getDateFormat(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.rec_id_new);
//        tasks.add(new Task("Task 1", "The first task body",2));
//        tasks.add(new Task("Task 2", "The 2nd task body",1));
//        tasks.add(new Task("Task 3", "The 3rd task body",0));
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TextView welcome =  findViewById(R.id.usernameTasks);
        if(preferences.getString("username","User") != ""){
            welcome.setText(preferences.getString("username","User") + "’s tasks");
        }else{
            welcome.setText("User" + "’s tasks");
        }
        Button button =  findViewById(R.id.addTask);
        TextView usernameTasks = findViewById(R.id.usernameTasks);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent k = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(k);
            }
        });
        Button allTasks =  findViewById(R.id.allTasks);
        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent k = new Intent(MainActivity.this, AllTasksActivity.class);
                startActivity(k);
            }
        });

        Button setting =  findViewById(R.id.settings);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent k = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(k);
            }
        });

//
//        Button task1 =  findViewById(R.id.task1);
//        task1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent k = new Intent(MainActivity.this, TaskDetailActivity.class);
//                k.putExtra("title","Task 1");
//                k.putExtra("description","Description for Task 1");
//                startActivity(k);
//            }
//        });
//        Button task2 =  findViewById(R.id.task2);
//        task2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent k = new Intent(MainActivity.this, TaskDetailActivity.class);
//                k.putExtra("title","Task 2");
//                k.putExtra("description","Description for Task 2");
//                startActivity(k);
//            }
//        });
//
//        Button task3 =  findViewById(R.id.task3);
//        task3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                Intent k = new Intent(MainActivity.this, TaskDetailActivity.class);
//                k.putExtra("title","Task 3");
//                k.putExtra("description","Description for Task 3");
//
//                startActivity(k);
//            }
//        });
    }
}