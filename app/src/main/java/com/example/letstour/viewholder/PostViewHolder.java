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

public class PostViewHolder extends RecyclerView.ViewHolder {
    public TextView tvLocation,tvAgency,tvDate,tvCost,tvPerson,tvDecs,tvBordering,tvDecsAgency,tvDecsDate;
    public MaterialButton btJoin,btCancel;
    public CardView cardView;
    public ImageView ivDropDown,ivDropUp;
    public LinearLayout linDecs,linMiniDesc;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        tvLocation=itemView.findViewById(R.id.tv_event_lay_location);
        tvAgency=itemView.findViewById(R.id.tv_event_lay_agency);
        tvDate=itemView.findViewById(R.id.tv_event_lay_date);
        cardView=itemView.findViewById(R.id.cardView);
        ivDropDown=itemView.findViewById(R.id.iv_event_lay_drop_down);
        ivDropUp=itemView.findViewById(R.id.iv_event_lay_drop_up);
        linDecs=itemView.findViewById(R.id.lin_lay_event_lay_decs);
        linMiniDesc=itemView.findViewById(R.id.lin_lay_event_lay_mini_desc);
        tvCost=itemView.findViewById(R.id.tv_event_lay_cost);
        tvPerson=itemView.findViewById(R.id.tv_event_lay_person);
        tvBordering=itemView.findViewById(R.id.tv_event_lay_bordering);
        tvDecsDate=itemView.findViewById(R.id.tv_event_lay_decs_date);
    /*    btJoin=itemView.findViewById(R.id.bt_event_lay_join);
        btCancel=itemView.findViewById(R.id.bt_event_lay_cancel);*/
    }
}
