package com.example.capstonedesign.mainFeature;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.capstonedesign.R;
import com.example.capstonedesign.Retrofit.DTO.schDT;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFinalSelectActivity extends AppCompatActivity {
    private RetrofitClient rc;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    private String schDate = "";
    private String schTime = "";
    //값은 번들이나 인텐트로 받아서 넣어줘야함
    int placeid = 0;
    private Calendar c = Calendar.getInstance();
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_final_select);

        rc = new RetrofitClient();
        Button calender = findViewById(R.id.DateButton);
        Button timer = findViewById(R.id.TimeButton);
        //
        TextView textView = findViewById(R.id.textView);
        TextView textView_Time = findViewById(R.id.txt_time_ing);
        TextView textView_Date = findViewById(R.id.txt_date_ing);
        Intent intent = getIntent();

        int placeId = Integer.parseInt(intent.getStringExtra("ID"));
        String place =  intent.getStringExtra("PLACE");

        //달력 -> 테스트하기 위해서 LocationSettingFragment에서 일부러 바꾼코드
        //LocationSettingFragment 만들고,
        //cafe를 클릭하면 여기로 넘어오게 만들어놨다 ====> 다시 정리해둬야한다, 그쪽에도 주석으로 표시해둠
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //처음 다이얼로그가 뜨면 기본으로 잡혀있는 날짜
                DatePickerDialog dialog = new DatePickerDialog(getApplicationContext(), callbackMethod, 2021, 5, 23);
//                Date date = new Date();
//                DatePickerDialog dialog = new DatePickerDialog(getContext(), callbackMethod, date.getYear(), date.getMonth(), date.getDay());
                dialog.show();
            }
        });
        //달련확인 버튼을 눌러서 날짜가 변경될때, 텍스트의 값을 변경
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_Date.setText(year + "." + monthOfYear + "." + dayOfMonth + "");
                //schDate라는 변수에 선택된 값을 받아서, 저장해뒀다가 나중에 전송
                schDate = year + "," + monthOfYear + "," + dayOfMonth;
            }
        };

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        String state = "AM";
//                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
//                        if (selectedHour > 12) {
//                            selectedHour -= 12;
//                            state = "PM";
//                        }
//                        // EditText에 출력할 형식 지정
//                        textView_Time.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                        textView_Time.setText(selectedHour + " : "+selectedMinute);
                        schTime = selectedHour + " : " + selectedMinute;
                    }
                }, hour, minute, false);
                mTimePicker.show();
            }
        });


        Button btn_locationfinal_complete = findViewById(R.id.btn_locationfinal_complete);
        btn_locationfinal_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //android material design time picker 나 날짜를 활용하거나
                //드롭다운 방식을 사용해서 값을 받아오쟈
                //time
                // https://android-arsenal.com/details/1/7701
                // https://androidexample365.com/material-time-picker-for-developer/

                Call<schDT> call = rc.dataFlowService.schDT(schDate, schTime, placeid);
                call.enqueue(new Callback<schDT>() {
                    @Override
                    public void onResponse(Call<schDT> call, Response<schDT> response) {
                        final schDT sentData = response.body();
                        System.out.println("schDT DATA SEND SUCCESS!!!");
                        System.out.println("=========================================================");
                        System.out.println(sentData.toString());
                        //response에서 넘어오는 placeId자리에 schId가 있으니까 getPlaceId로 받고, locationFinishFragment으로 넘기자
                        placeid = sentData.getPlaceId();

                        System.out.println("=========================================================");
                    }
                    @Override
                    public void onFailure(Call<schDT> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("schDT DATA SEND FAIL!!!");
                    }
                });

                Intent intent = new Intent(getApplicationContext(), LocationFinishActivity.class);
                intent.putExtra("schId", placeid);
                startActivity(intent);
            }
        });

//        //Toolbar -> BackArrow+Title .version
//        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
//        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
//        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
//        actionBar.setTitle("Final Selections");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

    }
}