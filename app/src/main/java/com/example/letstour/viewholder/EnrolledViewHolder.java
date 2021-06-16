package com.example.letstour.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letstour.R;

public class EnrolledViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName,tvNumber;
    public EnrolledViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName=itemView.findViewById(R.id.tv_enrolled_lay_name);
        tvNumber=itemView.findViewById(R.id.tv_enrolled_lay_number);
    }
}
