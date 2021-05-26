package com.example.capstonedesign.Retrofit.DTO;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class midAndPlace {
    @SerializedName("latitude1")
    private double latitude1;

    @SerializedName("latitude2")
    private double latitude2;

    @SerializedName("latitude3")
    private double latitude3;

    @SerializedName("longitude1")
    private double longitude1;

    @SerializedName("longitude2")
    private double longitude2;

    @SerializedName("longitude3")
    private double longitude3;

    @SerializedName("userId1")
    private int userId1;

    @SerializedName("userId2")
    private int userId2;

    @SerializedName("userId3")
    private int userId3;

    @SerializedName("userName1")
    private String userName1;

    @SerializedName("userName2")
    private String userName2;

    @SerializedName("userName3")
    private String userName3;

    @SerializedName("midLat")
    private double midLat;

    @SerializedName("midLong")
    private double midLong;

}
