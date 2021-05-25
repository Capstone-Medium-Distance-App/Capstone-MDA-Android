package com.example.capstonedesign.mainFeature;

import static com.example.capstonedesign.user.UserInfo.userId;
import static com.example.capstonedesign.user.UserInfo.userName;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.capstonedesign.R;


public class LocationSettingFragment extends Fragment implements View.OnClickListener{
    String TAG = "Tag";
    Fragment locationSettingOneFragment = new LocationSettingOneFragment();

    //locationFinalSelect test -> DELETE SOMEDAY!
    Fragment lc = new LocationFinalSelectFragment();

    //
    private Button btn_cafe,btn_rest,btn_act;
    public LocationSettingFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_setting, container, false);
        TextView tv = rootView.findViewById(R.id.edit_locationsettingone_name);
        btn_cafe = rootView.findViewById(R.id.btn_setting_cafe);
        btn_rest = rootView.findViewById(R.id.btn_setting_restaurant);
        btn_act = rootView.findViewById(R.id.btn_setting_activity);
        btn_cafe.setOnClickListener(this);
        btn_rest.setOnClickListener(this);
        btn_act.setOnClickListener(this);


        //Toolbar -> BackArrow+Title .version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Condition Setting");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_setting_cafe:
                {
                    String btn_txt =btn_cafe.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("btn_txt",btn_txt);
                    locationSettingOneFragment.setArguments(bundle);
                    //original
                    ((LocationActivity)getActivity()).replaceFragment(locationSettingOneFragment);

                    //locationFinalSelect test -> DELETE SOMEDAY!
                    //((LocationActivity)getActivity()).replaceFragment(lc);

                    break;
                }
            case R.id.btn_setting_restaurant:
                {
                    String btn_txt =btn_rest.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("btn_txt",btn_txt);
                    locationSettingOneFragment.setArguments(bundle);
                    ((LocationActivity)getActivity()).replaceFragment(locationSettingOneFragment);
                    break;
            }
            case R.id.btn_setting_activity:
                {
                    String btn_txt =btn_act.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("btn_txt",btn_txt);
                    locationSettingOneFragment.setArguments(bundle);
                    ((LocationActivity)getActivity()).replaceFragment(locationSettingOneFragment);
                    break;
            }

        }
    }
}
