package com.example.capstonedesign.Retrofit;

import com.example.capstonedesign.Retrofit.DTO.infoList;
import com.example.capstonedesign.Retrofit.DTO.midAndPlace;
import com.example.capstonedesign.Retrofit.DTO.schDT;
import com.example.capstonedesign.Retrofit.DTO.userVote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataFlowService {
    //서버로부터 모든 사용자의 위치와 중간 위치를 받는 과정
    @GET("/getMidAndPlace")
    Call<midAndPlace> getMidAndPlace();

    @GET("infoList")
    Call<infoList> infoList();

    @GET("userEnterCnt")
    Call<Integer> userEnterCnt();

    @FormUrlEncoded
    @POST("/userVote")
    Call<userVote> userVote(
            @Field("voteUserId")int voteUserId,
            @Field("votePlaceName")int votePlaceName
    );

    @FormUrlEncoded
    @POST("/schDT")
    Call<schDT> schDT(
            @Field("schDate")String schDate,
            @Field("schTime")String schTime,
            @Field("placeId")int placeId
    );



}
