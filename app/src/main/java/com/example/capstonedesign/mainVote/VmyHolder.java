package com.example.capstonedesign.mainVote;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.R;
import com.example.capstonedesign.schedule.ItemClickListener;

public class VmyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ItemClickListener itemClickListener;
    TextView mPlace,mName,mPlaceId;
    public VmyHolder(@NonNull View itemView) {
        super(itemView);
        this.mName = itemView.findViewById(R.id.txtname_row_vote);
        this.mPlace = itemView.findViewById(R.id.txtpalce_row_vote);
        this.mPlaceId = itemView.findViewById(R.id.txt_placeId_vote);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       this.itemClickListener.onItemClickListener(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }
}
