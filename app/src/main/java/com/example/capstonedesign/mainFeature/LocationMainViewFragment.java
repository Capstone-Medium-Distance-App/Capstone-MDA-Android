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
import com.example.capstonedesign.Retrofit.DTO.infoList;
import com.example.capstonedesign.Retrofit.RetrofitClient;
import com.example.capstonedesign.mainView.ViewAdapter;
import com.example.capstonedesign.mainView.ViewModel;
import com.example.capstonedesign.schedule.MyAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationMainViewFragment extends Fragment {
    RecyclerView recyclerView;
    ViewAdapter viewAdapter;
    infoList info;
    RetrofitClient rc = new RetrofitClient();
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

        Call<infoList> call = rc.dataFlowService.infoList();
        call.enqueue(new Callback<infoList>() {
            @Override
            public void onResponse(Call<infoList> call, Response<infoList> response) {
                System.out.println("infoList DATA RECEIVE SUCCESS!!!");
                System.out.println("=========================================================");
                info = response.body();
                System.out.println(info.toString());
                System.out.println("=========================================================");
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<infoList> call, Throwable t) {

            }
        });



        return rootView;
    }
    private ArrayList<ViewModel> getMyList(){
        ArrayList<ViewModel> models = new ArrayList<>();
        ViewModel m = new ViewModel();
        m.setViewTitle(info.getPlaceName1());
        m.setViewPlace(info.getPlaceArea1());
        m.setViewTag(info.getPlaceType1());
        m.setView(R.color.orangeC);
        m.setPlaceId(info.getPlaceId1());
        models.add(m);

        m =new ViewModel();
        m.setViewTitle(info.getPlaceName2());
        m.setViewPlace(info.getPlaceArea2());
        m.setViewTag(info.getPlaceType2());
        m.setView(R.color.orangeC);
        m.setPlaceId(info.getPlaceId2());
        models.add(m);

        m =new ViewModel();
        m.setViewTitle(info.getPlaceName3());
        m.setViewPlace(info.getPlaceArea3());
        m.setViewTag(info.getPlaceType3());
        m.setPlaceId(info.getPlaceId3());
        m.setView(R.color.orangeC);
        models.add(m);


//        ViewModel m = new ViewModel();
//        m.setViewTitle("");
//        m.setViewPlace("");
//        m.setViewTag("");
////        m.setPlaceId(Integer.parseInt("1"));
//        m.setView(R.color.orangeC);
//        models.add(m);
//
//        m =new ViewModel();
//        m.setViewTitle("");
//        m.setViewPlace("");
//        m.setViewTag("");
//        m.setView(R.color.orangeC);
//        models.add(m);
//
//        m =new ViewModel();
//        m.setViewTitle("");
//        m.setViewPlace("");
//        m.setViewTag("");
//        m.setView(R.color.orangeC);
//        models.add(m);


        return models;
    }

}