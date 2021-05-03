package com.example.capstonedesign.mainFeature;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.capstonedesign.MainActivity;
import com.example.capstonedesign.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class LocationMainFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;

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
        this.googleMap = googleMap;
        // 37.659627, 126.773459 정발산역
        LatLng latLng = new LatLng(37.659627, 126.773459);

        //camera가 바라보는 방향 정하기( 처음 위치 설정)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //camera 확대 정도
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
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