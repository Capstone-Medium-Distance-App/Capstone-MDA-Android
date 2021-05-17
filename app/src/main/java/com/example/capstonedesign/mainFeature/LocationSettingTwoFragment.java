package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.capstonedesign.DTO.LocInitSet;
import com.example.capstonedesign.R;
import com.example.capstonedesign.Service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationSettingTwoFragment extends Fragment {
   public LocationSettingTwoFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_setting_two, container, false);
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Condition Setting");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/locationInitSet")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //필요한
        Button btn = (Button)rootView.findViewById(R.id.button10);
        TextView cate = (TextView)rootView.findViewById(R.id.textView3);

        //Button 클릭시 6개의 데이터 넘김
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트로 받거나 값들을 받아와서 변수에 넣어줌
                String schName = "SCHEDULE_NAME";
                String schAge = "SECHEDULE_AGE";
                String schGender = "SECHEDULE_GENDER";
                String schPeople = "SECHEDULE_PEOPLE";
                String schType = "SECHEDULE_TYPE";
                String schCate = cate.getText().toString();

                Call<LocInitSet> SendCall = retrofitService.locationInitSet(schName, schAge, schGender, schPeople, schType, schCate);

                SendCall.enqueue(new Callback<LocInitSet>() {
                    @Override
                    public void onResponse(Call<LocInitSet> call, Response<LocInitSet> response) {
                        final LocInitSet sentData = response.body();
                        System.out.println("LocInitSet DATA SEND SUCCESS!!!");
                        System.out.println("=========================================================");
                        System.out.println(sentData.toString());
                        System.out.println("=========================================================");
                    }

                    @Override
                    public void onFailure(Call<LocInitSet> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("LocInitSet DATA SEND FAIL!!!");
                    }
                });
            }
        });


        return rootView;
    }
}