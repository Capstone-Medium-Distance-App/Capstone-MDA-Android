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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ScheduleDetailActivity extends AppCompatActivity {

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

        //가져온 리스트와 리스트의 몇번째에 있는 숫자가 있는지넘겨받기
        int schId = getArguments().getInt("selectedSchId");
        ArrayList<ScheduleDto> schLists = (ArrayList<ScheduleDto>)getArguments().get("list");

        //가져온거 확인해보기
        for(int i=0; i<schLists.size(); i++){
            System.out.println(schLists.get(i).getScheduleName());
        }

        //가져온걸로 설정해줌
        where.setText(schLists.get(schId).getSchedulePlaceArea());
        who.setText(schLists.get(schId).getScheduleWithUserName());
        place.setText(schLists.get(schId).getSchedulePlaceName());
        date.setText(schLists.get(schId).getScheduleDate());
        time.setText(schLists.get(schId).getScheduleTime());

        Bundle bundle = new Bundle();
        int tmp = 0;
        tmp = schLists.get(schId).getScheduleId();
        bundle.putInt("schId", tmp);

        Intent in =getIntent();
        String mTitle = getIntent().getStringExtra("title");

        txt.setText(mTitle);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScheduleReviewActivity.class);
//                intent.putExtra("title", gTitle);
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