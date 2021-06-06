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

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.example.taskmaster.Databases.AppDataBase;
import com.example.taskmaster.Models.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@androidx.annotation.NonNull @NotNull com.google.android.gms.tasks.Task task) {

                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = (String) task.getResult();
                        Log.d("token",token);
                    }
                });

        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", e);
        }
        findViewById(R.id.sign_up).setOnClickListener(v ->{
            Intent signup = new Intent(this,SignUpActivity.class);
            startActivity(signup);
        });
        TextView welcome = findViewById(R.id.usernameTasks);
        welcome.setText("User" + "’s tasks");
        Amplify.Auth.fetchAuthSession(
                result ->{
                    try {
                        Map<String, String> userData = AWSMobileClient.getInstance().getUserAttributes();
                        if (userData.get("name")==null){
                            userData.put("name",AWSMobileClient.getInstance().getUsername());
                        }
                        welcome.setText((userData.get("name") + "’s tasks"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.e("AuthQuickStart ", error.toString())
        );
//
//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "my@email.com")
//                .build();
//        Amplify.Auth.signUp("username", "Password123", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );
        // Add this line, to include the Auth plugin.


        android.text.format.DateFormat.getDateFormat(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.rec_id_new);
//        tasks.add(new Task("Task 1", "The first task body",2));
//        tasks.add(new Task("Task 2", "The 2nd task body",1));
//        tasks.add(new Task("Task 3", "The 3rd task body",0));
        findViewById(R.id.confirm).setOnClickListener(v->{
            Intent conf = new Intent(this,ConfirmSignUpActivity.class);
            conf.putExtra("username_signup", "Username");
            startActivity(conf);
        });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

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
//        });
        findViewById(R.id.log_in).setOnClickListener(v -> {
            Intent singin = new Intent(this, SignInActivity.class);
            startActivity(singin);
        });
        findViewById(R.id.sign_out).setOnClickListener(v -> {
            Amplify.Auth.fetchAuthSession(
                    result ->{
                        if(result.isSignedIn()) {
                            Amplify.Auth.signOut(
                                    () -> Log.i("AuthQuickstart", "Signed out successfully"),
                                    error -> Log.e("AuthQuickstart", error.toString())
                            );
                        }
                    },
                    error -> Log.e("AuthQuickStart ", error.toString())
            );

        });

    }
}