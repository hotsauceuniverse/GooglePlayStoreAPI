package com.example.googleplaystoreapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView user_name, user_email;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);

        Intent intent = getIntent();
        if (intent != null) {
            String userName = intent.getStringExtra("userName");
            String userEmail = intent.getStringExtra("userEmail");

            if (userName != null && userEmail != null) {
                user_name.setText(userName);
                user_email.setText(userEmail);
            }
        }
    }
}
