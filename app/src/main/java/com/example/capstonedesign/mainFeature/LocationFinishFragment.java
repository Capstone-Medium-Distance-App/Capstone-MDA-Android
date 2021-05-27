package com.example.capstonedesign.mainFeature;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstonedesign.MainActivity;
import com.example.capstonedesign.R;
import com.example.capstonedesign.Retrofit.DTO.locFin;
import com.example.capstonedesign.Retrofit.RetrofitClient;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationFinishFragment extends Fragment {
    private RetrofitClient rc;

    public LocationFinishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_location_finish, container, false);
        TextView appoint = rootView.findViewById(R.id.txt_locationfinish_appoint);
        TextView where = rootView.findViewById(R.id.txt_locationfinish_where);
        TextView who = rootView.findViewById(R.id.txt_locationfinish_who);
        TextView place = rootView.findViewById(R.id.txt_locationfinish_place);


        //약속정보를 받아와서 출력해줌----------------------------여기
        int schId = 1;
        rc = new RetrofitClient();
        Call<locFin> call = rc.mainFlowService.locFin(schId);
        call.enqueue(new Callback<locFin>() {
            @Override
            public void onResponse(Call<locFin> call, Response<locFin> response) {
                System.out.println("placeDetails DATA RECEIVE SUCCESS!!!");
                System.out.println("=========================================================");
                System.out.println(response.toString());
                locFin recData = response.body();
                System.out.println(recData.getSchName());
                System.out.println(recData.getSchPlaceName());
                System.out.println(recData.getSchPlaceArea());
                System.out.println(recData.getSchWIthUserName());
                System.out.println("=========================================================");

                //다 받아왔으면 출력에 설정해주면 된다!
                appoint.setText(recData.getSchName());
                where.setText(recData.getSchPlaceArea());
                who.setText(recData.getSchWIthUserName());
                place.setText(recData.getSchPlaceName());
            }

            @Override
            public void onFailure(Call<locFin> call, Throwable t) {

            }
        });

        Button button = rootView.findViewById(R.id.btn_locationfinish_inform);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"Appointment is completed",Toast.LENGTH_SHORT).show();
            }
        });

        //Toolbar -> BackArrow+Title .version
        Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((LocationActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((LocationActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Final Checkup");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_tool_back);

        // Inflate the layout for this fragment
        return rootView;
    }
}