package com.example.capstonedesign.mainFeature;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.capstonedesign.Retrofit.DTO.schDT;
import com.example.capstonedesign.R;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationFinalSelectFragment extends Fragment {
    private RetrofitClient rc;

    public LocationFinalSelectFragment() {
        // Required empty public constructor
    }

    //스케쥴 날짜랑 시간 정해서 서버에게 보내는 코드
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_final_select, container, false);
        rc = new RetrofitClient();

        Button button = rootView.findViewById(R.id.btn_locationfinal_complete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), MapActivity.class);
                //startActivity(intent);
                ((LocationActivity)getActivity()).replaceFragment(new LocationFinishFragment());

                //값은 xml에서 받아와야함
                String schDate = "schDate";
                String schTime = "schTime";
                Call<schDT> call = rc.dataFlowService.schDT(schDate, schTime);
                call.enqueue(new Callback<schDT>() {
                    @Override
                    public void onResponse(Call<schDT> call, Response<schDT> response) {
                        final schDT sentData = response.body();
                        System.out.println("schDT DATA SEND SUCCESS!!!");
                        System.out.println("=========================================================");
                        System.out.println(sentData.toString());
                        System.out.println("=========================================================");
                    }
                    @Override
                    public void onFailure(Call<schDT> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("schDT DATA SEND FAIL!!!");
                    }
                });
            }
        });

        //Toolbar -> BackArrow+Title .version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Final Selections");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

        // Inflate the layout for this fragment
        return rootView;
    }
}