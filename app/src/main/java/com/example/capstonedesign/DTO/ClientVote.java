package com.example.capstonedesign.DTO;

import com.google.gson.annotations.SerializedName;

public class ClientVote {
    @SerializedName("voteUserName")
    private String voteUserName;

    @SerializedName("votePlaceName")
    private String votePlaceName;
}
