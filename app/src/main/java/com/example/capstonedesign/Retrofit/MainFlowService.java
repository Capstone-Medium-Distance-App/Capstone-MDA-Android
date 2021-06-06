package com.example.capstonedesign.Retrofit;

import com.example.capstonedesign.Retrofit.DTO.ScheduleDto;
import com.example.capstonedesign.Retrofit.DTO.ScheduleList;
import com.example.capstonedesign.Retrofit.DTO.locFin;
import com.example.capstonedesign.Retrofit.DTO.DTO;
import com.example.capstonedesign.Retrofit.DTO.LocInitSet;
import com.example.capstonedesign.Retrofit.DTO.PlaceDto;
import com.example.capstonedesign.Retrofit.DTO.rating;
import com.example.capstonedesign.Retrofit.DTO.userEnter;

import retrofit2.Call;
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
    @POST("userEnter")
    Call<userEnter> userEnter(
            @Field("userId")String userId,
            @Field("userLatitude")double userLatitude,
            @Field("userLongitude")double userLongitude
    );

    @FormUrlEncoded
    @POST("userLogin")
    Call<String> userLogin(@Field("userName") String userName);

    //서버에서 사용자가 선택한 장소에 대한 정보를 받는 메소드
    @GET("/placeDetail/{placeId}")
    Call<PlaceDto> placeDetail(@Path("placeId")int placeId);

    //서버에서 최종 약속에 대한 정보를 받는 메소드
    @GET("locationFin/{schId}")
    Call<locFin> locFin(@Path("schId")int schId);

    //약속했던 기록들을 한꺼번에 받는 메소드
    @GET("getSchedule/{userId}")
    Call<ScheduleList> getScheduleList(@Path("userId") int userId);

    //평점을 보내는 메소드
    @FormUrlEncoded
    @POST("rating")
    Call<rating> userRate(
            @Field("userId") int userId,
            @Field("conditionEval") int conditionEval,
            @Field("schId") int schId
    );

    //schId를 보내면 서버에서 해당 스케쥴을 리턴해줌
    @GET("schDetail")
    Call<ScheduleDto> schDetail(@Path("schId")int schId);
}
