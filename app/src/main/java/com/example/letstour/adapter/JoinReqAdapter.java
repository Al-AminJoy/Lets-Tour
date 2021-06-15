package com.example.letstour.adapter;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letstour.R;
import com.example.letstour.model.CancelRequest;
import com.example.letstour.model.EventUser;
import com.example.letstour.model.JoinRequest;
import com.example.letstour.model.Post;
import com.example.letstour.utils.CommonTask;
import com.example.letstour.viewholder.PostViewHolder;
import com.example.letstour.viewholder.RequestViewHolder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class JoinReqAdapter extends RecyclerView.Adapter<RequestViewHolder> {
    private Context context;
    private List<JoinRequest> requests;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    public JoinReqAdapter(Context context, List<JoinRequest> requests) {
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
        JoinRequest list=requests.get(position);
        holder.tvName.setText(list.getUser_name());
        holder.tvPrimary.setText(list.getUser_pri_num());
        holder.tvPrimary.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_phone, 0, 0, 0);
        holder.tvLocation.setText(list.getLocation());
        holder.tvEmail.setText(list.getUser_email());
        holder.tvEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_email, 0, 0, 0);
        if (list.getUser_image()==null ||list.getUser_image().equals("")){
            Picasso.get().load(R.drawable.profile).into(holder.ivProfile);
        }else {
            Picasso.get().load(list.getUser_image()).into(holder.ivProfile);
        }
        holder.btApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=list.getUser_id();
                String event_id=list.getEvent_id();
                String location=list.getLocation();
                String name=list.getUser_name();
                String agency_id=list.getAgency_id();
                String pri_num=list.getUser_pri_num();
                EventUser request=new EventUser(event_id,agency_id,location,uid,name,pri_num);
                db.collection("event_user").add(request)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                String key=list.getKey();
                                db.document("join_req/"+""+key).delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //For Remove From ArrayList
                                        requests.remove(position);
                                        notifyDataSetChanged();
                                        Toast.makeText(context,"Approved",Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });
            }
        });
        holder.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=list.getKey();
                db.document("join_req/"+""+key).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
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
