package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capstonedesign.R;


public class LocationSettingOneFragment extends Fragment {

    Fragment locationSettingThrFragment = new LocationSettingThrFragment();
    public LocationSettingOneFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_setting_one, container, false);
        Button btn_next = rootView.findViewById(R.id.btn_locationsettingone);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edit_txt_name= rootView.findViewById(R.id.edit_locationsettingone_name);
                EditText edit_txt_num= rootView.findViewById(R.id.edit_locationsettingone_num);

                String edit_name =edit_txt_name.getText().toString();
                String edit_num =edit_txt_num.getText().toString();

                Bundle bundle = new Bundle();
                String btn_txt = getArguments().getString("btn_txt");

                bundle.putString("btn_txt",btn_txt);
                bundle.putString("edit_name",edit_name);
                bundle.putString("edit_num",edit_num);

                locationSettingThrFragment.setArguments(bundle);
                ((LocationActivity)getActivity()).replaceFragment(locationSettingThrFragment);
            }
        });




        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Condition Setting");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

        return rootView;
    }
}