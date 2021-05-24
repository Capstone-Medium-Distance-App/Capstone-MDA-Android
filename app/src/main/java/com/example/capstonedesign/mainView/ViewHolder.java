package com.example.capstonedesign.mainView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.R;
import com.example.capstonedesign.schedule.ItemClickListener;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView img;
    TextView vTitle,vPlace,vTag;
    ItemClickListener itemClickListener;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.img = itemView.findViewById(R.id.view_main_view);
        this.vTitle = itemView.findViewById(R.id.txt_title_view);
        this.vPlace = itemView.findViewById(R.id.txt_place_view);
        this.vTag = itemView.findViewById(R.id.txt_tag_view);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener =ic;
    }
}
