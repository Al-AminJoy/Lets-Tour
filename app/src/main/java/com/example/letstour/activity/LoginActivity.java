package com.example.letstour.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.letstour.R;
import com.example.letstour.model.Agency;
import com.example.letstour.model.User;
import com.example.letstour.utils.CommonTask;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG ="LoginActivity" ;
    MaterialButton btStart;
    boolean exist;
    boolean agencyExist;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
 //   private ActivityGoogleBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

// ... // Configure Google Sign In
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
           .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
               // Log.w(TAG, "Google sign in failed", e);
                // ...
                Log.d(TAG, "firebaseAuthWithGoogle:" + e);
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        btStart=findViewById(R.id.btStartId);
        btStart.setOnClickListener(this);
     if (currentUser!=null){
          Toast.makeText(getApplicationContext(),"I am on Start",Toast.LENGTH_SHORT).show();
          startActivity(new Intent(LoginActivity.this,MainActivity.class));
          finish();
      }

    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"I am on Success",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String currentEmail=user.getEmail();
                          checkUser(currentEmail,user);
                        }
                    }


                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "firebaseAuthWithGoogle:" + e);
            }
        });
    }
    private void checkUser(String currentEmail, FirebaseUser user) {
        exist=false;
        db.collection("user").whereEqualTo("email",currentEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(getApplicationContext(),"Outer",Toast.LENGTH_SHORT).show();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                exist=true;
                              //  Toast.makeText(getApplicationContext(),"I am first task",Toast.LENGTH_SHORT).show();
                               User data = document.toObject(User.class);
                                String fName=data.getFirst_name();
                                String lName=data.getLast_name();
                                String fullName=fName+" "+lName;
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_KEY,data.getKey());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.AGENCY_NAME,"");
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NAME,fullName);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_EMAIL,data.getEmail());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_GENDER,data.getGender());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_PRI_NUMBER,data.getPri_num());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER1,data.getNum1());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER2,data.getNum2());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_IMAGE,data.getImage());
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();
                            }
                            if (exist==false){
                                checkAgency(currentEmail,user);
                            }

                        }
                    }
                });

    }

    private void checkAgency(String currentEmail, FirebaseUser user) {
        agencyExist=false;
        db.collection("agency").whereEqualTo("email",currentEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                agencyExist=true;
                                Agency data = document.toObject(Agency.class);
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_KEY,data.getKey());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.AGENCY_NAME,data.getName());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NAME,data.getName());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_EMAIL,data.getEmail());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_GENDER,"");
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_PRI_NUMBER,data.getPri_num());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER1,data.getNum1());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER2,data.getNum2());
                                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_IMAGE,data.getImage());
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();
                            }
                            if (agencyExist==false){
                                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                                intent.putExtra("email",currentEmail);
                                intent.putExtra("image",user.getPhotoUrl().toString());
                                startActivity(intent);
                                finish();
                            }

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btStartId){
            signIn();
        }
    }
}