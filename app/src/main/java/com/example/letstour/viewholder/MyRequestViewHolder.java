package com.example.letstour.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letstour.R;

public class MyRequestViewHolder extends RecyclerView.ViewHolder {
    public TextView tvLocation,tvAgency;
    public MyRequestViewHolder(@NonNull View itemView) {
        super(itemView);
        tvLocation=itemView.findViewById(R.id.tv_my_req_location);
        tvAgency=itemView.findViewById(R.id.tv_my_req_agency);
    }
}
