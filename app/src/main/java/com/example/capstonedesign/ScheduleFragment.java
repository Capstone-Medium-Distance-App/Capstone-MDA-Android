package com.example.capstonedesign;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.capstonedesign.Retrofit.DTO.PlaceDto;
import com.example.capstonedesign.Retrofit.DTO.ScheduleDto;
import com.example.capstonedesign.Retrofit.DTO.ScheduleList;
import com.example.capstonedesign.Retrofit.RetrofitClient;
import com.example.capstonedesign.mainFeature.LocationActivity;
import com.example.capstonedesign.schedule.Model;
import com.example.capstonedesign.schedule.MyAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment {
    private RetrofitClient rc;
    private Gson mGson;
    private ArrayList<ScheduleDto> arr = new ArrayList<>();
    private Fragment sch_detail_fra = new Schedule_DetailFragment();

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    public ScheduleFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_schedule, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView_sch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myAdapter = new MyAdapter(getActivity(), getMyList());
        recyclerView.setAdapter(myAdapter);

//        rc = new RetrofitClient();
//        Call<ScheduleList> call = rc.mainFlowService.getScheduleList();
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
//
//                //가져온거 잘왔는지 확인
//                System.out.println(receivedData.getUserId());
//                for (int i = 0; i < arr.size(); i++) {
//                    System.out.println(arr.get(i).getScheduleName());
//                }
//                System.out.println("=========================================================");
//
//            }
//
//            @Override
//            public void onFailure(Call<ScheduleList> call, Throwable t) {
//                t.printStackTrace();
//                System.out.println("scheduleList DATA RECEIVE FAIL!!!");
//            }
//        });

//        LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.sch1);
//        ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!arr.isEmpty()){
//                    System.out.println("====================다음프래그먼트로 전송=====================");
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("list", arr);
//                    bundle.putInt("selectedSchId", 0);
//                    sch_detail_fra.setArguments(bundle);
//                    System.out.println("=========================================================");
//                    ((MainActivity)getActivity()).replaceFragment(sch_detail_fra);
//                }
//
//            }
//        });

        //Toolbar -> Nothing.version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Recorded Schedules");
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        return rootView;
    }


    private ArrayList<Model> getMyList(){
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("Test title 1");
        m.setPeople("test people 1");
        m.setPlace("Test Geonggi 1");
        m.setImg(R.drawable.ic_planned_sch_24);
        models.add(m);

        m = new Model();
        m.setTitle("Test title 2");
        m.setPeople("test people 2");
        m.setPlace("Test Geonggi 2");
        m.setImg(R.drawable.ic_passed_sch_24);
        models.add(m);


        m = new Model();
        m.setTitle("Test title 2");
        m.setPeople("test people 2");
        m.setPlace("Test Geonggi 2");
        m.setImg(R.drawable.ic_passed_sch_24);
        models.add(m);
        return models;
    }
}