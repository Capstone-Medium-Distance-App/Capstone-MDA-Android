package com.example.capstonedesign.Service;

import com.example.capstonedesign.DTO.ClientVote;
import com.example.capstonedesign.DTO.DTO;
import com.example.capstonedesign.DTO.LocInitSet;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    //맨 처음에 테스트용
    @GET("test/{num}")
    Call<DTO> getPosts(@Path("num")String post);

    //LocationSetting의 마지막 단계에서 보냄
    @FormUrlEncoded
    @POST("locationInitSet")
    Call<LocInitSet> locationInitSet(
            @Field("schName")String schName,
            @Field("schAge")String schAge,
            @Field("schGender")String schGender,
            @Field("schPeople")String schPeople,
            @Field("schType")String schType,
            @Field("schCate")String schCate
    );

    @FormUrlEncoded
    @POST("Client/{num}/vote")
    Call<ClientVote> ClientVote(@Field("voteUserName")String voteUserName, @Field("votePlaceName")String votePlaceName);


}
