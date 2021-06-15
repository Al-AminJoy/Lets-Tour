package com.example.letstour.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letstour.R;
import com.example.letstour.model.Post;

public class DescriptionActivity extends AppCompatActivity {
    private Post post;
    private TextView tvLocation,tvCost,tvDate,tvPerson,tvBordering,tvAgency,tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        if (getIntent().hasExtra("POST")) {
            post = getIntent().getParcelableExtra("POST");
            Toast.makeText(this, post.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        setViews();
    }

    private void setViews() {
        tvLocation.setText(post.getLocation());
        tvCost.setText(String.valueOf(post.getCost()));
        tvDate.setText(post.getDate());
        tvPerson.setText(String.valueOf(post.getPerson()));
        tvBordering.setText(post.getBorderingPoint());
        tvDescription.setText(post.getDescription());
        tvAgency.setText(post.getAgencyName());
    }

    private void initView() {
        tvLocation=findViewById(R.id.tv_event_des_location);
        tvCost=findViewById(R.id.tv_event_desc_cost);
        tvDate=findViewById(R.id.tv_event_desc_date);
        tvPerson=findViewById(R.id.tv_event_desc_seat);
        tvBordering=findViewById(R.id.tv_event_desc_bordering);
        tvAgency=findViewById(R.id.tv_event_desc_agency);
        tvDescription=findViewById(R.id.tv_event_desc_description);
    }

}