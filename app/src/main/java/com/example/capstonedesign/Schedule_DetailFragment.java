package com.example.capstonedesign;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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



        TextView where = (TextView)rootView.findViewById(R.id.textView19);
        TextView who = (TextView)rootView.findViewById(R.id.textView21);
        TextView place = (TextView)rootView.findViewById(R.id.textView23);
        TextView date = (TextView)rootView.findViewById(R.id.textView25);
        TextView time = (TextView)rootView.findViewById(R.id.textView27);

        //가져온 리스트와 리스트의 몇번째에 있는 숫자가 있는지넘겨받기
        int schId = getArguments().getInt("selectedSchId");
        ArrayList<ScheduleDto> schLists = (ArrayList<ScheduleDto>)getArguments().get("list");

        //가져온거 확인해보기
        for(int i=0; i<schLists.size(); i++){
            System.out.println(schLists.get(i).getScheduleName());
        }

        //가져온걸로 설정해줌
        where.setText(schLists.get(schId).getSchedulePlaceArea());
        who.setText(schLists.get(schId).getScheduleWithUserName());
        place.setText(schLists.get(schId).getSchedulePlaceName());
        date.setText(schLists.get(schId).getScheduleDate());
        time.setText(schLists.get(schId).getScheduleTime());

        Bundle bundle = new Bundle();
        int tmp = 0;
        tmp = schLists.get(schId).getScheduleId();
        bundle.putInt("schId", tmp);

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