package com.example.letstour.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letstour.R;
import com.example.letstour.adapter.CancelReqAdapter;
import com.example.letstour.adapter.JoinReqAdapter;
import com.example.letstour.model.CancelRequest;
import com.example.letstour.model.JoinRequest;
import com.example.letstour.utils.CommonTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CancelReqFragment extends Fragment {
    RecyclerView recyclerView;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    List<CancelRequest> requests=new ArrayList<>();
    CancelReqAdapter adapter;
    private ProgressDialog progressDialog;
    public CancelReqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cancel_req, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rv);
        initRecyclerView();
        intProgress();
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
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void onResume() {
        super.onResume();
        load();

    }

    private void load() {
        progressDialog.show();
        requests.clear();
        db.collection("cancel_req").whereEqualTo("agency_id", CommonTask.getDataFromSharedPreference(getContext(),CommonTask.USER_KEY))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CancelRequest data = document.toObject(CancelRequest.class);
                                data.setKey(document.getId());
                                requests.add(data);
                            }
                            Collections.reverse(requests);
                            adapter=new CancelReqAdapter(getContext(),requests);
                            recyclerView.setAdapter(adapter);
                            progressDialog.dismiss();
                        } else {
                        }
                    }
                });
    }
}