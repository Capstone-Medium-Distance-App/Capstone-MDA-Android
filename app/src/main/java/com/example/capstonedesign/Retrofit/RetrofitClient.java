package com.example.capstonedesign.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    Retrofit retrofit = new Retrofit.Builder()
            //.baseUrl("http://ec2-3-37-60-253.ap-northeast-2.compute.amazonaws.com:8080/")
            .baseUrl("http://192.168.35.225:8080/")
            //.baseUrl("http://192.168.35.71:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public MainFlowService mainFlowService = retrofit.create(MainFlowService.class);
    public DataFlowService dataFlowService = retrofit.create(DataFlowService.class);

}
