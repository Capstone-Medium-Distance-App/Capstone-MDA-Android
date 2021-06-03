package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.capstonedesign.R;

public class LocationSettingThrFragment extends Fragment {

     public LocationSettingThrFragment() { }
    Fragment locationSettingTwoFragment = new LocationSettingTwoFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_setting_thr, container, false);
        Button btn_next = rootView.findViewById(R.id.btn_locationsettingthr);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroupAge = rootView.findViewById(R.id.rbtn_locationsetting_age);
                RadioGroup radioGroupGender = rootView.findViewById(R.id.rbtn_locationsetting_gender);
                RadioButton radioButtonAge = rootView.findViewById(radioGroupAge.getCheckedRadioButtonId());
                RadioButton radioButtonGender = rootView.findViewById(radioGroupGender.getCheckedRadioButtonId());

                String btn_txt = getArguments().getString("btn_txt");
                String edit_name = getArguments().getString("edit_name");
                String edit_num = getArguments().getString("edit_num");

                String txt_age = radioButtonAge.getText().toString();
                String txt_gender = radioButtonGender.getText().toString();

                Bundle bundle = new Bundle();

                bundle.putString("txt_age",txt_age);
                bundle.putString("txt_gender",txt_gender);
                bundle.putString("btn_txt",btn_txt);
                bundle.putString("edit_name",edit_name);
                bundle.putString("edit_num",edit_num);


                locationSettingTwoFragment.setArguments(bundle);
                ((LocationActivity)getActivity()).replaceFragment(locationSettingTwoFragment);
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