package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class ConfirmSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sign_up);
        ((EditText) findViewById(R.id.username_confirm)).setText(getIntent().getExtras().getString("username_signup"));
        findViewById(R.id.confirm_submit).setOnClickListener(e->{
            Amplify.Auth.confirmSignUp(
                    ((EditText) findViewById(R.id.username_confirm)).getText().toString(),
                    ((EditText) findViewById(R.id.code)).getText().toString(),
                    result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });
    }
}