package com.example.capstonedesign;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.capstonedesign.calendar.CAdapter;
import com.example.capstonedesign.calendar.Cmodel;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalendarFragment extends Fragment {
    RecyclerView recyclerView;
    CAdapter cAdapter;

    public CalendarFragment() {
        // Required empty public constructor
    }

//com.skyhope.eventcalenderlibrary.CalenderEvent로 검색해서 값들 어떻게 집어넣을껀지 확인

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_calendar, container, false);

        recyclerView.findViewById(R.id.recyclerview_calendar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        CalendarView mCalendar = (CalendarView) rootView.findViewById(R.id.calendarView);
        TextView tx  = (TextView) rootView.findViewById(R.id.details);

        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + (month+1) + "/" + dayOfMonth;
                tx.setText(date);
                cAdapter = new CAdapter(getActivity(),getMyList(dayOfMonth));
                recyclerView.setAdapter(cAdapter);
            }
        });

        //Toolbar -> Nothing.version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Calendar");
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        return rootView;
    }

    private ArrayList<Cmodel> getMyList(int num){
        ArrayList<Cmodel> cmodels = new ArrayList<>();
        Cmodel c = new Cmodel();
        c.setTitle("");
        c.setTime("");
        cmodels.add(c);

        c = new Cmodel();
        c.setTitle("");
        c.setTime("");
        cmodels.add(c);

        c = new Cmodel();
        c.setTitle("");
        c.setTime("");
        cmodels.add(c);

        return cmodels;
    }

}