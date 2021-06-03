package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

import java.text.BreakIterator;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Amplify.Auth.fetchAuthSession(
                result ->{
                    if(!result.isSignedIn()) {

                        Amplify.Auth.signInWithWebUI(
                                this,
                                results -> Log.i("AuthQuickStart failed1", results.toString()),
                                error -> Log.e("AuthQuickStart failed2", error.toString())
                        );
                    }
                },
                error -> Log.e("AuthQuickStart ", error.toString())
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode);
        System.out.println(resultCode);
        System.out.println(data);
        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
            Amplify.Auth.handleWebUISignInResponse(data);
        }
    }
}