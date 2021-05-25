package com.example.capstonedesign.mainView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.R;
import com.example.capstonedesign.mainFeature.LocationDetailActivity;
import com.example.capstonedesign.schedule.ItemClickListener;
import com.example.capstonedesign.schedule.Model;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context c;
    ArrayList<ViewModel> models;


    public ViewAdapter(Context c, ArrayList<ViewModel> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_view,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.vTitle.setText(models.get(position).getViewTitle());
        holder.vPlace.setText(models.get(position).getViewPlace());
        holder.vTag.setText(models.get(position).getViewTag());
        holder.img.setImageResource(models.get(position).getView());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String gTitle =models.get(position).getViewTitle();

                Intent intent = new Intent(c, LocationDetailActivity.class);
                intent.putExtra("title",gTitle);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
