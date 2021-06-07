package com.example.capstonedesign.schedule;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.MainActivity;
import com.example.capstonedesign.R;
import com.example.capstonedesign.ScheduleDetailActivity;
import com.example.capstonedesign.mainFeature.LocationActivity;
import com.example.capstonedesign.mainFeature.LocationMainFragment;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context context;
    ArrayList<Model> models;

    public MyAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_schedule,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.mTitle.setText(models.get(position).getTitle());
        holder.mPlace.setText(models.get(position).getPlace());
        holder.mPeople.setText(models.get(position).getPeople());
        holder.imageView.setImageResource(models.get(position).getImg());
        //schId전용
        holder.mSchId.setText(Integer.toString(models.get(position).getSchId()));
        System.out.println("This is Adapter's schId : "+models.get(position).getSchId());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                int schId = models.get(position).getSchId();
                Intent intent = new Intent(context, ScheduleDetailActivity.class);
                intent.putExtra("schId", schId);
//                intent.putExtra("title", gTitle);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
