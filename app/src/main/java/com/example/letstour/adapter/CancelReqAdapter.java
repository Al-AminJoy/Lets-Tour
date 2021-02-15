package com.example.letstour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letstour.R;
import com.example.letstour.model.CancelRequest;
import com.example.letstour.model.JoinRequest;
import com.example.letstour.viewholder.RequestViewHolder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CancelReqAdapter extends RecyclerView.Adapter<RequestViewHolder> {
    private Context context;
    private List<CancelRequest> requests;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    public CancelReqAdapter(Context context, List<CancelRequest> requests) {
        this.context = context;
        this.requests = requests;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.request_layout,parent,false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        CancelRequest list=requests.get(position);
        holder.tvName.setText("Name : "+list.getUser_name());
        holder.tvPrimary.setText("Number : "+list.getUser_pri_num());
       holder.btApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=list.getKey();
                db.document("event_user/"+""+key).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //For Remove From ArrayList
                        requests.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context,"Removed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=list.getKey();
                db.document("cancel_req/"+""+key).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //For Remove From ArrayList
                        requests.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context,"Removed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return requests.size();
    }
}
