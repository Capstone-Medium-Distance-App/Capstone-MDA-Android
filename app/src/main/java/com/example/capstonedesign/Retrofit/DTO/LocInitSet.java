package com.example.capstonedesign.Retrofit.DTO;

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

    @Override
    public String toString() {
        return "LocInitSet{" +
                "schName='" + schName + '\'' +
                ", schAge='" + schAge + '\'' +
                ", schGender='" + schGender + '\'' +
                ", schPeople='" + schPeople + '\'' +
                ", schType='" + schType + '\'' +
                ", schPlaceCate='" + schPlaceCate + '\'' +
                '}';
    }
}