package com.example.capstonedesign.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-3-37-60-253.ap-northeast-2.compute.amazonaws.com:8080/")
//            .baseUrl("http://192.168.35.225:8080/")
            //.baseUrl("http://192.168.35.71:8080/")
            //.baseUrl("http://192.168.219.109:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public MainFlowService mainFlowService = retrofit.create(MainFlowService.class);
    public DataFlowService dataFlowService = retrofit.create(DataFlowService.class);

}
