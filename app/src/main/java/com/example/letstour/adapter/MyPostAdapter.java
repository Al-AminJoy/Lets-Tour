package com.example.letstour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letstour.R;
import com.example.letstour.database.entity.Event;
import com.example.letstour.model.CancelRequest;
import com.example.letstour.model.JoinRequest;
import com.example.letstour.model.Post;
import com.example.letstour.utils.CommonTask;
import com.example.letstour.viewholder.MyPostViewHolder;
import com.example.letstour.viewholder.PostViewHolder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostViewHolder> {
    private Context context;
    private List<Event> events;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    public MyPostAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public MyPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.my_event_layout,parent,false);
        return new MyPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPostViewHolder holder, int position) {
        Event list=events.get(position);
        holder.tvLocation.setText(list.getLocation());
        holder.tvAgency.setText("Agency : "+list.getAgencyName());
        holder.tvDate.setText("Date : "+list.getDate());
        holder.tvDecs.setText(list.getDescription());
        holder.tvPerson.setText("Person : "+list.getPerson());
        holder.tvCost.setText("Cost : "+list.getCost());
        holder.tvBordering.setText("Bordering : "+list.getBorderingPoint());
        holder.tvDecsAgency.setText("Agency : "+list.getAgencyName());
        holder.tvDecsDate.setText("Date : "+list.getDate());
        holder.ivDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivDropDown.setVisibility(View.GONE);
                holder.linMiniDesc.setVisibility(View.GONE);
                holder.linDecs.setVisibility(View.VISIBLE);
            }
        });
        holder.ivDropUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivDropDown.setVisibility(View.VISIBLE);
                holder.linMiniDesc.setVisibility(View.VISIBLE);
                holder.linDecs.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
