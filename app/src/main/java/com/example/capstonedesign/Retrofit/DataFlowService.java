 package com.example.capstonedesign.Retrofit;

import com.example.capstonedesign.Retrofit.DTO.infoList;
import com.example.capstonedesign.Retrofit.DTO.midAndPlace;
import com.example.capstonedesign.Retrofit.DTO.schDT;
import com.example.capstonedesign.Retrofit.DTO.voteStatus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataFlowService {
    //서버로부터 모든 사용자의 위치와 중간 위치를 받는 과정
    @GET("/midAndPlace")
    Call<midAndPlace> getMidAndPlace();

    @GET("infoList")
    Call<infoList> infoList();

    @GET("voteUserList")
    //Call<List<voteStatus>> userVoteList();
    Call<ArrayList<voteStatus>> userVoteList();

    @FormUrlEncoded
    @POST("userVote")
    Call<ResponseBody> userVote(
//            @Field("voteUserId")String voteUserId,
            @Field("pVotedUserName")String pVotedUserName,
            @Field("placePname")String placePname,
            @Field("pId")String pId
    );

    @FormUrlEncoded
    @POST("schDT")
    Call<schDT> saveSchDT(
            @Field("schDate") String schDate,
            @Field("schTime") String schTime,
            @Field("placeId") int placeId
    );



}
