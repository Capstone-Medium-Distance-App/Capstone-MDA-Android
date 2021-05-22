package com.example.capstonedesign.Retrofit.DTO;

import com.google.gson.annotations.SerializedName;

public class rating {
    @SerializedName("userId")
    private int userId;

    @SerializedName("conditionEval")
    private int conditionEval;

    @SerializedName("kindnessEval")
    private int kindnessEval;

    @SerializedName("facilityEval")
    private int facilityEval;
}
