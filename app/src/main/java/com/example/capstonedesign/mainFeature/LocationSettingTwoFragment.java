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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.capstonedesign.Retrofit.DTO.LocInitSet;
import com.example.capstonedesign.R;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationSettingTwoFragment extends Fragment implements  CompoundButton.OnCheckedChangeListener  {
   public LocationSettingTwoFragment() { }
    private CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8;
    private TextView txt_category;
    Fragment locationMainFragment = new LocationMainFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_setting_two, container, false);
        Button button = rootView.findViewById(R.id.btn_locationsettingtwo);
        txt_category = rootView.findViewById(R.id.txt_category);

        cb1 = rootView.findViewById(R.id.cb_1);
        cb2 = rootView.findViewById(R.id.cb_2);
        cb3 = rootView.findViewById(R.id.cb_3);
        cb4 = rootView.findViewById(R.id.cb_4);
        cb5 = rootView.findViewById(R.id.cb_5);
        cb6 = rootView.findViewById(R.id.cb_6);
        cb7 = rootView.findViewById(R.id.cb_7);
        cb8 = rootView.findViewById(R.id.cb_8);


        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);
        cb5.setOnCheckedChangeListener(this);
        cb6.setOnCheckedChangeListener(this);
        cb7.setOnCheckedChangeListener(this);
        cb8.setOnCheckedChangeListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                String txt_categ = txt_category.getText().toString();

                String schName = getArguments().getString("edit_name");
                String schPeople = getArguments().getString("edit_num");
                String schType = getArguments().getString("btn_txt");
                String schAge = getArguments().getString("txt_age");
                String schGender = getArguments().getString("txt_gender");

                bundle.putString(schType,"btn_txt");
//                bundle.putString(edit_name,"edit_name");
//                bundle.putString(edit_num,"edit_num");
//                bundle.putString(txt_age,"txt_age");
//                bundle.putString(txt_gender,"txt_gender");
//                bundle.putString(txt_categ,"txt_categ");
//
//                Toast.makeText(getActivity(),btn_txt+"   "+ txt_gender+"   "+ txt_categ,Toast.LENGTH_SHORT).show();
                RetrofitClient rc = new RetrofitClient();
                String schPlaceCate = txt_categ;
                Call<LocInitSet> call = rc.mainFlowService.locationInitSet(schName, schAge, schGender, schPeople, schType, schPlaceCate);
                call.enqueue(new Callback<LocInitSet>() {
                    @Override
                    public void onResponse(Call<LocInitSet> call, Response<LocInitSet> response) {
                        System.out.println("LocInitSet DATA SEND SUCCESS!!!");
                        System.out.println("=========================================================");
                        Log.d("TAG",response.code()+"");
                        Log.d("TAG",response.errorBody()+"");
                        System.out.println(response);
                        System.out.println("=========================================================");

                    }

                    @Override
                    public void onFailure(Call<LocInitSet> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println(t.getMessage());
                    }
                });
                locationMainFragment.setArguments(bundle);
                ((LocationActivity)getActivity()).replaceFragment(locationMainFragment);
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
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String result ="";
        if(cb1.isChecked()) result += " #"+cb1.getText().toString();
        if(cb2.isChecked()) result += " #"+cb2.getText().toString();
        if(cb3.isChecked()) result += " #"+cb3.getText().toString();
        if(cb4.isChecked()) result += " #"+cb4.getText().toString();
        if(cb5.isChecked()) result += " #"+cb5.getText().toString();
        if(cb6.isChecked()) result += " #"+cb6.getText().toString();
        if(cb7.isChecked()) result += " #"+cb7.getText().toString();
        if(cb8.isChecked()) result += " #"+cb8.getText().toString();
        txt_category.setText(result);
    }
}