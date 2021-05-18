package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        TextView textViewTitle = findViewById(R.id.title);
        textViewTitle.setText(getIntent().getExtras().getString("title"));

        TextView textViewDescription = findViewById(R.id.description);
        textViewDescription.setText(getIntent().getExtras().getString("description"));
    }
}