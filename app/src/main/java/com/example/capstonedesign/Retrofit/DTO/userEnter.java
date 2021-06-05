package com.example.capstonedesign.Retrofit.DTO;

import com.google.gson.annotations.SerializedName;

public class userEnter {
    @SerializedName("userId")
    private String userId;

    @SerializedName("userLatitude")
    private double userLatitude;

    @SerializedName("userLongitude")
    private double userLongitude;

    @Override
    public String toString() {
        return "userEnter{" +
                "userId='" + userId + '\'' +
                ", userLatitude=" + userLatitude +
                ", userLongitude=" + userLongitude +
                '}';
    }
}
