package com.example.capstonedesign.mainFeature;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.capstonedesign.R;
import com.example.capstonedesign.Retrofit.DTO.userEnter;
import com.example.capstonedesign.Retrofit.MainFlowService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.android.ui.IconGenerator;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationMainFragment extends Fragment implements OnMapReadyCallback,TaskLoadedCallback,LocationListener {


    private GoogleMap googleMap;
    private Retrofit retrofit;
    private MapView mapView;
    private double lat = 0.0, log = 0.0;
    private LatLng latLng1,latLng2,latLng3,mainlatLng;
    LocationManager locationManager;
    LocationListener locationListener;
    MarkerOptions place1, place2, place3, mainplace;
    Polyline currentPolyline;
    TextView txt_ex2;

    public LocationMainFragment() {
    }


    public static LocationMainFragment newInstance() {
        LocationMainFragment fragment = new LocationMainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_location_main, container, false);
         txt_ex2 = new TextView(getContext());
        txt_ex2.setText("승원이네야");
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.mapView);

        mapFragment.getMapAsync(this);


//        mapView.getMapAsync(this);
//        mapView = (MapView)rootView.findViewById(R.id.mapView);
//        mapView.onCreate(savedInstanceState);
//        mapView.onResume();
//        mapView.getMapAsync(this);

        //서버로부터 중간값과 나머지 참가자들의 위치를 받는 코드 -kyu
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
                ((LocationActivity) getActivity()).replaceFragment(new LocationDetailFragment());
            }
        });
        Button button2 = rootView.findViewById(R.id.btn_locationmain_test2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LocationActivity) getActivity()).replaceFragment(new LocationFinalSelectFragment());
            }
        });


//        //Toolbar -> BackArrow + title + icons .version
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((LocationActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity) getActivity()).getSupportActionBar();
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

        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latLng1 = new LatLng(location.getLatitude(), location.getLongitude());
                latLng2 = new LatLng(37.648984, 126.774089);
                latLng3 = new LatLng(37.671873, 126.785645);
                mainlatLng = new LatLng(37.659627, 126.773459);


                googleMap.clear();
//                IconGenerator generator = new IconGenerator(getContext());
//                generator.setBackground(getResources().getDrawable(R.drawable.ic_main_person_24));
//                generator.setContentView(txt_ex2);
//                Bitmap icon = generator.makeIcon();


                place1 =new MarkerOptions().position(latLng1).title("승원").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
                place2 =new MarkerOptions().position(latLng2).title("규도").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
                place3 =new MarkerOptions().position(latLng3).title("재석").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
                mainplace =new MarkerOptions().position(mainlatLng).title("약속장소").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_meet_24)).draggable(true);

                googleMap.addMarker(place1);
                googleMap.addMarker(place2);
                googleMap.addMarker(place3);
                googleMap.addMarker(mainplace).showInfoWindow();

//                String url = getUrl(place1.getPosition(), place2.getPosition(),"driving");
//                new FetchURL(getActivity()).execute(url,"driving");

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(mainlatLng));
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(14));
            }
        };
        askLocationPermission();

        //현재위치를 가져와서
        String userid = "123456789";
        String userLat = "11.111111";
        String userLong = "99.999999";


//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(lat, log))      // Sets the center of the map to Mountain View
//                .zoom(17)                   // Sets the zoom
//                .bearing(90)                // Sets the orientation of the camera to east
//                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
//                .build();                   // Creates a CameraPosition from the builder
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //처음에 접속했을때 자신의 위치를 서버로 보내는 코드 -kyu
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-3-37-60-253.ap-northeast-2.compute.amazonaws.com:8080/")
                //.baseUrl("http://192.168.35.225:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        MainFlowService mainFlowService = retrofit.create(MainFlowService.class);

        Call<userEnter> SendCall = mainFlowService.userEnter(userid, userLat, userLong);

        SendCall.enqueue(new Callback<userEnter>() {
            @Override
            public void onResponse(Call<userEnter> call, Response<userEnter> response) {
                System.out.println("userEnter DATA SEND SUCCESS!!!");
                System.out.println("=========================================================");
                Log.d("TAG", response.code() + "");
                Log.d("TAG", response.errorBody() + "");
                System.out.println(response);
                System.out.println("=========================================================");
            }

            @Override
            public void onFailure(Call<userEnter> call, Throwable t) {
                t.printStackTrace();
                System.out.println("userEnter DATA SEND FAIL!!!");
            }
        });
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawble = ContextCompat.getDrawable(context,vectorResId);
        vectorDrawble.setBounds(0,0, vectorDrawble.getIntrinsicWidth(),vectorDrawble.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawble.getIntrinsicWidth(),vectorDrawble.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawble.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }


    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = googleMap.addPolyline((PolylineOptions) values[0]);
    }

    private void askLocationPermission() {
        Dexter.withContext(requireActivity()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                latLng1 = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                latLng2 = new LatLng(37.648984, 126.774089);
                latLng3 = new LatLng(37.671873, 126.785645);
                mainlatLng = new LatLng(37.659627, 126.773459);


                googleMap.clear();
//                IconGenerator generator = new IconGenerator(getContext());
//                generator.setBackground(getResources().getDrawable(R.drawable.ic_main_person_24));
//                generator.setContentView(txt_ex2);
//                Bitmap icon = generator.makeIcon();


                place1 =new MarkerOptions().position(latLng1).title("승원").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
                place2 =new MarkerOptions().position(latLng2).title("규도").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
                place3 =new MarkerOptions().position(latLng3).title("재석").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
                mainplace =new MarkerOptions().position(mainlatLng).title("약속장소").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_meet_24)).draggable(true);

                googleMap.addMarker(place1);
                googleMap.addMarker(place2);
                googleMap.addMarker(place3);
                googleMap.addMarker(mainplace).showInfoWindow();

//                String url = getUrl(place1.getPosition(), place2.getPosition(),"driving");
//                new FetchURL(getActivity()).execute(url,"driving");

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(mainlatLng));
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(14));
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                    permissionToken.continuePermissionRequest();
            }
        }).check();
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

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}