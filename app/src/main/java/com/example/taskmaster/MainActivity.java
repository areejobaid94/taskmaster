package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.example.taskmaster.Databases.AppDataBase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onStart()
    {
        super.onStart();
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            this.handleRViewShow();
        }catch (AmplifyException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        System.out.println("here");
        this.handleRViewShow();
    }

    private void handleRViewShow(){
        List<com.example.taskmaster.Models.Task> tasksToView = new ArrayList<>();
        try {
            Amplify.DataStore.query(Task.class,
                    tasks -> {
                        while (tasks.hasNext()) {
                            Task task = tasks.next();
                            tasksToView.add(new com.example.taskmaster.Models.Task(task.getTitle(),task.getBody(),task.getState()));
                            System.out.println(task);
                        }
                    },
                    failure -> Log.e("Tutorial", "Could not query DataStore", failure)
            );
            try{
                Thread.sleep(1500);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec_id_new);
                TaskAdpater taskAdpater = new TaskAdpater(this,tasksToView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(taskAdpater);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onStart();
        System.out.println("Start After");
        setContentView(R.layout.activity_main);
        android.text.format.DateFormat.getDateFormat(getApplicationContext());
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
    }
}