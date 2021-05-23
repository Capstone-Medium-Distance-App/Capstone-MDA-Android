package com.example.capstonedesign.schedule;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.R;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView imageView;
    TextView mTitle,mPlace,mPeople;
    ItemClickListener itemClickListener;


    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.imageView = itemView.findViewById(R.id.img_plan_sch);
        this.mTitle = itemView.findViewById(R.id.txt_title_sch);
        this.mPlace = itemView.findViewById(R.id.txt_place_sch);
        this.mPeople = itemView.findViewById(R.id.txt_people_sch);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v, getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener =ic;
    }
}
