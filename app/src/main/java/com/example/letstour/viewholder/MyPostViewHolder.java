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

public class MyPostViewHolder extends RecyclerView.ViewHolder {
    public TextView tvLocation,tvAgency,tvDate,tvCost,tvPerson,tvDecs,tvBordering,tvDecsAgency,tvDecsDate;
    public CardView cardView;
    public ImageView ivDropDown,ivDropUp;
    public LinearLayout linDecs,linMiniDesc;
    public MyPostViewHolder(@NonNull View itemView) {
        super(itemView);
        tvLocation=itemView.findViewById(R.id.tv_event_lay_location);
        tvAgency=itemView.findViewById(R.id.tv_event_lay_agency);
        tvDate=itemView.findViewById(R.id.tv_event_lay_date);
        cardView=itemView.findViewById(R.id.cv_event_lay);
        ivDropDown=itemView.findViewById(R.id.iv_event_lay_drop_down);
        ivDropUp=itemView.findViewById(R.id.iv_event_lay_drop_up);
        linDecs=itemView.findViewById(R.id.lin_lay_event_lay_decs);
        linMiniDesc=itemView.findViewById(R.id.lin_lay_event_lay_mini_desc);
        tvCost=itemView.findViewById(R.id.tv_event_lay_cost);
        tvPerson=itemView.findViewById(R.id.tv_event_lay_person);
        tvDecs=itemView.findViewById(R.id.tv_event_lay_decs);
        tvBordering=itemView.findViewById(R.id.tv_event_lay_bordering);
        tvDecsAgency=itemView.findViewById(R.id.tv_event_lay__decs_agency);
        tvDecsDate=itemView.findViewById(R.id.tv_event_lay_decs_date);
    }
}
