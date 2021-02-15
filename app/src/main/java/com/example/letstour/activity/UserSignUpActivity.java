package com.example.letstour.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.letstour.R;
import com.example.letstour.model.User;
import com.example.letstour.utils.CommonTask;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton gender;
    private RadioGroup radioGroup;
    private Button btSignUp;
    private EditText etFirstName,etLastName,etAddress,etPrimaryNum,etNum1,etNum2;
    private String email,firstName,lastName,address,primaryNumber,num1,num2,userGender,userImage;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
    }

    @Override
    protected void onStart() {
        super.onStart();
        radioGroup=findViewById(R.id.rg_signUp_user);
        btSignUp=findViewById(R.id.bt_user_SignUp);
        etFirstName=findViewById(R.id.et_user_SignUp_first_Name);
        etLastName=findViewById(R.id.et_user_SignUp_last_name);
        etAddress=findViewById(R.id.et_user_SignUp_address);
        etPrimaryNum=findViewById(R.id.et_user_SignUp_Pri_Number);
        etNum1=findViewById(R.id.et_user_SignUp_Num1);
        etNum2=findViewById(R.id.et_user_SignUp_Num2);
        btSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bt_user_SignUp){
            int selectId=radioGroup.getCheckedRadioButtonId();
            gender=findViewById(selectId);
            userGender=gender.getText().toString();
          //  Toast.makeText(this,userGender,Toast.LENGTH_SHORT).show();
            email=getIntent().getStringExtra("email");
            userImage=getIntent().getStringExtra("image");
            firstName=etFirstName.getText().toString().trim();
            lastName=etLastName.getText().toString().trim();
            address=etAddress.getText().toString().trim();
            primaryNumber=etPrimaryNum.getText().toString().trim();
            num1=etNum1.getText().toString().trim();
            num2=etNum2.getText().toString().trim();
            if (firstName.length()<0 || firstName.length()>16){
                etFirstName.findFocus();
                etFirstName.setError("");
                return;
            }
            if (lastName.length()<0 || lastName.length()>16){
                etLastName.findFocus();
                etLastName.setError("");
                return;
            }
            if (address.length()<0 || address.length()>64){
                etAddress.findFocus();
                etAddress.setError("");
                return;
            }
            if (primaryNumber.length()<0 || primaryNumber.length()>11){
                etPrimaryNum.findFocus();
                etPrimaryNum.setError("");
                return;
            }
            if (num1.length()<0 || num1.length()>11){
                etNum1.findFocus();
                etNum2.setError("");
                return;
            }
            if (num2.length()<0 || num2.length()>11){
                etNum2.findFocus();
                etNum2.setError("");
                return;
            }
            if (firstName.length()!=0 && lastName.length()!=0 && address.length()!=0 && primaryNumber.length()!=0 && num1.length()!=0 && num2.length()!=0){
                User user=new User(firstName,lastName,address,email,userGender,userImage,primaryNumber,num1,num2);
                db.collection("user").add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"User Added",Toast.LENGTH_SHORT).show();
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_KEY,documentReference.getId());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.AGENCY_NAME,"");
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NAME,firstName+" "+lastName);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_EMAIL,email);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_GENDER,userGender);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_PRI_NUMBER,primaryNumber);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER1,num1);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER2,num2);
                                startActivity(new Intent(UserSignUpActivity.this,MainActivity.class));
                                finish();
                            }
                        });
            }

        }
    }
}