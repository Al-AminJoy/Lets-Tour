package com.example.letstour.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.letstour.R;
import com.example.letstour.model.Agency;
import com.example.letstour.model.User;
import com.example.letstour.utils.CommonTask;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AgencySignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName,etAddress,etPrimaryNum,etNum1,etNum2;
    private String email,name,address,primaryNumber,num1,num2,userImage;
    private MaterialButton btSignUp;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agency_sign_up);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btSignUp=findViewById(R.id.bt_Agency_SignUp);
        etName=findViewById(R.id.et_Agency_SignUp_Name);
        etAddress=findViewById(R.id.et_Agency_SignUp_Address);
        etPrimaryNum=findViewById(R.id.et_Agency_SignUp_Pri_Number);
        etNum1=findViewById(R.id.et_Agency_SignUp_Num1);
        etNum2=findViewById(R.id.et_Agency_SignUp_Num2);
        btSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        email=getIntent().getStringExtra("email");
        userImage=getIntent().getStringExtra("image");
        name=etName.getText().toString().trim();
        address=etAddress.getText().toString().trim();
        primaryNumber=etPrimaryNum.getText().toString().trim();
        num1=etNum1.getText().toString().trim();
        num2=etNum2.getText().toString().trim();
        if (v.getId()==R.id.bt_Agency_SignUp){
            if (name.isEmpty() || name.length()>16){
                etName.findFocus();
                etName.setError("");
                return;
            }

            if (address.isEmpty() || address.length()>64){
                etAddress.findFocus();
                etAddress.setError("");
                return;
            }
            if (primaryNumber.isEmpty() || primaryNumber.length()>11){
                etPrimaryNum.findFocus();
                etPrimaryNum.setError("");
                return;
            }
            if (num1.isEmpty() || num1.length()>11){
                etNum1.findFocus();
                etNum2.setError("");
                return;
            }
            if (num2.isEmpty() || num2.length()>11){
                etNum2.findFocus();
                etNum2.setError("");
                return;
            }
            if (name.length()!=0 && address.length()!=0 && primaryNumber.length()!=0 && num1.length()!=0 && num2.length()!=0){
                Agency agency=new Agency(name,email,address,userImage,primaryNumber,num1,num2);
                db.collection("agency").add(agency)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"Agency Added",Toast.LENGTH_SHORT).show();
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_KEY,documentReference.getId());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.AGENCY_NAME,name);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NAME,name);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_EMAIL,email);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_IMAGE,userImage);
                                startActivity(new Intent(AgencySignUpActivity.this,MainActivity.class));
                                finish();
                            }
                        });
            }
        }
    }
}