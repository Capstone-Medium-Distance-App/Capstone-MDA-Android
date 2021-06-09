package com.example.capstonedesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.capstonedesign.mainFeature.LocationActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private LocationStartFragment fragmentLocationStart = new LocationStartFragment();
    private ScheduleFragment fragmentSchedule = new ScheduleFragment();
    private CalendarFragment fragmentCalendar = new CalendarFragment();
    private SettingFragment fragmentSetting = new SettingFragment();
    private static String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);

        getDynamicLinkFromFireBase();
        Log.i("LocationActivity",id);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.frameLayout, fragmentLocationStart).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.locationStartFragment:
                    transaction.replace(R.id.frameLayout, fragmentLocationStart).commitAllowingStateLoss();
                    break;
                case R.id.scheduleFragment:
                    transaction.replace(R.id.frameLayout, fragmentSchedule).commitAllowingStateLoss();
                    break;
                case R.id.calendarFragment:
                    transaction.replace(R.id.frameLayout, fragmentCalendar).commitAllowingStateLoss();
                    break;
                case R.id.settingsFragment:
                    transaction.replace(R.id.frameLayout, fragmentSetting).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    private void getDynamicLinkFromFireBase() {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                        }
                        if (deepLink != null) {
                            id =deepLink.getQueryParameter("id");
                            Log.i("LocationActivity","Dnamic Link Success"+id);
                            Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }


                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("LocationActivity", "getDynamicLink:onFailure", e);
                    }
                });
    }
}