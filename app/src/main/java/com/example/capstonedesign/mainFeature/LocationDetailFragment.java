package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.capstonedesign.R;
import com.example.capstonedesign.Service.MainFlowService;

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