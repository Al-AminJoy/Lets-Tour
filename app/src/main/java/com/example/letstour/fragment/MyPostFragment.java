package com.example.letstour.fragment;

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
import com.example.letstour.adapter.MyPostAdapter;
import com.example.letstour.database.entity.Event;
import com.example.letstour.database.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

public class MyPostFragment extends Fragment {
    private RecyclerView recyclerView;
    private EventRepository userRepository;
    private ArrayList<Event> allEvents;
    private MyPostAdapter adapter;
    public MyPostFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

    }
    @Override
    public void onResume() {
        super.onResume();

        userRepository = new EventRepository(getContext());

        allEvents = (ArrayList<Event>) userRepository.getAll();


        adapter = new MyPostAdapter(getContext(),allEvents);

        recyclerView.setAdapter(adapter);
    }

}