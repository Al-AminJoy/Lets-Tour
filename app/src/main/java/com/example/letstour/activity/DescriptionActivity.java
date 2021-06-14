package com.example.letstour.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.letstour.R;
import com.example.letstour.model.Post;

public class DescriptionActivity extends AppCompatActivity {
    Post post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
       // Toast.makeText(this, post.toString(), Toast.LENGTH_SHORT).show();
        //post = (Post) getIntent().getParcelableExtra("POST");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         post = data.getParcelableExtra("POST");
       // Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();

    }
}