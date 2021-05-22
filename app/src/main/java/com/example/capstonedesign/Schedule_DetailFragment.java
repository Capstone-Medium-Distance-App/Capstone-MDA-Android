package com.example.capstonedesign;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.capstonedesign.Retrofit.DTO.ScheduleDto;

import java.util.ArrayList;


public class Schedule_DetailFragment extends Fragment {


    public Schedule_DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_schedule__detail, container, false);

        //ArrayList<ScheduleDto> 형태의 객체로 번들을 넘겨받기
        ArrayList<ScheduleDto> schLists = (ArrayList<ScheduleDto>)getArguments().get("list");
        //가져온거 확인해보기
        for(int i=0; i<schLists.size(); i++){
            System.out.println(schLists.get(i).getScheduleName());
        }

        //Toolbar -> BackArrow+Title .version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Details of Schedule");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

        return rootView;
    }
}