package com.example.letstour.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letstour.R;
import com.google.android.material.button.MaterialButton;

public class RequestViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName,tvPrimary;
    public MaterialButton btApprove,btCancel;
    public RequestViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName=itemView.findViewById(R.id.tv_req_lay_name);
        tvPrimary=itemView.findViewById(R.id.tv_req_lay_number);
        btApprove=itemView.findViewById(R.id.bt_req_lay_approve);
        btCancel=itemView.findViewById(R.id.bt_req_lay_cancel);
    }
}
