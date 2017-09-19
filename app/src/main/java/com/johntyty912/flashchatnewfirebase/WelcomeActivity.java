package com.johntyty912.flashchatnewfirebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        mAuth = FirebaseAuth.getInstance();
        attemptAutoLogin();
    }

    private void attemptAutoLogin() {
        SharedPreferences prefs = getSharedPreferences(LoginActivity.LOGIN_PREFS, MODE_PRIVATE);
        final String email = prefs.getString(LoginActivity.EMAIL_KEY,"");
        final String password = prefs.getString(LoginActivity.PASSWORD_KEY, "");
        if (email.isEmpty() || password.isEmpty()) return;
        Toast.makeText(this, "Login in progress...", Toast.LENGTH_SHORT).show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("FlashChat", "signInWithEmailAndPassword onComplete: " + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    finish();
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(WelcomeActivity.this, MainChatActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }
}
