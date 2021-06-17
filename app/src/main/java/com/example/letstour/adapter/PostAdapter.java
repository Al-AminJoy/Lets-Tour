package com.example.letstour.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letstour.R;
import com.example.letstour.activity.DescriptionActivity;
import com.example.letstour.model.CancelRequest;
import com.example.letstour.model.JoinRequest;
import com.example.letstour.model.Post;
import com.example.letstour.utils.CommonTask;
import com.example.letstour.viewholder.PostViewHolder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> implements Filterable {
    private Context context;
    private List<Post> post;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Post> searchList;

    public PostAdapter(Context context, List<Post> post) {
        this.context = context;
        this.post = post;
        searchList = new ArrayList<>(post);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_layout_design, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post list = post.get(position);
        holder.tvLocation.setText(list.getLocation());
        holder.tvAgency.setText("Agency : " + list.getAgencyName());
        holder.tvDate.setText("Date : " + list.getDate());
        holder.tvPerson.setText("Limit : " + list.getPerson());
        holder.tvCost.setText("Cost : " + list.getCost());
        holder.tvBordering.setText("Bordering : " + list.getBorderingPoint());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("POST", list);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return post.size();
    }

    @Override
    public Filter getFilter() {
        if (exampleFilter == null) {

            return null;

        }
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Post> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0|| constraint.toString().equals("")) {
                filteredList.addAll(searchList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Post item : searchList) {

                    if (item.getLocation().toLowerCase().contains(filterPattern)
                            || item.getAgencyName().toLowerCase().contains(filterPattern)
                            || String.valueOf(item.getCost()).toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            post.clear();
            post.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
