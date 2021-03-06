package com.example.capstonedesign.mainFeature;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.fragment.app.FragmentTransaction;

import com.example.capstonedesign.R;
import com.example.capstonedesign.Retrofit.DTO.midAndPlace;
import com.example.capstonedesign.Retrofit.DTO.userEnter;
import com.example.capstonedesign.Retrofit.MainFlowService;
import com.example.capstonedesign.Retrofit.RetrofitClient;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.capstonedesign.mainFeature.FetchURL;
import com.example.capstonedesign.mainFeature.TaskLoadedCallback;


import static android.content.Context.CLIPBOARD_SERVICE;
import static com.example.capstonedesign.user.UserInfo.userName;

public class LocationMainFragment extends Fragment implements OnMapReadyCallback,TaskLoadedCallback,LocationListener {

    private LocationMainViewFragment fragmentView = new LocationMainViewFragment();
    private LocationMainVoteFragment fragmentVote = new LocationMainVoteFragment();
    private GoogleMap googleMap;
    private Retrofit retrofit;
    private RetrofitClient rc = new RetrofitClient();
    private MapView mapView;
    private double lat = 37.66657228807503, log = 126.76327377041474;
    //37.66657228807503, 126.76327377041474
    private LatLng latLng1,latLng2,latLng3,mainlatLng;
    LocationManager locationManager;
    LocationListener locationListener;
    MarkerOptions place1, place2, place3, mainplace;
    Polyline currentPolyline;
    TextView txt_ex2;
    private String category;
    public LocationMainFragment() { }

    public static LocationMainFragment newInstance() {
        LocationMainFragment fragment = new LocationMainFragment();
        return fragment;
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            switch(menuItem.getItemId())
            {
                case R.id.cafe:
                    Toast.makeText(getActivity(), category, Toast.LENGTH_LONG);
                    if(category.equals("cafe")) {
                        menuItem.setIcon(R.drawable.ic_cafe_24);
                        menuItem.setTitle("cafe");
                    }else if(category.equals("restaurant")) {
                        menuItem.setIcon(R.drawable.ic_restaurant_24);
                        menuItem.setTitle("restaurant");
                    }else if(category.equals("activity")) {
                        menuItem.setIcon(R.drawable.ic_act_24);
                        menuItem.setTitle("activity");
                    }
                    transaction.replace(R.id.main_location_FrameLayout, fragmentView);
                    transaction.commit();
                    break;
                case R.id.vote:
                    transaction.replace(R.id.main_location_FrameLayout, fragmentVote);
                    transaction.commit();
                    break;
            }
            return true;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_location_main, container, false);

            category = getArguments().getString("btn_txt");

        BottomNavigationView bottomNavigationView = rootView.findViewById(R.id.bottomNavi_main_location);
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.main_location_FrameLayout, fragmentView);
//        transaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.mapView);

        mapFragment.getMapAsync(this);



        //??????????????? ???????????? ????????? ??????????????? ????????? ?????? ?????? -kyu
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
//                    Log.d(TAG, "onResponse: ??????, ??????\n");
//                }else{
//                    Log.d(TAG, "onRespones: ??????");
//                }
//            }
//            @Override
//            public void onFailure(Call<cli_Loc> call, Throwable t) {
//                Log.d(TAG, "onFailure: "+t.getMessage());
//            }
//        });

//        String btn_txt = getArguments().getString("btn_txt");
//        //?????? ???????????? ?????? ????????? ??????????????? ??????????????? ?????? -> ????????? ??????????????? url?????? ?????????????????????, ?????????????????? ????????? ??????????????? ???????????? ???????????? ?????? ?????????????????? ???????????????
//        switch(btn_txt){
//            case "cafe":{
//
//            }
//
//        }



//        //Toolbar -> BackArrow + title + icons .version
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar_main_location);
        ((LocationActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Where To Go?");
        actionBar.setDisplayHomeAsUpEnabled(true); //???????????? ??????
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back); //????????????????????? ??????????????? ??????
        actionBar.setDisplayShowCustomEnabled(true); //?????????????????????
        setHasOptionsMenu(true);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        googleMap.addMarker(place2);
                        googleMap.addMarker(place3);
                        googleMap.addMarker(mainplace).showInfoWindow();
                    }
                },2000);

            }
        });


        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        // 37.659627, 126.773459 ????????????

        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lat = location.getLatitude();
                log = location.getLongitude();

                latLng1 = new LatLng(lat, log);
                latLng2 = new LatLng(37.648984, 126.774089);
                latLng3 = new LatLng(37.671873, 126.785645);
                mainlatLng = new LatLng(37.659627, 126.773459);
                //marker listener ???????????? ???????????? ????????? ???????????? ??????????????????

                googleMap.clear();
//
//                place1 =new MarkerOptions().position(latLng1).title("??????").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
//                place2 =new MarkerOptions().position(latLng2).title("??????").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
//                place3 =new MarkerOptions().position(latLng3).title("??????").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
//                mainplace =new MarkerOptions().position(mainlatLng).title("????????????").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_meet_24)).draggable(true);

//
//                googleMap.addMarker(place1);
//
//
//                if(userName.equals("kyu"))
//                {
//                    googleMap.addMarker(place2);
//                    googleMap.addMarker(place3);
//                    googleMap.addMarker(mainplace).showInfoWindow();
//                }


//                String url = getUrl(place1.getPosition(), place2.getPosition(),"driving");
//                new FetchURL(getActivity()).execute(url,"driving");

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(mainlatLng));
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(14));
            }
        };
        askLocationPermission();

        //??????????????? ????????????
        String userid = "123456789";
        Double userLat = lat;
        Double userLong = log;


//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(lat, log))      // Sets the center of the map to Mountain View
//                .zoom(17)                   // Sets the zoom
//                .bearing(90)                // Sets the orientation of the camera to east
//                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
//                .build();                   // Creates a CameraPosition from the builder
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //????????? ??????????????? ????????? ????????? ????????? ????????? ?????? -kyu
        // String -> Double??? ????????????~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Call<userEnter> SendCall = rc.mainFlowService.userEnter(userid, lat, log);

        SendCall.enqueue(new Callback<userEnter>() {
            @Override
            public void onResponse(Call<userEnter> call, Response<userEnter> response) {
                System.out.println("=========================================================");
                System.out.println("userEnter DATA SEND SUCCESS!!!");
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

        //??????????????? ???????????? ????????? ??????????????? ????????? ?????? ?????? -kyu
        //mainfragment??? ?????????, ?????? LocationMainView??? ????????? infoList(?????????????????????)??? ?????? ??????????????? ???????????????
        //retrofit ?????? : infoList -> userEnter -> midAndPlace
        //????????? midAndPlace??? oncreate??? ?????? ????????????
        Call<midAndPlace> cli_locCall = rc.dataFlowService.getMidAndPlace();
        cli_locCall.enqueue(new Callback<midAndPlace>(){
            String TAG = "TAG";
            @Override
            public void onResponse(Call<midAndPlace> call, Response<midAndPlace> response) {

                System.out.println("=========================================================");
                System.out.println("User123 lat,log /  DATA RECEIVED SUCCESS!!!");
                midAndPlace result = response.body();
                System.out.println("latitude 1,2,3 : "+result.getLatitude1()+" / "+result.getLatitude2()+" / "+result.getLatitude3());
                System.out.println("longitude 1,2,3 : "+result.getLongitude1()+" / "+result.getLongitude2()+" / "+result.getLongitude3());
                System.out.println("username 1,2,3 : "+result.getUserName1()+" / "+result.getUserName2()+" / "+result.getUserName3());
                System.out.println("userid 1,2,3 : "+result.getUserId1()+" / "+result.getUserId2()+" / "+result.getUserId3());
                System.out.println("midLat, Long : "+result.getMidLat()+" / "+result.getMidLong());
                System.out.println("=========================================================");
//                    latLng1 = new LatLng(result.getLatitude1(), result.getLongitude1());
//                    latLng2 = new LatLng(result.getLatitude2(), result.getLongitude2());
//                    latLng3 = new LatLng(result.getLatitude3(), result.getLongitude3());
//                    mainlatLng = new LatLng(result.getMidLat(), result.getMidLong());
//                    //mapFragment.getMapAsync(LocationMainFragment.this::onMapReady);


            }
            @Override
            public void onFailure(Call<midAndPlace> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable",getActivity().getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
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
//                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                lat = lastLocation.getLatitude();
//                log = lastLocation.getLongitude();

//                latLng1 = new LatLng(lat, log);
//                latLng2 = new LatLng(37.648984, 126.774089);
//                latLng3 = new LatLng(37.671873, 126.785645);
//                mainlatLng = new LatLng(37.659627, 126.773459);
//                latLng1 = new LatLng(lat, log);
                latLng1 = new LatLng(37.618669, 126.924600);//?????????
                latLng2 = new LatLng(37.605043, 127.140592);//?????????
                latLng3 = new LatLng(37.486886, 127.048225);//?????????
                mainlatLng = new LatLng(37.569997, 126.992131);//??????3???


                googleMap.clear();


                place1 =new MarkerOptions().position(latLng1).title("koo").snippet("1h 20m").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
                place2 =new MarkerOptions().position(latLng2).title("sim").snippet("1h 00m").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_person_24));
                place3 =new MarkerOptions().position(latLng3).title("you").snippet("1h 10m").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_baseline_person_pin_24));
                mainplace =new MarkerOptions().position(mainlatLng).title("<Here!!>").icon(bitmapDescriptorFromVector(getActivity(),R.drawable.ic_main_meet_24)).draggable(true);
                googleMap.addMarker(place1);

                if(userName.equals("sim"))
                {
                    googleMap.addMarker(place2);
                    googleMap.addMarker(place3);
                    googleMap.addMarker(mainplace).showInfoWindow();
                }


                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
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
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", "https://capstonedesign.page.link/signIn");
//                        Uri copyUri = Uri.parse("https://capstonedesign.page.link/signIn");
//                        ClipData clip = ClipData.newUri(getActivity().getContentResolver(), "URI", copyUri);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity(), "Copy : https://capstonedesign.page.link/signIn", Toast.LENGTH_SHORT).show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        googleMap.addMarker(place2);
                        googleMap.addMarker(place3);
                        googleMap.addMarker(mainplace).showInfoWindow();
                        googleMap.addPolyline(new PolylineOptions().add(latLng1,mainlatLng).width(9).color(Color.GRAY));
                        googleMap.addPolyline(new PolylineOptions().add(latLng2,mainlatLng).width(9).color(Color.GRAY));
                        googleMap.addPolyline(new PolylineOptions().add(latLng3,mainlatLng).width(9).color(Color.GRAY));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mainlatLng));
                        googleMap.moveCamera(CameraUpdateFactory.zoomTo(11));
                    }
                },2000);

//                Toast.makeText(getActivity(), "This is SHARE", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}