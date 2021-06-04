package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        TextView textViewTitle = findViewById(R.id.title);
        textViewTitle.setText(getIntent().getExtras().getString("title"));

        TextView textViewDescription = findViewById(R.id.description);
        textViewDescription.setText(getIntent().getExtras().getString("description"));
        String key = getIntent().getExtras().getString("key");
        String isImg = getIntent().getExtras().getString("is_img");
        if(key != "" && isImg == "true"){
            download(key);
        };
    }
    private void download (String fileName){
        Amplify.Storage.downloadFile(
                fileName,
                new File(getApplicationContext().getFilesDir() + "/"+fileName+".jpg"),
                result -> Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getPath()),
                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
        );
    }
}