package com.example.capstonedesign.mainFeature;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.MainActivity;
import com.example.capstonedesign.R;
import com.example.capstonedesign.Retrofit.DTO.locFin;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFinishActivity extends AppCompatActivity {
    private RetrofitClient rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_finish);


        TextView appoint = findViewById(R.id.txt_locationfinish_appoint);
        TextView where = findViewById(R.id.txt_locationfinish_where);
        TextView who = findViewById(R.id.txt_locationfinish_who);
        TextView place = findViewById(R.id.txt_locationfinish_place);

        //바로 이전 화면인 LocationFinalSelectFragment에서 bundle로 보낸 schId를 받아서
        //retrofit에서 URL에 껴서 보내줘야한다!!
        Intent intent = getIntent();
        int schId = 0;
        schId = intent.getExtras().getInt("schId");

        //약속정보를 받아와서 출력해줌----------------------------여기
        rc = new RetrofitClient();
        Call<locFin> call = rc.mainFlowService.locFin(schId);
        call.enqueue(new Callback<locFin>() {
            @Override
            public void onResponse(Call<locFin> call, Response<locFin> response) {
                System.out.println("placeDetails DATA RECEIVE SUCCESS!!!");
                System.out.println("=========================================================");
                System.out.println(response.toString());
                System.out.println(response.body().toString());
                locFin recData = response.body();
                System.out.println(recData.getSchName());
                System.out.println(recData.getSchPlaceName());
                System.out.println(recData.getSchPlaceArea());
                System.out.println(recData.getSchWIthUserName());
                System.out.println("=========================================================");

                //다 받아왔으면 출력에 설정해주면 된다!
                appoint.setText(recData.getSchName());
                where.setText(recData.getSchPlaceArea());
                who.setText("koo, sim, you");
                place.setText(recData.getSchPlaceName());
            }

            @Override
            public void onFailure(Call<locFin> call, Throwable t) {

            }
        });

        Button button = findViewById(R.id.btn_locationfinish_inform);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Schedule is ready !!",Toast.LENGTH_SHORT).show();
            }
        });

//        //Toolbar -> BackArrow+Title .version
//        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
//        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
//        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
//        actionBar.setTitle("Final Checkup");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

    }
}