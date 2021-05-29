package com.example.capstonedesign.mainFeature;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.capstonedesign.R;
import com.example.capstonedesign.Retrofit.DTO.PlaceDto;
import com.example.capstonedesign.Retrofit.DTO.userVote;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationDetailActivity extends AppCompatActivity {
    private PlaceDto curPlace;
    private RetrofitClient rc;
    private int curPlaceId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        TextView tx = findViewById(R.id.txt_locationdetail_name);

        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("title");
        int placeId = 0;
        placeId = Integer.parseInt(intent.getStringExtra("ID"));


        tx.setText(mTitle);

        rc = new RetrofitClient();
        Call<PlaceDto> call = rc.mainFlowService.placeDetail(placeId);

        call.enqueue(new Callback<PlaceDto>() {
            @Override
            public void onResponse(Call<PlaceDto> call, Response<PlaceDto> response) {
                System.out.println("placeDetails DATA RECEIVE SUCCESS!!!");
                System.out.println("=========================================================");
                Log.d("TAG",response.code()+"");
                Log.d("TAG",response.errorBody()+"");
                System.out.println(response);
                curPlace = response.body();
                curPlaceId = curPlace.getPlaceId();
                System.out.println(curPlace.getPlaceArea());
                System.out.println(curPlace.getPlaceCategory());
                System.out.println(curPlace.getPlaceDescription());
                System.out.println(curPlace.getPlaceImgUrl());
                System.out.println(curPlace.getPlaceName());
                System.out.println(curPlace.getPlaceType());
                System.out.println(curPlace.getPlaceId());
                System.out.println("=========================================================");

                tx.setText(curPlace.getPlaceName());
            }

            @Override
            public void onFailure(Call<PlaceDto> call, Throwable t) {
                t.printStackTrace();
                System.out.println("placeDetails DATA RECEIVE FAIL!!!");
            }
        });
        Button button = findViewById(R.id.btn_locationdetail_vote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //번들로 가져와야할듯? -> locationMain에서 id값을 정한 다음에 생각
                int userid = 0;
                Call<userVote> call2 = rc.dataFlowService.userVote(userid, curPlaceId);
                call2.enqueue(new Callback<userVote>() {
                    @Override
                    public void onResponse(Call<userVote> call2, Response<userVote> response) {
                        final userVote sentData = response.body();
                        System.out.println("uesrVote DATA SEND SUCCESS!!!");
                        System.out.println("=========================================================");
//                        System.out.println(sentData.toString());
                        System.out.println("=========================================================");
                    }

                    @Override
                    public void onFailure(Call<userVote> call2, Throwable t) {
                        t.printStackTrace();
                        System.out.println("userEnter DATA SEND FAIL!!!");
                    }
                });
                    finish();
//                ((LocationActivity)getActivity()).replaceFragment(new LocationMainFragment());
            }
        });

//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        this.setSupportActionBar(toolbar);
//        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
//        actionBar.setTitle("Details");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);
    }
}