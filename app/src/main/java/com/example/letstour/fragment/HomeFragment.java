package com.example.letstour.fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.letstour.R;
import com.example.letstour.adapter.PostAdapter;
import com.example.letstour.model.Post;
import com.example.letstour.utils.CommonConstant;
import com.example.letstour.utils.CommonTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    List<Post> posts=new ArrayList<>();
    PostAdapter adapter;
    private ProgressDialog progressDialog;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Trip Pioneer");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rv);
        initRecyclerView();
        intProgress();
    }

    @Override
    public void onResume() {
        super.onResume();
       // load();
        if (CommonTask.getDataFromSharedPreference(getContext(),CommonTask.AGENCY_NAME).equals("")){
            load();
        }
        else{
            loadAgencyPost();
        }
    }

    private void loadAgencyPost() {
        progressDialog.show();
        posts.clear();
        db.collection("post")
                .whereEqualTo("agencyKey",CommonTask.getDataFromSharedPreference(getContext(),CommonTask.USER_KEY))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Post data = document.toObject(Post.class);
                                data.setKey(document.getId());
                                posts.add(data);
                               // Toast.makeText(getContext(), "I am", Toast.LENGTH_SHORT).show();
                            }
                            Collections.reverse(posts);
                            adapter=new PostAdapter(getContext(),posts);
                            recyclerView.setAdapter(adapter);
                            progressDialog.dismiss();
                        } else {
                        }
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("HOME", e.toString());
            }
        });
    }

    private void load() {
        progressDialog.show();
       posts.clear();
        db.collection("post")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Post data = document.toObject(Post.class);
                                data.setKey(document.getId());
                                posts.add(data);
                            }
                            adapter=new PostAdapter(getContext(),posts);
                            recyclerView.setAdapter(adapter);
                            progressDialog.dismiss();
                        } else {
                        }
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();

    }

    private void intProgress() {
        //Progress Dialogue
        progressDialog = new ProgressDialog(getContext(), R.style.ProgressColor);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}