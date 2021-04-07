package com.example.capstonedesign.DTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("test/{num}")
    Call<DTO> getPosts(@Path("num")String post);
}
