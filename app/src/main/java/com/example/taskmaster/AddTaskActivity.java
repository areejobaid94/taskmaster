package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.State;
import com.amplifyframework.datastore.generated.model.Task;
import com.example.taskmaster.Databases.AppDataBase;

public class AddTaskActivity extends AppCompatActivity {
    private Spinner spinner;
    private static final String[] paths = {"new", "assigned", "in progress","complete"};
    private int selectedItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Button button =  findViewById(R.id.addTask_add);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddTaskActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    selectedItem =  item.toString() == "new" ? 1: item.toString() == "assigned" ? 2 : item.toString() == "in progress" ? 3 : 4;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedItem = 1;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AddTaskActivity.this,MainActivity.class);
                EditText titleField = (EditText)findViewById(R.id.title);
                EditText bodyField = (EditText)findViewById(R.id.body);
                if((titleField.getText() != null) && titleField.getText().toString() != "" && bodyField.getText() != null && bodyField.getText().toString() != ""){
//                    AppDataBase.getAppDataBase(getApplicationContext()).taskDao().insertAll(new Task(titleField.getText().toString(),bodyField.getText().toString(),selectedItem));
                    try {
                        Task item = Task.builder()
                                .title(titleField.getText().toString())
                                .body(bodyField.getText().toString())
                                .state(State.values()[selectedItem])
                                .build();
                        Amplify.DataStore.save(item,
                                success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
                                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
                        );
                        Log.i("Tutorial", "Initialized Amplify");
                    } catch (Exception e) {
                        Log.e("Tutorial", "Could not initialize Amplify", e);
                    }
                    TextView submitted  =  findViewById(R.id.submitted);
                    submitted.setText("submitted!");
                    startActivity(intent);
                }else {
                    TextView submitted  =  findViewById(R.id.submitted);
                    submitted.setText("Fill out all required fields");
                }

            }
        });

    }
}