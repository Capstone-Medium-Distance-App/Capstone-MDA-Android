package com.example.capstonedesign.Service;

import com.example.capstonedesign.DTO.ClientVote;
import com.example.capstonedesign.DTO.DTO;
import com.example.capstonedesign.DTO.LocInitSet;
import com.example.capstonedesign.DTO.cafeMapStart;
import com.example.capstonedesign.DTO.cli_Loc;
import com.example.capstonedesign.DTO.userEnter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MainFlowService {

    //맨 처음에 테스트용
    @GET("test/{num}")
    Call<DTO> getPosts(@Path("num")String post);

//    //LocationSetting의 마지막 단계에서 보냄
    @FormUrlEncoded
    @POST("locationInitSet")
    Call<LocInitSet> locationInitSet(
            @Field("schName")String schName,
            @Field("schAge")String schAge,
            @Field("schGender")String schGender,
            @Field("schPeople")String schPeople,
            @Field("schType")String schType,
            @Field("schPlaceCate")String schCate
    );


    //main으로 사용자가 넘어갈때
    @FormUrlEncoded
    @POST("client/{num}/enter")
    Call<userEnter> userEnter(
            @Field("userId")String userId,
            @Field("userLatitude")String userLatitude,
            @Field("userLongitude")String userLongitude
    );


    @FormUrlEncoded
    @POST("Client/{num}/vote")
    Call<ClientVote> ClientVote(@Field("voteUserName")String voteUserName, @Field("votePlaceName")String votePlaceName);

}
