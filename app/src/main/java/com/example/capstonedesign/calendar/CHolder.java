package com.example.capstonedesign.calendar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.R;
import com.example.capstonedesign.schedule.ItemClickListener;

import org.w3c.dom.Text;

public class CHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView cTitle,cTime;
    ItemClickListener itemClickListener;

    public CHolder(@NonNull View itemView) {
        super(itemView);
        this.cTime = itemView.findViewById(R.id.calendar_time);
        this.cTitle = itemView.findViewById(R.id.calendar_title);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v, getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic){
        this.itemClickListener = ic;
    }

}
