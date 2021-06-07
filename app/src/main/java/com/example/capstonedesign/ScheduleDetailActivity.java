package com.example.capstonedesign;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.capstonedesign.Retrofit.DTO.ScheduleDto;
import com.example.capstonedesign.Retrofit.DTO.locFin;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleDetailActivity extends AppCompatActivity {

    private RetrofitClient rc = new RetrofitClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);


        TextView txt = findViewById(R.id.textView17);
        Button btn = findViewById(R.id.button5);

        TextView where = findViewById(R.id.textView19);
        TextView who = findViewById(R.id.textView21);
        TextView place = findViewById(R.id.textView23);
        TextView date = findViewById(R.id.textView25);
        TextView time = findViewById(R.id.textView27);

        //schedule.MyAdapter에서 보내주는 클릭된 schId값을 전달 받음
        Intent intent = getIntent();
        int schId = intent.getExtras().getInt("schId");
        System.out.println("This is detail's schId : "+schId);

        Call<ScheduleDto> call = rc.mainFlowService.schDetail(schId);
        call.enqueue(new Callback<ScheduleDto>() {
            @Override
            public void onResponse(Call<ScheduleDto> call, Response<ScheduleDto> response) {
                System.out.println("REQUESTED SCHEDULE DATA RECEIVE SUCCESS!!!");
                System.out.println("=========================================================");
                System.out.println(response.toString());
                ScheduleDto recData = response.body();
                //던져준 schId에 대한 값을 받아서 각각의 텍스트입력
                txt.setText(recData.getScheduleName());
                where.setText(recData.getSchedulePlaceArea());
                who.setText(recData.getScheduleWithUserName());
                place.setText(recData.getSchedulePlaceName());
                date.setText(recData.getScheduleDate());
                time.setText(recData.getScheduleTime());
                System.out.println("=========================================================");
            }

            @Override
            public void onFailure(Call<ScheduleDto> call, Throwable t) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScheduleReviewActivity.class);
                intent.putExtra("schId", schId);
                startActivity(intent);
            }
        });
//        //Toolbar -> BackArrow+Title .version
//        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
//        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
//        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
//        actionBar.setTitle("Details of Schedule");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);
    }

}