package com.example.capstonedesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;

import com.example.capstonedesign.Retrofit.DTO.rating;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.capstonedesign.user.UserInfo.userId;

public class ScheduleReviewActivity extends AppCompatActivity {
    private RetrofitClient rc = new RetrofitClient();
    //별점 받을 변수들
    int conditionEval=0, kindnessEval=0, facilityEval=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_review);

        RatingBar rb1 = findViewById(R.id.txt1_ratingBar);
        RatingBar rb2 = findViewById(R.id.txt2_ratingBar);
        RatingBar rb3 = findViewById(R.id.txt3_ratingBar);
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


    }
}