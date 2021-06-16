package com.example.letstour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letstour.R;
import com.example.letstour.model.EventUser;
import com.example.letstour.viewholder.EnrolledViewHolder;

import java.util.List;

public class EnrolledAdapter extends RecyclerView.Adapter<EnrolledViewHolder> {
    private Context context;
    private List<EventUser> eventUserList;

    public EnrolledAdapter(Context context, List<EventUser> eventUserList) {
        this.context = context;
        this.eventUserList = eventUserList;
    }

    @NonNull
    @Override
    public EnrolledViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EnrolledViewHolder(LayoutInflater.from(context).inflate(R.layout.enrolled_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EnrolledViewHolder holder, int position) {
        EventUser eventUser=eventUserList.get(position);
        holder.tvNumber.setText("-"+eventUser.getUser_number());
        holder.tvName.setText(eventUser.getUser_name());
    }

    @Override
    public int getItemCount() {
        return eventUserList.size();
    }
}
