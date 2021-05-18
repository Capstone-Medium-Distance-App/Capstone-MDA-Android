package com.example.capstonedesign.DTO;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @SerializedName("schPlaceCate")
    private String schPlaceCate;

}