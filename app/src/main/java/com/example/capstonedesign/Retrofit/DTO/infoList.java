package com.example.capstonedesign.Retrofit.DTO;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class infoList {
    @SerializedName("placeId1")
    private int placeId1;

    @SerializedName("placeId2")
    private int placeId2;

    @SerializedName("placeId3")
    private int placeId3;

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

    @Override
    public String toString() {
        return "infoList{" +
                "placeId1=" + placeId1 +
                ", placeId2=" + placeId2 +
                ", placeId3=" + placeId3 +
                ", placeName1='" + placeName1 + '\'' +
                ", placeName2='" + placeName2 + '\'' +
                ", placeName3='" + placeName3 + '\'' +
                ", placeArea1='" + placeArea1 + '\'' +
                ", placeArea2='" + placeArea2 + '\'' +
                ", placeArea3='" + placeArea3 + '\'' +
                ", placeType1='" + placeType1 + '\'' +
                ", placeType2='" + placeType2 + '\'' +
                ", placeType3='" + placeType3 + '\'' +
                '}';
    }
}
