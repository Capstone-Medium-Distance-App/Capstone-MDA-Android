package com.example.capstonedesign.Retrofit.DTO;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class infoList {
    @SerializedName("placeId1")
    private String placeId1;

    @SerializedName("placeId2")
    private String placeId2;

    @SerializedName("placeId3")
    private String placeId3;

    @SerializedName("placeName1")
    private String placeName1;

    @SerializedName("placeName2")
    private String placeName2;

    @SerializedName("placeName3")
    private String placeName3;

    @SerializedName("placeArea1")
    private String placeArea1;

    @SerializedName("placeArea2")
    private String placeArea2;

    @SerializedName("placeArea3")
    private String placeArea3;

    @SerializedName("placeType1")
    private String placeType1;

    @SerializedName("placeType2")
    private String placeType2;

    @SerializedName("placeType3")
    private String placeType3;
}
