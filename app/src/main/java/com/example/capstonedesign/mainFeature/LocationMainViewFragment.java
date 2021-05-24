package com.example.capstonedesign.mainFeature;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.capstonedesign.R;
import com.example.capstonedesign.mainView.ViewAdapter;
import com.example.capstonedesign.mainView.ViewModel;
import com.example.capstonedesign.schedule.MyAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class LocationMainViewFragment extends Fragment {
    RecyclerView recyclerView;
    ViewAdapter viewAdapter;
    public LocationMainViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_location_main_view, container, false);


        recyclerView = rootView.findViewById(R.id.recyclerView_main_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        viewAdapter = new ViewAdapter(getActivity(),getMyList());
        recyclerView.setAdapter(viewAdapter);

        return rootView;
    }
    private ArrayList<ViewModel> getMyList(){
        ArrayList<ViewModel> models = new ArrayList<>();
        ViewModel m = new ViewModel();
        m.setViewTitle("Test1 title");
        m.setViewPlace("Test1 place");
        m.setViewTag("#Test1 #tag");
        m.setView(R.drawable.cafe1);
        models.add(m);

        m.setViewTitle("Test2 title");
        m.setViewPlace("Test2 place");
        m.setViewTag("#Test2 #tag");
        m.setView(R.color.orangeC);
        models.add(m);

        m.setViewTitle("Test3 title");
        m.setViewPlace("Test3 place");
        m.setViewTag("#Test3 #tag");
        m.setView(R.color.orangeC);
        models.add(m);


        return models;
    }

}