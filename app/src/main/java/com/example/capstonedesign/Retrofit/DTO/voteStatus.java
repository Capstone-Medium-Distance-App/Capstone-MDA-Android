package com.example.capstonedesign.Retrofit.DTO;


import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class voteStatus {
    @SerializedName("pVotedUser")
    private String pVotedUser;

    @SerializedName("placePname")
    private String placePname;

    @SerializedName("pId")
    private int pId;
}
