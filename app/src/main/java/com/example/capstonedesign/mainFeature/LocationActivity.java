package com.example.capstonedesign.mainFeature;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.capstonedesign.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;


public class LocationActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
//    private LocationDetailFragment fragmentLocationDetail = new LocationDetailFragment();
    private LocationMainFragment fragmentMainLocation = new LocationMainFragment();
//    private LocationFinalSelectFragment fragmentFinalSelect = new LocationFinalSelectFragment();
//    private LocationFinishFragment fragmentLocationFinish = new LocationFinishFragment();
    private LocationSettingFragment fragmentSetting = new LocationSettingFragment();
    String id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        Intent intent = getIntent();
        id = intent.getStringExtra("idid");

//        if(intent.getStringExtra("idid")!=null) {
//            id = intent.getStringExtra("idid");
//        }

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout2, fragmentSetting).commitAllowingStateLoss();

            

        //   FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //   fragmentTransaction.add(R.id.ContentLayout, StudyFragment.newInstance()).commit();

    }



    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout2, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }


}