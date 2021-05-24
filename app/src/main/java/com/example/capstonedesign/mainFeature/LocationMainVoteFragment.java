package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.capstonedesign.R;


public class LocationMainVoteFragment extends Fragment {



    public LocationMainVoteFragment() { }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_location_main_vote, container, false);

        return rootView;
    }
}