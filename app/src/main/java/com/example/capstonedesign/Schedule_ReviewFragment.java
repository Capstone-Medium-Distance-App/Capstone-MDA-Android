package com.example.capstonedesign;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.capstonedesign.Retrofit.DTO.rating;
import com.example.capstonedesign.Retrofit.DTO.schDT;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Schedule_ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Schedule_ReviewFragment extends Fragment {
    private RetrofitClient rc = new RetrofitClient();
    //별점 받을 변수들
    int conditionEval=0, kindnessEval=0, facilityEval=0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Schedule_ReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Schedule_ReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Schedule_ReviewFragment newInstance(String param1, String param2) {
        Schedule_ReviewFragment fragment = new Schedule_ReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int userid=0;
        Call<rating> call = rc.mainFlowService.userRate(userid, conditionEval, kindnessEval, facilityEval);
        call.enqueue(new Callback<rating>() {
            @Override
            public void onResponse(Call<rating> call, Response<rating> response) {
                final rating sentData = response.body();
                System.out.println("rating DATA SEND SUCCESS!!!");
                System.out.println("=========================================================");
                System.out.println(sentData.toString());
                System.out.println("=========================================================");
            }
            @Override
            public void onFailure(Call<rating> call, Throwable t) {
                t.printStackTrace();
                System.out.println("rating DATA SEND FAIL!!!");
            }
        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule__review, container, false);


    }
}