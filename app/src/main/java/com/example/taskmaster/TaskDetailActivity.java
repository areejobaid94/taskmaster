package com.example.taskmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.File;
import java.util.Objects;

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
        String location = getIntent().getExtras().getString("location");
        TextView textLocation= findViewById(R.id.location);
        textLocation.setText(location);

        System.out.println(key);
        System.out.println(isImg);
        System.out.println(getIntent().getExtras().getString("description"));
        if(!key.equals("") && isImg.equals("true")){
            download(key);
        };
    }

    @Override
    public boolean equals(@Nullable @org.jetbrains.annotations.Nullable Object obj) {
        return super.equals(obj);
    }

    private void download (String fileName){
        System.out.println(fileName);
        System.out.println("fileName");
        try {
            Amplify.Storage.downloadFile(
                    fileName,
                    new File(getApplicationContext().getFilesDir() + "/"+fileName+".jpg"),
                    result -> {
                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getPath());
                    },
                    error -> Log.e("MyAmplifyApp",  "Download Failure", error)
            );
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (Exception e) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", e);
        }

    }

}