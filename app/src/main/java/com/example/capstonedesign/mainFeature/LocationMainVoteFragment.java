package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.capstonedesign.R;
import com.example.capstonedesign.Retrofit.DTO.ScheduleList;
import com.example.capstonedesign.Retrofit.DTO.voteStatus;
import com.example.capstonedesign.Retrofit.RetrofitClient;
import com.example.capstonedesign.mainVote.Vmodel;
import com.example.capstonedesign.mainVote.VmyAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationMainVoteFragment extends Fragment {
    RecyclerView recyclerView;
    VmyAdapter vmyAdapter;

    public LocationMainVoteFragment() { }

    private Button button1,button2,button3;
    private RetrofitClient rc = new RetrofitClient();
    private ArrayList<voteStatus> arr = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_location_main_vote, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerview_vote);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<ArrayList<voteStatus>> call = rc.dataFlowService.userVoteList();
        call.enqueue(new Callback<ArrayList<voteStatus>>() {
            @Override
            public void onResponse(Call<ArrayList<voteStatus>> call, Response<ArrayList<voteStatus>> response) {
                System.out.println("=========================================================");
                System.out.println("arrayList<voteStatus> DATA RECEIVE SUCCESS!");
                arr = response.body();
                for(int i=0; i<arr.size(); i++){
                    System.out.println(arr.get(i).getPvotedUserName()+" vote "+arr.get(i).getPlacePname()+"("+arr.get(i).getPid()+")");
                }
                System.out.println("=========================================================");
                vmyAdapter = new VmyAdapter(getActivity(),getMyList());
                recyclerView.setAdapter(vmyAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<voteStatus>> call, Throwable t) {

            }
        });

//        button1 = rootView.findViewById(R.id.btn_vote1);
//        button2 = rootView.findViewById(R.id.btn_vote2);
//        button3 = rootView.findViewById(R.id.btn_vote3);
//        button1.setOnClickListener(this);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);

        return rootView;
    }
    private ArrayList<Vmodel>getMyList(){
        ArrayList<Vmodel> vmodels = new ArrayList<>();

        for(int i=0; i<arr.size(); i++){
            Vmodel m = new Vmodel();
            m.setVPlace(arr.get(i).getPlacePname());
            m.setVName(arr.get(i).getPvotedUserName());
            m.setVPlaceId(arr.get(i).getPid());
            vmodels.add(m);
        }

//        Vmodel m = new Vmodel();
//        m.setVPlace("STARBUCKS");
//        m.setVName("SEUNG");
//        vmodels.add(m);
//
//        m = new Vmodel();
//        m.setVPlace("is CAFE");
//        m.setVName("kyu");
//        vmodels.add(m);
//
//        m = new Vmodel();
//        m.setVPlace("CSCS CAFE");
//        m.setVName("jae,jung");
//        vmodels.add(m);

        return vmodels;
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_vote1: {
//                String btn_txt = button1.getText().toString();
//                Bundle bundle = new Bundle();
//                bundle.putString("btn_txt", btn_txt);
//                locationFinalSelectFragment.setArguments(bundle);
//                ((LocationActivity) getActivity()).replaceFragment(locationFinalSelectFragment);
//                break;
//            }
//            case R.id.btn_vote2: {
//                String btn_txt = button2.getText().toString();
//                Bundle bundle = new Bundle();
//                bundle.putString("btn_txt", btn_txt);
//                locationFinalSelectFragment.setArguments(bundle);
//                ((LocationActivity) getActivity()).replaceFragment(locationFinalSelectFragment);
//                break;
//            }
//            case R.id.btn_vote3: {
//                String btn_txt = button3.getText().toString();
//                Bundle bundle = new Bundle();
//                bundle.putString("btn_txt", btn_txt);
//                locationFinalSelectFragment.setArguments(bundle);
//                ((LocationActivity) getActivity()).replaceFragment(locationFinalSelectFragment);
//                break;
//            }
//        }
//    }
}