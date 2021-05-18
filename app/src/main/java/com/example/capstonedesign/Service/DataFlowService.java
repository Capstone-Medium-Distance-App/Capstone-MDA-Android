package com.example.capstonedesign.Service;

import com.example.capstonedesign.DTO.cafeMapStart;
import com.example.capstonedesign.DTO.cli_Loc;

import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface DataFlowService {
    //서버로부터 모든 사용자의 위치와 중간 위치를 받는 과정
    @GET("clientsLocation")
    Call<cli_Loc> cli_Loc(
            @Field("latitude1")String latitude1,
            @Field("latitude2")String latitude2,
            @Field("latitude3")String latitude3,
            @Field("longitude1")String longitude1,
            @Field("longitude2")String longitude2,
            @Field("longitude3")String longitude3,
            @Field("userName1")String userName1,
            @Field("userName2")String userName2,
            @Field("userName3")String userName3,
            @Field("midLat")String midLat,
            @Field("midLong")String midLong

    );

    @GET("/cafeInit")
    Call<cafeMapStart> cafeMapStart(
            @Field("cafe1PlaceName")String cafe1PlaceName,
            @Field("cafe2PlaceName")String cafe2PlaceName,
            @Field("cafe3PlaceName")String cafe3PlaceName,
            @Field("cafe1PlaceArea")String cafe1PlaceArea,
            @Field("cafe2PlaceArea")String cafe2PlaceArea,
            @Field("cafe3PlaceArea")String cafe3PlaceArea,
            @Field("cafe1PlaceType")String cafe1PlaceType,
            @Field("cafe2PlaceType")String cafe2PlaceType,
            @Field("cafe3PlaceType")String cafe3PlaceType
    );

}
