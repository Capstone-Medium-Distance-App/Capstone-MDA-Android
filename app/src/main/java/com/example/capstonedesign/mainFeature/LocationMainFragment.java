package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.capstonedesign.DTO.cafeMapStart;
import com.example.capstonedesign.DTO.cli_Loc;
import com.example.capstonedesign.DTO.userEnter;
import com.example.capstonedesign.R;
import com.example.capstonedesign.Service.DataFlowService;
import com.example.capstonedesign.Service.MainFlowService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationMainFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private Retrofit retrofit;

    private MapView mapView;
    public LocationMainFragment() {
        // Required empty public constructor
    }
    public static LocationMainFragment newInstance(){
        LocationMainFragment fragment = new LocationMainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_main, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

//        mapView.getMapAsync(this);
//        mapView = (MapView)rootView.findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//        mapView.onResume();
//        mapView.getMapAsync(this);

        //c1, c2, c3의 좌표와 중간값의 좌표를 구한다
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://localhost:8080/clientsLocation")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        DataFlowService dataFlowService = retrofit.create(DataFlowService.class);
//
//        Call<cli_Loc> cli_locCall = dataFlowService.cli_Loc("la1", "la2", "la3",
//                "lo1", "lo2", "lo3",
//                "u1", "u2", "u3",
//                "midLat", "midLong");
//        cli_locCall.enqueue(new Callback<cli_Loc>(){
//            String TAG = "TAG";
//            @Override
//            public void onResponse(Call<cli_Loc> call, Response<cli_Loc> response) {
//                if(response.isSuccessful()){
//                    cli_Loc result = response.body();
//                    Log.d(TAG, "onResponse: 성공, 결과\n");
//                }else{
//                    Log.d(TAG, "onRespones: 실패");
//                }
//            }
//            @Override
//            public void onFailure(Call<cli_Loc> call, Throwable t) {
//                Log.d(TAG, "onFailure: "+t.getMessage());
//            }
//        });

//        String btn_txt = getArguments().getString("btn_txt");
//        //이걸 한꺼번에 받는 객체를 만들기에는 너무많은거 같음 -> 만약에 나누게되면 url까지 나눠서해야하고, 안나누게되면 하나의 객체안에서 너무많은 속성들이 있음 어떻게하는게 좋아보이나
//        switch(btn_txt){
//            case "cafe":{
//
//            }
//
//        }



        Button button = rootView.findViewById(R.id.btn_locationmain_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LocationActivity)getActivity()).replaceFragment(new LocationDetailFragment());
            }
        });
        Button button2 = rootView.findViewById(R.id.btn_locationmain_test2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LocationActivity)getActivity()).replaceFragment(new LocationFinalSelectFragment());
            }
        });


//        //Toolbar -> BackArrow + title + icons .version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Where To Go?");
        actionBar.setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back); //뒤로가기버튼을 아이콘으로 변경
        actionBar.setDisplayShowCustomEnabled(true); //커스텀하기위함
        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(getActivity(),"MAP READY!",Toast.LENGTH_SHORT).show();
        this.googleMap = googleMap;
        // 37.659627, 126.773459 정발산역
        LatLng latLng = new LatLng(37.659627, 126.773459);

        //현재위치를 가져와서
        String userid="1";
        String userLat="11.111111";String userLong="99.999999";

        //camera가 바라보는 방향 정하기( 처음 위치 설정)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //camera 확대 정도
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));


        //Retrofit
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://localhost:8080/client/{num}/enter")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        MainFlowService mainFlowService = retrofit.create(MainFlowService.class);
//
//        Call<userEnter> SendCall = mainFlowService.userEnter(userid, userLat, userLong);
//
//        SendCall.enqueue(new Callback<userEnter>() {
//            @Override
//            public void onResponse(Call<userEnter> call, Response<userEnter> response) {
//                final userEnter sentData = response.body();
//                System.out.println("userEnter DATA SEND SUCCESS!!!");
//                System.out.println("=========================================================");
//                System.out.println(sentData.toString());
//                System.out.println("=========================================================");
//            }
//
//            @Override
//            public void onFailure(Call<userEnter> call, Throwable t) {
//                t.printStackTrace();
//                System.out.println("userEnter DATA SEND FAIL!!!");
//            }
//        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                Toast.makeText(getActivity(), "This is EDIT", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(getActivity(), "This is SHARE", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}