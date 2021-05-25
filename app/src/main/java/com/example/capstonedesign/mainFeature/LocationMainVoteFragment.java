package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.capstonedesign.R;


public class LocationMainVoteFragment extends Fragment implements View.OnClickListener {

    Fragment locationFinalSelectFragment = new LocationFinalSelectFragment();

    public LocationMainVoteFragment() { }

    private Button button1,button2,button3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_location_main_vote, container, false);


        button1 = rootView.findViewById(R.id.btn_vote1);
        button2 = rootView.findViewById(R.id.btn_vote2);
        button3 = rootView.findViewById(R.id.btn_vote3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_vote1: {
                String btn_txt = button1.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("btn_txt", btn_txt);
                locationFinalSelectFragment.setArguments(bundle);
                ((LocationActivity) getActivity()).replaceFragment(locationFinalSelectFragment);
                break;
            }
            case R.id.btn_vote2: {
                String btn_txt = button2.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("btn_txt", btn_txt);
                locationFinalSelectFragment.setArguments(bundle);
                ((LocationActivity) getActivity()).replaceFragment(locationFinalSelectFragment);
                break;
            }
            case R.id.btn_vote3: {
                String btn_txt = button3.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("btn_txt", btn_txt);
                locationFinalSelectFragment.setArguments(bundle);
                ((LocationActivity) getActivity()).replaceFragment(locationFinalSelectFragment);
                break;
            }
        }
    }
}