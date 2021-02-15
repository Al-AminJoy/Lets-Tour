package com.example.letstour.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.letstour.R;
import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private MaterialButton btUserSignUp,btAgentSignUp;
    private String email,userImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    protected void onStart() {
        super.onStart();
        email=getIntent().getStringExtra("email");
        userImage=getIntent().getStringExtra("image");
        btUserSignUp=findViewById(R.id.btUserSignUpId);
        btAgentSignUp=findViewById(R.id.btAgencySignUpId);
        btAgentSignUp.setOnClickListener(this);
        btUserSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btUserSignUpId){
            Intent intent=new Intent(SignUpActivity.this,UserSignUpActivity.class);
            intent.putExtra("email",email);
            intent.putExtra("image",userImage);
            startActivity(intent);
            finish();

        }
        else  if(v.getId()==R.id.btAgencySignUpId){
            Intent intent=new Intent(SignUpActivity.this,AgencySignUpActivity.class);
            intent.putExtra("email",email);
            intent.putExtra("image",userImage);
            startActivity(intent);
            finish();
        }

    }
}