package com.example.capstonedesign.Service;

import com.example.capstonedesign.DTO.ClientVote;
import com.example.capstonedesign.DTO.DTO;
import com.example.capstonedesign.DTO.LocInitSet;
import com.example.capstonedesign.DTO.PlaceDto;
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

    //LocationSetting의 마지막 단계에서 보냄
    //데이터가 넘어갔는지 확인할려면 '메인주소/locationInitSet'으로 접속해보면 확인 가능
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


    //서버로 사용자 자신의 위치를 보내는 메소드
    //데이터가 넘어갔는지 확인할려면 '메인주소/client_enter'으로 접속해보면 확인 가능
    @FormUrlEncoded
    @POST("client_enter")
    Call<userEnter> userEnter(
            @Field("userId")String userId,
            @Field("userLatitude")String userLatitude,
            @Field("userLongitude")String userLongitude
    );

    //서버에서 사용자가 선택한 장소에 대한 정보를 받는 메소드
    @GET("/placeDetail")
    Call<PlaceDto> placeDetail();


    @FormUrlEncoded
    @POST("Client/{num}/vote")
    Call<ClientVote> ClientVote(@Field("voteUserName")String voteUserName, @Field("votePlaceName")String votePlaceName);

}
