package com.example.capstonedesign.DTO;

import com.google.gson.annotations.SerializedName;

public class LocInitSet {
    @SerializedName("schName")
    private String schName;

    @SerializedName("schAge")
    private String schAge;

    @SerializedName("schGender")
    private String schGender;

    @SerializedName("schPeople")
    private String schPeople;

    @SerializedName("schType")
    private String schType;

    @SerializedName("schCate")
    private String schCate;

    @Override
    public String toString() {
        return "LocInitSet{" +
                "schName='" + schName + '\'' +
                ", schAge='" + schAge + '\'' +
                ", schGender='" + schGender + '\'' +
                ", schPeople='" + schPeople + '\'' +
                ", schType='" + schType + '\'' +
                ", schCate='" + schCate + '\'' +
                '}';
    }
}