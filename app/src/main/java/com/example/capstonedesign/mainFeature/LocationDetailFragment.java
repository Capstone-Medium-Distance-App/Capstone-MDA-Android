package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.capstonedesign.DTO.PlaceDto;
import com.example.capstonedesign.DTO.userEnter;
import com.example.capstonedesign.R;
import com.example.capstonedesign.Service.MainFlowService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LocationDetailFragment extends Fragment {
    private Retrofit retrofit;

    public LocationDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_detail, container, false);

        //서버로부터 place객체를 받아서 그 객체의 이름값을 텍스트에 설정해주는 과정 -kyu
        TextView tx = rootView.findViewById(R.id.txt_locationdetail_name);
        Gson gson  = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-37-60-253.ap-northeast-2.compute.amazonaws.com:8080/")
                //.baseUrl("http://192.168.35.225:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MainFlowService mainFlowService = retrofit.create(MainFlowService.class);

        Call<PlaceDto> SendCall = mainFlowService.placeDetail();

        SendCall.enqueue(new Callback<PlaceDto>() {
            @Override
            public void onResponse(Call<PlaceDto> call, Response<PlaceDto> response) {
                System.out.println("placeDetails DATA RECEIVE SUCCESS!!!");
                System.out.println("=========================================================");
                Log.d("TAG",response.code()+"");
                Log.d("TAG",response.errorBody()+"");
                System.out.println(response);
                PlaceDto test = response.body();
                System.out.println(test.getPlaceArea());
                System.out.println(test.getPlaceCategory());
                System.out.println(test.getPlaceDescription());
                System.out.println(test.getPlaceImgUrl());
                System.out.println(test.getPlaceName());
                System.out.println(test.getPlaceType());
                System.out.println(test.getPlaceId());
                System.out.println("=========================================================");

                tx.setText(test.getPlaceName());
            }

            @Override
            public void onFailure(Call<PlaceDto> call, Throwable t) {
                t.printStackTrace();
                System.out.println("placeDetails DATA RECEIVE FAIL!!!");
            }
        });


        Button button = rootView.findViewById(R.id.btn_locationdetail_vote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//            retrofit = new Retrofit.Builder()
//                    .baseUrl("http://localhost:8080/client/{num}/enter")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            MainFlowService mainFlowService = retrofit.create(MainFlowService.class);
//
//            Call<userEnter> SendCall = mainFlowService.userEnter(userid, userLat, userLong);
//
//            SendCall.enqueue(new Callback<userEnter>() {
//                @Override
//                public void onResponse(Call<userEnter> call, Response<userEnter> response) {
//                    final userEnter sentData = response.body();
//                    System.out.println("userEnter DATA SEND SUCCESS!!!");
//                    System.out.println("=========================================================");
//                    System.out.println(sentData.toString());
//                    System.out.println("=========================================================");
//                }
//
//                @Override
//                public void onFailure(Call<userEnter> call, Throwable t) {
//                    t.printStackTrace();
//                    System.out.println("userEnter DATA SEND FAIL!!!");
//                }
//            });

                ((LocationActivity)getActivity()).replaceFragment(new LocationMainFragment());
            }
        });

        //Toolbar -> BackArrow+Title .version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

        //detail

        return rootView;
    }
}