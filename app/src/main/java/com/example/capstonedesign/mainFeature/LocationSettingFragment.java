package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.capstonedesign.DTO.DTO;
import com.example.capstonedesign.DTO.RetrofitService;
import com.example.capstonedesign.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LocationSettingFragment extends Fragment {
    String TAG = "Tag";
    public LocationSettingFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_setting, container, false);
        TextView tv = rootView.findViewById(R.id.edit_locationsettingone_name);
        Button button = rootView.findViewById(R.id.btn_locationsetting_complete);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LocationActivity)getActivity()).replaceFragment(new LocationMainFragment());
            }
        });





        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
                //내부 ip주소(내가 임의로 돌리는 웹서버) -> ip에서보는 외부 주소
                .baseUrl("http://192.168.35.225:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService test = retrofit.create(RetrofitService.class);
        //세부 엔드포인트를 넣어줌 -> 192.168.35.71:8080/test/1
        Call<DTO> call = test.getPosts("1");
        call.enqueue(new Callback<DTO>(){
            String TAG = "TAG";
            @Override
            public void onResponse(Call<DTO> call, Response<DTO> response) {
                if(response.isSuccessful()){
                    //웹서버에서 던지는 json값을 result에 저장 -> 이건 서버쪽이랑 맞춰봐야함
                    DTO result = response.body();
                    tv.setText(result.getTitle()+" / "+result.getContent());
                    //Log.d(TAG, "onResponse: 성공, 결과\n"+result.toString());
                    Log.d(TAG, "onResponse: 성공, 결과\n");
                    //Getter를 따로 만들어서 하나하나를 꺼내보는 것 까지 확인인
                    Log.d(TAG, result.getTitle());
                    Log.d(TAG, result.getContent());
                }else{
                    Log.d(TAG, "onRespones: 실패");
                }
            }

            @Override
            public void onFailure(Call<DTO> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

        //Toolbar -> BackArrow+Title .version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Condition Setting");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

        return rootView;
    }

}
