package com.example.capstonedesign.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstonedesign.MainActivity;
import com.example.capstonedesign.R;
import com.example.capstonedesign.ScheduleFragment;
import com.example.capstonedesign.mainFeature.LocationActivity;
import com.example.capstonedesign.mainFeature.LocationSettingTwoFragment;
import com.example.capstonedesign.schedule.ItemClickListener;

import java.util.ArrayList;

public class CAdapter extends RecyclerView.Adapter<CHolder> {
    Fragment scheduleFragment = new ScheduleFragment();

    Context c;
    ArrayList<Cmodel> models;

    public CAdapter(Context c, ArrayList<Cmodel> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public CHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_row, null);

        return new CHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CHolder holder, int position) {
        holder.cTitle.setText(models.get(position).getTitle());
        holder.cTime.setText(models.get(position).getTime());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                ((MainActivity) scheduleFragment.getActivity()).replaceFragment(scheduleFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
