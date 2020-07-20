package com.example.xcaliberstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                   Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                   startActivity(intent);

                } catch (Exception e) {

                }
            }
        }, 3000);
    }
}