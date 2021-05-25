package com.example.capstonedesign.mainVote;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.R;
import com.example.capstonedesign.mainFeature.LocationFinalSelectActivity;
import com.example.capstonedesign.mainFeature.LocationFinalSelectFragment;
import com.example.capstonedesign.schedule.ItemClickListener;

import java.util.ArrayList;

public class VmyAdapter extends RecyclerView.Adapter<VmyHolder> {

    Context c;
    ArrayList<Vmodel> vModels;

    public VmyAdapter(Context c, ArrayList<Vmodel> vModels) {
        this.c = c;
        this.vModels = vModels;
    }

    @NonNull
    @Override
    public VmyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_vote,null);
        return new VmyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VmyHolder holder, int position) {
        holder.mPlace.setText(vModels.get(position).getVPlace());
        holder.mName.setText(vModels.get(position).getVName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String gPlace = vModels.get(position).getVPlace();
                Intent intent = new Intent(c, LocationFinalSelectActivity.class);
//                intent.putExtra(" ")
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vModels.size();
    }
}
