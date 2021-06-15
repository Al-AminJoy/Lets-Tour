package com.example.letstour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letstour.R;
import com.example.letstour.model.JoinRequest;
import com.example.letstour.viewholder.MyRequestViewHolder;

import java.util.List;

public class MyRequestAdapter  extends RecyclerView.Adapter<MyRequestViewHolder> {
    private Context context;
    private List<JoinRequest> joinRequestList;

    public MyRequestAdapter(Context context, List<JoinRequest> joinRequestList) {
        this.context = context;
        this.joinRequestList = joinRequestList;
    }

    @NonNull
    @Override
    public MyRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyRequestViewHolder(LayoutInflater.from(context).inflate(R.layout.my_request_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRequestViewHolder holder, int position) {
        JoinRequest request=joinRequestList.get(position);
        holder.tvLocation.setText(request.getLocation());
        holder.tvAgency.setText(request.getAgency_name());
    }

    @Override
    public int getItemCount() {
        return joinRequestList.size();
    }
}
