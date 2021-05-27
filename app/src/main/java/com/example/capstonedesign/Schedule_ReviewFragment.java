package com.example.capstonedesign;

import static com.example.capstonedesign.user.UserInfo.userId;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.capstonedesign.Retrofit.DTO.rating;
import com.example.capstonedesign.Retrofit.DTO.schDT;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Schedule_ReviewFragment extends Fragment {
    private RetrofitClient rc = new RetrofitClient();
    //별점 받을 변수들
    int conditionEval=0, kindnessEval=0, facilityEval=0;



    public Schedule_ReviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_schedule__review, container, false);

        RatingBar rb1 = rootView.findViewById(R.id.txt1_ratingBar);
        RatingBar rb2 = rootView.findViewById(R.id.txt2_ratingBar);
        RatingBar rb3 = rootView.findViewById(R.id.txt3_ratingBar);
        conditionEval = rb1.getNumStars();
        kindnessEval = rb2.getNumStars();
        facilityEval = rb3.getNumStars();

        //스케쥴 아이디값도 받아보쟈
        int schId = getArguments().getInt("schId");

        Call<rating> call = rc.mainFlowService.userRate(userId, conditionEval, kindnessEval, facilityEval);
        call.enqueue(new Callback<rating>() {
            @Override
            public void onResponse(Call<rating> call, Response<rating> response) {
                final rating sentData = response.body();
                System.out.println("rating DATA SEND SUCCESS!!!");
                System.out.println("=========================================================");
                System.out.println(sentData.toString());
                System.out.println("=========================================================");
            }
            @Override
            public void onFailure(Call<rating> call, Throwable t) {
                t.printStackTrace();
                System.out.println("rating DATA SEND FAIL!!!");
            }
        });


        return rootView;


    }
}