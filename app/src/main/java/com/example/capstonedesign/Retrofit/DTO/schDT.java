package com.example.capstonedesign.Retrofit.DTO;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class schDT {
    @SerializedName("schDate")
    String schDate;

    @SerializedName("schTime")
    String schTime;

    @SerializedName("placeId")
    int placeId;
}
