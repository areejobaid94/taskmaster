package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findViewById(R.id.submit_signup).setOnClickListener(v -> {
            AuthSignUpOptions options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(),  ((EditText)findViewById(R.id.email_signup)).getText().toString())
                    .build();
            Intent comf = new Intent(this,ConfirmSignUpActivity.class);
            comf.putExtra("username_signup", ((EditText)findViewById(R.id.username_signup)).getText().toString());
            System.out.println(((EditText)findViewById(R.id.username_signup)).getText().toString());
            System.out.println(((EditText)findViewById(R.id.password_signup)).getText().toString());
            System.out.println(((EditText)findViewById(R.id.email_signup)).getText().toString());

            Amplify.Auth.signUp(((EditText)findViewById(R.id.username_signup)).getText().toString(), ((EditText)findViewById(R.id.password_signup)).getText().toString(), options,
                    result -> startActivity(comf),
                    error -> Log.e("AuthQuickStart", "Sign up failed", error)
            );
        });

    }
}
