package com.example.capstonedesign;

import static com.example.capstonedesign.user.UserInfo.userId;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.Retrofit.DTO.ScheduleDto;
import com.example.capstonedesign.Retrofit.DTO.ScheduleList;
import com.example.capstonedesign.Retrofit.RetrofitClient;
import com.example.capstonedesign.calendar.CAdapter;
import com.example.capstonedesign.calendar.Cmodel;
import com.example.capstonedesign.calendar.EventDecorator;
import com.example.capstonedesign.calendar.OneDayDecorator;
import com.example.capstonedesign.calendar.SaturdayDecorator;
import com.example.capstonedesign.calendar.SundayDecorator;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.PolylineOptions;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarFragment extends Fragment {
    RecyclerView recyclerView;
    CAdapter cAdapter;
    RetrofitClient rc = new RetrofitClient();
    ArrayList<ScheduleDto> arr = new ArrayList<>();
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    MaterialCalendarView materialCalendarView;
    CalendarView mCalendar;
    String[] dates;

    public CalendarFragment() { }

//com.skyhope.eventcalenderlibrary.CalenderEvent로 검색해서 값들 어떻게 집어넣을껀지 확인

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_calendar, container, false);

        materialCalendarView = rootView.findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2017, 0, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        //retrofit
        rc = new RetrofitClient();
        Call<ScheduleList> call = rc.mainFlowService.getScheduleList(userId);
        call.enqueue(new Callback<ScheduleList>() {
            @Override
            public void onResponse(Call<ScheduleList> call, Response<ScheduleList> response) {
                System.out.println("scheduleList DATA RECEIVE SUCCESS!!!");
                System.out.println("=========================================================");
                Log.d("TAG",response.code()+"");
                Log.d("TAG",response.errorBody()+"");
                for(int i=0; i<response.body().getList().size(); i++){
                    System.out.println(response.body().getList().get(i).getScheduleName());
                }
                ScheduleList receivedData = response.body();
                arr = receivedData.getList();
                System.out.println("=========================================================");
                dates = new String[arr.size()+1];
                for(int i=0; i<arr.size(); i++){
                    dates[i] = arr.get(i).getScheduleDate();
                }
                //마지막용 더미 데이터
                dates[arr.size()] = "0000-00-00";

                //retrofit에서 받은 date들 확인
                for (int i = 0; i < dates.length; i++) {
                    System.out.println(dates[i]);
                }


            }

            @Override
            public void onFailure(Call<ScheduleList> call, Throwable t) {
                t.printStackTrace();
                System.out.println("scheduleList DATA RECEIVE FAIL!!!");
            }
        });

//        String[] result = {"2021,05,18","2021,04,18","2021,05,16","2021,05,11"};

//        //arr ArrayList에 3개가 들어가있음
//        ScheduleDto s1 = new ScheduleDto();
//        s1.setScheduleName("첫 번째 테스트 스케쥴");
//        s1.setScheduleDate("2021,01,01");
//        s1.setScheduleTime("11:11");
//        arr.add(s1);
//        ScheduleDto s2 = new ScheduleDto();
//        s2.setScheduleName("두 번째 테스트 스케쥴");
//        s2.setScheduleDate("2021,02,02");
//        s2.setScheduleTime("22:22");
//        arr.add(s2);
//        ScheduleDto s3 = new ScheduleDto();
//        s3.setScheduleName("세 번째 테스트 스케쥴");
//        s3.setScheduleDate("2021,03,05");
//        s3.setScheduleTime("00:00");
//        arr.add(s3);
//        ScheduleDto s4 = new ScheduleDto();
//        s4.setScheduleName("네 번째 테스트 스케쥴");
//        s4.setScheduleDate("2021,05,15");
//        s4.setScheduleTime("00:00");
//        arr.add(s4);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new ApiSimulator(dates).executeOnExecutor(Executors.newSingleThreadExecutor());
            }
        },1000);


//        new ApiSimulator(dates).executeOnExecutor(Executors.newSingleThreadExecutor());

        recyclerView = rootView.findViewById(R.id.recyclerview_calendar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        TextView tx  = (TextView) rootView.findViewById(R.id.details);
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                String shot_Day = Year + "," + Month + "," + Day;
                tx.setText(shot_Day);
                cAdapter = new CAdapter(getActivity(),getMyList(Year, Month, Day));
                recyclerView.setAdapter(cAdapter);

                Log.i("shot_Day test", shot_Day + "");

//                materialCalendarView.clearSelection();

                Toast.makeText(getContext(), shot_Day , Toast.LENGTH_SHORT).show();
            }
        });
//        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
//                String date = year + "/" + (month+1) + "/" + dayOfMonth;
//                //tx.setText(date);
//                cAdapter = new CAdapter(getActivity(),getMyList(year, month+1, dayOfMonth));
//                recyclerView.setAdapter(cAdapter);
//            }
//        });

        //Toolbar -> Nothing.version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Calendar");
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        return rootView;
    }

    private ArrayList<Cmodel> getMyList(int year, int month, int dayOfMonth){
        ArrayList<Cmodel> cmodels = new ArrayList<>();
        for(int i=0; i<arr.size(); i++){

            String tmp[] = new String[3];
            tmp = arr.get(i).getScheduleDate().split("-");
            if(
                    (year == Integer.parseInt(tmp[0]))
                    &&(month == Integer.parseInt(tmp[1]))
                    &&(dayOfMonth == Integer.parseInt(tmp[2]))
            ){
//                System.out.println(tmp[0]+"/"+tmp[1]+"/"+tmp[2]);
//                System.out.println("CALENDERSUCCESS!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                Cmodel c = new Cmodel();
                c.setTime(arr.get(i).getScheduleTime());
                c.setTitle(arr.get(i).getScheduleName());
            }

        }

//        Cmodel c = new Cmodel();
//        c.setTitle("");
//        c.setTime("");
//        cmodels.add(c);
//
//        c = new Cmodel();
//        c.setTitle("");
//        c.setTime("");
//        cmodels.add(c);
//
//        c = new Cmodel();
//        c.setTitle("");
//        c.setTime("");
//        cmodels.add(c);

        return cmodels;
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for(int i = 0 ; i < Time_Result.length ; i ++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split("-");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,dayy);
            }



            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

//            if (isFinishing()) {
//                return;
//            }

            materialCalendarView.addDecorator(new EventDecorator(Color.RED, calendarDays,getActivity()));
        }
    }

}