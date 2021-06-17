package com.example.letstour.fragment;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
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
    private RecyclerView recyclerView;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private List<Post> posts=new ArrayList<>();
    private PostAdapter adapter;
    private ProgressDialog progressDialog;
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
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
        searchView=view.findViewById(R.id.searchView);
        swipeRefreshLayout=view.findViewById(R.id.swip_refresh);
        initRecyclerView();
        intProgress();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                if (CommonTask.getDataFromSharedPreference(getContext(),CommonTask.AGENCY_NAME).equals("")){
                    posts.clear();
                    load();
                }
                else{
                    posts.clear();
                    loadAgencyPost();
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (CommonTask.getDataFromSharedPreference(getContext(),CommonTask.AGENCY_NAME).equals("")){
            load();
        }
        else{
            loadAgencyPost();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                // Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

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
                            adapter.notifyDataSetChanged();
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
                            Collections.reverse(posts);
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
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}