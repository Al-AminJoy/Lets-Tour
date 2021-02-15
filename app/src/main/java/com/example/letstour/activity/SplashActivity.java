package com.example.letstour.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.letstour.R;

public class SplashActivity extends AppCompatActivity {
    private int SLEEP_TIME=3*1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler()
                .postDelayed(()->{startActivity(new Intent(SplashActivity.this,LoginActivity.class));finish();},SLEEP_TIME);
    }
}