package com.example.letstour.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private Context context;
    private List<Post> post;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    public PostAdapter(Context context, List<Post> post) {
        this.context = context;
        this.post = post;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.event_layout_design,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post list=post.get(position);
        holder.tvLocation.setText(list.getLocation());
        holder.tvAgency.setText("Agency : "+list.getAgencyName());
        holder.tvDate.setText("Date : "+list.getDate());
        holder.tvPerson.setText("Limit : "+list.getPerson());
        holder.tvCost.setText("Cost : "+list.getCost());
        holder.tvBordering.setText("Bordering : "+list.getBorderingPoint());
      /*  holder.tvBordering.setText("Bordering : "+list.getBorderingPoint());
        holder.tvDecsDate.setText("Date : "+list.getDate());*/
       /* holder.ivDropDown.setOnClickListener(new View.OnClickListener() {
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
        if (!CommonTask.getDataFromSharedPreference(context, CommonTask.AGENCY_NAME).equals("")){
            holder.btJoin.setVisibility(View.GONE);
            holder.btCancel.setVisibility(View.GONE);
        }
        holder.btJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String agency_id=list.getAgencyKey();
                String uid=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_KEY);
                String event_id=list.getKey();
                String location=list.getLocation();
                String name=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_NAME);
                String email=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_EMAIL);
                String gender=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_GENDER);
                String pri_num=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_PRI_NUMBER);
                String num1=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_NUMBER1);
                String num2=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_NUMBER2);
                JoinRequest request=new JoinRequest(agency_id,event_id,location,uid,name,email,gender,pri_num,num1,num2);
                db.collection("join_req").add(request)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(context,"Send Request",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        holder.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String agency_id=list.getAgencyKey();
                String uid=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_KEY);
                String event_id=list.getKey();
                String location=list.getLocation();
                String name=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_NAME);
                String email=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_EMAIL);
                String gender=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_GENDER);
                String pri_num=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_PRI_NUMBER);
                String num1=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_NUMBER1);
                String num2=CommonTask.getDataFromSharedPreference(context,CommonTask.USER_NUMBER2);
                CancelRequest request=new CancelRequest(agency_id,event_id,location,uid,name,email,gender,pri_num,num1,num2);
                db.collection("cancel_req").add(request)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(context,"Send Request",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });*/
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DescriptionActivity.class);
                intent.putExtra("POST",list);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return post.size();
    }
}
