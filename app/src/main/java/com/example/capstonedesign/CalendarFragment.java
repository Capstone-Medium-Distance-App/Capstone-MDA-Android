package com.example.capstonedesign;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.capstonedesign.Retrofit.DTO.ScheduleDto;
import com.example.capstonedesign.Retrofit.DTO.ScheduleList;
import com.example.capstonedesign.Retrofit.RetrofitClient;
import com.example.capstonedesign.calendar.CAdapter;
import com.example.capstonedesign.calendar.Cmodel;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.capstonedesign.user.UserInfo.userId;

public class CalendarFragment extends Fragment {
    RecyclerView recyclerView;
    CAdapter cAdapter;
    RetrofitClient rc = new RetrofitClient();
    ArrayList<ScheduleDto> arr = new ArrayList<ScheduleDto>();

    public CalendarFragment() {
        // Required empty public constructor
    }

//com.skyhope.eventcalenderlibrary.CalenderEvent로 검색해서 값들 어떻게 집어넣을껀지 확인

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_calendar, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerview_calendar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //retrofit
//        rc = new RetrofitClient();
//        Call<ScheduleList> call = rc.mainFlowService.getScheduleList(userId);
//        call.enqueue(new Callback<ScheduleList>() {
//            @Override
//            public void onResponse(Call<ScheduleList> call, Response<ScheduleList> response) {
//                System.out.println("scheduleList DATA RECEIVE SUCCESS!!!");
//                System.out.println("=========================================================");
//                Log.d("TAG",response.code()+"");
//                Log.d("TAG",response.errorBody()+"");
//                System.out.println(response);
//                ScheduleList receivedData = response.body();
//                arr = receivedData.getList();
//                System.out.println("=========================================================");
//            }
//
//            @Override
//            public void onFailure(Call<ScheduleList> call, Throwable t) {
//                t.printStackTrace();
//                System.out.println("scheduleList DATA RECEIVE FAIL!!!");
//            }
//        });

        //arr ArrayList에 3개가 들어가있음
        ScheduleDto s1 = new ScheduleDto();
        s1.setScheduleName("첫 번째 테스트 스케쥴");
        s1.setScheduleDate("2021 01 01");
        s1.setScheduleTime("11:11");
        ScheduleDto s2 = new ScheduleDto();
        s2.setScheduleName("두 번째 테스트 스케쥴");
        s2.setScheduleDate("2021 02 02");
        s2.setScheduleTime("22:22");
        ScheduleDto s3 = new ScheduleDto();
        s3.setScheduleName("세 번째 테스트 스케쥴");
        s3.setScheduleDate("2021 03 03");
        s3.setScheduleTime("00:00");
        arr.add(s1);
        arr.add(s2);
        arr.add(s3);
        
        

        //색칠하는 코드는 여기에서 arr을 사용해서
        for(int i=0; i<arr.size(); i++){
            String tmp[] = new String[3];
            tmp = arr.get(i).getScheduleDate().split(" ");
            //tmp[0] : 연도, tmp[1] : 달, tmp[2] : 일

        }


        CalendarView mCalendar = (CalendarView) rootView.findViewById(R.id.calendarView);
        TextView tx  = (TextView) rootView.findViewById(R.id.details);

        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + (month+1) + "/" + dayOfMonth;
                tx.setText(date);
                cAdapter = new CAdapter(getActivity(),getMyList(year, month+1, dayOfMonth));
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

    private ArrayList<Cmodel> getMyList(int year, int month, int dayOfMonth){
        ArrayList<Cmodel> cmodels = new ArrayList<>();
        for(int i=0; i<arr.size(); i++){

            String tmp[] = new String[3];
            tmp = arr.get(i).getScheduleDate().split(" ");
            if(
                    (year == Integer.parseInt(tmp[0]))
                    &&(month == Integer.parseInt(tmp[1]))
                    &&(dayOfMonth == Integer.parseInt(tmp[2]))
            ){
//                System.out.println(tmp[0]+"/"+tmp[1]+"/"+tmp[2]);
//                System.out.println("CALENDERSUCCESS!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                Cmodel c = new Cmodel();
                c.setTime(arr.get(i).getScheduleTime());
                c.setTitle(arr.get(i).getScheduleName());
            }

        }

//        Cmodel c = new Cmodel();
//        c.setTitle("");
//        c.setTime("");
//        cmodels.add(c);
//
//        c = new Cmodel();
//        c.setTitle("");
//        c.setTime("");
//        cmodels.add(c);
//
//        c = new Cmodel();
//        c.setTitle("");
//        c.setTime("");
//        cmodels.add(c);

        return cmodels;
    }

}