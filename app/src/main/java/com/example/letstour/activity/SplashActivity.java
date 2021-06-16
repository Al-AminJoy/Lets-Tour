package com.example.letstour.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.letstour.R;

public class SplashActivity extends AppCompatActivity {
    private int SLEEP_TIME=2*1000;
    private Animation animSlideDown,animZoomIn;
    private TextView tvAppName,tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();

    }

    private void initView() {
        animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);
        tvAppName=findViewById(R.id.tv_splash_app_name);
        tvTitle=findViewById(R.id.tv_splash_app_motto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvAppName.startAnimation(animZoomIn);
       Handler handler=new Handler();
        handler.postDelayed(()->{
            tvTitle.setVisibility(View.VISIBLE);
                    tvTitle.startAnimation(animSlideDown);
            handler.postDelayed(()->{
                                startActivity(
                                        new Intent(SplashActivity.this,LoginActivity.class));
                                finish();
                            },1000);
                },SLEEP_TIME);
    }
}