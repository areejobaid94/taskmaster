package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button button =  findViewById(R.id.save);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                TextView is_req = findViewById(R.id.is_req);
                EditText usernameField = findViewById(R.id.name);
                String name;
                if(usernameField.getText() != null && usernameField.getText().toString() != ""){
                    name = usernameField.getText().toString();
                    editor.putString("username",name);
                    editor.apply();
                    is_req.setText("");
                    Intent k = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(k);
                }else{
                    is_req.setText("Required Field");
                }

            }
        });
    }
}