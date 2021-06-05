package com.example.capstonedesign.mainFeature;

import static com.example.capstonedesign.user.UserInfo.userName;
import static com.example.capstonedesign.user.UserInfo.userId;
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
import com.example.capstonedesign.Retrofit.DTO.voteStatus;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationDetailActivity extends AppCompatActivity {
    private PlaceDto curPlace;
    private RetrofitClient rc;
    private int curPlaceId=0;
    private String curPlaceName="";
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
                curPlaceName = curPlace.getPlaceName();
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
                //Call<voteStatus> call2 = rc.dataFlowService.userVote(Integer.toString(userId), curPlaceName, Integer.toString(curPlaceId));
                Call<ResponseBody> callvote = rc.dataFlowService.userVote(userName, curPlaceName, Integer.toString(curPlaceId));
                callvote.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //voteStatus sentData = response.body();
                        System.out.println("userVote DATA SEND SUCCESS!!!");
                        System.out.println("=========================================================");
                        try {
                            System.out.println(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("=========================================================");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
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