package com.example.letstour.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letstour.R;
import com.example.letstour.adapter.EnrolledAdapter;
import com.example.letstour.adapter.JoinReqAdapter;
import com.example.letstour.adapter.PostAdapter;
import com.example.letstour.model.EventUser;
import com.example.letstour.model.JoinRequest;
import com.example.letstour.model.Post;
import com.example.letstour.utils.CommonTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity {
    private Post post;
    private TextView tvLocation,tvCost,tvDate,tvPerson,tvBordering,tvAgency,tvDescription,tvEnrolled;
    private MaterialButton btJoin;
    private FirebaseFirestore db;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<EventUser> eventUsers=new ArrayList<>();
    private EnrolledAdapter adapter;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        if (getIntent().hasExtra("POST")) {
            post = getIntent().getParcelableExtra("POST");
            //Toast.makeText(this, post.toString(), Toast.LENGTH_SHORT).show();
        }
        initView();
        setViews();
        if (!CommonTask.getDataFromSharedPreference(this, CommonTask.AGENCY_NAME).equals("")){
            btJoin.setVisibility(View.GONE);
        }
        toolBar();
        db=FirebaseFirestore.getInstance();
        checkJoined();
        recyclerView=findViewById(R.id.rv);
        initRecyclerView();
        intProgress();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!CommonTask.getDataFromSharedPreference(getApplicationContext(), CommonTask.AGENCY_NAME).equals("")){
            tvEnrolled.setVisibility(View.VISIBLE);
            load();
        }
    }
    private void load() {
        progressDialog.show();
        db.collection("event_user")
                .whereEqualTo("event_id",post.getKey())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                EventUser data = document.toObject(EventUser.class);
                                data.setKey(document.getId());
                                eventUsers.add(data);
                            }
                            Collections.reverse(eventUsers);
                            adapter=new EnrolledAdapter(getApplicationContext(),eventUsers);
                            recyclerView.setAdapter(adapter);
                            progressDialog.dismiss();
                        } else {
                        }
                    }
                });
    }
    private void intProgress() {
        progressDialog = new ProgressDialog(this, R.style.ProgressColor);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void toolBar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        getSupportActionBar().setTitle("Details");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        btJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String agency_id=post.getAgencyKey();
                String uid= CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_KEY);
                String event_id=post.getKey();
                String location=post.getLocation();
                String name=CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_NAME);
                String email=CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_EMAIL);
                String gender=CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_GENDER);
                String pri_num=CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_PRI_NUMBER);
                String num1=CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER1);
                String num2=CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER2);
                String image=CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_IMAGE);
                String agency_name=post.getAgencyName();
                JoinRequest request=new JoinRequest(agency_id,event_id,location,uid,name,email,gender,pri_num,num1,num2,agency_name,image);

                btJoin.setEnabled(false);
                db.collection("join_req").add(request)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"Send Request",Toast.LENGTH_SHORT).show();
                                btJoin.setText("Already Request Send");
                            }

                        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        btJoin.setEnabled(true);
                    }
                });
            }
        });
    }

    private void checkJoined() {
        db.collection("join_req").whereEqualTo("user_id", CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_KEY))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                JoinRequest data = document.toObject(JoinRequest.class);
                                if (data.getEvent_id().equals(post.getKey())){
                                   // Toast.makeText(DescriptionActivity.this, "1st", Toast.LENGTH_SHORT).show();
                                    btJoin.setText("Already Request Send");
                                    btJoin.setEnabled(false);
                                    break;
                                }
                                /*else {
                                    Toast.makeText(DescriptionActivity.this, "2nd", Toast.LENGTH_SHORT).show();

                                    btJoin.setText("Apply on This Event");
                                }*/
                            }

                        } else {
                        }
                    }
                });
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
        btJoin=findViewById(R.id.bt_event_desc_apply);
        tvEnrolled=findViewById(R.id.tv_enrolled);
    }

}